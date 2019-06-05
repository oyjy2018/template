package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;

public interface MemberService {

    /**
     * 用户登录
     *
     * @param mobilePhone
     * @return
     */
    Member memberLogin(String mobilePhone);

    /**
     * 用户注册
     *
     * @param memberApplicationVO
     * @return
     */
    Result memberRegister(MemberApplicationVO memberApplicationVO);

    /**
     * 用户修改密码
     *
     * @param memberId
     * @param mobilePhone
     * @param password
     * @return
     */
    Result memberUpdatePassword(Integer memberId, String mobilePhone, String password);

    /**
     * 检验密码
     *
     * @param memberId
     * @param password
     * @return
     */
    Result checkMemberPassword(Integer memberId, String password);

    /**
     * 检验用户是否注册
     *
     * @param mobilePhone
     * @return
     */
    Result checkMobileIsRegister(String mobilePhone, Integer application);

    /**
     * 根据id获取用户
     * @param memberId
     * @return
     */
    Member getMemberById(Integer memberId);

    /**
     * 更新手机号码更新
     * @param member
     * @return
     */
    Integer updateWeixinInfoByMobilePhone(Member member);

    /**
     * 根据微信的openId获取用户
     * @param openId
     * @return
     */
    Member getMemberByOpenId(String openId);
}
