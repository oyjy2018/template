package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentQueryVO;
import com.ydc.model.cgj.rental.RentalAccident;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RentalAccidentDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int insert(RentalAccident record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int insertSelective(RentalAccident record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    RentalAccident selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int updateByPrimaryKeySelective(RentalAccident record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int updateByPrimaryKey(RentalAccident record);

    /**
     * 根据处理事故单id获取事故信息
     * @param maintenanceOrderId
     * @return
     */
    List<Map<String, Object>> selectAccidentByMaintenanceId(Integer maintenanceOrderId);

    /**
     * 通过id查询事故详情
     * @param id
     * @return
     */
    Map<String, Object> getRentalAccidentById(@Param(value = "id") Integer id);

    /**
     * c查询所有符合条件的事故信息
     * @param rentalAccidentQueryDTO
     * @return
     */
    List<RentalAccidentQueryVO> getRentalAccidentList(RentalAccidentQueryDTO rentalAccidentQueryDTO);

    /**
     * 事故信息软删除
     * @param id
     * @return
     */
    int deleteRentalAccident(@Param("id") int id);

    /**
     * 根据用单类型为机务单和用单id查询事故
     *
     * @param id
     * @return
     */
    RentalAccident selectAccidentByMaintenanceOrderId(Integer id);
}