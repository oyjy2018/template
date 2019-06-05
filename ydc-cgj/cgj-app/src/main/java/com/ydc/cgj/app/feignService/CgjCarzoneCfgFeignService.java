package com.ydc.cgj.app.feignService;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(value = "service-sys")
public interface CgjCarzoneCfgFeignService {

    @PostMapping(value = "/carzone/queryCarzoneCfgList")
    String getCarzoneListByType(CgjCarzoneCfgQueDTO cgjViolationRecordQueDTO);
}
