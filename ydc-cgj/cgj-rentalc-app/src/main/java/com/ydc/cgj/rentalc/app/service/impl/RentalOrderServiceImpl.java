package com.ydc.cgj.rentalc.app.service.impl;

import com.ydc.cgj.rentalc.app.common.SubjectUtil;
import com.ydc.cgj.rentalc.app.feignService.OrderFeignService;
import com.ydc.cgj.rentalc.app.service.RentalOrderService;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.rental.CancelUseCarOrderUtil;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.rental.RentalOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * @create 2018-11-22 13:01
 **/
@Service
public class RentalOrderServiceImpl implements RentalOrderService {

    private static final Logger logger = LogManager.getLogger(RentalOrderService.class);

    @Autowired
    OrderFeignService orderFeignService;

    @Override
    public String getRentalOrderListCByMemberId(Map<String, Object> req) {
        return orderFeignService.getRentalOrderListCByMemberId(req);
    }

    @Override
    public String getRentalOrderDetailsAPPC(Map<String, Object> req) {
        return orderFeignService.getRentalOrderDetailsAPPC(req);
    }

    @Override
    public Result updateConsentAuthorization(Integer memberId, Integer orderId, String status) {
        Result result= orderFeignService.updateConsentAuthorization(memberId, orderId, status);
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS ){


        }
        return orderFeignService.updateConsentAuthorization(memberId, orderId, status);
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
        updateRentalOrderDTO.setCloseCause("C端取消订单");
        updateRentalOrderDTO.setCloseTime(DateUtil.format(new Date(),DateUtil.DATATIMEF_STR));
        Member member = SubjectUtil.getMember();
        updateRentalOrderDTO.setUserId(member.getId());
        return orderFeignService.cancelUseCarOrder(updateRentalOrderDTO);
    }

    @Override
    public RentalOrder getRentalOrderByOrderId(Integer orderId) {
        return orderFeignService.getRentalOrderByOrderId(orderId);
    }
}
