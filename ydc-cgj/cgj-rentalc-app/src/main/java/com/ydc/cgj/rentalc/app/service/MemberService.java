package com.ydc.cgj.rentalc.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import java.util.List;

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
     * 通过客户id和类型获取图片信息
     * @param memberId
     * @param types
     * @return
     */
    List<MemberFile> getMemberFileByMemberIdAndType(Integer memberId, String types);
}
