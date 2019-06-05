package com.ydc.service.user.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.commom.view.vo.cgj.MemberVO;
import com.ydc.model.cgj.Member;

import java.util.List;

/**
 * 会员
 *
 * @author gongjin
 * @create 2018-09-05 10:39
 **/
public interface MemberService {

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer insert(Member record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Member selectByPrimaryKey(Integer id);

    /**
     * 根据手机号和密码获取用户
     *
     * @param mobilePhone
     * @param password
     * @return
     */
    Member selectByMobileAndPassword(String mobilePhone, String password);

    /**
     * 根据手机号获取用户
     *
     * @param mobilePhone
     * @return
     */
    Member selectByMobilePhone(String mobilePhone);

    /**
     * 根据mobilePhone更新password
     *
     * @param memberId
     * @param mobilePhone
     * @param password
     * @return
     */
    Integer updatePasswordByMobilePhone(Integer memberId, String mobilePhone, String password);

    /**
     * 校验用户是否已注册
     * @param mobilePhone
     * @return
     */
    Result checkMobileIsRegister(String mobilePhone, Integer application);


    /**
     * 会员列表
     * @param memberDTO
     * @return
     */
    List<MemberVO> getMemberList(MemberDTO memberDTO);

    /**
     * 查询正常会员
     * @param mobilePhone
     * @return
     */
    Member getMemberByMobilePhone(String mobilePhone);

    /**
     * 查询会员
     * @param idCard
     * @return
     */
    Member getMemberByIdCard(String idCard);

    /**
     * 更新会员状态
     * @param memberDTO
     * @return
     */
    Integer updateMemberStatus(MemberDTO memberDTO);

    /**
     * 注册用户
     * @param memberApplicationVO
     * @return
     */
    Integer registerMember(MemberApplicationVO memberApplicationVO);

    /**
     * 更新会员
     * @param member
     * @return
     */
    Integer updateByPrimaryKeySelective(Member member);

    /**
     * 更新用户认证状态
     * @param member
     * @return
     */
    Integer updateCertificateByPrimaryKeySelective(Member member);

    /**
     * 会员身份认证
     * @param memberApplicationVO
     * @return
     */
    Result rentalOrderMember(MemberApplicationVO memberApplicationVO);

    /**
     * 新增租车订，身份证认证通过，更新实名认证状态
     * @param memberApplicationVO
     * @return
     */
    Integer updateMemberWhetherRealNameStatus(MemberApplicationVO memberApplicationVO);

    /**
     * 更新用户驾驶证认证状态
     * @param record
     * @return
     */
    Integer updateDriversLicenseCertificate(Member record);

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
