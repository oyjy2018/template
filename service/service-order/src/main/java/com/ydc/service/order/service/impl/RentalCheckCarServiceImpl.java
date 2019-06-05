package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalCarByCarPlateVO;
import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.dao.cgj.rental.RentalCarPublishNumDao;
import com.ydc.dao.cgj.rental.RentalCheckCarDao;
import com.ydc.dao.cgj.rental.RentalEnterpriseOrderDao;
import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.rental.RentalCarPublishNum;
import com.ydc.model.cgj.rental.RentalCheckCar;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;
import com.ydc.service.order.service.RentalCheckCarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2019-01-04 10:39
 **/
@Service
public class RentalCheckCarServiceImpl implements RentalCheckCarService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    RentalCheckCarDao rentalCheckCarDao;
    @Autowired
    RentalEnterpriseOrderDao rentalEnterpriseOrderDao;
    @Autowired
    RentalCarDao rentalCarDao;
    @Autowired
    RentalCarPublishNumDao rentalCarPublishNumDao;

    @Override
    public List<RentalCheckCar> getRentalCheckCar(RentalCheckCarDTO dto) {
        List<RentalCheckCar> rentalCheckCars = rentalCheckCarDao.getRentalCheckCar(dto);
        rentalCheckCars.forEach(item -> {
            try {
                item.setResourceSideComeCarImgBrowse(FileUtil.getBrowseFile(item.getResourceSideComeCarImgUrl(),item.getResourceSideComeCarImgName()));
                item.setDemandSideComeCarImgBrowse(FileUtil.getBrowseFile(item.getDemandSideComeCarImgUrl(),item.getDemandSideComeCarImgName()));
                item.setResourceSideRepayCarImgBrowse(FileUtil.getBrowseFile(item.getResourceSideRepayCarImgUrl(),item.getResourceSideRepayCarImgName()));
                item.setDemandSideRepayCarImgBrowse(FileUtil.getBrowseFile(item.getDemandSideRepayCarImgUrl(),item.getDemandSideRepayCarImgName()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","出车文件处理",e);
            }
        });
        return rentalCheckCars;
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void insertRentalCheckComeCar(List<RentalCheckCarDTO> list) {
        rentalCheckCarDao.batchInsert(list);
        //更新订单状态
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS50.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS50.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS50.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS50.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(list.get(0).getOrderId());
        rentalEnterpriseOrder.setUpdateBy(list.get(0).getCreateBy());
        rentalEnterpriseOrder.setComeCarTime(list.get(0).getComeCarTime());
        rentalEnterpriseOrder.setComeCarPersonId(list.get(0).getComeCarPersonId());
        rentalEnterpriseOrder.setComeCarPersonName(list.get(0).getComeCarPerson());
        int resultCount;
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new ServiceRuntimeException("更新租车订单失败");
        //更新车商端剩余台数
        List<String> carList = list.stream().map(item -> item.getCarNumber()).collect(Collectors.toList());
        rentalCarDao.batchUpdateCar(carList, CommCarEnum.RentalCarStatusEnum.RENT.getCode(),list.get(0).getCreateBy());
        List<RentalCarByCarPlateVO> rentalCarByCarPlateVOS = rentalCarDao.getRentalCarByCarPlateList(carList);
        Map<String, List<RentalCarByCarPlateVO>> rentalCarByCarPlateMap = rentalCarByCarPlateVOS.stream().collect(Collectors.groupingBy(RentalCarByCarPlateVO::getCarPlate));
        logger.info("subject:{},rentalCarByCarPlateMap:{}","根据车牌号查询明细",JsonUtil.gsonStr(rentalCarByCarPlateMap));
        RentalEnterpriseOrder rentalOrder = rentalEnterpriseOrderDao.selectByPrimaryKey(list.get(0).getOrderId());
        //更新发布明细对应车型信息
        String carLevel = rentalOrder.getCarLevel();
        String carBrand = rentalOrder.getCarBrand();
        String carSeries = rentalOrder.getCarSeries();
        String carModel = rentalOrder.getCarModel();
        List<Integer> numList = Lists.newArrayList();
        carList.forEach(item ->{
            List<RentalCarByCarPlateVO> carByCarPlateVOS = rentalCarByCarPlateMap.get(item);
            RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
            if(carByCarPlateVOS != null && !carByCarPlateVOS.isEmpty()){
                RentalCarByCarPlateVO rentalCarByCarPlateVO = carByCarPlateVOS.get(0);
                logger.info("subject:{},rentalCarByCarPlateVO:{}","遍历修改数量",JsonUtil.gsonStr(rentalCarByCarPlateVO));
                rentalCarPublishNum.setCarLevel(rentalCarByCarPlateVO.getCarLevel());
                rentalCarPublishNum.setCarBrand(rentalCarByCarPlateVO.getBrand());
                rentalCarPublishNum.setCarSeries(rentalCarByCarPlateVO.getSeries());
                rentalCarPublishNum.setCarModel(rentalCarByCarPlateVO.getModel());
                rentalCarPublishNum.setCarSeriesId(rentalCarByCarPlateVO.getCarSeriesId());
                rentalCarPublishNumDao.updateSubtractCarNum(rentalCarPublishNum);
                if(!(Objects.equals(carLevel,rentalCarByCarPlateVO.getCarLevel()) && Objects.equals(carBrand,rentalCarByCarPlateVO.getBrand())
                        && Objects.equals(carSeries,rentalCarByCarPlateVO.getSeries()) && Objects.equals(carModel,rentalCarByCarPlateVO.getModel()))){
                    numList.add(1);
                }
            }
        });
        if(numList.isEmpty())return;
        //将发布明细表对应车型数量相加
        RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
        rentalCarPublishNum.setCarLevel(carLevel);
        rentalCarPublishNum.setCarBrand(carBrand);
        rentalCarPublishNum.setCarSeries(carSeries);
        rentalCarPublishNum.setCarModel(carModel);
        rentalCarPublishNum.setPublishId(rentalOrder.getResourceInfoId());
        rentalCarPublishNum.setCarNum(numList.size());
        logger.info("subject:{},numList:{},rentalCarPublishNum:{}","将发布明细表对应车型数量相加",numList.size(),JsonUtil.gsonStr(rentalCarPublishNum));
        rentalCarPublishNumDao.updateCarNum(rentalCarPublishNum);
    }



    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void insertRentalCheckRepayCar(List<RentalCheckCarDTO> list){
        rentalCheckCarDao.batchUpdate(list);
        //更新订单状态
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS70.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS70.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS70.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS70.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(list.get(0).getOrderId());
        rentalEnterpriseOrder.setUpdateBy(list.get(0).getCreateBy());
        rentalEnterpriseOrder.setRepayCarTime(list.get(0).getRepayCarTime());
        rentalEnterpriseOrder.setRepayCarPersonId(list.get(0).getRepayCarPersonId());
        rentalEnterpriseOrder.setRepayCarPersonName(list.get(0).getRepayCarPerson());
        int resultCount;
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new ServiceRuntimeException("更新租车订单失败");
        //更新车商端剩余台数
        List<String> carList = list.stream().map(item -> item.getCarNumber()).collect(Collectors.toList());
        rentalCarDao.batchUpdateCar(carList, CommCarEnum.RentalCarStatusEnum.TO_PUBLISH.getCode(),list.get(0).getCreateBy());
        List<RentalCarByCarPlateVO> rentalCarByCarPlateVOS = rentalCarDao.getRentalCarByCarPlateList(carList);
        Map<String, List<RentalCarByCarPlateVO>> rentalCarByCarPlateMap = rentalCarByCarPlateVOS.stream().collect(Collectors.groupingBy(RentalCarByCarPlateVO::getCarPlate));
        logger.info("subject:{},rentalCarByCarPlateMap:{}","根据车牌号查询明细",JsonUtil.gsonStr(rentalCarByCarPlateMap));
        //更新发布明细对应车型信息
        carList.forEach(item ->{
            List<RentalCarByCarPlateVO> carByCarPlateVOS = rentalCarByCarPlateMap.get(item);
            RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
            if(carByCarPlateVOS != null && !carByCarPlateVOS.isEmpty()){
                RentalCarByCarPlateVO rentalCarByCarPlateVO = carByCarPlateVOS.get(0);
                logger.info("subject:{},rentalCarByCarPlateVO:{}","遍历修改数量",JsonUtil.gsonStr(rentalCarByCarPlateVO));
                rentalCarPublishNum.setCarLevel(rentalCarByCarPlateVO.getCarLevel());
                rentalCarPublishNum.setCarBrand(rentalCarByCarPlateVO.getBrand());
                rentalCarPublishNum.setCarSeries(rentalCarByCarPlateVO.getSeries());
                rentalCarPublishNum.setCarModel(rentalCarByCarPlateVO.getModel());
                rentalCarPublishNum.setCarSeriesId(rentalCarByCarPlateVO.getCarSeriesId());
                rentalCarPublishNumDao.updateAddCarNum(rentalCarPublishNum);
            }
        });
    }
}
