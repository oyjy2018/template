package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.UserDetailVO;
import com.ydc.commom.view.vo.cgj.UserQueryVO;
import com.ydc.model.cgj.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer insert(User record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int insertSelective(User record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer updateByPrimaryKeySelective(User record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int updateByPrimaryKey(User record);

    List<User> queryAll();

    /**
     * 根据loginName查询用户
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 查询有效用户
     * @param mobilePhone
     * @return
     */
    User getUserByMobilePhoneNoStatus(@Param("mobilePhone") String mobilePhone);

    /**
     * 返回所有有效用户
     * @return
     */
    List<User> getValidUser();

    /**
     * 查询人员列表
     * @param userQueryDto
     * @return
     */
    List<UserQueryVO> getUserList(UserQueryDTO userQueryDto);

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
    int updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 增加人员
     * @param userInsertDTO
     * @return
     */
    int insertUser(UserInsertDTO userInsertDTO);
}