package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.appointment.MemberAppointmentDTO;
import com.ydc.commom.view.dto.cgj.appointment.MemberAppointmentDetailDTO;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import com.ydc.commom.view.vo.cgj.ServiceReservationVO;
import com.ydc.model.cgj.MemberAppointment;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface MemberAppointmentDao {
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
    int insert(MemberAppointment record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int insertSelective(MemberAppointment record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    MemberAppointment selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKeySelective(MemberAppointment record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    int updateByPrimaryKey(MemberAppointment record);

    /**
     * 根据状态查询会员预约服务
     * @param map
     * @return
     */
    List<MemberAppointmentDTO> selectMemberAppointByStatus(Map<String,Object> map);

    /**
     * 查询会员预约服务详细信息
     * @param map
     * @return
     */
    MemberAppointmentDetailDTO selectMemberAppointDetail(Map<String,Object> map);

    /**
     * 根据订单号查询
     * 参数:查询条件,订单号
     * 返回:对象
     */
    MemberAppointment selectByOrderNo(String orderNo);


    /**
     * 根据B端订单号查询
     * 参数:查询条件,订单号
     * 返回:对象
     */
    MemberAppointment selectByBOrderNo(String bOrderNo);

    /**
     * 服务预约列表
     * @param serviceReservationDTO
     * @return
     */
    List<ServiceReservationVO> getServiceReservationList(@Param("serviceReservationDTO") ServiceReservationDTO serviceReservationDTO);

    /**
     * 根据orderCode更新预约订单B端信息
     * @param memberAppointment
     * @return
     */
    Integer updateAppointBInfo(@Param("memberAppointment") MemberAppointment memberAppointment);
}