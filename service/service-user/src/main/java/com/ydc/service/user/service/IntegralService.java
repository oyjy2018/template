package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.model.cgj.Integral;
import com.ydc.model.cgj.entity.IntegralEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-04 19:10
 **/
public interface IntegralService {

    /**
     * 积分列表
     *
     * @param integralManageDTO
     * @return
     */
    List<Map<String, Object>> getIntegralList(IntegralManageDTO integralManageDTO);

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
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer insert(Integral record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integral selectByPrimaryKey(Integer id);

    /**
     * 积分充值逻辑处理
     *
     * @param integralManageDTO
     */
    void logicIntergralRecharge(IntegralManageDTO integralManageDTO) throws Exception;

    /**
     * 会员id会员积分
     *
     * @param memberId
     * @return
     */
    Integral getIntegralByMumberId(Integer memberId);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    Integer updateByPrimaryKey(Integral record);

    /**
     * 积分批量充值
     * @param list
     */
    void batchRecharge(List<IntegralEntity> list) throws Exception;

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
