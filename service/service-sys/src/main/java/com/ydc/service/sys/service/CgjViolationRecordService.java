package com.ydc.service.sys.service;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.CgjViolationRecord;

import java.util.List;

public interface CgjViolationRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(CgjViolationRecord record);

    int insertSelective(CgjViolationRecord record);

    CgjViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CgjViolationRecord record);

    int updateByPrimaryKey(CgjViolationRecord record);

    List<CgjViolationRecord> selectOneRecord(CgjViolationRecordQueDTO cgjViolationRecordQueDTO);
}
