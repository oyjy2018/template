package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.AppointmentOrderFeignService;
import com.ydc.cgj.web.service.AppointmentOrderService;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务预约
 *
 * @author
 * @create 2018-11-06 15:53
 **/
@Service
public class AppointmentOrderServiceImpl implements AppointmentOrderService {

    @Autowired
    AppointmentOrderFeignService appointmentOrderFeignService;

    @Override
    public String getServiceReservationList(ServiceReservationDTO serviceReservationDTO) {
        return appointmentOrderFeignService.getServiceReservationList(serviceReservationDTO);
    }
}
