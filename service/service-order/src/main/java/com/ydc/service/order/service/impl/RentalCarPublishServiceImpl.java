package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.vo.cgj.rental.PublishInfoVO;
import com.ydc.commom.view.vo.cgj.rental.carPublishVO.CarPublishCarDetailsVO;
import com.ydc.dao.cgj.car.CommCarSeriesDAO;
import com.ydc.dao.cgj.rental.RentalCarPublishDao;
import com.ydc.dao.cgj.rental.RentalCarPublishNumDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.car.CommCarSeries;
import com.ydc.model.cgj.rental.RentalCarLevel;
import com.ydc.model.cgj.rental.RentalCarPublish;
import com.ydc.model.cgj.rental.RentalCarPublishNum;
import com.ydc.service.order.service.DictionaryDetailService;
import com.ydc.service.order.service.RentalCarPublishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author
 * @create 2019-01-09 9:51
 **/
@Service
public class RentalCarPublishServiceImpl implements RentalCarPublishService {

    private final Logger logger = LogManager.getLogger(RentalCarPublishServiceImpl.class);

    @Autowired
    RentalCarPublishDao rentalCarPublishDao;
    @Autowired
    DictionaryDetailService dictionaryDetailService;
    @Autowired
    CommCarSeriesDAO commCarSeriesDAO;
    @Autowired
    RentalCarPublishNumDao rentalCarPublishNumDao;

    @Override
    public RentalCarPublish selectByPrimaryKey(Integer id) {
        return rentalCarPublishDao.selectByPrimaryKey(id);
    }

    @Override
    public PublishInfoVO getPublishInfo(RentalCarPublishDTO dto) {
        PublishInfoVO publishInfoVO = rentalCarPublishDao.getCarPublishBasicDetails(dto);
        logger.info("publishInfoVO: {}", JsonUtil.gsonStr(publishInfoVO));
        if(publishInfoVO == null) return new PublishInfoVO();
        CarPublishCarDetailsVO carPublishCarDetailsVO = rentalCarPublishDao.getCarPublishCarDetails(dto);
        if(carPublishCarDetailsVO == null) return publishInfoVO;
        publishInfoVO.setCarLevel(carPublishCarDetailsVO.getCarLevel());
        publishInfoVO.setCarStructure(carPublishCarDetailsVO.getCarStructure());
        publishInfoVO.setCarSeat(carPublishCarDetailsVO.getCarSeat());
        publishInfoVO.setStoreId(carPublishCarDetailsVO.getStoreId());
        publishInfoVO.setCarSeriesId(carPublishCarDetailsVO.getCarSeriesId());
        CommCarSeries commCarSeries = commCarSeriesDAO.selectByPrimaryKey(dto.getSeriesId());
        if(commCarSeries == null) return publishInfoVO;
        publishInfoVO.setCarBrand(commCarSeries.getBrand());
        publishInfoVO.setCarSeries(commCarSeries.getSeries());
        publishInfoVO.setCarModel(commCarSeries.getModel());
        publishInfoVO.setCarModelImgUrl(commCarSeries.getMainImgUrl());
        publishInfoVO.setCarModelImgName(commCarSeries.getMainImgName());
        try {
            publishInfoVO.setViewCarModelImgUrl(FileUtil.getBrowseFile(publishInfoVO.getCarModelImgUrl(), publishInfoVO.getCarModelImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","发布信息，车辆图片浏览异常",e);
        }
        RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
        rentalCarPublishNum.setPublishId(dto.getPublishId());
        rentalCarPublishNum.setCarSeriesId(dto.getSeriesId());
        rentalCarPublishNum.setCarLevel(dto.getCarLevel());
        rentalCarPublishNum.setCarBrand(dto.getBrand());
        rentalCarPublishNum.setCarSeries(dto.getSeries());
        rentalCarPublishNum.setCarModel(dto.getModel());
        List<RentalCarPublishNum> rentalCarPublishNums = rentalCarPublishNumDao.getRentalCarPublishNum(rentalCarPublishNum);
        if(rentalCarPublishNums == null || rentalCarPublishNums.isEmpty())return publishInfoVO;
        rentalCarPublishNum = rentalCarPublishNums.get(0);
        publishInfoVO.setPublishNum(rentalCarPublishNum.getCarNum());

        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.AUTHORIZATION_AMOUNT)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.AUTHORIZATION_AMOUNT)));
        List<DictionaryDetail> detailList = optional.get();
        //获取系统配置的保证金预授额度和车辆押金
        detailList.forEach(detail -> {
//            if(RentalDictionaryConstant.RENTAL_ENTERPRISE_CAR_AUTHORIZATION_AMOUNT.equals(detail.getDictKey())){
//                publishInfoVO.setMargin(new BigDecimal(detail.getDictValue()));
//            }
            if(RentalDictionaryConstant.RENTAL_ENTERPRISE_DEPOSIT.equals(detail.getDictKey())){
                publishInfoVO.setCashPledge(new BigDecimal(detail.getDictValue()));
            }
        });
        RentalCarLevel rentalCarLevel = rentalCarPublishDao.getPreAuthorizationByCarLevel(publishInfoVO.getCarLevel());
        logger.info("subject:{},publishInfoVO:{},rentalCarLevel:{}","获取发布信息",JsonUtil.gsonStr(publishInfoVO),JsonUtil.gsonStr(rentalCarLevel));
        if(rentalCarLevel != null){
            publishInfoVO.setMargin(rentalCarLevel.getPreAuthorization());
        }
        logger.info("publishInfoVO: {}", JsonUtil.gsonStr(publishInfoVO));
        return publishInfoVO;
    }
}
