package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.model.cgj.Integral;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralDao {
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
    Integer insert(Integral record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    int insertSelective(Integral record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integral selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer updateByPrimaryKeySelective(Integral record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer updateByPrimaryKey(Integral record);

    /**
     * 积分列表
     *
     * @param integralManageDTO
     * @return
     */
    List<Map<String, Object>> getIntegralList(@Param("integralManageDTO") IntegralManageDTO integralManageDTO);

    /**
     * 积分充值
     *
     * @param seMap
     * @return
     */
    Integer saveOrUpdateIntegral(Map<String, Object> seMap);

    /**
     * 积分消耗
     *
     * @param seMap
     * @return
     */
    Integer updateIntegral(Map<String, Object> seMap);

    /**
     * 会员id会员积分
     *
     * @param memberId
     * @return
     */
    Integral getIntegralByMumberId(@Param("memberId") Integer memberId);

    /**
     * 批量新增积分
     * @param list
     */
    void insertBatch(List<Integral> list);

    /**
     * 批量更新积分
     * @param list
     */
    void updateBatch(List<Integral> list);
}