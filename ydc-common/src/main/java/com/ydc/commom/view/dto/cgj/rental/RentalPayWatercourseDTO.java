package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 租赁支付流水表
 *
 * @author
 * @create 2018-11-26 15:45
 **/
public class RentalPayWatercourseDTO implements Serializable {
    private static final long serialVersionUID = -982411621097418061L;


    /**
     * t_rental_pay_watercourse.order_id (订单ID)
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    private Integer orderId;

    /**
     * t_rental_pay_watercourse.deposit_type (流水类型 1:租车 2:机务 3:租车冻结 4：违章冻结)
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    private Byte depositType;

    private BigDecimal payableAmount;//剩余应收总额

    private BigDecimal actualAmount;//实付金额

    public RentalPayWatercourseDTO() {
    }

    public RentalPayWatercourseDTO(Integer orderId, Byte depositType) {
        this.orderId = orderId;
        this.depositType = depositType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Byte getDepositType() {
        return depositType;
    }

    public void setDepositType(Byte depositType) {
        this.depositType = depositType;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = StringUtil.isEmpty(actualAmount)
                ? actualAmount : BigDecimal.valueOf(Double.valueOf(actualAmount.toString().trim()));
    }
}
