package com.ydc.service.store.service;

import com.ydc.model.cgj.rental.Organization;

import java.util.List;

/**
 * 机构
 *
 * @author
 * @create 2018-11-19 10:42
 **/
public interface OrganizationService {


    /**
     * 查询上级机构
     * @return
     */
    List<Organization> getOrganization();
}
