package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;

/**
 * @author
 * @create 2018-10-29 16:17
 **/
public interface MemberService {

    /**
     * 查询会员列表
     *
     * @param memberDTO
     * @return
     */
    String getMemberList(MemberDTO memberDTO);

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
}
