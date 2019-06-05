package com.ydc.service.sys.service.impl;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.dao.cgj.sys.CgjViolationRecordDao;
import com.ydc.model.cgj.CgjViolationRecord;
import com.ydc.service.sys.service.CgjViolationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CgjViolationRecordServiceImpl implements CgjViolationRecordService {

    @Autowired
    CgjViolationRecordDao violationRecordDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return violationRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CgjViolationRecord record) {
        return violationRecordDao.insert(record);
    }

    @Override
    public int insertSelective(CgjViolationRecord record) {
        return violationRecordDao.insertSelective(record);
    }

    @Override
    public CgjViolationRecord selectByPrimaryKey(Integer id) {
        return violationRecordDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CgjViolationRecord record) {
        return violationRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CgjViolationRecord record) {
        return violationRecordDao.updateByPrimaryKey(record);
    }

    @Override
    public List<CgjViolationRecord> selectOneRecord(CgjViolationRecordQueDTO cgjViolationRecordQueDTO) {
        return violationRecordDao.selectOneRecord(cgjViolationRecordQueDTO);
    }
}
