package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.IntegralService;
import com.ydc.cgj.app.service.MemberRollService;
import com.ydc.cgj.app.service.MemberService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.MemberRollDto;
import com.ydc.model.cgj.Integral;
import com.ydc.model.cgj.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户券controller
 */
@RestController
@RequestMapping(value = "/memberRoll")
public class MemberRollController {
    private final Logger logger = LogManager.getLogger(MemberRollController.class);

    @Autowired
    private MemberRollService memberRollService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private MemberService memberService;

    /**
     * 根据type获取用户券列表
     * @param data
     * @return
     */
    @GetMapping(value = "/getMemberRoll")
    public String getMemberRoll(@RequestParam("data") String data){
        MemberRollDto memberRollDto = JSON.parseObject(data, MemberRollDto.class);
        memberRollDto.setMemberId(SubjectUtil.getMember().getId());
        return memberRollService.getMemberRoll(memberRollDto).toJSON();
    }
    /**
     * 查看我的相关功能
     * @author: hejiangping
     * @date: 2019/1/4
     */
    @PostMapping(value = "/myInfo")
    public String myInfo(){
        try {
            Member member = SubjectUtil.getMember();
            //Member member = memberService.getMemberByOpenId("ududududu");
            if(member == null){
                return Result.failure("用户未登录").toJSON();
            }
            Map<String, Object> jMap = new HashMap<>();
            // 用户信息
            Map<String, Object> memberInfo = new HashMap<>();
            String hideMobile = member.getMobilePhone()==null?"":member.getMobilePhone().substring(0, 3)+"****"+member.getMobilePhone().substring(member.getMobilePhone().length()-4);
            memberInfo.put("memberId", member.getId());
            memberInfo.put("memberName", StringUtil.isEmpty(member.getMemberName()) ?hideMobile:member.getMemberName());
            memberInfo.put("mobilePhone",member.getMobilePhone()==null?"":member.getMobilePhone());
            jMap.put("memberInfo",memberInfo);
            // 优惠卡券数量
            MemberRollDto memberRollDto = new MemberRollDto();
            memberRollDto.setMemberId(member.getId());
            memberRollDto.setRollStatus(0);
            Result rollCountResult = memberRollService.getMemberRoll(memberRollDto);
            Map<String, Object> data = (Map<String, Object>) rollCountResult.getData();
            jMap.put("rollCount", data.get("totalCount") == null ? 0.00 : new BigDecimal(data.get("totalCount").toString()));
            // 积分数量
            String inResultStr = integralService.getMyIntegral(member.getId());
            Result inResult = JsonUtil.jsonToBean(inResultStr,Result.class);
            Map<String, Object> inData = (Map<String, Object>) inResult.getData();
            jMap.put("usableIntegral", inData.get("usableIntegral") == null ? 0.00 : new BigDecimal(inData.get("usableIntegral").toString()));
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查看我的相关功能异常",e);
            return Result.failure().toJSON();
        }
    }
}
