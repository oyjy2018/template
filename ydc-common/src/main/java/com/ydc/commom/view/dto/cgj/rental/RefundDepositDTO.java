package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @create 2019-01-08 14:24
 **/
public class RefundDepositDTO implements Serializable {
    private static final long serialVersionUID = 216097561791369359L;

    @Attribute(name = "订单ID",required = true,emptyStringVerify = true)
    private Integer orderId;//订单ID

    @Attribute(name = "退还日期",required = true,emptyStringVerify = true)
    private Date actualRefundTime;//实际押金退还时间

//    @Attribute(name = "退还日期",required = true,emptyStringVerify = true)
//    private BigDecimal actualRefundAmount;//实际退还金额

    /**
     * (退还凭证名)
     */
    @Attribute(name = "退还凭证名",required = true,emptyStringVerify = true)
    private String refundVoucherImgName;

    /**
     * (退还凭证路径)
     */
    @Attribute(name = "退还凭证路径",required = true,emptyStringVerify = true)
    private String refundVoucherImgUrl;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getActualRefundTime() {
        return actualRefundTime;
    }

    public void setActualRefundTime(Date actualRefundTime) {
        this.actualRefundTime = actualRefundTime;
    }

    public String getRefundVoucherImgName() {
        return refundVoucherImgName;
    }

    public void setRefundVoucherImgName(String refundVoucherImgName) {
        this.refundVoucherImgName = refundVoucherImgName;
    }

    public String getRefundVoucherImgUrl() {
        return refundVoucherImgUrl;
    }

    public void setRefundVoucherImgUrl(String refundVoucherImgUrl) {
        this.refundVoucherImgUrl = refundVoucherImgUrl;
    }
}
