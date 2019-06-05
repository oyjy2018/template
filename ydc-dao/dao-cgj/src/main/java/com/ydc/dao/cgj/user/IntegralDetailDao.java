package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.commom.view.vo.cgj.order.IntegralDetailVO;
import com.ydc.model.cgj.IntegralDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralDetailDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    Integer insert(IntegralDetail record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int insertSelective(IntegralDetail record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    IntegralDetail selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKeySelective(IntegralDetail record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    int updateByPrimaryKey(IntegralDetail record);


    /**
     * 积分明细列表
     *
     * @param integralManageDTO
     * @return
     */
    List<IntegralDetailVO> getIntegralDetailList(@Param("integralManageDTO") IntegralManageDTO integralManageDTO);

    /**
     * 会员id会员积分明细
     *
     * @param memberId
     * @return
     */
    List<IntegralDetail> getIntegralDetailByMemberId(@Param("memberId") Integer memberId);

    /**
     * 批量新增积分明细
     * @param list
     */
    void insertBatch(List<IntegralDetail> list);

    /**
     * H5积分明细
     * @param integralDTO
     * @return
     */
    List<IntegralDetailVO> h5GetIntegralDetailList(@Param("integralDTO") IntegralDTO integralDTO);
}