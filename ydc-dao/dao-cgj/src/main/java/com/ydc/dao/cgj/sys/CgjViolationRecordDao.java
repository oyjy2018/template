package com.ydc.dao.cgj.sys;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.CgjViolationRecord;

import java.util.List;

public interface CgjViolationRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CgjViolationRecord record);

    int insertSelective(CgjViolationRecord record);

    CgjViolationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CgjViolationRecord record);

    int updateByPrimaryKey(CgjViolationRecord record);

    /**
     * 根据条件查询一条违章记录
     *
     */
    List<CgjViolationRecord> selectOneRecord(CgjViolationRecordQueDTO cgjViolationRecordQueDTO);
}