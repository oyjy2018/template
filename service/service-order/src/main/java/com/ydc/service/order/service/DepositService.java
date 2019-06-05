package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;

public interface DepositService {

    RentalDeposit selectByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int insert(RentalDeposit record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int insertSelective(RentalDeposit record);

    RentalDeposit queryRentalDepositByOrderAndType(Integer orderId,Integer type,Integer orderType);

    Result updateAliPayOrderDeposit(PayRentalDepositRequestDTO ailPayFundAuthNotifyDTO);

    //回调冻结押金
    Result freezeOrderDeposit(PayRentalDepositRequestDTO payRentalDepositRequestDTO);

    /**
     * 手动处理押金
     */
    Result manualOrderDeposit(ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO);

    // 押金结算
    Result payOrderDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO);

    /**
     * 回调解冻
     * @param alipayTradePayResponseDTO
     * @return
     */
    Result unfreezeOrderDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO);

    //结算押金




}
