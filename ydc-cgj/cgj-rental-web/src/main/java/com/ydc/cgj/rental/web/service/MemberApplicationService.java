package com.ydc.cgj.rental.web.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;

public interface MemberApplicationService {

    /**
     * 获取客户列表
     *
     * @param memberDTO
     * @return
     */
    Result getMemberApplicationList(MemberDTO memberDTO);

    /**
     * 后台-新增会员
     *
     * @param memberApplicationVO
     * @return
     */
    String insertMember(MemberApplicationVO memberApplicationVO);

    /**
     * 更新会员状态
     *
     * @param memberDTO
     * @return
     */
    String updateMemberStatus(MemberDTO memberDTO);

    /**
     * 编辑客戶資料
     *
     * @param memberApplicationVO
     * @return
     */
    String updateMemberApplication(MemberApplicationVO memberApplicationVO);

    /**
     * 通过memberId查询客户资料
     * @param memberId
     * @return
     */
    Result getMemberApplicationById(int memberId);
}
