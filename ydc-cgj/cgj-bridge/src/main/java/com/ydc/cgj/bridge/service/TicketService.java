package com.ydc.cgj.bridge.service;

import com.ydc.commom.result.Result;

import java.util.List;

public interface TicketService {

    /**
     * 批量更新空券状态
     * @param rollCodes
     * @param status
     * @return
     */
    Result batchUpdateBlankRollStatus(List<String> rollCodes, Integer status);
}
