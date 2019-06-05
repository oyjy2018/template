package com.ydc.cgj.app.feignService;


import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(value = "service-store")
public interface IShopCartFeignService {

    @RequestMapping(value = "/shopCart/{memberId}", method = RequestMethod.GET)
    Result getMemberShopCart(@PathVariable(name = "memberId") Integer memberId);

    @RequestMapping(value = "/shopCart/add", method = RequestMethod.POST)
    Result addMemberShopCart(@RequestParam("memberId") Integer memberId,
                             @RequestParam("modelId") Integer modelId,
                             @RequestParam("commodityId") Integer commodityId,
                             @RequestParam("number") Integer number);

    @RequestMapping(value = "/shopCart", method = RequestMethod.POST)
    Result updateMemberShopCart(@RequestParam("id") Integer id,
                                @RequestParam("memberId") Integer memberId,
                                @RequestParam("number") Integer number);

    @RequestMapping(value = "/shopCart", method = RequestMethod.DELETE)
    Result deleteMemberShopCart(@RequestBody List<Integer> ids,
                                @RequestParam("memberId") Integer memberId);

}
