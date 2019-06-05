package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.UserDetailVO;
import com.ydc.commom.view.vo.cgj.UserQueryVO;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.model.cgj.User;
import com.ydc.service.user.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author gongjin
 * @create 2018-09-05 14:51
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    DdService ddService;
    @Autowired
    LogLoginService logLoginService;
    @Autowired
    UserRoleFunctionService userRoleFunctionService;
    @Autowired
    ServiceUserRoleService serviceUserRoleService;

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestBody User user) {
        logger.info("更新用户:"+user);
        return Result.success(userService.updateByPrimaryKeySelective(user)).toJSON();
    }

    /**
     * loginName查询用户
     * @param loginName
     * @return
     */
    @PostMapping(value = "/getUserByLoginName")
    public User getUserByLoginName(@RequestParam(value = "loginName") String loginName) {
        logger.info("loginName查询用户:"+loginName);
        return userService.getUserByLoginName(loginName);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(@RequestBody User user) {
        logger.info("新增用户:"+user);
        return Result.success(userService.insert(user)).toJSON();
    }

    /**
     * mobilePhone查询有效用户
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/getUserByMobilePhoneNoStatus")
    public User getUserByMobilePhoneNoStatus(@RequestParam(value = "mobilePhone") String mobilePhone){
        logger.info("mobilePhone查询有效用户:"+mobilePhone);
        return userService.getUserByMobilePhoneNoStatus(mobilePhone);
    }

    /**
     * 获取钉钉配置
     * @return
     */
    @PostMapping(value = "/getDdConfigByServiceIdentifying")
    public Dd getDdConfigByServiceIdentifying(@RequestParam(value = "serviceIdentifying") String serviceIdentifying){
        try {
            logger.info("subject:{},serviceIdentifying:{}","获取钉钉配置",serviceIdentifying);
            return ddService.getDdConfigByServiceIdentifying(serviceIdentifying);
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取钉钉配置异常",e);
        }
        return null;
    }

    /**
     * 保存登录日志
     * @param record
     */
    @PostMapping(value = "/saveLogLogin")
    public String saveLogLogin(@RequestBody LogLogin record){
        logger.info("subject:{},record:{}","保存登录日志:",JsonUtil.gsonStr(record));
        logLoginService.insert(record);
        return Result.success().toJSON();
    }

    /**
     * 角色id查询功能code
     * @param roleId
     * @return
     */
    @PostMapping(value = "/getFunInfoById")
    public Set<String> getFunInfoById(@RequestParam("roleId") Integer roleId) {
        logger.info("subject:{},roleId:{}","角色id和服务标识查询角色",roleId);
        return userRoleFunctionService.getFunInfoById(roleId);
    }

    /**
     * 获取用户服务角色
     * @param userId
     * @return
     */
    @PostMapping(value = "/getServiceUserRoleByUserId")
    public ServiceUserRole getServiceUserRoleByUserId(@RequestParam("userId") Integer userId) {
        logger.info("subject:{},userId:{}","获取用户服务角色",userId);
        return serviceUserRoleService.getServiceUserRoleByUserId(userId);
    }

    /**
     * 更新用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/updateServiceUserRole")
    public int updateServiceUserRole(@RequestBody ServiceUserRole record) {
        logger.info("subject:{},record:{}","更新用户服务角色",JsonUtil.gsonStr(record));
        return serviceUserRoleService.updateServiceUserRole(record);
    }

    /**
     * 更新或新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/updateOrInsert")
    public int updateOrInsert(@RequestBody ServiceUserRole record) {
        logger.info("subject:{},record:{}","更新或新增用户服务角色",JsonUtil.gsonStr(record));
        return serviceUserRoleService.updateOrInsert(record);
    }

    /**
     * 新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/insertServiceUserRole")
    public int insertServiceUserRole(@RequestBody ServiceUserRole record) {
        logger.info("subject:{},record:{}","新增用户服务角色",JsonUtil.gsonStr(record));
        return serviceUserRoleService.insertServiceUserRole(record);
    }

    /**
     * 返回所有有效用户
     * @return
     */
    @PostMapping("/getValidUser")
    public String getValidUser(){
        return userService.getValidUser();
    }

    /**
     * 查询人员列表
     * @param userQueryDTO
     * @return
     */
    @PostMapping(value = "/getUserList")
    public String getUserList(@RequestBody UserQueryDTO userQueryDTO){
        logger.info("subject:{},userQueryDTO:{}","查询人员列表",JsonUtil.gsonStr(userQueryDTO));
        List<UserQueryVO> userList = userService.getUserList(userQueryDTO);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("totalCount", PaginationUtil.pageTotalQuery(userList));
        retMap.put("rows", userList);
        return Result.success(retMap).toJSON();
    }
    /**
     * 查询人员详情
     * @param id
     * @return
     */
    @PostMapping(value = "/getUserInfo")
    public String getUserInfo(@RequestParam("id") Integer id){
        logger.info("subject:{},id:{}","查询人员详情",id);
        UserDetailVO userDetailVO = userService.getUserInfo(id);
        return Result.success(userDetailVO).toJSON();
    }
    /**
     * 修改人员
     * @param userUpdateDTO
     * @return
     */
    @PostMapping(value = "/modifyUser")
    public String modifyUser(@RequestBody UserUpdateDTO userUpdateDTO){
        logger.info("subject:{},userUpdateDTO:{}","修改人员",JsonUtil.jsonStr(userUpdateDTO));
        return userService.updateUser(userUpdateDTO);
    }
    /**
     * 增加人员
     * @param userInsertDTO
     * @return
     */
    @PostMapping(value = "/insertUser")
    public String insertUser(@RequestBody UserInsertDTO userInsertDTO){
        logger.info("subject:{},userUpdateDTO:{}","新增人员",JsonUtil.jsonStr(userInsertDTO));
        return userService.insertUser(userInsertDTO);
    }

    /**
     * 获取机构权限树及已选机构
     * @param userId
     * @return
     */
    @PostMapping(value = "/getViewOrgTreeAndChecked")
    public String getViewOrgTreeAndChecked(@RequestParam(value = "userId",required = false) Integer userId){
        logger.info("subject:{},userId:{}","获取机构权限树及已选机构",userId);
        return userService.getViewOrgTreeAndChecked(userId);
    }
}
