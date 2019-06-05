package com.ydc.dao.cgj.loan;


import com.ydc.model.cgj.LoanApply;

import java.util.List;
import java.util.Map;

public interface LoanApplyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanApply record);

    int insertSelective(LoanApply record);

    LoanApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanApply record);

    int updateByPrimaryKey(LoanApply record);

    Integer getApplyNumByProductType(Integer applyProductType);

    List<LoanApply> getApplyList(Map<String,Object> map);
}