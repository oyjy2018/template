package com.ydc.service.user.service.impl;

import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.MD5Util;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.commom.view.vo.cgj.MemberVO;
import com.ydc.dao.cgj.common.MemberFileDao;
import com.ydc.dao.cgj.user.MemberApplicationDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberApplication;
import com.ydc.model.cgj.MemberFile;
import com.ydc.service.user.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员
 *
 * @author gongjin
 * @create 2018-09-05 10:39
 **/
@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LogManager.getLogger(MemberService.class);

    @Resource
    MemberDao memberDao;

    @Resource
    private MemberApplicationDao memberApplicationDao;

    @Resource
    private MemberFileDao memberFileDao;

    @Override
    public Integer insert(Member record) {
        return memberDao.insert(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberDao.selectByPrimaryKey(id);
    }

    @Override
    public Member selectByMobilePhone(String mobilePhone) {
        return memberDao.selectByMobileAndPassword(mobilePhone, null);
    }

    @Override
    public Member selectByMobileAndPassword(String mobilePhone, String password) {
        return memberDao.selectByMobileAndPassword(mobilePhone, password);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result checkMobileIsRegister(String mobilePhone, Integer application) {
        MemberApplication memberApplication = memberApplicationDao.getMemberApplication(mobilePhone, null, application);
        if (memberApplication == null){
            return Result.success();
        }

        Result result = Result.failure("手机号已注册");
        Map<String, Object> map = new HashMap<>();
        map.put("hasPassword", true);
        result.setData(map);
        if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOGOFF_STATUS){
            result.setMessage("当前账户已被注销，请联系客服");
            return result;
        }
        if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOCKED_STATUS){
            result.setMessage("当前账户已被锁定，请联系客服");
            return result;
        }
        if (StringUtil.isEmpty(memberApplication.getMemberPassword())){
            result.setMessage("您在一点车已有账户，只需设置密码即可享受车主服务");
            map.put("hasPassword", false);
            return result;
        }
        return result;
    }

    @Override
    public List<MemberVO> getMemberList(MemberDTO memberDTO) {
        return PaginationUtil.paginationQuery(memberDTO ,(tempMemberDTO) -> memberDao.getMemberList(memberDTO));
    }

    @Override
    public Member getMemberByMobilePhone(String mobilePhone) {
        return memberDao.getMemberByMobilePhone(mobilePhone);
    }

    @Override
    public Member getMemberByIdCard(String idCard) {
        return memberDao.getMemberByIdCard(idCard);
    }

    @Override
    public Integer updateMemberStatus(MemberDTO memberDTO) {
        memberApplicationDao.updateStatusByMemberId(memberDTO);
        return memberDao.updateMemberStatus(memberDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public Integer updatePasswordByMobilePhone(Integer memberId, String mobilePhone, String password) {
        memberApplicationDao.updatePasswordByMobilePhone(memberId, mobilePhone, password);
        return memberDao.updatePasswordByMobilePhone(memberId, mobilePhone, password);
    }

    @Override
    public Integer registerMember(MemberApplicationVO memberApplicationVO) {
        // 添加member信息
        Member member = memberDao.getMemberByMobilePhone(memberApplicationVO.getMobilePhone());
        if (member == null){
            member = memberApplicationVO;
            memberDao.insert(member);
        }

        //设置密码
        if (StringUtil.isNotEmpty(memberApplicationVO.getPassword())){
            member.setPassword(MD5Util.getPassword(member.getId(), memberApplicationVO.getPassword()));
            memberDao.updateByPrimaryKeySelective(member);
        }

        // 添加客户文件信息
        List<MemberFile> fileList = memberApplicationVO.getFileList();
        if (fileList != null && fileList.size() > 0) {
            for (MemberFile memberFile : fileList) {
                memberFile.setMemberId(member.getId());
                memberFile.setStatus(CodeConstant.NORMAL_STATUS);
                memberFile.setCreateTime(new Date());
                memberFile.setUpdateTime(new Date());
            }
            memberFileDao.batchAddMemberFile(fileList);
        }

        // 添加 memberApplication信息
        MemberApplication memberApplication = new MemberApplication();
        memberApplication.setMemberId(member.getId());
        memberApplication.setMemberName(memberApplicationVO.getMemberName());
        memberApplication.setMemberLoginName(memberApplicationVO.getMemberLoginName());
        memberApplication.setMemberPhone(member.getMobilePhone());
        memberApplication.setMemberPassword(member.getPassword());
        memberApplication.setMemberStatus(memberApplicationVO.getStatus());
        memberApplication.setApplication(memberApplicationVO.getApplication());
        memberApplication.setStatus(CodeConstant.NORMAL_STATUS);
        memberApplication.setCreateTime(memberApplicationVO.getCreateTime());
        memberApplication.setUpdateTime(memberApplicationVO.getUpdateTime());
        memberApplication.setUpdateBy(memberApplicationVO.getUpdateBy());
        return memberApplicationDao.insert(memberApplication);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Member member) {
        return memberDao.updateByPrimaryKeySelective(member);
    }

    @Override
    public Integer updateCertificateByPrimaryKeySelective(Member member) {
        return memberDao.updateCertificateByPrimaryKeySelective(member);
    }

    @Override
    public Result rentalOrderMember(MemberApplicationVO memberApplicationVO) {
        logger.info("subject:{},memberApplicationVO:{}","租车身份验证:请求参数", JsonUtil.gsonStr(memberApplicationVO));
        Result result = Result.failure();
        MemberApplication memberApplication = memberApplicationDao.getMemberApplication(memberApplicationVO.getMobilePhone(), null, memberApplicationVO.getApplication());
        logger.info("subject:{},memberApplication:{}","租车身份验证", JsonUtil.gsonStr(memberApplication));
        Member member = selectByMobilePhone(memberApplicationVO.getMobilePhone());
        if (memberApplication == null){
            if(member == null){
                member = memberApplicationVO;
                member.setRealName(memberApplicationVO.getMemberName());    //真实姓名
                memberDao.insert(member);
                result.setMessage("新增会员，并进行实名认证");
            }else
            if(member.getWhetherRealName() == null
                    || member.getWhetherRealName() != CodeConstant.HAD){
                result.setMessage("会员已存在，并未实名认证");
            }
            // 添加 memberApplication信息
            memberApplication = new MemberApplication();
            memberApplication.setMemberId(member.getId());
            memberApplication.setMemberName(memberApplicationVO.getMemberName());
            memberApplication.setMemberLoginName(memberApplicationVO.getMemberLoginName());
            memberApplication.setMemberPhone(member.getMobilePhone());
            memberApplication.setMemberStatus(memberApplicationVO.getStatus());
            memberApplication.setApplication(memberApplicationVO.getApplication());
            memberApplication.setStatus(CodeConstant.NORMAL_STATUS);
            memberApplication.setCreateTime(memberApplicationVO.getCreateTime());
            memberApplication.setUpdateTime(memberApplicationVO.getUpdateTime());
            memberApplication.setUpdateBy(memberApplicationVO.getUpdateBy());
            memberApplicationDao.insert(memberApplication);
            result.setCode(CodeConstant.NOT);
            result.setData(member.getId());
            return result;
        }else{
            if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOGOFF_STATUS){
                result.setMessage("当前账户已被注销，请联系客服");
                return result;
            }
            if (memberApplication.getMemberStatus() == MemberConstant.MEMBER_LOCKED_STATUS){
                result.setMessage("当前账户已被锁定，请联系客服");
                return result;
            }
            if(member != null && StringUtil.isNotEmpty(member.getWhetherRealName()) && member.getWhetherRealName() != CodeConstant.HAD){
                result.setCode(CodeConstant.NOT);
                result.setData(member.getId());
                return result;
            }
            result = Result.success();
            result.setCode(CodeConstant.HAD);
            result.setData(memberApplication.getMemberId());
            return result;
        }
    }

    @Override
    public Integer updateMemberWhetherRealNameStatus(MemberApplicationVO memberApplicationVO) {
        Member member = selectByPrimaryKey(memberApplicationVO.getId());
        member.setWhetherRealName(memberApplicationVO.getWhetherRealName());
        member.setUpdateBy(memberApplicationVO.getUpdateBy());
        return updateCertificateByPrimaryKeySelective(member);
    }

    @Override
    public Integer updateDriversLicenseCertificate(Member member) {
        return memberDao.updateDriversLicenseCertificate(member);
    }

    @Override
    public Member getMemberByOpenId(String openId) {
        return memberDao.getMemberByOpenId(openId);
    }

    @Override
    public Integer updateWeixinInfoByMobilePhone(Member member) {
        return memberDao.updateWeixinInfoByMobilePhone(member);
    }
}
