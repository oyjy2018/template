package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.MemberService;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员管理
 *
 * @author
 * @create 2018-10-29 16:20
 **/
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LogManager.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    /**
     * 会员列表
     *
     * @param data
     * @returngetMemberList
     *
     */
    @PostMapping(value = "/getMemberList")
    public String getMemberList(@RequestParam("data") String data) {
        logger.info("subject:{},memberDTO:{}", "会员列表", data);
        MemberDTO memberDTO = JSONObject.parseObject(data,MemberDTO.class);
        memberDTO.setApplication(MemberConstant.APPLICATION_CGJ);
        return memberService.getMemberList(memberDTO.changeDTO());
    }

    /**
     * 新增会员
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/insertMember")
    public String insertMember(@RequestParam("data") String data) {
        logger.info("subject:{},member:{}", "新增会员", data);
        MemberApplicationVO memberApplicationVO = JSONObject.parseObject(data,MemberApplicationVO.class);
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_CGJ);
        return memberService.insertMember(memberApplicationVO);
    }

    /**
     * 更新会员状态
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateMemberStatus")
    public String updateMemberStatus(@RequestParam("data") String data) {
        logger.info("subject:{},memberDTO:{}", "更新会员状态", data);
        MemberDTO memberDTO = JSONObject.parseObject(data,MemberDTO.class);
        memberDTO.setApplication(MemberConstant.APPLICATION_CGJ);
        memberDTO.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return memberService.updateMemberStatus(memberDTO);
    }
}
