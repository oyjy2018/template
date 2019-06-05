package com.ydc.cgj.rentalb.app.service.impl;

import com.google.gson.annotations.JsonAdapter;
import com.ydc.cgj.rentalb.app.common.UserUtil;
import com.ydc.cgj.rentalb.app.feignService.OrderFeignService;
import com.ydc.cgj.rentalb.app.service.RentalOrderService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalOrderFileConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.enums.rental.RentalOrderStatusOneEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.AnnotationDealUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.rental.CancelUseCarOrderUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 租车订单
 *
 * @author
 * @create 2018-11-22 14:29
 **/
@Service
public class RentalOrderServiceImpl implements RentalOrderService {
    private static final Logger logger = LogManager.getLogger(RentalOrderService.class);

    @Autowired
    OrderFeignService orderFeignService;

    @Override
    public String getComeCarData(CommCarDTO commCarDTO) {
        RentalOrder rentalOrder = getRentalOrderByOrderId(commCarDTO.getOrderId());

        if(rentalOrder.getStatus().intValue() != RentalOrderStatusConstant.RENTAL_ORDER_STATUS_1
                && rentalOrder.getFlowOneStatus() != RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus()
                && rentalOrder.getFlowTwoStatus() != RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_100.getFlowTwoStatus()){
            return Result.failure("该订单已经不存于当前流程，请刷新页面").toJSON();
        }
//        Result result = RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.getResult(rentalOrder.getFlowOneStatus());
//        logger.info("subject:{},result:{}","身份认证和风控认证结果",JsonUtil.gsonStr(result));
//        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
//            return result.toJSON();
//        }
//        result = RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.getResult(rentalOrder.getFlowOneStatus());
//        logger.info("subject:{},result:{}","授权认证结果",JsonUtil.gsonStr(result));
//        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
//            return result.toJSON();
//        }
        return orderFeignService.getComeCarData(commCarDTO);
    }

