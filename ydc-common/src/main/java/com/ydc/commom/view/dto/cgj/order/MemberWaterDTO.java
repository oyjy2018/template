package com.ydc.commom.view.dto.cgj.order;

import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * @author hejiangping
 * @date 2019/1/15
 */
public class MemberWaterDTO extends Pagination implements Serializable {
    private Integer id;
    private String mobilePhone;
    private String orderNo;
    private String payWater;
    private String startCreateTime;
    private String endCreateTime;

    public void Suffix(){
        this.startCreateTime = DateUtil.jointMinSuffix(this.startCreateTime);
        this.endCreateTime = DateUtil.jointMaxSuffix(this.endCreateTime);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
