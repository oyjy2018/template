package com.ydc.dao.cgj.service;

import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.model.cgj.BRollDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BRollDetailDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int insert(BRollDetail record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int insertSelective(BRollDetail record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    BRollDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKeySelective(BRollDetail record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKey(BRollDetail record);

    /**
     * 券明细
     * @param list
     */
    void insertRollDetailBatch(List<BRollDetail> list);

    /**
     * 获取有效空券张数
     * @return
     */
    List<Map<String, Object>> getBlankRollCount();

    /**
     * 根据类型获取一定数量的空券
     * @param rollType
     * @param num
     * @return
     */
    List<BRollDetail> getRollDetailTypeNum(@Param("rollType") Integer rollType, @Param("num") int num);

    /**
     * 更新空券c端是否发放状态
     * @param rollCodeList
     * @return
     */
    Integer updateRollDetailHasSend(@Param("rollCodeList") List<String> rollCodeList);

    /**
     * 批量更新券状态
     * @param rollCodes
     * @param status
     * @return
     */
    Integer batchUpdateTicketStatus(@Param("rollCodes") List<String> rollCodes, @Param("status") Integer status);

    /**
     * 获取空券列表
     * @param blankRollDto
     * @return
     */
    List<BRollDetail> getBlankRollList(@Param("blankRollDto") BlankRollDto blankRollDto);

    /**
     * 根据券码获取空券
     * @param rollCode
     * @return
     */
    BRollDetail getBlankRollByCode(String rollCode);

    /**
     * 根据券码获取空券
     * @param rollCodes
     * @return
     */
    List<BRollDetail> getBlankRollsByCode(@Param("rollCodes") List<String> rollCodes);
}