    @Override
    public String updateComeCarOrder(ComeCarOrderDTO comeCarOrderDTO) {
        if(comeCarOrderDTO == null)return Result.failure("出车订单信息不能为空").toJSON();
        Map<String, Object> checkResult = AnnotationDealUtil.validate(comeCarOrderDTO);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }
        User user = UserUtil.getUser();
        RentalOrder rentalOrder = getRentalOrderByOrderId(comeCarOrderDTO.getOrderId());
        if(rentalOrder.getStatus().intValue() != RentalOrderStatusConstant.RENTAL_ORDER_STATUS_1
                && rentalOrder.getFlowOneStatus() != RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus()
                && rentalOrder.getFlowTwoStatus() != RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_100.getFlowTwoStatus()){
            return Result.failure("该订单已经不存于当前流程，请刷新页面").toJSON();
        }
//        Result result = RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.getResult(rentalOrder.getFlowOneStatus());
//        logger.info("subject:{},result:{}","身份认证和风控认证结果",JsonUtil.gsonStr(result));
//        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
//            return result.toJSON();
//        }
//        result = RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.getResult(rentalOrder.getFlowTwoStatus());
//        logger.info("subject:{},result:{}","授权认证结果",JsonUtil.gsonStr(result));
//        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
//            return result.toJSON();
//        }
        comeCarOrderDTO.setComeCarRemark(StringUtil.removeNonBmpUnicode(comeCarOrderDTO.getComeCarRemark()));
        comeCarOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_2);
        comeCarOrderDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.getFlowOneStatus());
        comeCarOrderDTO.setFlowTwoStatus(RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_0.getFlowTwoStatus());
        comeCarOrderDTO.setFlowThreeStatus(RentalOrderStatusOneEnum.RentalOrderFlowThreeStatusEnum.FLOW_THREE_STATUS_0.getFlowThreeStatus());
        comeCarOrderDTO.setUpdateBy(user.getId());
        for(ComeCarOrderImgDTO comeCarOrderImgDTO:comeCarOrderDTO.getComeCarOrderImgDTOS()){
            checkResult = AnnotationDealUtil.validate(comeCarOrderImgDTO);
            logger.info("subject:{},e:{}","校验参数",checkResult);
            if(checkResult.get("result").equals(false)){
                return Result.failure(checkResult.get("message").toString()).toJSON();
            }
        }
        Date date = new Date();
        List<ComeCarOrderImgDTO> comeCarOrderImgDTOS = comeCarOrderDTO.getComeCarOrderImgDTOS()
                .stream().filter(item -> StringUtil.isNotEmpty(item.getFileUrl()) && StringUtil.isNotEmpty(item.getFileName())).collect(Collectors.toList());
        comeCarOrderImgDTOS.forEach(item ->{
            item.setOrderId(comeCarOrderDTO.getOrderId());
            item.setDescribeType(RentalOrderFileConstant.DESCRIBE_TYPE_COME_CAR);
            item.setStatus((int)DeleteStatusConstant.STATUS_NOT_DELETE);
            item.setCreateTime(date);
            item.setCreateBy(user.getId());
        });
        comeCarOrderDTO.setComeCarOrderImgDTOS(comeCarOrderImgDTOS);

        return orderFeignService.updateComeCarOrder(comeCarOrderDTO);
    }

    @Override
    public String getRepayCarData(CommCarDTO commCarDTO) {
        RentalOrder rentalOrder = getRentalOrderByOrderId(commCarDTO.getOrderId());
        if(rentalOrder.getStatus().intValue() != RentalOrderStatusConstant.RENTAL_ORDER_STATUS_2){
            return Result.failure("该订单已经不存于当前流程，请刷新页面").toJSON();
        }
        return orderFeignService.getRepayCarData(commCarDTO);
    }

    @Override
    public String updateRepayCarOrder(RepayCarOrderDTO repayCarOrderDTO) {
        if(repayCarOrderDTO == null)return Result.failure("还车订单信息不能为空").toJSON();
        Map<String, Object> checkResult = AnnotationDealUtil.validate(repayCarOrderDTO);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }

        User user = UserUtil.getUser();
        repayCarOrderDTO.setRepayCarRemark(StringUtil.removeNonBmpUnicode(repayCarOrderDTO.getRepayCarRemark()));
        repayCarOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_3);
        repayCarOrderDTO.setUpdateBy(user.getId());
        repayCarOrderDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.getFlowOneStatus());
        repayCarOrderDTO.setFlowTwoStatus(RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_0.getFlowTwoStatus());
        repayCarOrderDTO.setFlowThreeStatus(RentalOrderStatusOneEnum.RentalOrderFlowThreeStatusEnum.FLOW_THREE_STATUS_0.getFlowThreeStatus());
        if(repayCarOrderDTO.getRepayCarOrderImgDTOS() != null && repayCarOrderDTO.getRepayCarOrderImgDTOS().size() > 0){
            Date date = new Date();
            List<RepayCarOrderImgDTO> repayCarOrderImgDTOS = repayCarOrderDTO.getRepayCarOrderImgDTOS()
                    .stream().filter(item -> StringUtil.isNotEmpty(item.getFileUrl()) && StringUtil.isNotEmpty(item.getFileName()))
                    .collect(Collectors.toList());
            repayCarOrderImgDTOS.forEach(item ->{
                item.setOrderId(repayCarOrderDTO.getOrderId());
                item.setDescribeType(RentalOrderFileConstant.DESCRIBE_TYPE_REPAY_CAR);
                item.setStatus((int)DeleteStatusConstant.STATUS_NOT_DELETE);
                item.setCreateTime(date);
                item.setCreateBy(user.getId());
            });
            repayCarOrderDTO.setRepayCarOrderImgDTOS(repayCarOrderImgDTOS);
        }
        return orderFeignService.updateRepayCarOrder(repayCarOrderDTO);
    }

    @Override
    public String updateComeCarOrderInRepayCar(ComeCarOrderDTO comeCarOrderDTO) {
        if(comeCarOrderDTO == null)return Result.failure("出车订单信息不能为空").toJSON();
        Map<String, Object> checkResult = AnnotationDealUtil.validate(comeCarOrderDTO);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }
        RentalOrder rentalOrder = getRentalOrderByOrderId(comeCarOrderDTO.getOrderId());
        if(rentalOrder.getStatus().intValue() != RentalOrderStatusConstant.RENTAL_ORDER_STATUS_2){
            return Result.failure("该订单已经不存于当前流程，请刷新页面").toJSON();
        }
        User user = UserUtil.getUser();
        comeCarOrderDTO.setComeCarRemark(StringUtil.removeNonBmpUnicode(comeCarOrderDTO.getComeCarRemark()));
        comeCarOrderDTO.setUpdateBy(user.getId());
        comeCarOrderDTO.setStatus(rentalOrder.getStatus());
        comeCarOrderDTO.setFlowOneStatus(rentalOrder.getFlowOneStatus());
        comeCarOrderDTO.setFlowTwoStatus(rentalOrder.getFlowTwoStatus());
        comeCarOrderDTO.setFlowThreeStatus(rentalOrder.getFlowThreeStatus());
        for(ComeCarOrderImgDTO comeCarOrderImgDTO:comeCarOrderDTO.getComeCarOrderImgDTOS()){
            checkResult = AnnotationDealUtil.validate(comeCarOrderImgDTO);
            logger.info("subject:{},e:{}","校验参数",checkResult);
            if(checkResult.get("result").equals(false)){
                return Result.failure(checkResult.get("message").toString()).toJSON();
            }
        }
        Date date = new Date();
        List<ComeCarOrderImgDTO> comeCarOrderImgDTOS = comeCarOrderDTO.getComeCarOrderImgDTOS()
                .stream().filter(item -> StringUtil.isNotEmpty(item.getFileUrl()) && StringUtil.isNotEmpty(item.getFileName()))
                .collect(Collectors.toList());
        comeCarOrderImgDTOS.forEach(item ->{
            item.setOrderId(comeCarOrderDTO.getOrderId());
            item.setDescribeType(RentalOrderFileConstant.DESCRIBE_TYPE_COME_CAR);
            item.setStatus((int)DeleteStatusConstant.STATUS_NOT_DELETE);
            item.setCreateTime(date);
            item.setCreateBy(user.getId());
        });
        comeCarOrderDTO.setComeCarOrderImgDTOS(comeCarOrderImgDTOS);
        return orderFeignService.updateComeCarOrder(comeCarOrderDTO);
    }

    @Override
    public String getRentalOrderListBByStoreId(Map<String, Object> req) {
        return orderFeignService.getRentalOrderListBByStoreId(req);
    }

    @Override
    public String getRentalOrderDetailsAPPB(Map<String, Object> req) {
        return orderFeignService.getRentalOrderDetailsAPPB(req);
    }

    @Override
    public RentalOrder getRentalOrderByOrderId(Integer orderId) {
        return orderFeignService.getRentalOrderByOrderId(orderId);
    }

    @Override
    public String cancelUseCarOrder(UpdateRentalOrderDTO updateRentalOrderDTO) {
        if(StringUtil.isEmpty(updateRentalOrderDTO.getOrderId())){
            return Result.failure("订单id不能为空").toJSON();
        }
        RentalOrder rentalOrder = getRentalOrderByOrderId(updateRentalOrderDTO.getOrderId());
        if(rentalOrder == null || StringUtil.isEmpty(rentalOrder.getId())){
            return Result.failure("查询订单异常").toJSON();
        }

        Result result = CancelUseCarOrderUtil.cancelUseCarOrder(rentalOrder.getStatus(),rentalOrder.getFlowOneStatus(),rentalOrder.getFlowTwoStatus());
        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
            return result.toJSON();
        }
        updateRentalOrderDTO.setCloseBeforeStatus(rentalOrder.getStatus());
        updateRentalOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_98);
        updateRentalOrderDTO.setFlowOneStatus(0);
        updateRentalOrderDTO.setCloseCause("B端取消订单");
        updateRentalOrderDTO.setCloseTime(DateUtil.format(new Date(),DateUtil.DATATIMEF_STR));
        User user = UserUtil.getUser();
        updateRentalOrderDTO.setUserId(user.getId());
        logger.info("subject:{},updateRentalOrderDTO:{}","B端取消订单",JsonUtil.gsonStr(updateRentalOrderDTO));
        return orderFeignService.cancelUseCarOrder(updateRentalOrderDTO);
    }

    @Override
    public String getCarOilDesc(Integer carId) {
        return orderFeignService.getCarOilDesc(carId);
    }
}
