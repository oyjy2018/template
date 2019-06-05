package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjAppVersionQueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/3
 */
@FeignClient(value = "service-sys")
public interface IConfigFeignService {
    @RequestMapping(value = "/homeModule/queryHomeModules", method = RequestMethod.POST)
    Result queryHomeModules();
    @RequestMapping(value = "/advertConfig/queryAdverts", method = RequestMethod.POST)
    Result queryAdverts(@RequestBody AdvertConfigQueDTO advertConfigQueDTO);
    @RequestMapping(value = "/buttonConfig/queryButtonConfigs", method = RequestMethod.POST)
    Result queryButtonConfigs(AdvertConfigQueDTO advertConfigQueDTO);
    @RequestMapping(value = "/appVersion/queryAppVersion", method = RequestMethod.POST)
    Result getNewestVersion(CgjAppVersionQueDTO cgjAppVersionQueDTO);
}
