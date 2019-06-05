package com.ydc.dao.cgj.common;

import com.ydc.model.cgj.MemberFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberFileDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    int insert(MemberFile record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    int insertSelective(MemberFile record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    MemberFile selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    int updateByPrimaryKeySelective(MemberFile record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-19 10:33:35
     */
    int updateByPrimaryKey(MemberFile record);

    /**
     * 批量添加客户文件
     * @param list
     * @return
     */
    Integer batchAddMemberFile(@Param(value = "list") List<MemberFile> list);

    /**
     * 通过会员id获取图片信息
     * @param memberId
     * @return
     */
    List<MemberFile> getMemberFileByMember(@Param("memberId") int memberId);

    /**
     * 根据用户id和类型更新
     * @param record
     * @return
     */
    int updateByMemberIdAndType(MemberFile record);

    /**
     * 通过客户id和类型获取图片信息
     * @param memberId
     * @param type
     * @return
     */
    List<MemberFile> getMemberFileByMemberIdAndType(@Param("memberId") Integer memberId,
                                                    @Param("type") String type);
}