package com.ydc.cgj.rental.web.controller;

import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.UserService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 平台人员相关
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询人员列表
     * @param data
     * @return
     */
    @PostMapping("/getUserList")
    public String getUserList(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询人员列表",data);
        UserQueryDTO userQueryDTO = JsonUtil.jsonToBean(data,UserQueryDTO.class);
        return userService.getUserList(userQueryDTO);
    }

    /**
     * 查询人员详情
     * @param data
     * @return
     */
    @PostMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","查询人员详情",data);
        Map dataMap = JsonUtil.jsonToMap(data);
        if (StringUtil.isEmpty(dataMap.get("id"))){
            return Result.failure("员工id为空").toJSON();
        }
        Integer id = (Integer) dataMap.get("id");
        return userService.getUserInfo(id);
    }

    /**
     * 修改人员
     * @param data
     * @return
     */
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","修改人员",data);
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, UserUpdateDTO.class);
        if ("1".equals(vaildMap.get("code"))){
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        UserUpdateDTO userUpdateDTO = (UserUpdateDTO) vaildMap.get("object");
        //获取登录用户
        User user = WebShiroTokenManager.getUser();
        userUpdateDTO.setUpdateBy(user.getId());//设置修改人
        if (StringUtil.isEmpty(userUpdateDTO.getEntryDate())){
            userUpdateDTO.setEntryDate(null);
        }
        if (StringUtil.isEmpty(userUpdateDTO.getLeaveDate())){
            userUpdateDTO.setLeaveDate(null);
        }
        return userService.updateUser(userUpdateDTO);
    }

    /**
     * 增加人员
     * @param data
     * @return
     */
    @PostMapping("/insertUser")
    public String insertUser(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","增加人员",data);
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, UserInsertDTO.class);
        if ("1".equals(vaildMap.get("code"))){
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        UserInsertDTO userInsertDTO = (UserInsertDTO) vaildMap.get("object");
        //获取登录用户
        User user = WebShiroTokenManager.getUser();
        userInsertDTO.setCreateBy(user.getId());//设置新增人
        userInsertDTO.setUpdateBy(user.getId());//设置修改人


        if (StringUtil.isEmpty(userInsertDTO.getEntryDate())){
            userInsertDTO.setEntryDate(null);
        }
        if (StringUtil.isEmpty(userInsertDTO.getLeaveDate())){
            userInsertDTO.setLeaveDate(null);
        }
        return userService.insertUser(userInsertDTO);
    }

    /**
     * 获取机构权限树及已选机构
     * @param data
     * @return
     */
    @PostMapping("/getViewOrgTreeAndChecked")
    public String getViewOrgTreeAndChecked(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","获取机构权限树及已选机构",data);
        Map dataMap = JsonUtil.jsonToMap(data);
        Integer userId = StringUtil.isEmpty(dataMap.get("userId"))?null:(Integer.parseInt(dataMap.get("userId").toString()));
        return userService.getViewOrgTreeAndChecked(userId);
    }

    /**
     * 获取所有岗位
     * @return
     */
    @PostMapping("/getAllJobName")
    public String getAllJobName(){
        logger.info("subject:{}","获取所有岗位");
        return  "获取所有岗位";
    }
}
