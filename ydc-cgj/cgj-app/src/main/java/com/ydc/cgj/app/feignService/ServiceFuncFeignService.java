package com.ydc.cgj.app.feignService;

import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 服务功能service
 *
 * @author wangcan
 * @create 2018-09-04 19:53
 **/
@Service
@FeignClient(value = "service-sys")
public interface ServiceFuncFeignService {

    /**
     * 查询服务功能列表 h5  app
     * @param
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/searchServiceFunc")
    String searchServiceFunc(String data);

    /**
     * 查询用车服务 用于首页展示
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/searchServiceFuncShowHome")
    String searchServiceFuncShowHome();
}
