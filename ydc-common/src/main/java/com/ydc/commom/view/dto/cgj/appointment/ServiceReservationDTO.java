package com.ydc.commom.view.dto.cgj.appointment;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 服务预约列表
 * @author
 * @create 2018-11-06 15:33
 **/
public class ServiceReservationDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -3380711102958882905L;

    private Integer memberId;//会员id
    private String mobilePhone;//手机号码
    private String appointTimeStart;//预约时间
    private String appointTimeEnd;//预约时间
    private String orderNo;//订单号

    public ServiceReservationDTO() {
    }

    public ServiceReservationDTO(Integer memberId, String mobilePhone, String appointTimeStart, String appointTimeEnd, String orderNo) {
        this.memberId = memberId;
        this.mobilePhone = mobilePhone;
        this.appointTimeStart = appointTimeStart;
        this.appointTimeEnd = appointTimeEnd;
        this.orderNo = orderNo;
    }

    public ServiceReservationDTO changeDTO(){
        this.appointTimeStart = (StringUtils.isEmpty(this.appointTimeStart) ? null : this.appointTimeStart +" 00:00:00");
        this.appointTimeEnd = (StringUtils.isEmpty(this.appointTimeEnd) ? null : this.appointTimeEnd +" 23:59:59");
        return this;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAppointTimeStart() {
        return appointTimeStart;
    }

    public void setAppointTimeStart(String appointTimeStart) {
        this.appointTimeStart = appointTimeStart;
    }

    public String getAppointTimeEnd() {
        return appointTimeEnd;
    }

    public void setAppointTimeEnd(String appointTimeEnd) {
        this.appointTimeEnd = appointTimeEnd;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
