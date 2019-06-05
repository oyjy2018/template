package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.ServiceFuncFeignService;
import com.ydc.cgj.app.service.ServiceFuncService;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-10-22 16:31
 **/
@Service
public class ServiceFuncServiceImpl implements ServiceFuncService {

    @Autowired
    ServiceFuncFeignService serviceFuncFeignService;

    @Override
    public String searchServiceFunc(String data) {
        return serviceFuncFeignService.searchServiceFunc(data);
    }

    @Override
    public String searchServiceFuncShowHome() {
        return serviceFuncFeignService.searchServiceFuncShowHome();
    }
}
