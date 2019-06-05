package com.ydc.service.store.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.dao.cgj.rental.OrganizationDao;
import com.ydc.model.cgj.rental.Organization;
import com.ydc.service.store.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-11-19 10:43
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationDao organizationDao;
    @Override
    public List<Organization> getOrganization() {
        List<Organization>  organizations = organizationDao.getOrganization();
        RedisUtil.redisSet(RentalDictionaryConstant.RENTAL_STORE_CONFIG_SUPERIOR_ORGANIZATION, organizations, null);
        return organizations;
    }
}
