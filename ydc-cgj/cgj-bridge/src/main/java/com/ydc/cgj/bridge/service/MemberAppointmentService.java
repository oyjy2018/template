package com.ydc.cgj.bridge.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.model.cgj.MemberAppointment;

import java.util.List;

public interface MemberAppointmentService {

    /**
     * 更新预约订单的B端信息
     * @param memberAppointment
     * @return
     */
    Result updateAppointBInfo(MemberAppointment memberAppointment);

    /**
     * 更新预约订单的B端信息
     * @param memberId
     * @return
     */
    Boolean updateAppointStatusByBAppointStatus(BOrderResponseDTO bOrderResponseDTO, Integer memberId);

}
