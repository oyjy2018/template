package com.ydc.dao.cgj.rental;

import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;

public interface RentalCompanyLoginAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalCompanyLoginAccount record);

    int insertSelective(RentalCompanyLoginAccount record);

    RentalCompanyLoginAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCompanyLoginAccount record);

    int updateByPrimaryKey(RentalCompanyLoginAccount record);
}