package com.ydc.service.sys.service;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;

import java.util.List;

public interface CgjViolationRecordDetailService {
    int deleteByPrimaryKey(Integer id);

    int insert(CgjViolationRecordDetail record);

    int insertSelective(CgjViolationRecordDetail record);

    CgjViolationRecordDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CgjViolationRecordDetail record);

    int updateByPrimaryKey(CgjViolationRecordDetail record);

    List<CgjViolationRecordDetail> selectRecordDetailListByRecordId(CgjViolationRecordQueDTO cgjViolationRecordQueDTO);

    int updateDealStatusById(CgjViolationRecordDetail cgjViolationRecordDetail);

}
