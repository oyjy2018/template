package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.commom.view.vo.cgj.ServiceReservationVO;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.MemberAppointment;
import com.ydc.model.cgj.Pagination;

import java.util.List;

public interface AppointmentOrderService {

    /**
     * 添加用户预约订单
     * @param createAppointmentParamVO
     * @param memberId
     * @return
     */
    MemberAppointment addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO, Integer memberId) throws Exception;

    /**
     * 添加用户预约订单
     * @param createAppointmentParamVO
     * @return
     * @throws Exception
     */
    MemberAppointment addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO) throws Exception;

    /**
     *  查询用户预约
     * @param memberId
     * @param appointStatus
     * @param pagination
     * @return
     */
    Result getMemberAppointment(Integer memberId,Integer appointStatus,Pagination pagination);

    /**
     * 查询用户预约详情
     * @param memberId
     * @param orderNo
     * @return
     */
    Result getMemberAppointmentDetail(Integer memberId,String orderNo);

    /**
     * 取消用户预约
     * @param memberId
     * @param orderNo
     * @return
     */
    Result cancelMemberAppointment(Integer memberId,String orderNo,String reason);

    /**
     * 查看券码
     * @param memberId
     * @param orderNo
     * @return
     */
    Result queryCouponCode(Integer memberId,String orderNo);


    /**
     * 服务预约列表
     * @param serviceReservationDTO
     * @return
     */
    List<ServiceReservationVO> getServiceReservationList(ServiceReservationDTO serviceReservationDTO);

    /**
     * 根据orderCode更新预约订单B端信息
     * @param memberAppointment
     * @return
     */
    Integer updateAppointBInfo(MemberAppointment memberAppointment);


    /**
     * 根据B端订单状态更新 本地库订单状态
     * @param bOrderResponseDTOS
     * @param memberId
     * @return
     */
    Integer updateAppointStatusByBAppointStatus(List<BOrderResponseDTO> bOrderResponseDTOS, Integer memberId);
}