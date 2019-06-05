package com.ydc.cgj.app.service;

import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;


/**
 * 违章记录service
 */
public interface CgjViolationRecordService {

    /**
     * 获取违章记录列表
     */
    String getRecordList(String data);

}
