package com.ydc.service.order.controller;

import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.commom.view.vo.cgj.ServiceReservationVO;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.service.order.mq.service.ThirdPartySendMessageService;
import com.ydc.commom.enums.ReservationStatusEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.MemberAppointment;
import com.ydc.model.cgj.Pagination;
import com.ydc.service.order.service.AppointmentOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  预约订单
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentOrderController {
    private final Logger logger = LogManager.getLogger(AppointmentOrderController.class);
    @Autowired
    private AppointmentOrderService appointmentOrderService;

    @Autowired
    private ThirdPartySendMessageService thirdPartySendMessageService;

    /**
     * 添加预约订单
     * @param createAppointmentParamVO
     * @param memberId
     * @return
     */
    @PostMapping(value = "/addAppointmentOrder")
    public Result addAppointmentOrder(@RequestBody CreateAppointmentParamVO createAppointmentParamVO, @RequestParam("memberId") Integer memberId){
        logger.info("创建预约订单, 用户id: {}", memberId);
        MemberAppointment memberAppointment;
        try {
            memberAppointment = appointmentOrderService.addMemberAppointment(createAppointmentParamVO, memberId);
        }catch (ServiceRuntimeException serviceException){
            return Result.failure(serviceException.getMessage());
        }catch (Exception e){
            logger.error("用户预约异常，memberId:{}", memberId, e);
            return Result.failure("请稍后重试");
        }
        //第三方创建预约订单
        thirdPartySendMessageService.sendAddAppointmentOrderMessage(memberAppointment.getOrderNo(), memberAppointment.getStoreCode(),
                memberAppointment.getAppointTime(), createAppointmentParamVO.getRollCodes());
        Result result = Result.success();
        result.setData(memberAppointment.getOrderNo());
        return result;
    }

    /**
     * 创建B端预约订单
     * @param createAppointmentParamVO
     * @return
     */
    @PostMapping(value = "/createBAppointmentOrder")
    public Result createBAppointmentOrder(@RequestBody CreateAppointmentParamVO createAppointmentParamVO){
        logger.info("创建B端预约订单, createAppointmentParamVO: {}", createAppointmentParamVO);
        try {
            appointmentOrderService.addMemberAppointment(createAppointmentParamVO);
        }catch (ServiceRuntimeException serviceException){
            logger.error("创建B端预约订单异常，createAppointmentParamVO :{}", createAppointmentParamVO, serviceException);
            return Result.failure(serviceException.getMessage());
        }catch (Exception e){
            logger.error("创建B端预约订单Exception，createAppointmentParamVO:{}", createAppointmentParamVO, e);
            return Result.failure("请稍后重试");
        }
        return Result.success();
    }

    /**
     *  H5 查询我的预约订单
     * @param memberId
     * @param statusEnum
     * @param pagination
     * @return
     */
    @PostMapping(value = "/member/query")
    public Result queryMemberAppointment(@RequestParam(value ="memberId" ) Integer memberId,
                                         @RequestParam(value ="statusEnum" ) ReservationStatusEnum statusEnum,@RequestBody Pagination pagination){
        Integer appointStatus = null;
        switch (statusEnum) {
            case ALL:
                break;
            case ERVICE:
                appointStatus=2;
                break;
            case FINISH:
                appointStatus=3;
                break;
            case RESERVATION:
                appointStatus=1;
                break;
                default: return Result.failure("参数错误");
        }
        return  appointmentOrderService.getMemberAppointment(memberId,appointStatus,pagination);
    }


    /**
     *  H5 查询预订订单详细信息
     * @param memberId
     * @param orderNo
     * @return
     */
    @PostMapping(value = "/member/detail")
    public Result memberAppointmentDetail(@RequestParam(value ="memberId" ) Integer memberId,
                                    @RequestParam(value ="orderNo" ) String orderNo){
        return  appointmentOrderService.getMemberAppointmentDetail(memberId,orderNo);
    }

    /**
     *  H5 用户取消预约订单
     * @param memberId
     * @param orderNo
     * @return
     */
    @PostMapping(value = "/member/cancel")
    public Result memberAppointmentCancel(@RequestParam(value ="memberId" ) Integer memberId,
                                          @RequestParam(value ="orderNo" ) String orderNo,
                                          @RequestParam(value ="reason" ) String reason){
        return  appointmentOrderService.cancelMemberAppointment(memberId,orderNo,reason);
    }
    /**
     *  H5 查看券码
     * @param memberId
     * @param orderNo
     * @return
     */
    @PostMapping(value = "/coupon/code/query")
    public Result queryCouponCode(@RequestParam(value ="memberId" ) Integer memberId,
                                          @RequestParam(value ="orderNo" ) String orderNo){
        return  appointmentOrderService.queryCouponCode(memberId,orderNo);
    }

    /**
     * 查询服务预约列表
     * @param serviceReservationDTO
     * @return
     */
    @PostMapping(value = "/getServiceReservationList")
    public String getServiceReservationList(@RequestBody ServiceReservationDTO serviceReservationDTO){
        logger.info("subject:{},serviceReservationDTO:{}","查询服务预约列表",JsonUtil.gsonStr(serviceReservationDTO));
        try{
            List<ServiceReservationVO> ret = appointmentOrderService.getServiceReservationList(serviceReservationDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("查询服务预约列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 更新B端预约订单信息
     * @param memberAppointment
     * @return
     */
    @PostMapping(value = "/updateAppointBInfo")
    public Result updateAppointBInfo(@RequestBody MemberAppointment memberAppointment){
        logger.info("更新B端预约订单信息, memberAppointment: {}", JsonUtil.gsonStr(memberAppointment));
        appointmentOrderService.updateAppointBInfo(memberAppointment);
        return Result.success();
    }


    /**
     * 根据B端订单状态更新本库订单状态
     * @param bOrderResponseDTO
     * @return
     */
    @PostMapping(value = "/bOrderStatus/update")
    public Result updateAppointStatusByBAppointStatus(
            @RequestBody BOrderResponseDTO bOrderResponseDTO,
            @RequestParam(value ="memberId" ) Integer memberId){
        logger.info("更新B端预约订单信息, bOrderResponseDTOS: {}", JsonUtil.gsonStr(bOrderResponseDTO));
        if (null ==bOrderResponseDTO ){
            return Result.failure("参数不能为空");
        }
        //BOrderResponseDTO bOrderResponseDTO=new BOrderResponseDTO();
        //根据B端订单状态更新
      /*  int orderStatus=BOrderStatusEnum.getValueByKey(bOrderResponseDTO.getOrderStatus());
        if (orderStatus== -1){
            logger.info("无效状态");
            return Result.failure("无效状态");
        }*/
        if (null==memberId)memberId=1;
        List<BOrderResponseDTO> bOrderResponseDTOS=new ArrayList<>();
        bOrderResponseDTOS.add(bOrderResponseDTO);
        appointmentOrderService.updateAppointStatusByBAppointStatus(bOrderResponseDTOS,memberId);
        return Result.success();
    }
}
