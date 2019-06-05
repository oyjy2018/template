package com.ydc.cgj.rentalb.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;

import java.util.List;

public interface DepositService {

    /**
     *   查看资金二维码
     */
    Result getOrderDepositQrCode(Integer orderId, Integer type);

    /**
     * 回调 押金冻结
     */
    Result<String> freezeDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO);

    /**
     *  押金结算回调
     */
    Result payDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO);

    /**
     * 手动冻结押金
     * @param manualFreezeRentalDepositDTO
     * @return
     */
    Result manualOrderDeposit(ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO);

    /**
     * 获取流水
     * @param orderNo
     * @param thirdParthOrderNo
     * @return
     */
    List<RentalPayWatercourse> getRentalPayWatercourse(String orderNo,String thirdParthOrderNo);

    /**
     * 新增租赁押金
     * @param rentalDeposit
     * @return
     */
    public String addRentalDeposit(RentalDeposit rentalDeposit);

    /**
     * 回调 保证金冻结(企业租车)
     * @param aliPayFundAuthNotifyDTO
     * @return
     */
    public Result freezeEnterpriseDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO);

    /**
     *  解冻押金
     */
    public Result<String> unfreezeDeposit(AlipayTradePayResponseDTO alipayTradePayResponseDTO);
}
