package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.MemberRollDto;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberRoll;

import java.util.List;
import java.util.Map;

/**
 * 用户券service
 */
public interface MemberRollService {

    /**
     * 根据type获取用户券列表
     * @param memberRollDto
     * @return
     */
    List<Map<String, Object>> getMemberRollByType(MemberRollDto memberRollDto);

    /**
     * 获取有效空券张数
     * @return
     */
    List<Map<String, Object>> getBlankRollCount();

    /**
     * 根据类型获取一定数量的空券
     * @param rollType
     * @param num
     * @return
     */
    List<BRollDetail> getRollDetailTypeNum(Integer rollType, int num);

    /**
     * 用户派券
     * @param memberInfos
     * @param rollSizeList
     * @param params
     * @throws Exception
     */
    List<MemberRoll> sendRoll(List<Map<String, Integer>> memberInfos, List<Map<String, Object>> rollSizeList, Map<String, Object> params) throws Exception;

    /**
     * 获取用户优惠券列表
     * @param memberRollDto
     * @return
     */
    List<Map<String, Object>> getMemberRollList(MemberRollDto memberRollDto);

    /**
     * 更新借款单派券信息
     * @param loanIds
     */
    void updateSendRollInfo(List<Integer> loanIds);

    /**
     * 更新优惠券状态
     * @param rollCode
     * @param rollStatus
     */
    Integer updateRollStatus(String rollCode, Integer rollStatus);

    /**
     * 发放指定券
     * @param rollCodes
     * @param paramMap
     * @return
     */
    List<MemberRoll> sendAssignMemberRoll(List<String> rollCodes, Member member, Map<String, Object> paramMap) throws Exception;

    /**
     * 获取用户全部券的数量（不分页）
     * @param memberRollDto
     * @return
     */
    Integer getCountMemberRollByType(MemberRollDto memberRollDto);

    /**
     * 批量更新用户券状态（是否有效）
     * @param rollCodes
     * @param status
     * @return
     */
    Integer batchUpdateMemberRollStatus(List<String> rollCodes, Integer status);
}
