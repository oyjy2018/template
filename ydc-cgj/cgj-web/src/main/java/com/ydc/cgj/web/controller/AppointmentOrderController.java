package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.web.service.AppointmentOrderService;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务预约
 *
 * @author
 * @create 2018-11-06 15:54
 **/
@RestController
@RequestMapping(value = "/appointmentOrder")
public class AppointmentOrderController {

    private final Logger logger = LogManager.getLogger(AppointmentOrderController.class);

    @Autowired
    private AppointmentOrderService appointmentOrderService;

    /**
     * 服务预约
     * @param data
     * @return
     */
    @PostMapping(value = "/getServiceReservationList")
    public String getServiceReservationList(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","服务预约列表",data);
        ServiceReservationDTO serviceReservationDTO = JSONObject.parseObject(data,ServiceReservationDTO.class);
        return appointmentOrderService.getServiceReservationList(serviceReservationDTO.changeDTO());
    }
}
