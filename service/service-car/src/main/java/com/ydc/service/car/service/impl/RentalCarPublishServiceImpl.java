package com.ydc.service.car.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.view.vo.cgj.rental.RentalCarListVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarPublisMiniVO;
import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.dao.cgj.rental.RentalCarPublishDao;
import com.ydc.dao.cgj.rental.RentalCarPublishNumDao;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalCarPublish;
import com.ydc.service.car.service.RentalCarPublishService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RentalCarPublishServiceImpl implements RentalCarPublishService {

    private static final String SUCCESS_RESULT = "SUCCESS";

    @Resource
    private RentalCarDao rentalCarDao;
    @Resource
    private RentalCarPublishDao rentalCarPublishDao;
    @Resource
    private RentalCarPublishNumDao rentalCarPublishNumDao;

    private Logger logger = LogManager.getLogger(RentalCarPublishServiceImpl.class);

    @Override
    public List<RentalCarListVO> getCarListByStatus(Integer companyId, Integer status) {
        List<RentalCarListVO> showResult = new ArrayList<>();
        List<RentalCarListVO> result = rentalCarDao.getCarListByStatus(companyId, status);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        for (RentalCarListVO publishVO : result) {
            if (!showResult.contains(publishVO)) {
                List<RentalCarListVO> sameModelList = result.stream().filter(u -> u.equals(publishVO)).collect(Collectors.toList());
                RentalCarListVO toAdded = new RentalCarListVO(publishVO.getModel(), publishVO.getCarStructure(), publishVO.getCarSeat());
                List<RentalCarPublisMiniVO> sameModelCarList = new ArrayList<>();
                sameModelList.forEach(rentalCarListVO -> sameModelCarList.addAll(rentalCarListVO.getCarList()));
                toAdded.setCarList(sameModelCarList);
                showResult.add(toAdded);
            }
        }
        return showResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String publish(String data, Integer companyId) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer workdayRent = dataJSON.getInteger("workdayRent");
        Integer weekendRent = dataJSON.getInteger("weekendRent");
        Integer holidayRent = dataJSON.getInteger("holidayRent");
        Integer dayServiceCharge = dataJSON.getInteger("dayServiceCharge");
        JSONArray carArray = dataJSON.getJSONArray("carList");
        //新增车辆发布中间表数据
        Map<String, Object> params = new HashMap<>();
        Map<Integer, Integer> storePublishIdMap = this.publishCar(companyId, workdayRent, weekendRent, holidayRent, dayServiceCharge, carArray);
        Map<String, Integer> seriesIdNumMap = new HashMap<>();
        for (Object carObject : carArray) {
            JSONObject carJSON = (JSONObject) carObject;
            int carId = carJSON.getInteger("carId");
            int seriesId = carJSON.getInteger("seriesId");
            RentalCar rentalCar = rentalCarDao.selectByPrimaryKey(carId);
            //检测车辆状态是否是己发布
            this.checkRentalCar(companyId, carId, rentalCar);
            int storeId = carJSON.getInteger("storeId");
            params.put("publishId", storePublishIdMap.get(storeId));
            params.put("carId", carId);
            rentalCarDao.addPublishMiddle(params);
            //更新车辆状态
            this.updateRentalCarStatus(companyId, rentalCar, CommCarEnum.RentalCarStatusEnum.PUBLISH_SUCCESS);
            String seriesKey = seriesId + "#" + rentalCar.getCarLevel() + "#" + storePublishIdMap.get(storeId);
            if (!seriesIdNumMap.containsKey(seriesKey)) {
                seriesIdNumMap.put(seriesKey, 1);
            } else {
                seriesIdNumMap.put(seriesKey, seriesIdNumMap.get(seriesKey) + 1);
            }
        }
        //新增车辆数量发布中间表数据
        this.addNumMiddle(seriesIdNumMap);
        return SUCCESS_RESULT;
    }

    private void addNumMiddle(Map<String, Integer> seriesIdNumMap) {
        for (Map.Entry<String, Integer> seriesIdEntry : seriesIdNumMap.entrySet()) {
            int seriesId = Integer.valueOf(String.valueOf(seriesIdEntry.getKey().split("#")[0]));
            String carLevel = String.valueOf(seriesIdEntry.getKey().split("#")[1]);
            int publishId = Integer.valueOf(String.valueOf(seriesIdEntry.getKey().split("#")[2]));
            int carNum = seriesIdEntry.getValue();
            rentalCarPublishNumDao.addPublishNumMiddle(publishId, carNum, seriesId, carLevel);
        }
    }

    /**
     * 返回Map<门店id, 发布表id>
     */
    private Map<Integer, Integer> publishCar(Integer companyId, Integer workdayRent, Integer weekendRent, Integer holidayRent,
                                             Integer dayServiceCharge, JSONArray carArray) {
        Map<Integer, Integer> storeCarNumMap = new HashMap<>();
        Map<Integer, Integer> storePublishIdMap = new HashMap<>();
        for (Object carObject : carArray) {
            JSONObject carJSON = (JSONObject) carObject;
            int storeId = carJSON.getInteger("storeId");
            storeCarNumMap.put(storeId, storeCarNumMap.containsKey(storeId) ? (storeCarNumMap.get(storeId) + 1) : 1);
        }
        for (Map.Entry<Integer, Integer> entry : storeCarNumMap.entrySet()) {
            //新增发布数据
            Integer publishId = this.addPublishData(companyId, entry.getKey(), workdayRent, weekendRent, holidayRent, dayServiceCharge, entry.getValue());
            storePublishIdMap.put(entry.getKey(), publishId);
        }
        return storePublishIdMap;
    }

    private void checkRentalCar(Integer companyId, int carId, RentalCar rentalCar) {
        Optional.ofNullable(rentalCar).orElseThrow(() -> new ServiceRuntimeException(String.format("车辆id：%s不存在", carId)));
        if (!rentalCar.getCompanyId().equals(companyId)) {
            throw new ServiceRuntimeException(String.format("车辆id：%s不属于当前登陆用户", carId));
        }
        if (rentalCar.getStatus() != CommCarEnum.RentalCarStatusEnum.TO_PUBLISH.getCode()) {
            throw new ServiceRuntimeException(String.format("该车辆[%s]不是待发布状态", rentalCar.getCarPlate()));
        }
    }

    /**
     * 插入t_rental_car_publish数据
     */
    private Integer addPublishData(Integer companyId, Integer storeId, Integer workdayRent, Integer weekendRent, Integer holidayRent, Integer dayServiceCharge, int carNum) {
        RentalCarPublish rentalCarPublish = new RentalCarPublish();
        rentalCarPublish.setCompanyId(companyId);
        rentalCarPublish.setStoreId(storeId);
        rentalCarPublish.setWorkdayRent(workdayRent);
        rentalCarPublish.setWeekendRent(weekendRent);
        rentalCarPublish.setHolidayRent(holidayRent);
        rentalCarPublish.setDayServiceCharge(dayServiceCharge);
        rentalCarPublish.setCompanyId(companyId);
        rentalCarPublish.setPublishNum(carNum);
        rentalCarPublish.setCreateBy(companyId);
        rentalCarPublish.setCreateTime(new Timestamp(System.currentTimeMillis()));
        rentalCarPublish.setUpdateBy(companyId);
        rentalCarPublish.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        rentalCarPublishDao.insertSelective(rentalCarPublish);
        return rentalCarPublish.getId();
    }

    /**
     * 更新车辆状态
     */
    private void updateRentalCarStatus(int companyId, RentalCar rentalCar, CommCarEnum.RentalCarStatusEnum publishSuccess) {
        rentalCar.setStatus(publishSuccess.getCode());
        rentalCar.setUpdateBy(companyId);
        rentalCar.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        rentalCarDao.updateByPrimaryKeySelective(rentalCar);
    }

}
