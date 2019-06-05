package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author
 * @create 2018-11-28 13:38
 **/
@Service
@FeignClient(value = "service-order")
public interface RentalSettlementFeignService {


    /**
     * 返回结算需要订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/rentalSettlement/getRentalSettlement")
    String getRentalSettlement(@RequestParam("orderId") Integer orderId);


    /**
     * 首次结算信息入库
     * @param record
     * @return
     */
    @PostMapping(value = "/rentalSettlement/saveRentalSettlement")
    Result saveRentalSettlement(@RequestBody RentalSettlementDTO record);


    /**
     * 单独保存租车支付流水
     * @param rentalPayWatercourse
     * @return
     */
    @PostMapping(value = "/rentalSettlement/saveRentalPayWatercourse")
    String saveRentalPayWatercourse(@RequestBody RentalPayWatercourse rentalPayWatercourse);



    /**
     * 结算信息查询
     * @param orderId
     * @return
     */
    @PostMapping(value = "/rentalSettlement/getRentalSettlementByOrderId")
    RentalSettlement getRentalSettlementByOrderId(@RequestParam("orderId") Integer orderId);

    /**
     * 更新支付宝结算参数
     */
    @PostMapping(value = "/rentalSettlement/alipay/update")
    Result updateAliPaySettleDTO(@RequestParam("orderId")Integer orderId,
                                 @RequestParam("depositType") Integer depositType);
}
