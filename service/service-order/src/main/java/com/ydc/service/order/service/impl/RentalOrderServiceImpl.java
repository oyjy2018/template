package com.ydc.service.order.service.impl;


import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.constant.rental.RentalPayWatercourseConstant;
import com.ydc.commom.result.Result;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.constant.rental.RentalOrderFileConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.dao.cgj.car.CommCarDao;
import com.ydc.dao.cgj.car.CommCarOperLogDao;
import com.ydc.dao.cgj.rental.RentalOrderDao;
import com.ydc.dao.cgj.rental.RentalPayWatercourseDao;
import com.ydc.dao.cgj.rental.RentalStoreDao;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.car.CommCarOperLog;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.model.cgj.rental.RentalOrderCarImg;
import com.ydc.service.order.service.RentalDepositService;
import com.ydc.service.order.service.RentalOrderCarImgService;
import com.ydc.service.order.service.RentalOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author
 * @create 2018-11-21 20:48
 **/
@Service
public class RentalOrderServiceImpl implements RentalOrderService {

    private static final Logger logger = LogManager.getLogger(RentalOrderServiceImpl.class);
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    CommCarDao commCarDao;
    @Autowired
    RentalOrderDao rentalOrderDao;
    @Autowired
    RentalStoreDao rentalStoreDao;
    @Autowired
    RentalOrderCarImgService rentalOrderCarImgService;
    @Autowired
    RentalDepositService rentalDepositService;
    @Autowired
    CommCarOperLogDao commCarOperLogDao;
    @Autowired
    RentalPayWatercourseDao rentalPayWatercourseDao;

    @Override
    public List<Map<String, Object>> getCarLevelGroup() {
        return commCarDao.getCarLevelGroup();
    }

    @Override
    public List<Map<String, Object>> getBrandByCarLevel(CommCarDTO commCarDTO) {
        return commCarDao.getBrandByCarLevel(commCarDTO);
    }

    @Override
    public List<Map<String, Object>> getSeriesByBrand(CommCarDTO commCarDTO) {
        return commCarDao.getSeriesByBrand(commCarDTO);
    }

    @Override
    public List<Map<String, Object>> getModelBySeries(CommCarDTO commCarDTO) {
        return commCarDao.getModelBySeries(commCarDTO);
    }

