package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import com.ydc.model.cgj.rental.RentalOrderMaintenance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RentalOrderMaintenanceDao {
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
     * @param record
     */
    int insert(RentalOrderMaintenanceInsertDTO record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int insertSelective(RentalOrderMaintenance record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    RentalOrderMaintenance selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int updateByPrimaryKeySelective(RentalOrderMaintenance record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-21 20:25:20
     */
    int updateByPrimaryKey(RentalOrderMaintenance record);

    /**
     * 机务单列表
     * @param rentalOrderMaintenanceDTO
     * @return
     */
    List<RentalOrderMaintenanceVO> getMaintenanceOrderList(@Param(value = "rentalOrderMaintenanceDTO") RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO);

    /**
     * 根据主键查询VO
     * @param id
     * @return
     */
    RentalOrderMaintenanceVO selectVOByPrimaryKey(Integer id);
    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    Map<String, Object> selectMaintenanceOrderDrawOut(Integer id);

    /**
     * 根据车辆id获取机务单id(最新的一条)
     *
     * @param carId
     * @return
     */
    Map getMaintenanceOrderIdByCarId(Integer carId);

    /**
     * 插入机务单
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    int insertMaintenanceOrder(RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO);

    /**
     * 根据订单id查询租车订单信息
     * @param orderId
     * @return
     */
    Map getCarInfo(@Param("orderId") Integer orderId);

}