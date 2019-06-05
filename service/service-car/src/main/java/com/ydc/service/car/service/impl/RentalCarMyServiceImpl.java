package com.ydc.service.car.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMiniVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMyQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarSeriesVo;
import com.ydc.dao.cgj.rental.RentalCarCheckDao;
import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalCarCheck;
import com.ydc.service.car.service.RentalCarMyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentalCarMyServiceImpl implements RentalCarMyService {

    private static final String SUCCESS_RESULT = "SUCCESS";

    @Resource
    private RentalCarDao rentalCarDao;
    @Resource
    private RentalCarCheckDao rentalCarCheckDao;

    private Logger logger = LogManager.getLogger(RentalCarMyServiceImpl.class);

    @Override
    public List<String> getAddBrandList() {
        return rentalCarDao.getAddBrandList();
    }

    @Override
    public List<String> getAddSeriesByBrand(String brand) {
        return rentalCarDao.getAddSeriesByBrand(brand);
    }

    @Override
    public List<RentalCarSeriesVo> getAddModelBySeries(String series) {
        return rentalCarDao.getAddModelBySeries(series);
    }

    @Override
    public List<RentalCarMyQueryVO> getMy(RentalCarListDTO rentalCarListDTO) {
        List<RentalCarMyQueryVO> resultList = rentalCarDao.getMy(rentalCarListDTO);
        resultList.forEach(u -> {
            try {
                u.setMainImg(FileUtil.getBrowseFile(u.getMainImgUrl(), u.getMainImgName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }

    @Override
    public List<RentalCarMiniVO> getMyList(RentalCarMyListDTO rentalCarMyListDTO) {
        return rentalCarDao.getMyList(rentalCarMyListDTO);
    }

    @Override
    public String deleteMyCar(Integer carId, Integer companyId) {
        RentalCar rentalCar = rentalCarDao.selectByPrimaryKey(carId);
        if (rentalCar.getStatus() == CommCarEnum.RentalCarStatusEnum.RENT.getCode()) {
            throw new ServiceRuntimeException("该车辆己出租，无法删除");
        }
        if (rentalCar.getStatus() == CommCarEnum.RentalCarStatusEnum.PUBLISH_SUCCESS.getCode()) {
            throw new ServiceRuntimeException("该车辆己发布，请下架后在删除");
        }
        rentalCar.setId(carId);
        rentalCar.setStatus(CommCarEnum.RentalCarStatusEnum.DELETE.getCode());
        rentalCar.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        rentalCar.setUpdateBy(companyId);
        rentalCarDao.updateByPrimaryKeySelective(rentalCar);
        return SUCCESS_RESULT;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addMyCar(String data, Integer companyId) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer carSeriesId = dataJSON.getInteger("carSeriesId");
        Integer storeId = dataJSON.getInteger("storeId");
        String carSeat = dataJSON.getString("carSeat");
        String carStructure = dataJSON.getString("carStructure");
        JSONArray carList = dataJSON.getJSONArray("carList");
        //新增审核列表数据
        Integer checkId = this.insertCheckData(carSeriesId, storeId, carSeat, carStructure, companyId, carList);
        for (int i = 0; i < carList.size(); i++) {
            JSONObject carJSON = carList.getJSONObject(i);
            String carPlate = carJSON.getString("carPlate");
            String vin = carJSON.getString("vin");
            String upPlateDate = carJSON.getString("upPlateDate");
            String carLevel = carJSON.getString("carLevel");
            String drivingLicenseImgName = carJSON.getString("drivingLicenseImgName");
            String drivingLicenseImgUrl = carJSON.getString("drivingLicenseImgUrl");
            //检测车辆号是否重复
            RentalCar rentalCar = new RentalCar();
            rentalCar.setCarPlate(carPlate);
            int count = rentalCarDao.getCount(rentalCar);
            if (count > 0) {
                throw new ServiceRuntimeException(String.format("车牌号[%s]己存在", carPlate));
            }
            //新增我的车辆
            Integer carId = this.addRentalCar(carSeriesId, storeId, carSeat, companyId, carPlate, vin, upPlateDate,
                    carLevel, drivingLicenseImgName, drivingLicenseImgUrl, carStructure);
            //新增车辆审核中间表
            this.addCheckMiddle(carId, checkId);
        }
        return SUCCESS_RESULT;
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

    private void addCheckMiddle(Integer carId, Integer checkId) {
        Map<String, Object> params = new HashMap<>();
        params.put("carId", carId);
        params.put("checkId", checkId);
        rentalCarCheckDao.insertMiddleTable(params);
    }

    private Integer insertCheckData(Integer carSeriesId, Integer storeId, String carSeat, String carStructure, Integer companyId, JSONArray carList) {
        RentalCarCheck rentalCarCheck = new RentalCarCheck();
        rentalCarCheck.setCompanyId(companyId);
        rentalCarCheck.setCheckStatus(CommCarEnum.RentalCarCheckStatusEnum.TO_CHECK.getCode());
        rentalCarCheck.setCarNum(carList.size());
        rentalCarCheck.setCarSeriesId(carSeriesId);
        rentalCarCheck.setCarSeat(carSeat);
        rentalCarCheck.setCarStructure(carStructure);
        rentalCarCheck.setStoreId(storeId);
        rentalCarCheck.setCreateBy(companyId);
        rentalCarCheck.setCheckStatus(CommCarEnum.RentalCarCheckStatusEnum.TO_CHECK.getCode());
        rentalCarCheck.setCreateTime(new Time(System.currentTimeMillis()));
        rentalCarCheck.setUpdateBy(companyId);
        rentalCarCheck.setUpdateTime(new Time(System.currentTimeMillis()));
        rentalCarCheckDao.insertSelective(rentalCarCheck);
        return rentalCarCheck.getId();
    }

    private Integer addRentalCar(Integer carSeriesId, Integer storeId, String carSeat, Integer companyId, String carPlate,
                                 String vin, String upPlateDate, String carLevel, String drivingLicenseImgName, String drivingLicenseImgUrl, String carStructure) {
        RentalCar rentalCar = new RentalCar();
        rentalCar.setCompanyId(companyId);
        rentalCar.setCarSeriesId(carSeriesId);
        rentalCar.setStoreId(storeId);
        rentalCar.setCarSeat(carSeat);
        rentalCar.setCarPlate(carPlate);
        rentalCar.setVin(vin);
        rentalCar.setUpPlateDate(DateUtil.getDate(upPlateDate + " 00:00:00"));
        rentalCar.setCarLevel(carLevel);
        rentalCar.setDrivingLicenseImgName(drivingLicenseImgName);
        rentalCar.setDrivingLicenseImgUrl(drivingLicenseImgUrl);
        rentalCar.setCarStructure(carStructure);
        rentalCar.setStatus(CommCarEnum.RentalCarStatusEnum.TO_CHECK.getCode());
        rentalCar.setCreateBy(companyId);
        rentalCar.setCreateTime(new Time(System.currentTimeMillis()));
        rentalCar.setUpdateBy(companyId);
        rentalCar.setUpdateTime(new Time(System.currentTimeMillis()));
        rentalCarDao.insertSelective(rentalCar);
        return rentalCar.getId();
    }

}
