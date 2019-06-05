package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.appointment.AppointmentCouponDTO;
import com.ydc.model.cgj.MemberAppointment;
import com.ydc.model.cgj.MemberAppointmentRoll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberAppointmentRollDao {
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
    int insert(MemberAppointmentRoll record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int insertSelective(MemberAppointmentRoll record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    MemberAppointmentRoll selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKeySelective(MemberAppointmentRoll record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKey(MemberAppointmentRoll record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer batchInsert(@Param("list") List<MemberAppointmentRoll> list);

    /**
     * 根据预约ID 查询预约券
     * @param map
     * @return
     */
    List<AppointmentCouponDTO> selectMemberAppointCouponByOrderId(Map<String,Object> map);

    /**
     *  根据预约ID查询
     * @param appointId
     * @return
     */
    List<MemberAppointmentRoll> selectByAppointId(Integer appointId);

    /**
     * 根据属性查询
     * @param map
     * @return
     */
    List<MemberAppointmentRoll> selectByProperty(Map<String,Object> map);


    /**
     * 根据属性查询
     * @param map
     * @return
     */
    List<Map<String,Object>> selectMapByAppointIdAndCouponNo(Map<String,Object> map);


}