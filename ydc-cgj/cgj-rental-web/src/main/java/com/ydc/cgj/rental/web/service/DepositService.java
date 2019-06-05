package com.ydc.cgj.rental.web.service;


import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
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
    Result freezeDeposit(PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO);

    /**
     *  押金结算回调
     */
    Result payDepositCallBack(AlipayTradePayResponseDTO alipayTradePayResponseDTO);

    /**
     *  调用支付宝押金结算
     */
    void updateSettleAliPayDeposit(Integer orderId, Integer type);

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
    List<RentalPayWatercourse> getRentalPayWatercourse(String orderNo, String thirdParthOrderNo);


}
