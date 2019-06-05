package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

public interface MemberApplicationDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    int insert(MemberApplication record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    int insertSelective(MemberApplication record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    MemberApplication selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    int updateByPrimaryKeySelective(MemberApplication record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 09:37:42
     */
    int updateByPrimaryKey(MemberApplication record);

    /**
     * 获取客户应用信息
     * @param mobilePhone
     * @param loginName
     * @return
     */
    MemberApplication getMemberApplication(@Param(value = "mobilePhone") String mobilePhone,
                                           @Param(value = "loginName") String loginName,
                                           @Param(value = "application") Integer application);

    /**
     * 根据mobilePhone更新password
     *
     * @param mobilePhone
     * @param password
     * @return
     */
    Integer updatePasswordByMobilePhone(@Param("memberId") Integer memberId,
                                        @Param("mobilePhone") String mobilePhone,
                                        @Param("password") String password);

    /**
     * 更新状态
     * @param memberDTO
     * @return
     */
    Integer updateStatusByMemberId(@Param("memberDTO") MemberDTO memberDTO);

    /**
     * 获取客户列表
     * @param memberDTO
     * @return
     */
    List<Map<String, Object>> getMemberApplicationList(@Param("memberDTO") MemberDTO memberDTO);

    /**
     * 通过id查询客户资料
     * @param memberId
     * @return
     */
//    MemberApplicationVO getMemberApplicationById(@Param("memberId") int memberId);


    /**
     * 批量插入
     * @param list
     */
    void insertMemberApplicationBatch(List<MemberApplication> list);
}