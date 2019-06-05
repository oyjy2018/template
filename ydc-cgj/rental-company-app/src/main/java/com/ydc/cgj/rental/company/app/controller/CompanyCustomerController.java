package com.ydc.cgj.rental.company.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.rental.company.app.common.CompanyUtil;
import com.ydc.cgj.rental.company.app.service.CompanyCustomerService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ShiroConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.ValidateCode;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业登陆认证
 *
 * @author df
 */
@RestController
@RequestMapping(value = "/companyCustomer")
public class CompanyCustomerController {

    private static final Logger logger = LogManager.getLogger(CompanyCustomerController.class);


    @Autowired
    private CompanyCustomerService companyCustomerService;

    /**
     * 发送验证码
     * 验证码类型（1：注册；2：修改密码；3：登录；4：企业注册；5：企业登录）
     *
     * @param data
     * @return
     * @throws Exception
     * @author df
     */
    @PostMapping(value = "/sendValidateCode")
    public String sendValidateCode(@RequestParam(value = "data") String data) throws Exception{
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        Integer validateType = jsonObject.getInteger("validateType");
        return companyCustomerService.sendValidateCode(mobilePhone, validateType, MemberConstant.APPLICATION_RENTAL).toJSON();
    }

    /**
     * 企业客户登录
     *
     * @param data
     * @return
     * @author df
     */
    @PostMapping(value = "/login")
    public String Login(@RequestParam("data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("mobilePhone");
        String validateCode = jsonObject.getString("validateCode");
        if (StringUtil.isEmpty(validateCode)) {
            return Result.failure("登录失败，验证码不能为空").toJSON();
        }
        //验证码校验
        Result checkResult = this.checkValidateCode(mobilePhone, ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_6.getKey(), validateCode);
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
            //添加sign到结果中
            Map<String, Object> map = loginResult.getData();
            map.put("sign", checkResult.getData());
            loginResult.setData(map);
        }
        return loginResult.toJSON();

    }

