package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.UserDetailVO;
import com.ydc.commom.view.vo.cgj.UserQueryVO;
import com.ydc.model.cgj.User;

import java.util.List;

/**
 * 用户
 *
 * @author gongjin
 * @create 2018-09-05 14:49
 **/
public interface UserService {

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer updateByPrimaryKeySelective(User record);

    /**
     * 根据loginName查询用户
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer insert(User record);

    /**
     * 查询有效用户
     * @param mobilePhone
     * @return
     */
    User getUserByMobilePhoneNoStatus(String mobilePhone);

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
    List<UserQueryVO> getUserList(UserQueryDTO userQueryDTO);

    /**
     * 查询人员详情
     * @param id
     * @return
     */
    UserDetailVO getUserInfo(Integer id);

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
