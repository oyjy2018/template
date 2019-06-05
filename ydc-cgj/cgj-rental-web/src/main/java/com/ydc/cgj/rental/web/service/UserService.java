package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author
 * @create 2018-11-16 14:25
 **/
public interface UserService {


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    String updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(@RequestParam(value = "loginName") String loginName);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    String insert(User user);


    /**
     * 获取用户信息根据手机号码
     *
     * @param mobilePhone
     * @return
     */
    User getUserByMobilePhoneNoStatus(String mobilePhone);

    /**
     * 获取钉钉配置
     *
     * @return
     */
    Dd getDdConfigByServiceIdentifying(String serviceIdentifying);

    /**
     * 保存登录日志
     *
     * @param userId
     * @param userName
     * @param loginName
     * @param roleName
     * @param request
     * @return
     */
    String saveLogLogin(Integer userId, String userName, String loginName, String roleName, HttpServletRequest request);

    /**
     * 角色id和服务标识查询角色
     * @param roleId
     * @return
     */
    Set<String> getFunInfoById(Integer roleId);

    /**
     * 返回所有有效用户
     * @return
     */
    String getValidUser();

    /**
     * 查询人员列表
     * @param userQueryDTO
     * @return
     */
    String getUserList(UserQueryDTO userQueryDTO);

    /**
     * 查询人员详情
     * @param id
     * @return
     */
    String getUserInfo(Integer id);

    /**
     * 修改人员
     * @param userUpdateDTO
     * @return
     */
    String updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 增加人员
     * @param userInsertDTO
     * @return
     */
    String insertUser(UserInsertDTO userInsertDTO);

    /**
     * 获取机构权限树及已选机构
     * @param userId
     * @return
     */
    String getViewOrgTreeAndChecked(Integer userId);
}
