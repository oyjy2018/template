package com.ydc.cgj.bridge.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.MemberAppointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "service-order")
public interface MemberAppointmentFeignService {

    @PostMapping(value = "/appointment/updateAppointBInfo")
    Result updateAppointBInfo(@RequestBody MemberAppointment memberAppointment);


    @PostMapping(value = "/appointment/bOrderStatus/update")
    Result updateAppointStatusByBAppointStatus(@RequestBody BOrderResponseDTO bOrderResponseDTO,
                                                @RequestParam(value ="memberId" ) Integer memberId);

    @PostMapping(value = "/appointment/createBAppointmentOrder")
    Result createAppointByBOrder(@RequestBody CreateAppointmentParamVO createAppointmentParamVO);


}
