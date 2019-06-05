package com.ydc.commom.view.dto.cgj.rental;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 手动处理押金
 */
public class ManualFreezeRentalDepositDTO implements Serializable {


    private Integer  orderId; //订单ID

    private BigDecimal amount ;//押金金额

    private Byte depositType;//押金类型   1:表示租车冻结  2：表示违章冻结

    private Byte paymentMode;//支付方式 1：支付宝 ，2：信用卡  3：现金

    private Byte paymentStatus;//押金状态 1：未支付，2：已支付，3：已退还，9：退还失败

    private Integer operationMemberId;//操作用户ID

    private String operationMemberName;//操作人员名称

    private Date paymentTime;//支付时间


    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getDepositType() {
        return depositType;
    }

    public void setDepositType(Byte depositType) {
        this.depositType = depositType;
    }

    public Byte getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Byte paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getOperationMemberId() {
        return operationMemberId;
    }

    public void setOperationMemberId(Integer operationMemberId) {
        this.operationMemberId = operationMemberId;
    }

    public String getOperationMemberName() {
        return operationMemberName;
    }

    public void setOperationMemberName(String operationMemberName) {
        this.operationMemberName = operationMemberName;
    }
}
