package com.ydc.commom.view.dto.cgj.appointment;

import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.constant.cgj.MemberAppointmentConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  B 端订单返回信息
 */
public class BOrderResponseDTO implements Serializable {

    private String orderNo;//预约订单号

    private String updTime;//服务消费时间

    private Integer orderStatus;//订单状态

    private String orderStatusCH;//订单状态中文解释

    private String crtTime;//预约时间（下单时间）

    private String msg;

    private String storeNo;//门店码

    private Integer useType;//useType=1 预约消费 ；useType=2 直接消费

    private String telno;//用户手机号码


    private List<BOrderCouponResponseDTO> couponStatuss;



    public static CreateAppointmentParamVO convertBOrderResponseDTO(BOrderResponseDTO bOrderResponseDTO){
        CreateAppointmentParamVO createAppointmentParamVO=new CreateAppointmentParamVO();
        createAppointmentParamVO.setbOrderNo(bOrderResponseDTO.getOrderNo());
        createAppointmentParamVO.setOrderStatus(MemberAppointmentConstant.APPOINT_STATUS_FINISH);
        createAppointmentParamVO.setStoreNo(bOrderResponseDTO.getStoreNo());
        createAppointmentParamVO.setTelno(bOrderResponseDTO.getTelno());
        createAppointmentParamVO.setAppointTime(DateUtil.timeStamp2StrDate(bOrderResponseDTO.getUpdTime(),null));
        createAppointmentParamVO.setbOrderStatus(bOrderResponseDTO.getOrderStatus());
        createAppointmentParamVO.setUseType(RollConstant.APPOINT_TYPE_2);
        List<String> rollCodes=new ArrayList<>();
        for (BOrderCouponResponseDTO bOrderCouponResponseDTO:bOrderResponseDTO.getCouponStatuss()){
            rollCodes.add(bOrderCouponResponseDTO.getCouponNo());
            createAppointmentParamVO.setRollStatus(bOrderCouponResponseDTO.getStatus());
            createAppointmentParamVO.setRollUsedTime(DateUtil.timeStamp2Date(bOrderCouponResponseDTO.getUseTime(),null));
        }
        createAppointmentParamVO.setRollCodes(rollCodes);
        return  createAppointmentParamVO;
    }



    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusCH() {
        return orderStatusCH;
    }

    public void setOrderStatusCH(String orderStatusCH) {
        this.orderStatusCH = orderStatusCH;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public List<BOrderCouponResponseDTO> getCouponStatuss() {
        return couponStatuss;
    }

    public void setCouponStatuss(List<BOrderCouponResponseDTO> couponStatuss) {
        this.couponStatuss = couponStatuss;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }
}
