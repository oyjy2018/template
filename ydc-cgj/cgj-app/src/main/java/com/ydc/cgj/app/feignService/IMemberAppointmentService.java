package com.ydc.cgj.app.feignService;

import com.ydc.commom.enums.ReservationStatusEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.Pagination;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-order")
public interface IMemberAppointmentService {

    @PostMapping(value = "/appointment/addAppointmentOrder")
    Result addMemberAppointment(@RequestBody CreateAppointmentParamVO createAppointmentParamVO, @RequestParam("memberId") Integer memberId);


    @PostMapping(value = "/appointment/member/query")
    Result queryMemberAppointment(@RequestParam(value ="memberId" ) Integer memberId,
                                  @RequestParam(value ="statusEnum" ) ReservationStatusEnum statusEnum, @RequestBody Pagination pagination);

    @PostMapping(value = "/appointment/member/detail")
    Result queryMemberAppointmentDetail(@RequestParam(value ="memberId" ) Integer memberId,
                                        @RequestParam(value ="orderNo" ) String orderNo);

    @PostMapping(value = "/appointment/member/cancel")
    Result cancelMemberAppointment(@RequestParam(value ="memberId" ) Integer memberId,
                                    @RequestParam(value ="orderNo" ) String orderNo,
                                   @RequestParam(value ="reason" ) String reason);


    @PostMapping(value = "/appointment/coupon/code/query")
    Result queryCouponCode(@RequestParam(value ="memberId" ) Integer memberId,
                                   @RequestParam(value ="orderNo" ) String orderNo);


}
