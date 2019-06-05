package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.cgj.web.service.MemberService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.idCard.IdcardInfoExtractor;
import com.ydc.commom.util.idCard.IdcardUtil;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * 会员管理
 *
 * @author
 * @create 2018-10-29 16:18
 **/
@Service
public class MemberServiceImpl implements MemberService {


    @Autowired
    private UserFeignService userFeignService;


    @Override
    public String getMemberList(MemberDTO memberDTO) {
        return userFeignService.getMemberList(memberDTO);
    }

    @Override
    public String insertMember(MemberApplicationVO memberApplicationVO) {
        //验证参数是否为空
        if (memberApplicationVO == null) {
            return Result.failure("会员信息为空").toJSON();
        }
        if (StringUtil.isEmpty(memberApplicationVO.getMemberName())) {
            return Result.failure("会员姓名为空").toJSON();
        }
        if (StringUtil.isEmpty(memberApplicationVO.getMobilePhone())) {
            return Result.failure("会员手机号码为空").toJSON();
        }
        //如果身份证不为空，验证身份格式是否正确
        if (StringUtil.isNotEmpty(memberApplicationVO.getIdCard())) {
            if (!IdcardUtil.checkIdcard(memberApplicationVO.getIdCard())) {
                return Result.failure("会员身份证格式不正确").toJSON();
            }
            memberApplicationVO.setWhetherRealName(1);//是否实名（1：是；0：否）
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(memberApplicationVO.getIdCard());
            memberApplicationVO.setAge(String.valueOf(idcardInfoExtractor.getAge()));
            memberApplicationVO.setGender(idcardInfoExtractor.getGender());
        } else {
            memberApplicationVO.setWhetherRealName(0);
            memberApplicationVO.setWhetherLoan(0);//是否借款（1：是；0：否）
        }
        memberApplicationVO.setSource(1);//风控云
        memberApplicationVO.setStatus(1);//正常
        memberApplicationVO.setCreateTime(new Date());
        memberApplicationVO.setRealName(memberApplicationVO.getMemberName());
        return userFeignService.insertMember(memberApplicationVO);
    }

    @Override
    public String updateMemberStatus(MemberDTO memberDTO) {
        return userFeignService.updateMemberStatus(memberDTO);
    }
}
