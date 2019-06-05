package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.alipay.AlipayTradePayResponseDTO;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.PayRentalDepositRequestDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@FeignClient(value = "service-order")
public interface DepositFeignService {


    /**
     * 查询订单 押金
     * @param orderId
     * @param type
     * @return
     */
    @PostMapping(value = "/deposit/order/get")
    Result getOrderDeposit(@RequestParam(value = "orderId") Integer orderId,
                           @RequestParam(value = "type") Integer type );


    /**
     * 回调 押金冻结
     */
    @PostMapping(value = "/deposit/freeze/update")
    Result<String> freezeDeposit(@RequestBody PayRentalDepositRequestDTO payRentalDepositRequestDTO);


    /**
     * 手动冻结押金
     * @param manualFreezeRentalDepositDTO
     * @return
     */
    @PostMapping(value = "/deposit/freeze/manual/update")
    Result manualOrderDeposit(@RequestBody ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO);

    /**
     *  押金结算扣除
     */
    @PostMapping(value = "/deposit/pay/update")
    Result<String> payDeposit(@RequestBody AlipayTradePayResponseDTO alipayTradePayResponseDTO);



    /**
     * 获取订单押金
     * @param orderId
     * @param type
     * @return
     */
    @PostMapping(value = "/deposit/getRentalOrderDeposit")
    RentalDeposit getRentalOrderDeposit(@RequestParam(value = "orderId") Integer orderId,
                                        @RequestParam(value = "type") Integer type );


    /**
     * 获取流水
     * @param orderNo
     * @param thirdParthOrderNo
     * @return
     */
    @PostMapping(value = "/deposit/getRentalPayWatercourse")
    List<RentalPayWatercourse> getRentalPayWatercourse(@RequestParam(value = "orderNo") String orderNo,
                                                       @RequestParam(value = "thirdParthOrderNo") String thirdParthOrderNo);

    /**
     * 新增租赁押金
     * @param rentalDeposit
     * @return
     */
    @PostMapping(value = "/deposit/addRentalDeposit")
    public String addRentalDeposit(@RequestBody RentalDeposit rentalDeposit);

    /**
     * 回调 保证金冻结(企业租车)
     * @param aliPayFundAuthNotifyDTO
     * @return
     */
    @PostMapping(value = "/deposit/enterprise/freeze/update")
    public Result freezeEnterpriseDeposit(@RequestBody PayRentalDepositRequestDTO aliPayFundAuthNotifyDTO);

    /**
     *  解冻押金
     */
    @PostMapping(value = "/deposit/unfreezeDeposit")
    public Result<String> unfreezeDeposit(@RequestBody AlipayTradePayResponseDTO alipayTradePayResponseDTO);
}
