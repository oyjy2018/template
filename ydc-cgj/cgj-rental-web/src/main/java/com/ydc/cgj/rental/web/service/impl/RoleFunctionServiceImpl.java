package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.RoleFunctionFeignService;
import com.ydc.cgj.rental.web.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

    @Autowired
    private RoleFunctionFeignService roleFunctionFeignService;

    /**
     * 获取功能树
     * @param serviceIdentifying
     * @param functionName
     * @return
     */
    @Override
    public String getFunctionTree(String serviceIdentifying, String functionName) {
        return roleFunctionFeignService.getFunctionTree(serviceIdentifying, functionName);
    }
}
