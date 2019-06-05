package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-store")
public interface ICommodityModelService {

    /**
     * 获取商品型号列表
     *
     * @param commodityId
     * @return
     */
    @PostMapping(value = "/commodity/getCommodityModelList")
    Result getCommodityModelList(@RequestParam("commodityId") Integer commodityId);
}
