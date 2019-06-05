package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.RentalDispatch;

public interface RentalDispatchDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalDispatch record);

    int insertSelective(RentalDispatch record);

    RentalDispatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalDispatch record);

    int updateByPrimaryKey(RentalDispatch record);

    /**
     * 根据机务单id获取调用信息
     * @param maintenanceId
     * @return
     */
    RentalDispatch selectDispatchInfoByMaintenanceId(Integer maintenanceId);
}