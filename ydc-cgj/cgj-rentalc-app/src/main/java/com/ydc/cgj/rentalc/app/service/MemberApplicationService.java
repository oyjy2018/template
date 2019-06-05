package com.ydc.cgj.rentalc.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;

public interface MemberApplicationService {

    /**
     * 获取客户应用信息
     * @param mobilePhone
     * @return
     */
    MemberApplication getMemberApplicationByMobilePhone(String mobilePhone, Integer application);

    /**
     * 获取客户应用信息
     * @param loginName
     * @return
     */
    MemberApplication getMemberApplicationByLoginName(String loginName, Integer application);

    /**
     * 身份证认证校验
     * @param memberApplicationVO
     * @return
     */
    Result checkIdentityCertificate(MemberApplicationVO memberApplicationVO);

    /**
     * 驾驶证认证校验
     * @param memberApplicationVO
     * @return
     */
    Result checkDriversLicenseCertificate(MemberApplicationVO memberApplicationVO);

    /**
     * 认证客户
     * @param memberApplicationVO
     * @return
     */
    Result certificateMember(MemberApplicationVO memberApplicationVO, int isIdentity);
}
