package com.ydc.dao.cgj.service;


import com.ydc.model.cgj.Accept;

import java.util.List;

public interface AcceptApplyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Accept record);

    int insertSelective(Accept record);

    Accept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accept record);

    int updateByPrimaryKey(Accept record);

    List<Accept> getApplyApprList(Integer loanApplyId);
}