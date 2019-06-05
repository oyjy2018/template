package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-order")
public interface IPayFeignService {

    @RequestMapping(value = "/pay/order", method = RequestMethod.POST)
    Result payOrder(@RequestBody PayOrderParamVO payOrderParamVO,
                    @RequestParam("memberId") Integer memberId);

    /**
     * 购买券
     * @param buyRollParamVO
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/pay/buyRoll", method = RequestMethod.POST)
    Result buyRoll(@RequestBody BuyRollParamVO buyRollParamVO,  @RequestParam("memberId") Integer memberId);
}
