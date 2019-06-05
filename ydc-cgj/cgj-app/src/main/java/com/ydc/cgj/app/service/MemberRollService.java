package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberRollDto;

/**
 * 用户券service
 */
public interface MemberRollService {

    /**
     * 根据type获取用户券列表
     * @param memberRollDto
     * @return
     */
    Result getMemberRoll(MemberRollDto memberRollDto);
}