package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;

/**
 * @author
 * @create 2018-11-06 15:53
 **/
public interface AppointmentOrderService {

    /**
     * 查询服务预约列表
     * @param serviceReservationDTO
     * @return
     */
    String getServiceReservationList(ServiceReservationDTO serviceReservationDTO);
}
