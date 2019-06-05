package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.commom.WeiXinUtil;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.ydc.beans.commom.WeiXinUtil;
import com.ydc.beans.redis.MemberUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.RSAKeyUtil;
import com.ydc.beans.weiXinPay.MyWXPayConfig;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.MemberApplicationService;
import com.ydc.cgj.app.service.MemberLoginService;
import com.ydc.cgj.app.service.MemberService;
import com.ydc.cgj.app.service.ValidateCodeService;
import com.ydc.commom.constant.*;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.urlHttp.DefaultCallBack;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.*;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.*;
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
import java.util.*;

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
        String weiXinCode = jsonObject.getString("weiXinCode");
        String password = jsonObject.getString("password");

        //校验验证码
        Result checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_1.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //注册
        Result registerResult = this.executeRegister(mobilePhone, password);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == registerResult.getCode())) {
            return registerResult.toJSON();
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "", weiXinCode);
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
        return validateCodeService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_CGJ).toJSON();
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
        Result<Map<String, Object>> result = validateCodeService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_CGJ);
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
        String weiXinCode = jsonObject.getString("weiXinCode");

        if (StringUtil.isEmpty(mobilePhone)) {
            return Result.failure("登录失败，用户名不能为空").toJSON();
        }
        if (StringUtil.isEmpty(password)) {
            return Result.failure("登录失败，密码不能为空").toJSON();
        }
        //校验密码错误次数
        Object object = RedisUtil.redisGet(RedisConstant.CGJ_PASSWORD_FAULT_KEY.concat(mobilePhone));
        if (object != null && (int) object >= ShiroConstant.PASSWORD_FAULT_TIMES) {
            return Result.failure("密码连续输入错误，账户已被锁定").toJSON();
        }
        Result<Map<String, Object>> result = this.executeLogin(mobilePhone, password, weiXinCode);
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
        String weiXinCode = jsonObject.getString("weiXinCode");
        if (StringUtil.isEmpty(validateCode)) {
            return Result.failure("登录失败，验证码不能为空").toJSON();
        }
        //验证码校验
        Result checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_3.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //校验用户是否注册
        if (ResultConstant.RESULT_CODE_SUCCESS == memberService.checkMobileIsRegister(mobilePhone, MemberConstant.APPLICATION_CGJ).getCode()) {
            //注册
            Result registerResult = this.executeRegister(mobilePhone);
            if (!(ResultConstant.RESULT_CODE_SUCCESS == registerResult.getCode())){
                return registerResult.toJSON();
            }
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "", weiXinCode);
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
        MemberApplication memberApplication = memberApplicationService.getMemberApplicationByMobilePhone(mobilePhone, MemberConstant.APPLICATION_CGJ);
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
    private Result<Map<String, Object>> executeLogin(String mobilePhone, String password, String weiXinCode) {
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
                    result.setMessage("登录失败，密码连续输入错误，账户已被锁定");
                    break;
                default:
                    result.setMessage("网络异常，请稍后尝试");
                    break;
            }
            return result;
        }
        //登录成功
        //删除登录失败次数
        RedisUtil.remove(RedisConstant.CGJ_PASSWORD_FAULT_KEY.concat(mobilePhone));
        final Member member = SubjectUtil.getMember();
        result.setCode(ResultConstant.RESULT_CODE_SUCCESS);
        result.setMessage("登录成功");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", subject.getSession().getId());
        if (member != null) {
            data.put("memberId", member.getId());
            data.put("memberName", member.getMemberName());
            data.put("mobilePhone", member.getMobilePhone());
            data.put("hasPassword", StringUtil.isNotEmpty(member.getPassword()));
            //微信内就获取用户的openId（weiXinCode不为空）
            if (StringUtil.isNotEmpty(weiXinCode)){
                new Thread(() -> {
                    Map<String, Object> openIdResult = WeiXinUtil.getMemberOpenId(weiXinCode);
                    logger.info("subject: {}, weiXinCode: {}, openIdResult: {}", "获取用户openId结果", weiXinCode, openIdResult);
                    String openId = openIdResult == null ? null : (String) openIdResult.get("openid");
                    Member tempMember = new Member();
                    tempMember.setMobilePhone(member.getMobilePhone());
                    tempMember.setWeixinPayOpenId(openId);
                    memberService.updateWeixinInfoByMobilePhone(tempMember);
                }).start();
            }
            data.put("hasPassword", StringUtil.isNotEmpty(member.getPassword()));
            data.put("needBindingPhone", 0);
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
        memberLogin.setMemberApplication(MemberConstant.APPLICATION_CGJ);
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

    //无密码注册
    private Result executeRegister(String mobilePhone) {
        return executeRegister(mobilePhone, null, null);
    }

    //短信注册方法
    private Result executeRegister(String mobilePhone, String password) {
       return executeRegister(mobilePhone, password, null);
    }

    //微信注册方法
    private Result executeRegister(String mobilePhone, WeiXinUser weiXinUser){
        return executeRegister(mobilePhone, null, weiXinUser);
    }

    private Result executeRegister(String mobilePhone, String password, WeiXinUser weiXinUser) {
        if (StringUtil.isEmpty(mobilePhone)){
            return Result.failure("参数错误");
        }
        MemberApplicationVO memberApplicationVO = new MemberApplicationVO();
        memberApplicationVO.setMobilePhone(mobilePhone);
        memberApplicationVO.setPassword(password);
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_CGJ);
        memberApplicationVO.setSource(Member.SourceEnum.SOURCE_ENUM_2.getKey());
        memberApplicationVO.setStatus(CodeConstant.NORMAL_STATUS);
        memberApplicationVO.setCreateTime(new Date());
        memberApplicationVO.setUpdateTime(new Date());
        if (weiXinUser != null){
            memberApplicationVO.setWeixinOpenId(weiXinUser.getOpenid());
            memberApplicationVO.setWeixinUnionId(weiXinUser.getUnionid());
            memberApplicationVO.setWeixinNickName(StringUtil.removeNonBmpUnicode(weiXinUser.getNickname()));
            memberApplicationVO.setWeixinProvince(weiXinUser.getProvince());
            memberApplicationVO.setWeixinCity(weiXinUser.getCity());
            memberApplicationVO.setHeadPicture(weiXinUser.getHeadimgurl());
            memberApplicationVO.setGender(1 == weiXinUser.getSex() ? "男" : "女");
        }
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

    /**
     * 微信授权登录
     * @param data
     * @return
     */
    @PostMapping(value = "/doWeixinLogin")
    public String doWeixinLogin(@RequestParam(value = "data") String data){
        WeiXinUser weiXinUser = JSON.parseObject(data).getObject("weixinUserinfo", WeiXinUser.class);
        if (weiXinUser == null){
            return Result.failure("参数错误").toJSON();
        }

        //用户没有注册，则要用户去绑定手机号码
        Member member = memberService.getMemberByOpenId(weiXinUser.getOpenid());
        if (member == null || StringUtil.isEmpty(member.getMobilePhone())){
            Result result = Result.success();
            Map<String, Object> map = new HashMap<>(2);
            map.put(MemberConstant.WEIXIN_OPENID, weiXinUser.getOpenid());
            map.put("needBindingPhone", 1);
            result.setData(map);
            return result.toJSON();
        }
        //用户已注册
        String mobilePhone = member.getMobilePhone();
        if (ResultConstant.RESULT_CODE_SUCCESS == memberService.checkMobileIsRegister(mobilePhone, MemberConstant.APPLICATION_CGJ).getCode()) {
            //用户注册车管家
            this.executeRegister(mobilePhone, weiXinUser);
        }
        //更新用户微信信息
        updateWeixinInfoByMobilePhone(mobilePhone, weiXinUser);
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "", weiXinUser.getOpenid());
        if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
            //登录记录
            addMemberLogin(loginResult, MemberLogin.LoginTypeEnum.LOGIN_TYPE_ENUM_1.getKey(), MemberLogin.LoginSourceEnum.LOGIN_SOURCE_ENUM_1.getKey());
        }
        return loginResult.toJSON();
    }

    /**
     * 绑定手机号码
     * @param data
     * @return
     */
    @PostMapping(value = "/bindMemberPhone")
    public String bindMemberPhone(@RequestParam(value = "data") String data){
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String validateCode = jsonObject.getString("validateCode");
        WeiXinUser weiXinUser = JSON.parseObject(data).getObject("weixinUserinfo", WeiXinUser.class);
        //验证码校验
        Result checkResult = validateCodeService.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_1.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        Result isRegisterResult = memberService.checkMobileIsRegister(mobilePhone, MemberConstant.APPLICATION_CGJ);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == isRegisterResult.getCode())){
            //会员存在则更新微信信息
            updateWeixinInfoByMobilePhone(mobilePhone, weiXinUser);
        }else {
            //会员不存在则注册会员
            this.executeRegister(mobilePhone, weiXinUser);
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone, "", weiXinUser.getOpenid());
        if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
            //登录记录
            addMemberLogin(loginResult, MemberLogin.LoginTypeEnum.LOGIN_TYPE_ENUM_1.getKey(), MemberLogin.LoginSourceEnum.LOGIN_SOURCE_ENUM_1.getKey());
        }
        return loginResult.toJSON();
    }

    private Integer updateWeixinInfoByMobilePhone(String mobilePhone, WeiXinUser weiXinUser){
        Member member = new Member();
        member.setMobilePhone(mobilePhone);
        member.setWeixinOpenId(weiXinUser.getOpenid());
        member.setWeixinUnionId(weiXinUser.getUnionid());
        member.setWeixinNickName(StringUtil.removeNonBmpUnicode(weiXinUser.getNickname()));
        member.setWeixinProvince(weiXinUser.getProvince());
        member.setWeixinCity(weiXinUser.getCity());
        member.setHeadPicture(weiXinUser.getHeadimgurl());
        member.setGender(1 == weiXinUser.getSex() ? "男" : "女");
        return memberService.updateWeixinInfoByMobilePhone(member);
    }
}