    /**
     * 登录方法
     * @param mobilePhone
     * @return
     */
    private Result<Map<String, Object>> executeLogin(String mobilePhone) {
        Result<Map<String, Object>> result = Result.getResult();
        Subject subject = SecurityUtils.getSubject();
        try {
            RentalCompanyCustomerVO rentalCompanyCustomerVO = companyCustomerService.getCompanyCustomerByMobilePhone(mobilePhone);
            UsernamePasswordToken token = new UsernamePasswordToken(JsonUtil.gsonStr(rentalCompanyCustomerVO), "");
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败
            result.setCode(ResultConstant.RESULT_CODE_FAILURE);
            switch (e.getMessage()) {
                case ShiroConstant.NO_MEMBER_FAILURE_LOGIN:
                    result.setCode(ResultConstant.LOGIN_FAILURE_416);
                    result.setMessage("登录失败，手机号不存在");
                    break;
                case ShiroConstant.PASSWORD_FAILURE_LOGIN:
                    result.setMessage("登录失败，手机号或密码错误");
                    break;
                case ShiroConstant.MEMBER_LOGOFF_FAILURE_LOGIN:
                    result.setCode(ResultConstant.LOGIN_FAILURE_411);
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
        RentalCompanyCustomerVO rentalCompanyCustomerVO = CompanyUtil.getCompanyCustomer();
        result.setCode(ResultConstant.RESULT_CODE_SUCCESS);
        result.setMessage("登录成功");
        Map<String, Object> data = new HashMap<>();
        data.put("authorization", SecurityUtils.getSubject().getSession().getId());
        if (rentalCompanyCustomerVO != null) {
            data.put("id", rentalCompanyCustomerVO.getId());
            data.put("registeredPhone", rentalCompanyCustomerVO.getRegisteredPhone());
            data.put("registeredCompanyName", rentalCompanyCustomerVO.getRegisteredCompanyName());
        }
        result.setData(data);
        return result;
    }

    /**
     * 校验用户是否存在
     * @param mobilePhone
     * @return
     */
    private Result checkMobliePhone(String mobilePhone){
        if(StringUtil.isEmpty(mobilePhone)){
            return Result.failure("手机号码为空");
        }
        RentalCompanyCustomerVO rentalCompanyCustomerVO = companyCustomerService.getCompanyCustomerByMobilePhone(mobilePhone);
        if(rentalCompanyCustomerVO == null){
            return Result.failure("手机号码不存在");
        }
        if(rentalCompanyCustomerVO.getDeleteStatus()==(byte)CodeConstant.DISABLE_STATUS){
            return Result.failure("账号已被禁用");
        }
        return Result.success();
    }

    /**
     * 校验验证码
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     * @throws Exception
     */
    private Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) throws Exception {
        logger.info("subject:{}, mobilePhone:{}, validateType:{}, validateCode:{}","校验验证码", mobilePhone, validateType, validateCode);
        Result result = companyCustomerService.checkValidateCode(mobilePhone, validateType, validateCode);
        if (ResultConstant.RESULT_CODE_SUCCESS == result.getCode()) {
            String sign = SystemUtil.getUUID();
            RedisUtil.redisSet(RedisConstant.SIGNVALIDATECODE_SIGN.concat(mobilePhone), sign, 600L);
            result.setData(sign);
        }
        return result;
    }

    /**
     * 注册
     *
     * @param data
     * @return
     * @author df
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/doRegister")
    public String doRegister(@RequestParam(value = "data") String data) throws Exception {
        //获取参数
        JSONObject jsonObject = JSON.parseObject(data);
        String mobilePhone = jsonObject.getString("registeredPhone").replace(" ", "");
        String registeredCompanyName = StringUtil.filterSymbols(jsonObject.getString("registeredCompanyName"));
        String registeredCompanyAddress = StringUtil.filterSymbols(jsonObject.getString("registeredCompanyAddress"));
        String validateCode = jsonObject.getString("validateCode");

        //校验验证码
        Result checkResult = this.checkValidateCode(mobilePhone,ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_5.getKey(), validateCode);
        if (!(ResultConstant.RESULT_CODE_SUCCESS == checkResult.getCode())) {
            return checkResult.toJSON();
        }

        //校验用户是否注册
        Result result = this.checkMobliePhone(mobilePhone);
        if(result.getCode() == Result.success().getCode()){
            //登录
            Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone);
            if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
                loginResult.setMessage("账号已存在");
                //添加sign到结果中
                Map<String, Object> map = loginResult.getData();
                map.put("sign", checkResult.getData());
                loginResult.setData(map);
                return loginResult.toJSON();
            }
        }else{
            if("账号已被禁用".equals(result.getMessage())){
                return Result.failure("账号已被禁用").toJSON();
            }
            //检验注册公司名
            int nameNum = companyCustomerService.getCompanyCustomerByCompanyName(registeredCompanyName);
            if(nameNum > 0){
                return Result.failure("注册公司名已存在").toJSON();
            }
            //注册
            String registerResult = this.executeRegister(mobilePhone,registeredCompanyName,registeredCompanyAddress);
            if (!(ResultConstant.RESULT_CODE_SUCCESS == (Integer)JsonUtil.jsonToMap(registerResult).get("code"))) {
                return registerResult;
            }
            //登录
            Result<Map<String, Object>> loginResult = this.executeLogin(mobilePhone);
            if (ResultConstant.RESULT_CODE_SUCCESS == loginResult.getCode()) {
                loginResult.setMessage("注册成功");
                //添加sign到结果中
                Map<String, Object> map = loginResult.getData();
                map.put("sign", checkResult.getData());
                loginResult.setData(map);
                return loginResult.toJSON();
            }
        }

        return Result.failure("注册成功，请前往登录").toJSON();
    }

    /**
     * 注册方法
     * @param mobilePhone
     * @param registeredCompanyName
     * @param registeredCompanyAddress
     * @return
     */
    private String executeRegister(String mobilePhone, String registeredCompanyName, String registeredCompanyAddress) {
        RentalCompanyCustomer rentalCompanyCustomer = new RentalCompanyCustomer();
        rentalCompanyCustomer.setRegisteredPhone(mobilePhone);
        rentalCompanyCustomer.setRegisteredCompanyName(registeredCompanyName);
        rentalCompanyCustomer.setRegisteredCompanyAddress(registeredCompanyAddress);
        rentalCompanyCustomer.setAuthenticationStatus((byte)(0));//未认证状态
        return companyCustomerService.addCompanyCustomer(rentalCompanyCustomer);
    }

    /**
     * 用户退出
     * @return
     * @author df
     */
    @PostMapping("/doLogout")
    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return Result.success().toJSON();
    }

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param data
     * @return
     * @author df
     */
    @PostMapping(value = "/getCompanyCustomerById")
    public String getCompanyCustomerById(@RequestParam("data") String data) {
        Map dataMap = JsonUtil.jsonToMap(data);
        Integer id  = (Integer) dataMap.get("id");
        if (id == null) {
            return Result.failure("企业客户id为空").toJSON();
        }
        return companyCustomerService.getCompanyCustomerById(id);
    }

    /**
     * 企业认证
     *
     * @param data
     * @return
     * @author df
     */
    @PostMapping(value = "/enterpriseCertification")
    public String enterpriseCertification(@RequestParam("data") String data) {
        RentalCompanyCustomer rentalCompanyCustomer = JSONObject.parseObject(data, RentalCompanyCustomer.class);
        if(StringUtil.isEmpty(rentalCompanyCustomer.getName())||rentalCompanyCustomer.getName().length()>8){
            return Result.failure("联系人姓名不能为空且最长为8个字符").toJSON();
        }
        if(StringUtil.isEmpty(rentalCompanyCustomer.getLegalPerson())||rentalCompanyCustomer.getLegalPerson().length()>8){
            return Result.failure("法人姓名不能为空且最长为8个字符").toJSON();
        }
        //认证日期
        rentalCompanyCustomer.setAuthenticationDate(new Date());
        //待审核状态
        rentalCompanyCustomer.setAuthenticationStatus((byte)1);
        //更新人
        rentalCompanyCustomer.setUpdateBy(rentalCompanyCustomer.getId());
        return companyCustomerService.updateCompanyCustomer(rentalCompanyCustomer);
    }

    /**
     * 个人中心
     *
     * @param data
     * @return
     * @author df
     */
    @PostMapping(value = "/personalCenter")
    public String personalCenter(@RequestParam("data") String data) {
        Map dataMap = JsonUtil.jsonToMap(data);
        Integer id  = (Integer) dataMap.get("id");
        if (id == null) {
            return Result.failure("企业客户id为空").toJSON();
        }
        return companyCustomerService.personalCenter(id);
    }
}
