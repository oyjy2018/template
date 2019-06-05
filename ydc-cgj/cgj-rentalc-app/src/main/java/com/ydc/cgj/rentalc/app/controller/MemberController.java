package com.ydc.cgj.rentalc.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.commom.OCRUtil;
import com.ydc.beans.commom.tembin.ResponseData;
import com.ydc.beans.commom.tembin.TembinUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.file.FileUtil;
import com.ydc.cgj.rentalc.app.common.SubjectUtil;
import com.ydc.cgj.rentalc.app.service.MemberApplicationService;
import com.ydc.cgj.rentalc.app.service.MemberService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.TianchengConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.*;

/**
 * 客户管理
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
     * 认证身份证
     * @param data
     * @return
     */
    @PostMapping(value = "/certificateIdentity")
    public String certificateIdentity(@RequestParam(value = "data") String data) throws Exception{
        MemberApplicationVO memberApplicationVO = JSONObject.parseObject(data, MemberApplicationVO.class);
        if (StringUtil.isEmpty(memberApplicationVO.getIdCard())){
            return Result.failure("认证失败，身份证号不能为空").toJSON();
        }
        if (StringUtil.isEmpty(memberApplicationVO.getRealName())){
            return Result.failure("认证失败，真实姓名不能为空").toJSON();
        }
//        List<MemberFile> memberFileList = memberApplicationVO.getFileList();
//        if (memberFileList == null || memberFileList.size() <= 0){
//            return Result.failure("认证失败，身份证照片不能为空").toJSON();
//        }
        if (memberApplicationVO.getIdentityValidTime() != null &&
                DateUtil.date1LessThanOrEqualToDate2(memberApplicationVO.getIdentityValidTime(), new Date())){
            return Result.failure("认证失败，您的身份证已过期，请重新上传").toJSON();
        }

        Integer memberId = SubjectUtil.getMemberId();
        memberApplicationVO.setId(memberId);
        memberApplicationVO.setRealName(URLDecoder.decode(memberApplicationVO.getRealName(), "UTF-8"));
        memberApplicationVO.setMemberName(memberApplicationVO.getRealName());
        //校验
        Result checkResult = memberApplicationService.checkIdentityCertificate(memberApplicationVO);
        if (checkResult.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
            return checkResult.toJSON();
        }
        Date date = new Date();
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);
        memberApplicationVO.setIdentityCertificateTime(date);
        memberApplicationVO.setWhetherRealName(CodeConstant.HAD);
        //调天秤校验身份证号
        ResponseData<String> responseData = TembinUtil.idCardVerification(memberApplicationVO.getRealName(),
                memberApplicationVO.getIdCard());
        //调用成功
        if (responseData != null && TianchengConstant.IDENTITY_SUCCESS_CODE.equals(responseData.getHeader().getRtCode())){
            Map<String, Object> map = JsonUtil.jsonToMap(responseData.getBusinessData());
            if (!TianchengConstant.IDENTITY_BUSINESS_DATA_SUCCESS.equals(map.get(TianchengConstant.RESPONSE_RESULT))){
                return Result.failure("认证失败，姓名和身份证号不匹配").toJSON();
            }
        }else {
            memberApplicationVO.setWhetherRealName(CodeConstant.ING);
        }
        //添加
        return memberApplicationService.certificateMember(memberApplicationVO, MemberConstant.IDENTITY_CERTIFICATE).toJSON();
    }

    /**
     * 认证驾驶证
     * @param data
     * @return
     */
    @PostMapping(value = "/certificateDriversLicense")
    public String certificateDriversLicense(@RequestParam(value = "data") String data){
        MemberApplicationVO memberApplicationVO = JSONObject.parseObject(data, MemberApplicationVO.class);
//        if (StringUtil.isEmpty(memberApplicationVO.getDriversLicense())){
//            return Result.failure("认证失败，驾照证号不能为空").toJSON();
//        }
//        if (memberApplicationVO.getDriversLicenseValidTime() == null){
//            return Result.failure("认证失败，驾照证有效日期不能为空").toJSON();
//        }
//        if (StringUtil.isEmpty(memberApplicationVO.getDriversLicenseName())){
//            return Result.failure("认证失败，驾照证姓名不能为空").toJSON();
//        }
//        List<MemberFile> memberFileList = memberApplicationVO.getFileList();
//        if (memberFileList == null || memberFileList.size() <= 0){
//            return Result.failure("认证失败，驾驶证照片不能为空").toJSON();
//        }
//        if (memberApplicationVO.getDriversLicenseValidTime() != null &&
//                DateUtil.date1LessThanOrEqualToDate2(memberApplicationVO.getDriversLicenseValidTime(), new Date())){
//            return Result.failure("认证失败，您的身份证已过期，请重新上传").toJSON();
//        }

        Integer memberId = SubjectUtil.getMemberId();
        memberApplicationVO.setId(memberId);

        //校验
//        Result checkResult = memberApplicationService.checkDriversLicenseCertificate(memberApplicationVO);
//        if (checkResult.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
//            return checkResult.toJSON();
//        }
        Date date = new Date();
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);
        memberApplicationVO.setDriversLicenseCertificateTime(date);
        memberApplicationVO.setDriversLicenseCertificate(CodeConstant.HAD);
        //添加
        return memberApplicationService.certificateMember(memberApplicationVO, MemberConstant.DRIVERS_LICENSE_CERTIFICATE).toJSON();
    }


    /**
     * 身份证识别
     * @param data
     * @return
     */
    @PostMapping(value = "/callOcrIdCard")
    public String callOcrIdCard(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","身份证识别",data);
        try{
            JSONObject jsonObject = JSON.parseObject(data);
            if (StringUtil.isEmpty(jsonObject.get("fileName")) || StringUtil.isEmpty(jsonObject.get("fileUrl"))) {
                return Result.failure("文件名和文件路径不能为空").toJSON();
            }
            if (StringUtil.isEmpty(jsonObject.get("side"))) {
                return Result.failure("身份证类型不能为空").toJSON();
            }
            String fileName = (String) jsonObject.get("fileName");
            String fileUrl = (String) jsonObject.get("fileUrl");
            String side = (String) jsonObject.get("side");
            if(!("face".equals(side)) && !("back".equals(side))){
                return Result.failure("身份证类型参数错误").toJSON();
            }
            Map<String, Object> callOcrIdcardInfo = null;
            Map<String, Object> result = new HashMap<>();
            // 图片识别开关
            String SMSValidateSwitch = SystemPropertiesConfig.ALIYUN_OCR_SWITCH;
            if("off".equalsIgnoreCase(SMSValidateSwitch)){
                if("face".equals(side)){
                    result.put("memberName", "");
                    result.put("idCard", "");
                }else{
                    result.put("identityValidTime", "");
                }
                logger.info( "subject:{}","身份证别开关未开");
                return Result.success(result).toJSON();
            }
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            String browseFile = FileUtil.getBrowseFile(fileUrl, fileName+fileType);
            if(StringUtil.isEmpty(browseFile)){
                return Result.failure("未找到文件").toJSON();
            }
            callOcrIdcardInfo = OCRUtil.callOcrIdcard(FileUtil.getBase64Content(FileUtil.getFileStream(browseFile)), side);
            if(callOcrIdcardInfo == null){
                return Result.failure("文件识别失败").toJSON();
            }
            if("true".equals(callOcrIdcardInfo.get("reqState"))){
                if("face".equals(side)){
                    result.put("memberName", callOcrIdcardInfo.get("name"));
                    result.put("idCard", callOcrIdcardInfo.get("num"));
                }else{
                    String endDate = callOcrIdcardInfo.get("end_date").toString();
                    endDate = NumberUtil.isNum(endDate) ? endDate : "21000601";
                    endDate = endDate.substring(0, 4)+"-"+endDate.substring(4, 6)+"-"+endDate.substring(6, 8);
                    result.put("identityValidTime", endDate);
                }
            }else{
                return Result.failure(callOcrIdcardInfo.get("message") == null ? "身份证识别接口调用异常，请联系管理员！" : callOcrIdcardInfo.get("message").toString()).toJSON();
            }
            logger.info("subject:{},result:{}","身份证识别结果",callOcrIdcardInfo.toString());
            return Result.success(result).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","身份证识别异常",e);
            return Result.failure("身份证识别异常").toJSON();
        }
    }

    /**
     * 获取用户认证信息
     * @return
     */
    @GetMapping(value = "/getMemberCertificateInfo")
    public String getMemberCertificateInfo(){
        Member member = memberService.memberLogin(SubjectUtil.getMember().getMobilePhone());
        Result result = Result.success();
        Map<String, Object> map = new HashMap<>();
        map.put("whetherRealName", member.getWhetherRealName() == null ? 0 : member.getWhetherRealName());
        map.put("idCard", member.getIdCard());
        map.put("realName", member.getRealName());
        map.put("validTime", DateUtil.formatDate(member.getIdentityValidTime()));
        map.put("driversLicenseCertificate", member.getDriversLicenseCertificate() == null ? 0 : member.getDriversLicenseCertificate());
        result.setData(map);
        return result.toJSON();
    }

    /**
     * 获取客户图片
     * @return
     */
    @PostMapping(value = "/getMemberFileInfo")
    public String getMemberFileInfo(@RequestParam(value = "data") String data){
        Result result = Result.success();
        List<MemberFile> fileList = memberService.getMemberFileByMemberIdAndType(SubjectUtil.getMemberId(),
                JSON.parseObject(data).getString("data"));
        if (fileList != null && fileList.size() > 0){
            Map<String, List<String>> resultMap = new HashMap<>();
            fileList.forEach(memberFile -> {
                try {
                    String fileType = String.valueOf(memberFile.getType());
                    List<String> resultList = resultMap.get(fileType);
                    if (resultList == null){
                        resultList = new ArrayList<>();
                    }
                    resultList.add(FileUtil.getBrowseFile(memberFile.getFileUrl(), memberFile.getFileName()));
                    resultMap.put(fileType, resultList);
                } catch (Exception e) {
                    logger.error("生成客户驾驶证图片异常, memberId: {}", SubjectUtil.getMemberId(), e);
                }
            });
            result.setData(resultMap);
        }
        return result.toJSON();
    }
}
