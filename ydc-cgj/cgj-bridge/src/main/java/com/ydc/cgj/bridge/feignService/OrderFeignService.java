package com.ydc.cgj.bridge.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "service-order")
public interface OrderFeignService {

    @PostMapping(value = "/order/getOrderMemberId")
    Integer getOrderMemberId(@RequestParam("payWater") String payWater);

    /**
     * 配发券之后更新订单状态
     * @param orderList
     * @return
     */
    @PostMapping(value = "/order/updateSendRollOrderStatus")
    void updateSendRollOrderStatus(@RequestBody List<String> orderList);
}
