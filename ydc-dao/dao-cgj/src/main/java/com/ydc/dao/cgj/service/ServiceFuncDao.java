package com.ydc.dao.cgj.service;


import com.ydc.model.cgj.ServiceFunc;

import java.util.List;

public interface ServiceFuncDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceFunc record);

    int insertSelective(ServiceFunc record);

    ServiceFunc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceFunc record);

    int updateByPrimaryKey(ServiceFunc record);

    List<ServiceFunc> getServiceFuncListByServiceId(Integer serviceId);

    List<ServiceFunc> getServiceFuncListAllByServiceId(Integer serviceId);
}