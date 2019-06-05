package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.RSAKeyUtil;
import com.ydc.beans.shiro.web.ShiroAuthUtil;
import com.ydc.beans.shiro.web.WebShiroRealm;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rentalb.app.common.UserUtil;
import com.ydc.cgj.rentalb.app.constant.Constants;
import com.ydc.cgj.rentalb.app.service.ServiceUserRoleService;
import com.ydc.cgj.rentalb.app.service.UserService;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.ValidateCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author
 * @create 2018-11-19 13:58
 **/
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    @Autowired
    UserService userService;
    @Autowired
    WebShiroRealm webShiroRealm;
    @Autowired
    ServiceUserRoleService serviceUserRoleService;
    /**
     * 发送验证码
     * 验证码类型（1：注册；2：修改密码；3：登录）
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/sendValidateCode")
    public String sendValidateCode(@RequestParam(value = "data") String data) {
        logger.info("subject:{},data:{}","发送验证码",data);
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        Result result = this.checkMobliePhone(mobilePhone);
        if(result.getCode() == Result.failure().getCode()){
            return Result.failure(result.getMessage()).toJSON();
        }
        result = this.auditMobilePhone(mobilePhone);
        if(result.getCode() == ResultConstant.RESULT_CODE_SUCCESS) return result.toJSON();
        return userService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_RENTAL).toJSON();
    }

    /**
     * 校验验证码
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/checkLoginValidateCode")
    public String checkLoginValidateCode(@RequestParam(value = "data") String data) {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        String validateCode = jsonObject.getString("validateCode");
        Result result = this.checkMobliePhone(mobilePhone);
        if(result.getCode() == Result.failure().getCode()){
            return Result.failure(result.getMessage()).toJSON();
        }
        result = this.auditMobilePhone(mobilePhone);
        if(result.getCode() == ResultConstant.RESULT_CODE_SUCCESS) return result.toJSON();
        return this.checkValidateCode(mobilePhone, validateType, validateCode).toJSON();
    }
    //校验用户是否存在
    private Result checkMobliePhone(String mobilePhone){
        if(StringUtil.isEmpty(mobilePhone)){
            return Result.failure("手机号码为空");
        }
        return userService.getUserByMobilePhoneNoStatus(mobilePhone) == null ? Result.failure("手机号码不存在") : Result.success();
    }

    //校验验证码
    private Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        logger.info("subject:{}, mobilePhone:{}, validateType:{}, validateCode:{}","校验验证码", mobilePhone, validateType, validateCode);
        Result result = userService.checkValidateCode(mobilePhone, validateType, validateCode);
        if (ResultConstant.RESULT_CODE_SUCCESS == result.getCode()) {
            String sign = SystemUtil.getUUID();
            RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone), sign, 600L);
            result.setData(sign);
        }
        return result;
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

    /**
     * 用户验证码登录
     *
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping("/doValidateLogin")
    @SuppressWarnings("unchecked")
    public String doValidateLogin(@RequestParam(value = "data") String data, HttpServletRequest request){
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String validateCode = jsonObject.getString("validateCode");
        if (StringUtil.isEmpty(validateCode)) {
            return Result.failure("登录失败，验证码不能为空").toJSON();
        }
        //验证码校验
        Result checkResult = this.auditMobilePhone(mobilePhone);
        if(checkResult.getCode() == ResultConstant.RESULT_CODE_FAILURE){
            checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_3.getKey(), validateCode);
        }
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }
        //校验用户是否注册
        Result result = this.checkMobliePhone(mobilePhone);
        if(result.getCode() == Result.failure().getCode()){
            return Result.failure(result.getMessage()).toJSON();
        }
        //登录
        Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone);
        if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
            //登录记录
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    User user = UserUtil.getUser();
                    userService.saveLogLogin(user.getId(),user.getUserName(),user.getLoginName(),null,request);
                }
            });
            //添加sign到结果中
            Map<String, Object> map = loginResult.getData();
            map.put("sign", checkResult.getData());
            loginResult.setData(map);
        }
        return loginResult.toJSON();
    }
    //登录方法
    private Result<Map<String, Object>> executeLogin(String mobilePhone) {
        Result<Map<String, Object>> result = Result.getResult();
        try {
            User user = userService.getUserByMobilePhoneNoStatus(mobilePhone);
            final ServiceUserRole serviceUserRole = serviceUserRoleService.getServiceUserRoleByUserId(user.getId());
            user.setRoleId(serviceUserRole == null ? null : serviceUserRole.getRentalRoleId());
            user.setServiceIdentifying(Constants.SERVICEIDENTIFYING);

            WebShiroTokenManager.login(user);
            webShiroRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
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
        User user = UserUtil.getUser();
        result.setCode(ResultConstant.RESULT_CODE_SUCCESS);
        result.setMessage("登录成功");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", SecurityUtils.getSubject().getSession().getId());
        if (user != null) {
            data.put("userId", user.getId());
            data.put("userName", user.getUserName());
            data.put("mobilePhone", user.getMobilePhone());
        }
        result.setData(data);
        return result;
    }

    /**
     * 审核测试账号登录
     * @param mobilePhone
     * @return
     */
    private Result auditMobilePhone(String mobilePhone){
        if("16688888888".equals(mobilePhone)){
            return Result.success("成功");
        }
        return Result.failure("失败");
    }

    /**
     * 角色权限版本
     * @return
     */
    @PostMapping(value = "/role/version")
    public String getRoleVersion(){
        Map<String,Object> data = new HashMap<>();
        data.put("version",ShiroAuthUtil.getRoleVersion());
        return Result.success(data).toJSON();
    }

    /**
     * 角色权限
     * @return
     */
    @PostMapping(value = "/role/responsePermissions")
    public String responsePermissions(){
        User user = null;
        try {
            user = WebShiroTokenManager.getUser();
            Map<String,List<String>> listMap = ShiroAuthUtil.responsePermissionsB(user.getRoleId(),user.getServiceIdentifying(),"B");
            logger.info("subject:{},listMap:{}","角色权限",JsonUtil.gsonStr(listMap));
            return Result.success(listMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},userName:{},e:{}","角色权限异常",user.getUserName(),e);
            return Result.failure().toJSON();
        }
    }
}
