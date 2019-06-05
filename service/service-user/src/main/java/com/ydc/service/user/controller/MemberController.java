package com.ydc.service.user.controller;

import com.ydc.beans.redis.MemberUtil;
import com.ydc.commom.enums.rental.RentalOrderStatusOneEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.commom.view.vo.cgj.MemberVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.service.user.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
    private static final Logger logger = LogManager.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private LendingCustomerService lendingCustomerService;

    @Autowired
    private MemberApplicationService memberApplicationService;

    @Autowired
    private MemberRentalOrderService memberRentalOrderService;

    @Autowired
    private MemberFileService memberFileService;

    /**
     * 用户登录
     * @param mobilePhone
     * @return
     */
    @PostMapping("/memberLogin")
    public Member memberLogin(String mobilePhone) throws Exception{
        logger.info("用户账号密码登录, mobilePhone: " + mobilePhone);
        return memberService.selectByMobilePhone(mobilePhone);
    }

    /**
     * 用户注册
     * @param memberApplicationVO
     * @return
     */
    @PostMapping("/memberRegister")
    public Result memberRegister(@RequestBody MemberApplicationVO memberApplicationVO) throws Exception{
        logger.info("用户注册, memberApplicationVO: " + memberApplicationVO);
        //校验手机号是否已注册
        Result result = memberService.checkMobileIsRegister(memberApplicationVO.getMobilePhone(), memberApplicationVO.getApplication());
        if(result.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
            return result;
        }
        //添加用户
        return memberService.registerMember(memberApplicationVO) <= 0 ? Result.failure("注册失败，请稍后尝试") : Result.success();
    }

    /**
     * 用户修改密码
     * @param memberId
     * @param mobilePhone
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("/memberUpdatePassword")
    public Result memberUpdatePassword(Integer memberId, String mobilePhone, String password) throws Exception {
        logger.info("用户修改密码, memberId: " + memberId + ";mobilePhone: " + mobilePhone + ";password: " + password);
       return memberService.updatePasswordByMobilePhone(memberId, mobilePhone, password) < 0 ? Result.failure("修改失败，请稍后尝试") : Result.success();
    }

    /**
     * 检验密码
     * @param memberId
     * @param password
     * @return
     */
    @PostMapping("/checkMemberPassword")
    public Result checkMemberPassword(Integer memberId, String password){
        logger.info("检验密码, memberId: " + memberId + ";password: " + password);
        Member member = memberService.selectByPrimaryKey(memberId);
        if (member == null || !password.equals(member.getPassword())){
            return Result.failure("密码不正确");
        }
        return Result.success("密码正确");
    }

    /**
     * 检验用户是否已注册
     * @param mobilePhone
     * @return
     */
    @PostMapping("/checkMobileIsRegister")
    public Result checkMobileIsRegister(@RequestParam(value = "mobilePhone") String mobilePhone,
                                        @RequestParam(value = "application") Integer application){
        logger.info("检验用户是否已注册, mobilePhone: " + mobilePhone);
        return memberService.checkMobileIsRegister(mobilePhone, application);
    }
    /**
     * 后台-会员列表
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/getMemberList")
    public String getMemberList(@RequestBody MemberDTO memberDTO) {
        logger.info("subject:{},memberDTO:{}","查询会员列表",JsonUtil.gsonStr(memberDTO));
        try {
            List<MemberVO> ret = memberService.getMemberList(memberDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            logger.info("subject:{};jMap:{}","会员列表查询结果",JsonUtil.gsonStr(jMap));
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询会员列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 后台-新增会员
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/insertMember")
    public String insertMember(@RequestBody MemberApplicationVO memberApplicationVO){
        logger.info("subject:{},memberDTO:{}","新增会员",JsonUtil.gsonStr(memberApplicationVO));
        try {
            if(memberApplicationVO == null){
                return Result.failure("会员信息为空").toJSON();
            }
            if (memberApplicationService.getMemberApplication(memberApplicationVO.getMobilePhone(), null, memberApplicationVO.getApplication()) != null){
                return Result.failure("会员信息手机号码已存在").toJSON();
            }
            if(StringUtil.isNotEmpty(memberApplicationVO.getIdCard()) && memberService.getMemberByIdCard(memberApplicationVO.getIdCard()) != null){
                return Result.failure("会员信息身份证已存在").toJSON();
            }
            return memberService.registerMember(memberApplicationVO) <= 0 ? Result.failure("新增失败").toJSON() : Result.success("成功").toJSON();
        }catch (Exception e){
            logger.error("新增会员异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 更新会员状态
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/updateMemberStatus")
    public String updateMemberStatus(@RequestBody MemberDTO memberDTO){
        logger.info("subject:{},memberDTO:{}","更新会员状态",JsonUtil.gsonStr(memberDTO));
        try{
            if(memberService.updateMemberStatus(memberDTO) > 0){
                Member member = memberService.selectByPrimaryKey(memberDTO.getMemberId());
                if(memberDTO.getStatus().intValue() != 1){
                    MemberUtil.memberLogout(member.getMobilePhone());
                }
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("更新失败").toJSON();
            }
        }catch (Exception e){
            logger.error("更新会员状态异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 放款派卷
     * @param lendingCustomerDTO
     * @return
     */
    @PostMapping(value = "/getLendingCustomerList")
    public String getLendingCustomerList(@RequestBody LendingCustomerDTO lendingCustomerDTO) {
        logger.info("subject:{},lendingCustomerDTO:{}","查询放款派卷列表",JsonUtil.gsonStr(lendingCustomerDTO));
        try {
            List<Map<String, Object>> ret = lendingCustomerService.getLendingCustomerList(lendingCustomerDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询放款派卷列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 身份证查询会员信息
     * @param idCard
     * @return
     */
    @PostMapping(value = "/getMemberByIdCard")
    public Member getMemberByIdCard(@RequestParam("idCard") String idCard) {
        logger.info("subject:{},idCard:{}","身份证查询会员信息",idCard);
        try{
            return memberService.getMemberByIdCard(idCard);
        }catch (Exception e){
            logger.error("subject:{},e:{}","身份证查询会员信息异常",e);
            return new Member();
        }
    }

    /**
     * 会员身份认证
     * @param member
     * @return
     */
    @PostMapping(value = "/certificateIdentity")
    public Result certificateIdentity(@RequestBody Member member){
        Integer memberResult = memberService.updateCertificateByPrimaryKeySelective(member);
        if (memberResult == null || memberResult <= 0){
            return Result.failure("认证失败，客户已认证或不存在");
        }
        // 更新订单状态
        RentalOrder rentalOrder = new RentalOrder();
        rentalOrder.setMemberId(member.getId());
        rentalOrder.setName(member.getRealName());
        rentalOrder.setIdCard(member.getIdCard());
        rentalOrder.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_10.getFlowOneStatus());
        memberRentalOrderService.updateOrderOneStatus(rentalOrder, RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.getFlowOneStatus(),
                RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_9.getFlowOneStatus());
        return Result.success();
    }

    /**
     * 租车会员身份认证
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/rentalOrderMember")
    public Result rentalOrderMember(@RequestBody MemberApplicationVO memberApplicationVO){
        logger.info("subject:{},memberApplicationVO:{}","租车会员信息",JsonUtil.gsonStr(memberApplicationVO));
        try{
            return memberService.rentalOrderMember(memberApplicationVO);
        }catch (Exception e){
            logger.error("subject:{},e:{}","租车会员身份认证",e);
            return Result.failure("租车会员身份认证异常");
        }
    }

    /**
     * 新增租车订，身份证认证通过，更新实名认证状态
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/updateMemberWhetherRealNameStatus")
    public Result updateMemberWhetherRealNameStatus(@RequestBody MemberApplicationVO memberApplicationVO){
        logger.info("subject:{},memberApplicationVO:{}","新增租车订，身份证认证通过，更新实名认证状态",JsonUtil.gsonStr(memberApplicationVO));
        try{
            return memberService.updateMemberWhetherRealNameStatus(memberApplicationVO) > 0 ? Result.success("成功") : Result.failure("会员实名认证更新失败");
        }catch (Exception e){
            logger.error("subject:{},e:{}","新增租车订，身份证认证通过，更新实名认证状态",e);
            return Result.failure("会员实名认证更新失败异常");
        }
    }

    /**
     * 通过客户id和类型获取图片信息
     * @param memberId
     * @param types
     * @return
     */
    @GetMapping(value = "/getMemberFileByMemberIdAndType")
    public List<MemberFile> getMemberFileByMemberIdAndType(@RequestParam(value = "memberId") Integer memberId,
                                                           @RequestParam(value = "types") String types){
        return memberFileService.getMemberFileByMemberIdAndType(memberId, types);
    }

    /**
     * 根据微信的openId获取用户
     * @param openId
     * @return
     */
    @GetMapping(value = "/getMemberByOpenId")
    public Member getMemberByOpenId(@RequestParam("openId") String openId){
        return memberService.getMemberByOpenId(openId);
    }

    /**
     * 根据手机号码更新微信信息
     * @param member
     * @return
     */
    @PostMapping(value = "/updateWeixinInfoByMobilePhone")
    public Integer updateWeixinInfoByMobilePhone(@RequestBody Member member){
        return memberService.updateWeixinInfoByMobilePhone(member);
    }

    @PostMapping(value = "/getMemberById")
    public Member getMemberById(@RequestParam(value = "memberId") Integer memberId){
        return memberService.selectByPrimaryKey(memberId);
    }

    /**
     * 通过手机号查询会员姓名和身份证号
     * @param mobilePhone  手机号
     * @return
     */
    @PostMapping(value = "/getMemberNameAndIdCardByMobilePhone")
    public String getMemberNameAndIdCardByMobilePhone(@RequestParam("mobilePhone")String mobilePhone){
        logger.info("subject:{},mobilePhone:{}","通过手机号查询会员姓名和身份证号",mobilePhone);
        Member  member = memberService.getMemberByMobilePhone(mobilePhone);
        if (member == null) {
            return Result.failure("没有查询到会员信息").toJSON();
        }
        String memberName = member.getMemberName();
        String idCard = member.getIdCard();
        Map retMap = new HashMap();
        retMap.put("memberName",memberName);
        retMap.put("idCard",idCard);
        return Result.success(retMap).toJSON();
    }
}
