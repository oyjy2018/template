package com.ydc.cgj.app.service;

import com.ydc.commom.enums.ReservationStatusEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.Pagination;

public interface MemberAppointmentService {

    /**
     * 添加用户预约订单
     * @param createAppointmentParamVO
     * @return
     */
    Result addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO, Integer memberId);

    /**
     * 查询用户预约
     * @param memberId
     * @param statusEnum
     * @param page
     * @return
     */
    Result queryMemberAppointment(Integer memberId, ReservationStatusEnum statusEnum, Pagination page);


    /**
     * 查询用户预约 详细信息
     * @param memberId
     * @param orderNo
     * @return
     */
    Result queryMemberAppointmentDetail(Integer memberId, String orderNo);

    /**
     * 取消预约
     * @param memberId
     * @param orderNo
     * @param reason
     * @return
     */
    Result cancelMemberAppointment(Integer memberId, String orderNo,String reason);


    /**
     * 查看券码
     * @param memberId
     * @param orderNo
     * @return
     */
    Result queryCouponCode(Integer memberId, String orderNo);

}
