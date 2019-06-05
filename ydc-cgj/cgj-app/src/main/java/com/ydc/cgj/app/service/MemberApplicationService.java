package com.ydc.cgj.app.service;

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
}
