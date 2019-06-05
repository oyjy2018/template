package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 服务预约
 *
 * @author
 * @create 2018-11-06 15:50
 **/
@FeignClient(value = "service-order")
public interface AppointmentOrderFeignService {

    /**
     * 查询服务预约列表
     * @param serviceReservationDTO
     * @return
     */
    @PostMapping(value = "/appointment/getServiceReservationList")
    String getServiceReservationList(@RequestBody ServiceReservationDTO serviceReservationDTO);
}
