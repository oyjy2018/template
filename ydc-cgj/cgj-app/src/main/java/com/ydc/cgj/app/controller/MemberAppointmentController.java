package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.MemberAppointmentService;
import com.ydc.commom.enums.ReservationStatusEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * 用户预约controller
 */
@RestController
@RequestMapping(value = "/memberAppointment")
public class MemberAppointmentController {

    private final Logger logger = LogManager.getLogger(MemberAppointmentController.class);

    @Autowired
    private MemberAppointmentService memberAppointmentService;

    /**
     * 创建预约订单
     * @param data
     * @return
     */
    @PostMapping(value = "/createMemberAppointment")
    public String createMemberAppointment(@RequestParam("data") String data){
        CreateAppointmentParamVO createAppointmentParamVO = JSONObject.parseObject(data, CreateAppointmentParamVO.class);
        Result checkResult = checkParams(createAppointmentParamVO);
        if (checkResult.getCode() != ResultConstant.RESULT_CODE_SUCCESS){
            return checkResult.toJSON();
        }
        //添加预约订单
        return memberAppointmentService.addMemberAppointment(createAppointmentParamVO, SubjectUtil.getMember().getId()).toJSON();
    }

    private Result checkParams(CreateAppointmentParamVO createAppointmentParamVO){
        if (createAppointmentParamVO == null){
            return Result.failure("请选择预约信息");
        }
        if (createAppointmentParamVO.getStoreId() == null){
            return Result.failure("请选择门店");
        }
        if (createAppointmentParamVO.getRollCodes() == null || createAppointmentParamVO.getRollCodes().size() <= 0){
            return Result.failure("请选择优惠券");
        }
        if (createAppointmentParamVO.getAppointTime() == null || ("").equals(createAppointmentParamVO.getAppointTime())){
            return Result.failure("请选择预约时间");
        }
        if (DateUtil.parseDateAndTime(createAppointmentParamVO.getAppointTime()).getTime() - new Date().getTime() < 2 * 60 *60 * 1000L){
            return Result.failure("请至少提前两小时预约");
        }
        return Result.success();
    }

    // 查询会员预约的服务
    @PostMapping("/query")
    public String memberAppointment(@RequestParam("data") String data){
        ReservationStatusEnum statusEnum;
        Pagination pagination;
        Integer memberId;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            statusEnum=ReservationStatusEnum.valueOf(jsonObject.getString("status"));
            pagination=new Pagination(jsonObject.getInteger("page"),jsonObject.getInteger("rows"));
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            memberId=member.getId();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
        return memberAppointmentService.queryMemberAppointment(memberId,statusEnum,pagination).toJSON();
    }

    /**
     * 查询预约详情
     * @param data
     * @return
     */
    @PostMapping("/detail/query")
    public String memberAppointmentDetail(@RequestParam("data") String data){
        String orderNo;
        Integer memberId;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            orderNo=jsonObject.getString("orderNo");
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            memberId=member.getId();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
        return memberAppointmentService.queryMemberAppointmentDetail(memberId,orderNo).toJSON();
    }

    /**
     * 取消预约
     * @param data
     * @return
     */
    @PostMapping("/cancel")
    public String cancelMemberAppointment(@RequestParam("data") String data){
        String orderNo;
        Integer memberId;
        String reason="用户取消预约";
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            orderNo=jsonObject.getString("orderNo");
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            memberId=member.getId();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
        return memberAppointmentService.cancelMemberAppointment(memberId,orderNo,reason).toJSON();
    }

    /**
     *  查看券码
     */
    @PostMapping("/code/query")
    public String queryCouponCode(@RequestParam("data") String data){
        String orderNo;
        Integer memberId;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            orderNo=jsonObject.getString("orderNo");
            Member member = SubjectUtil.getMember();
            if (member == null) return Result.failure("用户未登陆").toJSON();
            memberId=member.getId();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
        return memberAppointmentService.queryCouponCode(memberId,orderNo).toJSON();
    }



}
