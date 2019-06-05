package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.commom.OCRUtil;
import com.ydc.beans.commom.tembin.ResponseData;
import com.ydc.beans.commom.tembin.TembinUtil;
import com.ydc.cgj.rental.web.service.MemberApplicationService;
import com.ydc.cgj.rental.web.service.MemberService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.TianchengConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.idCard.IdcardInfoExtractor;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户controller
 */
@RestController
@RequestMapping(value = "/member")
public class MemberController {
    private final Logger logger = LogManager.getLogger(MemberController.class);

    @Autowired
    private MemberApplicationService memberApplicationService;

    @Autowired
    private MemberService memberService;

    /**
     * 获取客户列表
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:cus:manage:cus:view:query"})
    @PostMapping(value = "/getMemberApplicationList")
    public String getMemberApplicationList(@RequestParam("data") String data){
        MemberDTO memberDTO = JSONObject.parseObject(data, MemberDTO.class);
        memberDTO.setApplication(MemberConstant.APPLICATION_RENTAL);
        return memberApplicationService.getMemberApplicationList(memberDTO.changeEndTime(" 23:59:59")).toJSON();
    }

    /**
     * 编辑客戶資料
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:cus:manage:cus:view:update"})
    @PostMapping(value = "/updateMemberApplication")
    public String updateMemberApplication(@RequestParam("data") String data){
        MemberApplicationVO memberApplicationVO = JSONObject.parseObject(data, MemberApplicationVO.class);
        memberApplicationVO.setMemberName(memberApplicationVO.getRealName());
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);
        return memberApplicationService.updateMemberApplication(memberApplicationVO);
    }

    /**
     * 通过memberId查询客户资料
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:cus:manage:cus:view:detail"})
    @PostMapping(value = "/getMemberApplicationById")
    public String getMemberApplicationById(@RequestParam("data") String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        int memberId = jsonObject.getInteger("memberId");
        return memberApplicationService.getMemberApplicationById(memberId).toJSON();

    }

    /**
     * 新增会员
     * @param data
     * @return
     */
    @PostMapping(value = "/insertMember")
    public String insertMember(@RequestParam("data") String data) {
        MemberApplicationVO memberApplicationVO = JSONObject.parseObject(data,MemberApplicationVO.class);
        // 校验手机号为非空
        if (StringUtil.isEmpty(memberApplicationVO.getMobilePhone())) {
            return Result.failure("会员手机号码为空").toJSON();
        }
        memberApplicationVO.setCreateTime(new Date());
        memberApplicationVO.setSource(Member.SourceEnum.SOURCE_ENUM_2.getKey());// 风控云
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);   // 应用系统
        memberApplicationVO.setMemberStatus(Member.SourceEnum.MEMBER_STATUS_ENUM_1.getKey());
        memberApplicationVO.setStatus(CodeConstant.NORMAL_STATUS);// 正常
        memberApplicationVO.setMemberName(memberApplicationVO.getRealName());
        //添加客户操作
        doMemberInsert(memberApplicationVO);
        return memberApplicationService.insertMember(memberApplicationVO);
    }

    /**
     * 更新会员状态
     *
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:cus:manage:cus:view:disable","rental:cus:manage:cus:view:enabled"})
    @PostMapping(value = "/updateMemberStatus")
    public String updateMemberStatus(@RequestParam("data") String data) {
        MemberDTO memberDTO = JSONObject.parseObject(data,MemberDTO.class);
        memberDTO.setApplication(MemberConstant.APPLICATION_RENTAL);
        return memberApplicationService.updateMemberStatus(memberDTO);
    }

    /**
     * 会员身份认证
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:cus:manage:cus:view:auth"})
    @PostMapping(value = "/certificateIdentity")
    public String certificateIdentity(@RequestParam("data") String data){
        Member member = JSONObject.parseObject(data, Member.class);
        if (member == null){
            return Result.failure("参数错误").toJSON();
        }
        if (member.getRealName() == null || ("").equals(member.getRealName())){
            return Result.failure("参数错误，姓名不能为空").toJSON();
        }
        member.setMemberName(member.getRealName());
        if (member.getIdCard() == null || ("").equals(member.getIdCard())){
            return Result.failure("参数错误，身份证号不能为空").toJSON();
        }
        member.setWhetherRealName(CodeConstant.HAD);
        return memberService.certificateIdentity(member).toJSON();
    }

    //天秤认证身份证
    private int tembinVerification(String idCard, String realName){
        if (StringUtil.isEmpty(idCard) || StringUtil.isEmpty(realName)){
            return CodeConstant.NOT;
        }
        //调用天秤
        ResponseData<String> responseData = TembinUtil.idCardVerification(realName, idCard);
        if (responseData != null && TianchengConstant.IDENTITY_SUCCESS_CODE.equals(responseData.getHeader().getRtCode())){
            Map<String, Object> map = JsonUtil.jsonToMap(responseData.getBusinessData());
            if (TianchengConstant.IDENTITY_BUSINESS_DATA_SUCCESS.equals(map.get(TianchengConstant.RESPONSE_RESULT))){
                return CodeConstant.HAD;
            }
            return CodeConstant.NOT;
        }
        return CodeConstant.ING;
    }

    //添加会员操作
    private void doMemberInsert(MemberApplicationVO memberApplicationVO){
        List<MemberFile> fileList = memberApplicationVO.getFileList();
        //进行OCR身份证识别
        doOCRIdCard(memberApplicationVO, fileList);

        //识别身份证的年龄和性别
        if (!StringUtil.isEmpty(memberApplicationVO.getIdCard())) {
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(memberApplicationVO.getIdCard());
            memberApplicationVO.setAge(String.valueOf(idcardInfoExtractor.getAge()));
            memberApplicationVO.setGender(idcardInfoExtractor.getGender());
        }

        //调天秤校验身份证号，修改实名认证状态
        int whetherRealName = tembinVerification(memberApplicationVO.getRealName(), memberApplicationVO.getIdCard());
        memberApplicationVO.setWhetherRealName(whetherRealName);
        if (CodeConstant.HAD == whetherRealName){
            memberApplicationVO.setIdentityValidTime(new Date());
        }

        //驾驶证认证
        if (fileList != null) {
            String fileTypes = fileList.parallelStream()
                    .map(memberFile -> String.valueOf(memberFile.getType()))
                    .collect(Collectors.joining(","));
            if (fileTypes.contains(String.valueOf(MemberConstant.MEMBER_FILE_TYPE_3)) &&
                    fileTypes.contains(String.valueOf(MemberConstant.MEMBER_FILE_TYPE_4))) {
                memberApplicationVO.setDriversLicenseCertificate(CodeConstant.HAD);
                memberApplicationVO.setDriversLicenseCertificateTime(new Date());
            }
        }
    }

    //OCR身份证识别
    private void doOCRIdCard(MemberApplicationVO memberApplicationVO, List<MemberFile> fileList){
        if (memberApplicationVO == null){
            return;
        }
        if (fileList == null){
            return;
        }

        if ((StringUtil.isEmpty(memberApplicationVO.getIdCard()) ||
                StringUtil.isEmpty(memberApplicationVO.getRealName()))) {
            try {
                MemberFile faceMemberFile = fileList.parallelStream()
                        .filter((tempMemberFile) -> MemberConstant.MEMBER_FILE_TYPE_1 == tempMemberFile.getType())
                        .findAny().orElse(null);
                String ocrResponse = OCRUtil.doOcrIdCard(faceMemberFile);
                if (ocrResponse == null){
                    throw new ServiceRuntimeException("OCR识别结果为空");
                }
                Map<String, Object> responseMap = JsonUtil.jsonToMap(ocrResponse);
                Object success = responseMap.get("success");
                if (success == null || (!"true".equals(success) && !(Boolean) success)){
                    throw new ServiceRuntimeException("OCR识别结果为失败");
                }
                if (StringUtil.isEmpty(memberApplicationVO.getRealName())) {
                    memberApplicationVO.setRealName((String) responseMap.get("name"));
                }
                if (StringUtil.isEmpty(memberApplicationVO.getIdCard())) {
                    memberApplicationVO.setIdCard((String) responseMap.get("num"));
                }
            }catch (ServiceRuntimeException serviceException){
                logger.info("subject: {}, result: {}", "OCR识别结果失败", serviceException.getMessage());
            } catch (Exception e) {
                logger.error("subject: {}", "OCR身份证识别异常", e);
            }
        }
    }

    /**
     * 通过手机号查询会员姓名和身份证号
     * @param data
     * @return
     */
    @PostMapping("/getMemberNameAndIdCardByMobilePhone")
    public String getMemberNameAndIdCardByMobilePhone(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","通过手机号查询会员姓名和身份证号",data);
        Map dataMap = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(dataMap.get("mobilePhone"))) {
            return Result.failure("手机号为空").toJSON();
        }
        String mobilePhone = (String) dataMap.get("mobilePhone");
        return memberService.getMemberNameAndIdCardByMobilePhone(mobilePhone);
    }
}
