package com.ydc.commom.util.rental;

import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.enums.rental.RentalOrderStatusFourEnum;
import com.ydc.commom.enums.rental.RentalOrderStatusOneEnum;
import com.ydc.commom.result.Result;

/**
 * 取消用车订单
 *
 * @author
 * @create 2018-12-10 14:02
 **/
public class CancelUseCarOrderUtil {

    /**
     * 租车取消订单校验
     * @param orderStatus
     * @param flowOneStatus
     * @param flowTwoStatus
     * @return
     */
    public static Result cancelUseCarOrder(Integer orderStatus,Integer flowOneStatus,Integer flowTwoStatus){
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_1
                && flowOneStatus == RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus()
                && flowTwoStatus == RentalOrderStatusOneEnum.RentalOrderFlowTwoStatusEnum.FLOW_TWO_STATUS_100.getFlowTwoStatus()){
            return Result.failure("订单状态：已租车预授权-待出车");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_2){
            return Result.failure("订单状态：出车成功-待还车");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_3){
            return Result.failure("订单状态：已还车-待结算");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_4
                && flowOneStatus == RentalOrderStatusFourEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_1.getFlowOneStatus()){
            return Result.failure("订单状态：已还车-部分结算");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_4
                && flowOneStatus == RentalOrderStatusFourEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus()){
            return Result.failure("订单状态：已还车-已结算");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_98){
            return Result.failure("订单状态：已取消");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_99){
            return Result.failure("订单状态：已拒绝");
        }
        if(orderStatus == RentalOrderStatusConstant.RENTAL_ORDER_STATUS_100){
            return Result.failure("订单状态：结束");
        }
        return Result.success();
    }
}
