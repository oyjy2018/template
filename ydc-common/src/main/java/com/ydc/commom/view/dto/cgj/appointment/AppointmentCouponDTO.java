package com.ydc.commom.view.dto.cgj.appointment;

import com.ydc.model.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 预约券信息
 */
public class AppointmentCouponDTO implements Serializable {

    private static final long serialVersionUID = -7317458537178227800L;

    private String content;//详细内容

    private BigDecimal amount;//金额

    private String name;//名称

    private String couponNo;//券码

    private Integer status;//券状态


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getAmount() {

        if (amount==null){
            return  amount;
        }
        return amount.setScale(2,BigDecimal.ROUND_DOWN);

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouponNo() {
        if (null==status || status.intValue()!=1){
            //状态不是 使用中 全部打码
            this.couponNo=StringUtil.mosaicString(this.couponNo,4,4,4);
        }
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
}
