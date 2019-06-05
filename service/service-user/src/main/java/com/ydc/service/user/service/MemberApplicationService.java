package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;

import java.util.List;
import java.util.Map;

public interface MemberApplicationService {

    /**
     * 获取客户应用信息
     * @param mobilePhone
     * @param loginName
     * @return
     */
    MemberApplication getMemberApplication(String mobilePhone, String loginName, Integer application);

    /**
     * 获取客户列表
     * @param memberDTO
     * @return
     */
    List<Map<String, Object>> getMemberApplicationList(MemberDTO memberDTO);

    /**
     * 编辑客户资料
     *
     * @param memberApplicationVO
     * @return
     */
    int updateMemberApplication(MemberApplicationVO memberApplicationVO);

    /**
     * 通过memberId查询客户资料
     *
     * @param memberId
     * @return
     */
    MemberApplicationVO getMemberApplicationById(int memberId);
}
