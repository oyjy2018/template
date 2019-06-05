package com.ydc.service.sys.service.impl;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.dao.cgj.sys.CgjViolationRecordDetailDao;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;
import com.ydc.service.sys.service.CgjViolationRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CgjViolationRecordDetailServiceImpl implements CgjViolationRecordDetailService {

    @Autowired
    CgjViolationRecordDetailDao cgjViolationRecordDetailDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cgjViolationRecordDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CgjViolationRecordDetail record) {
        return cgjViolationRecordDetailDao.insert(record);
    }

    @Override
    public int insertSelective(CgjViolationRecordDetail record) {
        return cgjViolationRecordDetailDao.insertSelective(record);
    }

    @Override
    public CgjViolationRecordDetail selectByPrimaryKey(Integer id) {
        return cgjViolationRecordDetailDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CgjViolationRecordDetail record) {
        return cgjViolationRecordDetailDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CgjViolationRecordDetail record) {
        return cgjViolationRecordDetailDao.updateByPrimaryKey(record);
    }

    @Override
    public List<CgjViolationRecordDetail> selectRecordDetailListByRecordId(CgjViolationRecordQueDTO cgjViolationRecordQueDTO) {
        return cgjViolationRecordDetailDao.selectRecordDetailListByRecordId(cgjViolationRecordQueDTO);
    }

    @Override
    public int updateDealStatusById(CgjViolationRecordDetail cgjViolationRecordDetail) {
        return cgjViolationRecordDetailDao.updateDealStatusById(cgjViolationRecordDetail);
    }

}
