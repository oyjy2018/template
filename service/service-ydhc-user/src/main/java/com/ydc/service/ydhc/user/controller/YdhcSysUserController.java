package com.ydc.service.ydhc.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.model.ydhc.YdhcDd;
import com.ydc.model.ydhc.YdhcLogLogin;
import com.ydc.model.ydhc.YdhcSysUser;
import com.ydc.service.ydhc.user.service.YdhcDdService;
import com.ydc.service.ydhc.user.service.YdhcLogLoginService;
import com.ydc.service.ydhc.user.service.YdhcSysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ydhcSysUser")
public class YdhcSysUserController {

    private static final Logger logger = LogManager.getLogger(YdhcSysUserController.class);

    @Autowired
    private YdhcSysUserService ydhcSysUserService;
    @Autowired
    private YdhcDdService ydhcDdService;
    @Autowired
    private YdhcLogLoginService ydhcLogLoginService;

    /**
     * 更新用户
     * @param ydhcSysUser
     * @return
     */
    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestBody YdhcSysUser ydhcSysUser) {
        logger.info("更新用户:"+ydhcSysUser);
        return Result.success(ydhcSysUserService.updateByPrimaryKeySelective(ydhcSysUser)).toJSON();
    }

    /**
     * loginName查询用户
     * @param loginName
     * @return
     */
    @PostMapping(value = "/getUserByLoginName")
    public YdhcSysUser getUserByLoginName(@RequestParam(value = "loginName") String loginName) {
        logger.info("loginName查询用户:"+loginName);
        return ydhcSysUserService.getUserByLoginName(loginName);
    }

    /**
     * mobilePhone查询有效用户
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/getUserByMobilePhoneNoStatus")
    public YdhcSysUser getUserByMobilePhoneNoStatus(@RequestParam(value = "mobilePhone") String mobilePhone){
        logger.info("mobilePhone查询有效用户:"+mobilePhone);
        return ydhcSysUserService.getUserByMobilePhoneNoStatus(mobilePhone);
    }

    /**
     * 获取钉钉配置
     * @return
     */
    @PostMapping(value = "/getDdConfig")
    public YdhcDd getDdConfig(){
        try {
            logger.info("请求user /getDdConfig");
            return ydhcDdService.getDdConfig();
        }catch (Exception e){
            logger.info("请求user /getDdConfig"+ e.getMessage());
        }
        return null;
    }

    /**
     * 保存登录日志
     * @param record
     */
    @PostMapping(value = "/saveLogLogin")
    public String saveLogLogin(@RequestBody YdhcLogLogin record){
        logger.info("保存登录日志:"+record);
        ydhcLogLoginService.insert(record);
        return Result.success().toJSON();
    }

}
