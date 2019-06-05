package com.ydc.cgj.bridge.service;

import com.ydc.commom.result.Result;

import java.util.List;
import java.util.Map;

public interface MemberRollService {

    /**
     * 批量更新用户券状态（是否有效）
     * @param rollCodes
     * @param status
     * @return
     */
    Result batchUpdateMemberRollStatus(List<String> rollCodes, Integer status, List<Integer> loanIds);

    /**
     * 派券
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    Result sendRoll(String memberInfos, String rollTypes, String params);
}
