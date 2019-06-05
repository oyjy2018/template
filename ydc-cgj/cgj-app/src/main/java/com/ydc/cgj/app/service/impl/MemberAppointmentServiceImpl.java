package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.IMemberAppointmentService;
import com.ydc.cgj.app.service.MemberAppointmentService;
import com.ydc.commom.enums.ReservationStatusEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAppointmentServiceImpl implements MemberAppointmentService {

    @Autowired
    private IMemberAppointmentService iMemberAppointmentService;

    @Override
    public Result addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO, Integer memberId) {
        return iMemberAppointmentService.addMemberAppointment(createAppointmentParamVO, memberId);
    }

    @Override
    public Result queryMemberAppointment(Integer memberId, ReservationStatusEnum statusEnum, Pagination pagination) {
        return iMemberAppointmentService.queryMemberAppointment(memberId,statusEnum,pagination);
    }

    @Override
    public Result queryMemberAppointmentDetail(Integer memberId, String orderNo) {
        return iMemberAppointmentService.queryMemberAppointmentDetail(memberId, orderNo);
    }

    @Override
    public Result cancelMemberAppointment(Integer memberId, String orderNo, String reason) {
        return iMemberAppointmentService.cancelMemberAppointment(memberId, orderNo, reason);
    }

    @Override
    public Result queryCouponCode(Integer memberId, String orderNo) {
        return iMemberAppointmentService.queryCouponCode(memberId,orderNo);
    }
}
