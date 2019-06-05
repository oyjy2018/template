package com.ydc.commom.view.dto.cgj.appointment;

import java.io.Serializable;

/**
 *  B端 订单 券返回信息
 */
public class BOrderCouponResponseDTO  implements Serializable {

    private String couponNo;

    private Integer status;

    private String  useTime;//券使用时间

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}
