package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.ServiceFuncFeignService;
import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.cgj.web.service.ServiceFuncService;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
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
    public String insertServiceFunc(DictionaryDetailDTO dictionaryDetailDTO) {
        return serviceFuncFeignService.insertServiceFunc(dictionaryDetailDTO);
    }

    @Override
    public String updateServiceFunc(DictionaryDetailDTO dictionaryDetailDTO) {
        return serviceFuncFeignService.updateServiceFunc(dictionaryDetailDTO);
    }

    @Override
    public String daleteServiceFunc(Integer id) {
        return serviceFuncFeignService.daleteServiceFunc(id);
    }

    @Override
    public String searchServiceFunc(DictionaryDetail dictionaryDetail) {
        return serviceFuncFeignService.searchServiceFunc(dictionaryDetail);
    }

    @Override
    public String searchAllServiceFunc(DictionaryDetail dictionaryDetail) {
        return serviceFuncFeignService.searchAllServiceFunc(dictionaryDetail);
    }

    @Override
    public String searchServiceFuncShowHome() {
        return serviceFuncFeignService.searchServiceFuncShowHome();
    }

    @Override
    public String getEnumList() {
        return serviceFuncFeignService.getEnumList();
    }

    @Override
    public String writeRedis() {
        return serviceFuncFeignService.writeRedis();
    }
}
