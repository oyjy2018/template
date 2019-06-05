package com.ydc.service.car.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.dao.cgj.rental.RentalCompanyCustomerDao;
import com.ydc.dao.cgj.rental.RentalMainDao;
import com.ydc.model.cgj.common.Holiday;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.service.car.service.RentalCarMainService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RentalCarMainServiceImpl implements RentalCarMainService {

    private static final String SUCCESS_RESULT = "SUCCESS";

    @Resource
    private RentalMainDao rentalMainDao;
    @Resource
    private RentalCompanyCustomerDao rentalCompanyCustomerDao;

    private Logger logger = LogManager.getLogger(RentalCarMainServiceImpl.class);


    @Override
    public List<String> getMainContent() {
        //查询首页主图
        List<RentalCarImgVO> urlList = rentalMainDao.getMainBannerUrl();
        if (CollectionUtils.isEmpty(urlList)) {
            return null;
        }
        List<String> realUrlList = new ArrayList<>();
        urlList.forEach(u -> {
            try {
                realUrlList.add(FileUtil.getBrowseFile(u.getFileUrl(), u.getFileName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return realUrlList;
    }

    @Override
    public List<String> getBrandList() {
        return rentalMainDao.getBrandList();
    }

    @Override
    public List<RentalCarMainQueryVO> queryPublishCar(RentalCarMainQueryDTO rentalCarMainQueryDTO) {
        //更新历史访问城市
        if (null != rentalCarMainQueryDTO.getCompanyId() && rentalCarMainQueryDTO.getCompanyId() != 0) {
            RentalCompanyCustomer rentalCompanyCustomer = rentalCompanyCustomerDao.selectByPrimaryKey(rentalCarMainQueryDTO.getCompanyId());
            rentalCompanyCustomer.setHistoryCities(this.getNewHistoryCity(rentalCompanyCustomer.getHistoryCities(), rentalCarMainQueryDTO.getCity()));
            rentalCompanyCustomerDao.updateByPrimaryKeySelective(rentalCompanyCustomer);
        }
        List<RentalCarMainQueryVO> listResult = rentalMainDao.queryPublishCar(rentalCarMainQueryDTO);
        listResult.forEach(u -> {
            try {
                u.setMainImg(FileUtil.getBrowseFile(u.getMainImgUrl(), u.getMainImgName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return listResult;
    }

    private String getNewHistoryCity(String historyCities, String city) {
        if (StringUtils.isBlank(historyCities)) {
            return city + "##";
        }
        if (historyCities.contains(city)) {
            historyCities = historyCities.replace(city + "##", "");
        }
        String[] oldHistoryCities = historyCities.split("##");
        if (oldHistoryCities.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(city).append("##");
            if (!sb.toString().contains(oldHistoryCities[0])) {
                sb.append(oldHistoryCities[0]).append("##");
            }
            if (oldHistoryCities.length > 1 && !sb.toString().contains(oldHistoryCities[1])) {
                sb.append((oldHistoryCities[1])).append("##");
            }
            return sb.toString();
        } else {
            return city;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public RentalCarMainQueryDetailVO getCarDetail(RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO) throws Exception {
        RentalCarMainQueryDetailVO rentalCarMainQueryDetailVO = rentalMainDao.queryCarDetail(rentalCarMainDetailQueryDTO);
        if (rentalCarMainQueryDetailVO == null) {
            throw new ServiceRuntimeException("找不到对应的发布数据");
        }
        Date startDate = DateUtil.parseDate(rentalCarMainDetailQueryDTO.getStartTime(), DateUtil.DATATIMEF_STR);
        Date endDate = DateUtil.parseDate(rentalCarMainDetailQueryDTO.getEndTime(), DateUtil.DATATIMEF_STR);
        int days = DateUtil.differentDaysByMillisecond(startDate, endDate);
        if (days > 28) {
            return rentalCarMainQueryDetailVO;
        } else {
            Map<String, Holiday> holidayMap = (Map<String, Holiday>) RedisUtil.redisHashGet(RedisConstant.RENTAL_HOLIDAY_KEY);
            if (holidayMap.size() == 0) {
                throw new ServiceRuntimeException("找不到节假日数据");
            }
            List<RentalCarDayPriceVO> priceList = new ArrayList<>();
            for (int i = 0; i < 28; i++) {
                String currentDayStr = DateUtil.addDate(startDate, 3, i);
                Date currentDay = DateUtil.parseDate(currentDayStr);
                RentalCarDayPriceVO rentalCarDayPriceVO = new RentalCarDayPriceVO();
                Holiday holiday = holidayMap.get(currentDayStr);
                rentalCarDayPriceVO.setDayOfMonth(DateUtil.getMonth(currentDay));
                rentalCarDayPriceVO.setDayOfWeek(DateUtil.getWeek(currentDay));
                rentalCarDayPriceVO.setDayStr(currentDayStr);
                if (holiday != null && "1".equals(holiday.getStatus()) && holiday.getHolidayType() == 1) {
                    //节假日
                    rentalCarDayPriceVO.setCharge(rentalCarMainQueryDetailVO.getHolidayRent());
                } else if(holiday != null && "1".equals(holiday.getStatus()) && holiday.getHolidayType() == 2) {
                    //调休
                    rentalCarDayPriceVO.setCharge(rentalCarMainQueryDetailVO.getWorkdayRent());
                } else if (DateUtil.hasWeekend(currentDay)) {
                    rentalCarDayPriceVO.setCharge(rentalCarMainQueryDetailVO.getWeekendRent());
                } else {
                    rentalCarDayPriceVO.setCharge(rentalCarMainQueryDetailVO.getWorkdayRent());
                }
                priceList.add(rentalCarDayPriceVO);
            }
            rentalCarMainQueryDetailVO.setPriceList(priceList);
        }
        rentalCarMainQueryDetailVO.setMainImg(FileUtil.getBrowseFile(rentalCarMainQueryDetailVO.getMainImgUrl(), rentalCarMainQueryDetailVO.getMainImgName()));
        return rentalCarMainQueryDetailVO;
    }

    @Override
    public List<String> getAllCities() {
        return rentalMainDao.getAllCities();
    }

    @Override
    public RentalCarMainCityVo getHotCitiesList(Integer companyId) {
        RentalCarMainCityVo rentalCarMainCityVo = new RentalCarMainCityVo();
        String[] hotCities = new String[]{"北京", "上海", "广州", "深圳", "杭州", "长沙", "武汉", "厦门", "西安", "昆明", "成都", "重庆"};
        List<String> cityList = rentalMainDao.getAllCities();
        rentalCarMainCityVo.setCities(cityList);
        List<String> hotCitiesResult = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cityList)) {
            for (String city : hotCities) {
                if (cityList.contains(city + "市")) {
                    hotCitiesResult.add(city);
                }
            }
        }
        rentalCarMainCityVo.setHotcities(hotCitiesResult);
        return rentalCarMainCityVo;
    }
}