    @Override
    public List<CarPlateVO> getCarPlateList(CommCarDTO commCarDTO) {
        return commCarDao.getCarPlateList(commCarDTO);
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public Integer insertOrder(AddRentalOrderPCDTO addRentalOrderPCDTO) {
        Integer orderResult = rentalOrderDao.insertOrder(addRentalOrderPCDTO);
        addRentalOrderPCDTO.getRentalDeposit().setOrderId(addRentalOrderPCDTO.getId());
        Integer rentalDepositResult = rentalDepositService.insert(addRentalOrderPCDTO.getRentalDeposit());
        return (orderResult > 0 && rentalDepositResult > 0) ? 1 : 0;
    }

    @Override
    public Integer updateByOrderId(RentalOrder record) {
        return rentalOrderDao.updateByOrderId(record);
    }

    @Override
    public List<Map<String, Object>> getAllRentalStore() {
        return rentalStoreDao.getAllRentalStore();
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public Integer updateComeCarOrder(ComeCarOrderDTO comeCarOrderDTO) {
        rentalOrderCarImgService.updateRentalCarImg(comeCarOrderDTO.getOrderId(), RentalOrderFileConstant.DESCRIBE_TYPE_COME_CAR);
        rentalOrderCarImgService.insertComeCarImgBatch(comeCarOrderDTO.getComeCarOrderImgDTOS());
        CommCar commCar = commCarDao.selectByPrimaryKey(comeCarOrderDTO.getCarId());
        comeCarOrderDTO.setCarLevel(commCar.getCarLevel());
        comeCarOrderDTO.setBrand(commCar.getBrand());
        comeCarOrderDTO.setSeries(commCar.getSeries());
        comeCarOrderDTO.setModel(commCar.getModel());

        saveCommCarOperLogDao(comeCarOrderDTO.getCarId(),comeCarOrderDTO.getUpdateBy(), "订单出车");
        return rentalOrderDao.updateComeCarOrder(comeCarOrderDTO);
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public Integer updateRepayCarOrder(RepayCarOrderDTO repayCarOrderDTO) {
        rentalOrderCarImgService.updateRentalCarImg(repayCarOrderDTO.getOrderId(),RentalOrderFileConstant.DESCRIBE_TYPE_REPAY_CAR);
        if(repayCarOrderDTO.getRepayCarOrderImgDTOS() != null && repayCarOrderDTO.getRepayCarOrderImgDTOS().size() > 0){
            rentalOrderCarImgService.insertRepayCarImgBatch(repayCarOrderDTO.getRepayCarOrderImgDTOS());
        }

        RentalOrder rentalOrder = getRentalOrderByOrderId(repayCarOrderDTO.getOrderId());
        saveCommCarOperLogDao(rentalOrder.getCarId(),repayCarOrderDTO.getUpdateBy(), "订单还车");
        repayCarOrderDTO.setCarId(rentalOrder.getCarId());
        return rentalOrderDao.updateRepayCarOrder(repayCarOrderDTO);
    }

    @Override
    public List<RentalOrderListVO> getRentalOrderList(Map<String, Object> param) {
        return rentalOrderDao.getRentalOrderList(param);
    }

    @Override
    public Map<String, Object> getRentalOrderListCount(Map<String, Object> param) {
        return rentalOrderDao.getRentalOrderListCount(param);
    }

    @Override
    public List<RentalOrderListCVO> getRentalOrderListCByMemberId(Map<String, Object> param) {
        return rentalOrderDao.getRentalOrderListCByMemberId(param);
    }

    @Override
    public RentalOrder getRentalOrderByOrderId(Integer orderId) {
        return rentalOrderDao.getRentalOrderByOrderId(orderId);
    }

    @Override
    public List<RentalOrderListBVO> getRentalOrderListBByStoreId(Map<String, Object> param) {
        return rentalOrderDao.getRentalOrderListBByStoreId(param);
    }

    @Override
    public RentalOrder getRentalOrderByMemberId(Integer memberId) {
        return rentalOrderDao.getRentalOrderByMemberId(memberId);
    }

    @Override
    public Result updateConsentAuthorization(Integer orderId, Integer status) {
        RentalOrder rentalOrder=rentalOrderDao.getRentalOrderByOrderId(orderId);
        if (null== rentalOrder){
            return  Result.failure("订单不存在");
        }
        if (rentalOrder.getStatus().compareTo(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_1)==0
                &&  rentalOrder.getFlowOneStatus().compareTo(RentalOrderStatusConstant.RENTAL_ORDER_FLOW_STATUS_100)==0){
            //更新
            rentalOrder.setFlowTwoStatus(status);
           rentalOrderDao.updateByPrimaryKey(rentalOrder);
        }
        return Result.success(rentalOrder);
    }

    @Override
    public RentalOrderDetailsVO getRentalOrderDetailsPC(Integer rentalOrderId) {
        RentalOrderDetailsVO rentalOrderDetails = rentalOrderDao.getRentalOrderDetailsPC(rentalOrderId);
        String depositTypes = new StringBuffer()
                                    .append(RentalPayWatercourseConstant.DEPOSIT_TYPE_3)
                                    .append(",")
                                    .append(RentalPayWatercourseConstant.DEPOSIT_TYPE_4).toString();
        List<RentalPayWatercourseVO> settleBlotterList = rentalPayWatercourseDao.getPayWatercourseByDepositTypes(rentalOrderId,depositTypes);
        rentalOrderDetails.setSettleBlotterList(settleBlotterList);
        List<RentalOrderCarImg> rociList = rentalOrderCarImgService.getRentalOrderCarImgByOrderId(rentalOrderId);
        for(RentalOrderCarImg roci:rociList){
            try {
                roci.setViewFileUrl(FileUtil.getBrowseFile(roci.getFileUrl(),roci.getFileName()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","加密图片在线浏览地址异常",e);
            }
        }
        rentalOrderDetails.setRociList(rociList);
        return rentalOrderDetails;
    }

    @Override
    public RentalOrderDetailsAPPCVO getRentalOrderDetailsAPPC(Integer rentalOrderId) {
        RentalOrderDetailsAPPCVO rentalOrderDetails = rentalOrderDao.getRentalOrderDetailsAPPC(rentalOrderId);
        try {
            rentalOrderDetails.setViewMainImgUrl(FileUtil.getBrowseFile(rentalOrderDetails.getMainImgUrl(),
                    rentalOrderDetails.getMainImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","加密图片在线浏览地址异常",e);
        }
        return rentalOrderDetails;
    }

    @Override
    public RentalOrderDetailsAPPBVO getRentalOrderDetailsAPPB(Integer rentalOrderId) {
        RentalOrderDetailsAPPBVO rentalOrderDetails = rentalOrderDao.getRentalOrderDetailsAPPB(rentalOrderId);
        try {
            rentalOrderDetails.setViewMainImgUrl(FileUtil.getBrowseFile(rentalOrderDetails.getMainImgUrl(),
                    rentalOrderDetails.getMainImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","加密图片在线浏览地址异常",e);
        }
        return rentalOrderDetails;
    }

    @Override
    public ComeCarOrderVO getComeCarOrderData(Integer orderId) {
        return rentalOrderDao.getComeCarOrderData(orderId);
    }


    @Override
    public Integer updateRentalOrderStatus(UpdateRentalOrderDTO updateRentalOrderDTO) {
        return rentalOrderDao.updateRentalOrderStatus(updateRentalOrderDTO);
    }

    @Override
    public int updateRentalOrderFlowStatusById(String status, String flowOneStatus, String flowTwoStatus, String flowThreeStatus, Integer rentalOrderId) {
        return rentalOrderDao.updateRentalOrderFlowStatusById(status,flowOneStatus,flowTwoStatus,flowThreeStatus,rentalOrderId);
    }

    @Override
    public void saveCommCarOperLogDao(Integer carId,Integer userId, String content) {
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    CommCarOperLog commCarOperLog = new CommCarOperLog();
                    commCarOperLog.setCarId(carId);
                    commCarOperLog.setOperContent(content);
                    commCarOperLog.setCreateBy(userId);
                    commCarOperLog.setCreateTime(new Date());
                    commCarOperLog.setDeleteStatus((int)DeleteStatusConstant.STATUS_NOT_DELETE);
                    logger.info("subject:{},commCarOperLog:{}","新增车辆操作日志",JsonUtil.gsonStr(commCarOperLog));
                    commCarOperLogDao.insert(commCarOperLog);
                }catch (Exception e){
                    logger.error("subject:{},e:{}","新增车辆操作日志异常",e);
                }
            }
        });


    }

    @Override
    public RentalCarDataVO getCarOilDesc(Integer carId) {
        return rentalOrderDao.getCarOilDesc(carId);
    }

    @Override
    public CommCar getCarInfoByCarId(Integer id) {
        return commCarDao.selectByPrimaryKey(id);
    }

    //验证是否能新增
    @Override
    public Result verifyEnableInsert(AddRentalOrderPCDTO addRentalOrderPCDTO) {
        Map paramMap = new HashMap();
        paramMap.put("memberId",addRentalOrderPCDTO.getMemberId());  //会员id
        paramMap.put("appointmentFetchCarTime",addRentalOrderPCDTO.getAppointmentFetchCarTime()); //取车时间
        paramMap.put("appointmentRepayCarTime",addRentalOrderPCDTO.getAppointmentRepayCarTime()); //还车时间
        List<Map> list = rentalOrderDao.getExistEffectiveOrderList(paramMap); //获取同时段有效的租车订单
        //无数据时允许新增
        if (list == null || list.size() == 0){
            return Result.success("允许新增");
        }
        return Result.failure("该客户存在订单【"+list.get(0).get("id")+"】的用车时间 与 新增订单的用车时间有重叠，无法提交订单。");
    }

}
