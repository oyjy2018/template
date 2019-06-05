package com.ydc.cgj.rentalc.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.MemberUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.RSAKeyUtil;
import com.ydc.cgj.rentalc.app.common.SubjectUtil;
import com.ydc.cgj.rentalc.app.service.MemberApplicationService;
import com.ydc.cgj.rentalc.app.service.MemberLoginService;
import com.ydc.cgj.rentalc.app.service.MemberService;
import com.ydc.cgj.rentalc.app.service.ValidateCodeService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.enums.MemberEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.MD5Util;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberApplication;
import com.ydc.model.cgj.MemberLogin;
import com.ydc.model.cgj.ValidateCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author wangminghua
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberLoginService memberLoginService;

    @Autowired
    private MemberApplicationService memberApplicationService;

    /**
     * 注册
     *
     * @param data
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/doRegister")
    public String doRegister(@RequestParam(value = "data") String data) {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String validateCode = jsonObject.getString("validateCode");

        //校验验证码
        Result checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_1.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //注册
        Result registerResult = this.executeRegister(mobilePhone);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == registerResult.getCode())) {
            return registerResult.toJSON();
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "");
        if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
            //登录记录
            addMemberLogin(loginResult, MemberLogin.LoginTypeEnum.LOGIN_TYPE_ENUM_2.getKey(), MemberLogin.LoginSourceEnum.LOGIN_SOURCE_ENUM_1.getKey());
            loginResult.setMessage("注册成功");
            //添加sign到结果中
            Map<String, Object> map = loginResult.getData();
            map.put("sign", checkResult.getData());
            loginResult.setData(map);
            return loginResult.toJSON();
        }
        return Result.failure("注册成功，请前往登录").toJSON();
    }

    /**
     * 发送验证码
     *
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/sendValidateCode")
    public String sendValidateCode(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        return validateCodeService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_RENTAL).toJSON();
    }

    /**
     * 发送带有标记的验证码
     *
     * @param data
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @PostMapping(value = "/sendSignValidateCode")
    public String sendSignValidateCode(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        Result<Map<String, Object>> result = validateCodeService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_RENTAL);
        String sign = SystemUtil.getUUID();
        RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone).concat("_").concat(validateType.toString()), sign, 300L);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("sign", sign);
        result.setData(resultMap);
        return result.toJSON();
    }

    /**
     * 校验验证码
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/checkLoginValidateCode")
    public String checkLoginValidateCode(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        String validateCode = jsonObject.getString("validateCode");
        return this.checkValidateCode(mobilePhone, validateType, validateCode).toJSON();
    }

    /**
     * 校验密码
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/checkMemberPassword")
    public String checkMemberPassword(@RequestParam(value = "data") String data) {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String password = jsonObject.getString("password");
        Member member = SubjectUtil.getMember();
        Result result = memberService.checkMemberPassword(member.getId(), MD5Util.getPassword(member.getId(), password));
        if (result.getCode() == ResultConstant.RESULT_CODE_SUCCESS) {
            String sign = SystemUtil.getUUID();
            RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(member.getMobilePhone()), sign, 600L);
            result.setData(sign);
        }
        return result.toJSON();
    }

    /**
     * 用户密码登录
     *
     * @param data
     * @return
     */
    @PostMapping("/doPasswordLogin")
    public String doPasswordLogin(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String password = jsonObject.getString("password");

        if (StringUtil.isEmpty(mobilePhone)) {
            return Result.failure("登录失败，用户名不能为空").toJSON();
        }
        if (StringUtil.isEmpty(password)) {
            return Result.failure("登录失败，密码不能为空").toJSON();
        }
        //校验密码错误次数
        Object object = RedisUtil.redisGet(RedisConstant.RENTAL_PASSWORD_FAULT_KEY.concat(mobilePhone));
        if (object != null && (int) object >= ShiroConstant.PASSWORD_FAULT_TIMES) {
            return Result.failure("密码连续输入错误，账户已被锁定").toJSON();
        }
        Result<Map<String, Object>> result = this.executeLogin(mobilePhone, password);
        if (ResultConstant.RESULT_CODE_SUCCESS == result.getCode()) {
            //登录记录
            addMemberLogin(result, MemberLogin.LoginTypeEnum.LOGIN_TYPE_ENUM_2.getKey(), MemberLogin.LoginSourceEnum.LOGIN_SOURCE_ENUM_1.getKey());
        }
        return result.toJSON();
    }

    /**
     * 用户验证码登录
     *
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping("/doValidateLogin")
    @SuppressWarnings("unchecked")
    public String doValidateLogin(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String validateCode = jsonObject.getString("validateCode");
        if (StringUtil.isEmpty(validateCode)) {
            return Result.failure("登录失败，验证码不能为空").toJSON();
        }
        //验证码校验
        Result checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_3.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //校验用户是否注册
        if (ResultConstant.RESULT_CODE_SUCCESS == memberService.checkMobileIsRegister(mobilePhone, MemberConstant.APPLICATION_RENTAL).getCode()) {
            //注册
            this.executeRegister(mobilePhone);
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "");
        if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
            //登录记录
            addMemberLogin(loginResult, MemberLogin.LoginTypeEnum.LOGIN_TYPE_ENUM_1.getKey(), MemberLogin.LoginSourceEnum.LOGIN_SOURCE_ENUM_1.getKey());
            //添加sign到结果中
            Map<String, Object> map = loginResult.getData();
            map.put("sign", checkResult.getData());
            loginResult.setData(map);
        }
        return loginResult.toJSON();
    }

    /**
     * 修改密码
     *
     * @param data
     * @return
     */
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam(value = "data") String data) {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer memberId = jsonObject.getInteger("memberId");
        String password = jsonObject.getString("password");
        String updateSign = jsonObject.getString("updateSign");
        if (StringUtil.isEmpty(password)) {
            return Result.failure("密码不能为空").toJSON();
        }
        Object sign = RedisUtil.redisGet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone));
        //校验sign
        if (sign == null || updateSign == null || !updateSign.equals(sign.toString())) {
            return Result.failure("设置失败，请重新获取验证码尝试").toJSON();
        }
        MemberApplication memberApplication = memberApplicationService.getMemberApplicationByMobilePhone(mobilePhone, MemberConstant.APPLICATION_RENTAL);
        if (memberApplication == null) {
            return Result.failure("用户不存在").toJSON();
        }
        //用户id如果为null，则用数据库的用户id
        if (memberId == null || memberId == 0) {
            memberId = memberApplication.getMemberId();
        }
        //校验密码是否与原密码一致
        if (MD5Util.getPassword(memberId, password).equals(memberApplication.getMemberPassword())) {
            return Result.failure("新密码不能等于原密码").toJSON();
        }
        //修改密码
        return memberService.memberUpdatePassword(memberId, mobilePhone, MD5Util.getPassword(memberId, password)).toJSON();
    }

    /**
     * 用户未登录
     *
     * @return
     */
    @RequestMapping("/notLogin")
    public String notLogin() {
        Result result = Result.getResult();
        result.setCode(ResultConstant.NOT_LOGIN_FAILURE);
        result.setMessage("您未登录，请先登录");
        return result.toJSON();
    }

    /**
     * 错误输出
     *
     * @return
     */
    @RequestMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response ) {
        try {
            Object object=request.getAttribute("ServiceRuntimeException");
            if (object!=null){
                ServiceRuntimeException serviceRuntimeException= (ServiceRuntimeException) object;
                return Result.exception(serviceRuntimeException.getCode(),serviceRuntimeException.getMessage()).toJSON();
            }
        }catch (Exception e){
            return  Result.exception("网络竟然崩溃了").toJSON();
        }
        return  Result.exception("网络竟然崩溃了").toJSON();
    }

    /**
     * 用户退出
     *
     * @return
     */
    @PostMapping("/doLogout")
    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return Result.success().toJSON();
    }

    //登录方法
    private Result<Map<String, Object>> executeLogin(String mobilePhone, String password) {
        Result<Map<String, Object>> result = Result.getResult();
        if (StringUtil.isEmpty(mobilePhone)) {
            result.setCode(ResultConstant.RESULT_CODE_FAILURE);
            result.setMessage("登录失败，手机号码不能为空");
            return result;
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(mobilePhone, password);
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败
            result.setCode(ResultConstant.RESULT_CODE_FAILURE);
            switch (e.getMessage()) {
                case ShiroConstant.NO_MEMBER_FAILURE_LOGIN:
                    result.setMessage("登录失败，手机号不存在");
                    break;
                case ShiroConstant.PASSWORD_FAILURE_LOGIN:
                    result.setMessage("登录失败，手机号或密码错误");
                    break;
                case ShiroConstant.MEMBER_LOGOFF_FAILURE_LOGIN:
                    result.setMessage("登录失败，当前账户已被注销，请联系客服");
                    break;
                case ShiroConstant.MEMBER_LOCKED_FAILURE_LOGIN:
                    result.setMessage("登录失败，当前账户已被锁定，请联系客服");
                    break;
                default:
                    result.setMessage("网络异常，请稍后尝试");
                    break;
            }
            return result;
        }
        //登录成功
        //删除登录失败次数
        RedisUtil.remove(RedisConstant.RENTAL_PASSWORD_FAULT_KEY.concat(mobilePhone));
        Member member = SubjectUtil.getMember();
        result.setCode(ResultConstant.RESULT_CODE_SUCCESS);
        result.setMessage("登录成功");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", subject.getSession().getId());
        if (member != null) {
            data.put("memberName", member.getMemberName());
            data.put("mobilePhone", member.getMobilePhone());
            data.put("isIdentityCertificate", member.getWhetherRealName() == null ? CodeConstant.NOT : member.getWhetherRealName());
            data.put("isDriversCertificate", member.getDriversLicenseCertificate() == null ? CodeConstant.NOT : member.getDriversLicenseCertificate());
        }
        result.setData(data);
        return result;
    }

    //登录记录
    private void addMemberLogin(Result<Map<String, Object>> result, Integer loginType, Integer loginSource) {
        Map<String, Object> map = result.getData();
        //登录记录
        MemberLogin memberLogin = new MemberLogin();
        Date date = new Date();
        memberLogin.setUserId((Integer) map.get("memberId"));
        memberLogin.setLoginType(loginType);
        memberLogin.setLoginSource(loginSource);
        memberLogin.setLoginToken((String) map.get("authorization"));
        memberLogin.setMemberApplication(MemberConstant.APPLICATION_RENTAL);
        memberLogin.setInvaildTime(DateUtil.dateAdd(date, Calendar.DAY_OF_MONTH, 30));
        memberLogin.setLastLoginTime(date);
        memberLogin.setStatus(CodeConstant.NORMAL_STATUS);
        memberLogin.setCreateTime(date);
        memberLogin.setUpdateTime(date);
        memberLoginService.memberLoginAdd(memberLogin);
    }

    //校验验证码
    private Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        logger.info("subject:{}, mobilePhone:{}, validateType:{}, validateCode:{}","校验验证码", mobilePhone, validateType, validateCode);
        Result result = validateCodeService.checkValidateCode(mobilePhone, validateType, validateCode);
        if (ResultConstant.RESULT_CODE_SUCCESS == result.getCode()) {
            String sign = SystemUtil.getUUID();
            RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone), sign, 600L);
            result.setData(sign);
        }
        return result;
    }

    //注册方法
    private Result executeRegister(String mobilePhone) {
        MemberApplicationVO memberApplicationVO = new MemberApplicationVO();
        memberApplicationVO.setMobilePhone(mobilePhone);
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);
        memberApplicationVO.setSource(Member.SourceEnum.SOURCE_ENUM_2.getKey());
        memberApplicationVO.setWhetherRealName(MemberEnum.WhetherRealNameStatus.status0.getStatus());
        memberApplicationVO.setStatus(CodeConstant.NORMAL_STATUS);
        memberApplicationVO.setCreateTime(new Date());
        memberApplicationVO.setUpdateTime(new Date());
        return memberService.memberRegister(memberApplicationVO);
    }

    /**
     * 获取加密公钥
     * @return
     */
    @PostMapping(value = "/getLoadPublicKey")
    public String getLoadPublicKey(){
        Map<String,Object> data = new HashMap<>();
        data.put("publicKey",RSAKeyUtil.getPublicKey());
        return Result.success(data).toJSON();
    }

    @PostMapping(value = "/logout")
    public String logout(String data){
        String mobilePhone = JSON.parseObject(data).getString("mobilePhone");
        MemberUtil.memberLogout(mobilePhone);
        return null;
    }
}
