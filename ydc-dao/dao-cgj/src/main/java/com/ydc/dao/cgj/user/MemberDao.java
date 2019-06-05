package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberVO;
import com.ydc.model.cgj.Member;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MemberDao {
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
    Integer insert(Member record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int insertSelective(Member record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Member selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int updateByPrimaryKey(Member record);

    /**
     * 根据手机号和密码获取用户
     *
     * @param mobilePhone
     * @param password
     * @return
     */
    Member selectByMobileAndPassword(@Param("mobilePhone") String mobilePhone, @Param("password") String password);

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
     * 根据会员id和会员手机号码查询会员信息
     * @param memberId
     * @param mobilePhone
     * @return
     */
    Member getMemberByIdAndmobilePhone(@Param("memberId") Integer memberId, @Param("mobilePhone") String mobilePhone);

    /**
     * 会员列表
     * @param memberDTO
     * @return
     */
    List<MemberVO> getMemberList(@Param("memberDTO") MemberDTO memberDTO);

    /**
     * 查询会员
     * @param mobilePhone
     * @return
     */
    Member getMemberByMobilePhone(@Param("mobilePhone") String mobilePhone);

    /**
     * 查询会员
     * @param idCard
     * @return
     */
    Member getMemberByIdCard(@Param("idCard") String idCard);

    /**
     * 更新会员状态
     * @param memberDTO
     * @return
     */
    Integer updateMemberStatus(@Param("memberDTO") MemberDTO memberDTO);

    /**
     * 放款客户批量新增会员
     * @param list
     */
    void insertMemberBatch(List<Member> list);

    /**
     * 更新用户身份认证状态
     * @param record
     * @return
     */
    Integer updateCertificateByPrimaryKeySelective(Member record);

    /**
     * 更新用户驾驶证认证状态
     * @param record
     * @return
     */
    Integer updateDriversLicenseCertificate(Member record);

    Integer updateFirstAppointmentDate(@Param(value = "memberId") Integer memberId,
                                       @Param(value = "firstAppointmentTime") Date firstAppointmentTime);

    /**
     * 根据微信的openId获取用户
     * @param openId
     * @return
     */
    Member getMemberByOpenId(String openId);

    /**
     * 根据手机号码更新微信信息
     * @param member
     * @return
     */
    Integer updateWeixinInfoByMobilePhone(Member member);
}