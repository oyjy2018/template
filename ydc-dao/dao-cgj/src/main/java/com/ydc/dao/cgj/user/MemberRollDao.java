package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.MemberRollDto;
import com.ydc.model.cgj.MemberRoll;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MemberRollDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    int insert(MemberRoll record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    int insertSelective(MemberRoll record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    MemberRoll selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    int updateByPrimaryKeySelective(MemberRoll record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    int updateByPrimaryKey(MemberRoll record);

    /**
     * 根据type获取用户券列表
     * @param memberRollDto
     * @return
     */
    List<Map<String, Object>> getMemberRollByType(@Param("memberRollDto") MemberRollDto memberRollDto);

    /**
     * 批量插入数据
     * @param memberRollList
     * @return
     */
    Integer batchInsert(@Param("memberRollList") List<MemberRoll> memberRollList);

    /**
     * 根据loanId获取券发送时间和总数
     * @param loanId
     * @return
     */
    Map<String, Object> getTimeInfoByLoanId(Integer loanId);

    /**
     * 获取用户优惠券列表
     * @param memberRollDto
     * @return
     */
    List<Map<String, Object>> getMemberRollList(@Param("memberRollDto") MemberRollDto memberRollDto);

    /**
     * 获取用户券
     * @param rollCodeList
     * @param rollStatus
     * @return
     */
    List<MemberRoll> getRollByCodeAndStatus(@Param("rollCodeList") List<String> rollCodeList,
                                            @Param("memberId") Integer memberId,
                                            @Param("rollStatus") Integer rollStatus);

    /**
     * 更新用户券状态
     * @param rollCode
     * @param rollStatus
     * @return
     */
    Integer updateRollStatusByStatus(@Param("rollCode") String rollCode,
                                     @Param("useTime") Date useTime,
                                     @Param("rollStatus") Integer rollStatus);

    /**
     * 根据type获取用户券列表
     * @param memberRollDto
     * @return
     */
    Integer getCountMemberRollByType(@Param("memberRollDto") MemberRollDto memberRollDto);

    /**
     * 批量更新用户券状态（是否有效）
     * @param rollCodes
     * @param status
     * @return
     */
    Integer batchUpdateMemberRollStatus(@Param("rollCodes") List<String> rollCodes, @Param("status") Integer status);
}