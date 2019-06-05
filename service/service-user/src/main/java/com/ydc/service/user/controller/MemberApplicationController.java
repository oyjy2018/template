package com.ydc.service.user.controller;

import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberApplication;
import com.ydc.model.cgj.MemberFile;
import com.ydc.service.user.service.MemberApplicationService;
import com.ydc.service.user.service.MemberFileService;
import com.ydc.service.user.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/memberApplication")
public class MemberApplicationController {
    Logger logger = LogManager.getLogger(MemberApplicationController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberApplicationService memberApplicationService;

    @Autowired
    private MemberFileService memberFileService;

    /**
     * 获取客户应用信息
     * @param mobilePhone
     * @param application
     * @return
     */
    @GetMapping(value = "/getMemberApplicationByPhone")
    public MemberApplication getMemberApplicationByPhone(@RequestParam(value = "mobilePhone") String mobilePhone,
                                                         @RequestParam(value = "application") Integer application){
        return memberApplicationService.getMemberApplication(mobilePhone, null, application);
    }

    /**
     * 获取客户应用信息
     * @param loginName
     * @param application
     * @return
     */
    @GetMapping(value = "/getMemberApplicationByLoginName")
    public MemberApplication getMemberApplicationByLoginName(@RequestParam(value = "loginName") String loginName,
                                                             @RequestParam(value = "application") Integer application){
        return memberApplicationService.getMemberApplication(null, loginName, application);
    }

    /**
     * 获取客户列表
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/getMemberApplicationList")
    public Result getMemberApplicationList(@RequestBody MemberDTO memberDTO){
        Result result = Result.success();
        List<Map<String, Object>> list = memberApplicationService.getMemberApplicationList(memberDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", PaginationUtil.pageTotalQuery(list));
        result.setData(data);
        return result;
    }

    /**
     * 编辑客户资料
     * @param memberApplicationVO
     * @return
     * @author df
     */
    @PostMapping(value = "/updateMemberApplication")
    public String updateMemberApplication(@RequestBody MemberApplicationVO memberApplicationVO){
        logger.info("subject:{},memberDTO:{}","编辑客户资料",JsonUtil.gsonStr(memberApplicationVO));
        try{
            if(memberApplicationService.updateMemberApplication(memberApplicationVO) > 0){
                return Result.success("成功").toJSON();
            }else{
                return Result.failure("编辑客户资料失败").toJSON();
            }
        }catch (Exception e){
            logger.error("编辑客户资料异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 通过memberId查询客户资料
     * @param memberId
     * @return
     * @author df
     */
    @PostMapping(value = "/getMemberApplicationById/{memberId}")
    public Result getMemberApplicationById(@PathVariable("memberId") int memberId){
        Map<String, Object> data = new HashMap<>();
        data.put("memberApplicationVO",memberApplicationService.getMemberApplicationById(memberId));
        return Result.success(data);
    }

    /**
     * 身份证认证校验
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/checkIdentityCertificate")
    public Result checkIdentityCertificate(@RequestBody MemberApplicationVO memberApplicationVO){
        Member member = memberService.selectByPrimaryKey(memberApplicationVO.getId());
        if (member == null){
            return Result.failure("认证失败，客户不存在");
        }
        if (null != member.getWhetherRealName() && CodeConstant.HAD == member.getWhetherRealName()){
            return Result.failure("认证失败，您已认证");
        }
        member = memberService.getMemberByIdCard(memberApplicationVO.getIdCard());
        if (member != null && member.getStatus() != MemberConstant.MEMBER_LOGOFF_STATUS){
            return Result.failure("认证失败，该身份证号被占用");
        }
        return Result.success();
    }

    /**
     * 驾驶证认证校验
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/checkDriversLicenseCertificate")
    public Result checkDriversLicenseCertificate(@RequestBody MemberApplicationVO memberApplicationVO){
        Member member = memberService.selectByPrimaryKey(memberApplicationVO.getId());
        if (member == null){
            return Result.failure("认证失败，客户不存在");
        }
        if (null != member.getDriversLicenseCertificate() && CodeConstant.HAD == member.getDriversLicenseCertificate()){
            return Result.failure("认证失败，您已认证");
        }
        if (!memberApplicationVO.getDriversLicense().equals(member.getIdCard())){
            return Result.failure("认证失败，驾照证号和身份证号不一致");
        }
        if (!memberApplicationVO.getDriversLicenseName().equals(member.getRealName())){
            return Result.failure("认证失败，驾照姓名和身份证姓名不一致");
        }
        return Result.success();
    }

    /**
     * 认证客户
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/certificateMember")
    public Result certificateMember(@RequestBody MemberApplicationVO memberApplicationVO,
                                    @RequestParam(value = "isIdentity") int isIdentity){
        logger.info("客户认证，memberApplicationVO: {}; isIdentity: {}", memberApplicationVO, isIdentity);
        //添加文件
        List<MemberFile> memberFileList = memberApplicationVO.getFileList();
        if (memberFileList != null && memberFileList.size() > 0) {
            memberFileList.parallelStream().forEach(memberFile -> {
                memberFile.setMemberId(memberApplicationVO.getId());
                memberFile.setStatus(CodeConstant.NORMAL_STATUS);
                memberFile.setCreateTime(new Date());
                memberFile.setUpdateTime(new Date());
            });
            memberFileService.batchAddMemberFile(memberApplicationVO.getFileList());
        }

        switch (isIdentity){
            case MemberConstant.IDENTITY_CERTIFICATE:
                //更新客户身份证认证
                return memberService.updateCertificateByPrimaryKeySelective(memberApplicationVO) <= 0 ?
                        Result.failure("身份认证异常，客户不存在或者已认证") : Result.success();
            case MemberConstant.DRIVERS_LICENSE_CERTIFICATE:
                //更新客户驾驶证认证
                return memberService.updateDriversLicenseCertificate(memberApplicationVO) <= 0 ?
                        Result.failure("驾驶证认证异常，客户不存在或者已认证") : Result.success();
            default:
                return Result.failure("认证异常，未找到认证类型");
        }
    }
}
