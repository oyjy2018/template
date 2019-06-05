package com.ydc.cgj.ydhc.app.controller;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.ydhc.app.service.ValidateCodeService;
import com.ydc.cgj.ydhc.app.service.YdhcUserService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.beans.commom.WeiXinUtil;
import com.ydc.commom.view.dto.ydhc.LoginDto;
import com.ydc.commom.view.dto.ydhc.PublicReqDto;
import com.ydc.model.ydhc.YdhcUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    private YdhcUserService ydhcUserService;

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/sendValidateCode")
    public String sendValidateCode(String mobilePhone) throws Exception {
        logger.info("subject:{}, mobilePhone:{}", "发送验证码", mobilePhone);
        return validateCodeService.sendValidateCode(mobilePhone).toJSON();
    }

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    @GetMapping(value = "/checkLoginValidateCode")
    public String checkLoginValidateCode(String mobilePhone, Integer validateType, String validateCode) throws Exception {
        logger.info("subject:{}, mobilePhone:{}, validateType:{}, validateCode:{}", "校验验证码", mobilePhone, validateType, validateCode);
        return validateCodeService.checkValidateCode(mobilePhone, validateCode).toJSON();
    }

    /**
     * 用户验证码登录
     *
     * @param publicReqDto
     * @return
     * @throws Exception
     */
    @PostMapping("/doValidateLogin")
    @SuppressWarnings("unchecked")
    public String doValidateLogin(@RequestBody PublicReqDto publicReqDto) throws Exception {
        logger.info("subject:{}, data:{}", "用户验证码登录", publicReqDto.getData());
        if(StringUtil.isEmpty(publicReqDto.getData())){
            return Result.failure("登录失败，缺少登录数据").toJSON();
        }
        LoginDto loginDto = JsonUtil.jsonToBean(publicReqDto.getData(), LoginDto.class);
        if (StringUtil.isEmpty(loginDto.getMobilePhone())) {
            return Result.failure("登录失败，手机号码不能为空").toJSON();
        }
        if (StringUtil.isEmpty(loginDto.getMobilePhone())) {
            return Result.failure("登录失败，验证码不能为空").toJSON();
        }
        //验证码校验
        Result checkResult = validateCodeService.checkValidateCode(loginDto.getMobilePhone(), loginDto.getValidateCode());
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //校验用户是否注册
        if (ResultConstant.RESULT_CODE_SUCCESS != ydhcUserService.checkUserIsRegister(loginDto.getMobilePhone()).getCode()) {
            //注册
            this.executeRegister(loginDto);
        }else{
            YdhcUser ydhcUser = ydhcUserService.getUserByMobilePhone(loginDto.getMobilePhone());
            if(StringUtil.isEmpty(ydhcUser.getOpenid())){
                executeUpdate(ydhcUser,loginDto);
            }
        }
        //登录
        return this.executeLogin(loginDto.getMobilePhone(), "").toJSON();
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
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(mobilePhone, password);
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败
            result.setCode(ResultConstant.RESULT_CODE_FAILURE);
            result.setMessage("网络异常，请稍后尝试");
            return result;
        }
        //登录成功
        //删除登录失败次数
        RedisUtil.remove(RedisConstant.CGJ_PASSWORD_FAULT_KEY.concat(mobilePhone));
        Object principal = subject.getPrincipal();
        YdhcUser ydhcUser = principal instanceof YdhcUser ? (YdhcUser) principal : null;
        result.setCode(ResultConstant.RESULT_CODE_SUCCESS);
        result.setMessage("登录成功");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", subject.getSession().getId());
        if (ydhcUser != null) {
            data.put("userId", ydhcUser.getId());
            data.put("userName", ydhcUser.getUserName());
            data.put("mobilePhone", ydhcUser.getMobilePhone());
        }
        result.setData(data);
        return result;
    }

    //注册方法
    private Result executeRegister(LoginDto loginDto) {
        YdhcUser ydhcUser = new YdhcUser();
        ydhcUser.setHeadPicture(loginDto.getHeadPicture());
        ydhcUser.setUserName(loginDto.getUserName());
        ydhcUser.setMobilePhone(loginDto.getMobilePhone());
        ydhcUser.setIdCard(loginDto.getIdCard());
        ydhcUser.setGender(loginDto.getGender());
        ydhcUser.setAge(loginDto.getAge());
        ydhcUser.setEmail(loginDto.getEmail());
        ydhcUser.setOpenid(loginDto.getOpenid());
        ydhcUser.setHasSalesman(0);
        ydhcUser.setReferrerId(loginDto.getReferrerId());
        ydhcUser.setReferrerName(loginDto.getReferrerName());
        ydhcUser.setStatus(CodeConstant.NORMAL_STATUS);
        ydhcUser.setCreateTime(new Date());
        ydhcUser.setUpdateTime(new Date());
        return ydhcUserService.userRegister(ydhcUser);
    }

    /**
     * 更新用户
     * @param ydhcUser
     * @param loginDto
     * @return
     */
    private String executeUpdate(YdhcUser ydhcUser, LoginDto loginDto){
        ydhcUser.setOpenid(loginDto.getOpenid());
        ydhcUser.setHeadPicture(loginDto.getHeadPicture());
        ydhcUser.setUserName(loginDto.getUserName());
        ydhcUser.setMobilePhone(loginDto.getMobilePhone());
        ydhcUser.setIdCard(loginDto.getIdCard());
        ydhcUser.setGender(loginDto.getGender());
        ydhcUser.setAge(loginDto.getAge());
        ydhcUser.setEmail(loginDto.getEmail());
        ydhcUser.setOpenid(loginDto.getOpenid());
        ydhcUser.setUpdateTime(new Date());
        return ydhcUserService.updateYdhcUser(ydhcUser);
    }

    /**
     * 获取微信用户openid
     *
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getUserOpenid")
    public String getUserOpenid(@RequestBody Map<String, String> req) throws Exception {
        String code = req.get("code");
        logger.info("subject:{}, code:{}", "获取微信用户openid", code);
        Map<String, Object> res = WeiXinUtil.getSessionKeyAndOpenid(code);
        if(res != null && !res.containsKey("errcode")){
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("openid",res.get("openid"));
            return Result.success(jMap).toJSON();
        }else{
            String errMsg = "获取用户openid失败";
            if(res == null) errMsg += ",失败原因：访问微信接口异常";
            if(res != null) errMsg += ",错误code："+res.get("errcode").toString()+",错误消息："+res.get("errmsg").toString();
            return Result.failure(errMsg).toJSON();
        }
    }
}
