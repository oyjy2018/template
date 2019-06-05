package com.ydc.cgj.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.MemberRollDto;

import java.util.List;

/**
 * 用户券的service
 */
public interface MemberRollService {

    /**
     * 获取多种空白券的数量
     * @return
     */
    Result getBlankRollCount();

    /**
     * 派券检验
     * @param memberInfos
     * @param rollSizeList
     * @return
     */
    Result checkSendingRoll(String memberInfos, String rollSizeList);

    /**
     * 派券
     * @param memberInfos
     * @param rollTypes
     * @return
     */
    Result sendRoll(String memberInfos, String rollTypes, String params);

    /**
     * 派发指定券
     * @param rollCodes
     * @param params
     * @return
     */
    Result sendAssignMemberRoll(List<String> rollCodes, String params);

    /**
     * 更新用户券状态
     * @param rollCode
     * @param rollStatus
     * @return
     */
    Result updateRollStatus(String rollCode, Integer rollStatus);

    /**
     * 获取用户优惠券列表
     * @param memberRollDto
     * @return
     */
    Result getMemberRollList(MemberRollDto memberRollDto);

    /**
     * 获取空券列表
     * @param blankRollDto
     * @return
     */
    String getBlankRollList(BlankRollDto blankRollDto);

    /**
     * 刷新空券
     * @param rollCodes
     * @return
     */
    String flushBlankRolls(List<String> rollCodes);
}
