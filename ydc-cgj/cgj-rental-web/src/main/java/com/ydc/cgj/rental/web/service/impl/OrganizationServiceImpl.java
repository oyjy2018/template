package com.ydc.cgj.rental.web.service.impl;


import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.rental.web.feignService.StoreFeignService;
import com.ydc.cgj.rental.web.service.OrganizationService;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 机构
 * @author
 * @create 2018-11-19 10:51
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    StoreFeignService storeFeignService;

    @Override
    public String getOrganization() {
        Object obj = RedisUtil.redisGet(RentalDictionaryConstant.RENTAL_STORE_CONFIG_SUPERIOR_ORGANIZATION);
        return obj == null ? Result.success(storeFeignService.getOrganization()).toJSON() :  Result.success(obj).toJSON();
    }
}
