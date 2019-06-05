package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalPayWatercourseVO;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalPayWatercourseDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int insert(RentalPayWatercourse record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int insertSelective(RentalPayWatercourse record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    RentalPayWatercourse selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int updateByPrimaryKeySelective(RentalPayWatercourse record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int updateByPrimaryKey(RentalPayWatercourse record);

    /**
     * 结算流水
     * @param rentalPayWatercourseDTO
     * @return
     */
    List<RentalPayWatercourseVO> getPayWatercourseByOrderId(@Param("rentalPayWatercourseDTO") RentalPayWatercourseDTO rentalPayWatercourseDTO);

    /**
     * 根据多个流水类型查询结算流水
     * @param orderId
     * @param depositTypes
     * @return
     */
    List<RentalPayWatercourseVO> getPayWatercourseByDepositTypes(@Param("orderId") Integer orderId, @Param("depositTypes") String depositTypes);

    /**
     * 根据属性查询
     * @param record
     * @return
     */
    List<RentalPayWatercourse> selectByRentalPayWatercourse(RentalPayWatercourse record);


    /**
     * 流行批量新增
     * @param list
     */
    void batchInsert(List<RentalPayWatercourse> list);

}