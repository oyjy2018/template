package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalSettlementEnum;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 门店保证金
 *
 * @author
 * @create 2019-01-05 18:08
 **/
public class StoreRentalEnterpriseOrderDepositVO implements Serializable {
    private static final long serialVersionUID = 5202171697957831869L;

    private BigDecimal actualAmount;//实付金额
    private Integer paymentMode;//支付方式 1：芝麻 2：信用卡 3：现金'
    private String paymentModeName;
    private Date paymentTime;//支付时间
    private Integer paymentStatus;//押金状态 1：未支付，2：已支付，3：已退还，9：退还失败
    private String paymentStatusName;
    private Date actualRefundTime;//实际押金退还时间
    private BigDecimal actualRefundAmount;//实际退还金额


    public static StoreRentalEnterpriseOrderDepositVO getStoreRentalEnterpriseOrderDepositVO(RentalDeposit rentalDeposit){
        StoreRentalEnterpriseOrderDepositVO storeRentalEnterpriseOrderDepositVO = new StoreRentalEnterpriseOrderDepositVO();
        if(rentalDeposit == null)return storeRentalEnterpriseOrderDepositVO;
        storeRentalEnterpriseOrderDepositVO.setActualAmount(rentalDeposit.getActualAmount());
        storeRentalEnterpriseOrderDepositVO.setPaymentMode(rentalDeposit.getPaymentMode().intValue());
        storeRentalEnterpriseOrderDepositVO.setPaymentModeName(RentalSettlementEnum.getRentalSettlementEnum(rentalDeposit.getPaymentMode().intValue()));
        storeRentalEnterpriseOrderDepositVO.setPaymentTime(rentalDeposit.getPaymentTime());
        storeRentalEnterpriseOrderDepositVO.setPaymentStatus(rentalDeposit.getPaymentStatus().intValue());
        storeRentalEnterpriseOrderDepositVO.setPaymentStatusName(
                RentalDepositEnum.PaymentStatus.getPaymentStatusByStatus(rentalDeposit.getPaymentStatus().intValue()).getStatusCHEnterprise());
        storeRentalEnterpriseOrderDepositVO.setActualRefundTime(rentalDeposit.getActualRefundTime());
        storeRentalEnterpriseOrderDepositVO.setActualRefundAmount(rentalDeposit.getActualRefundAmount());
        return storeRentalEnterpriseOrderDepositVO;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }
    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatusName() {
        return paymentStatusName;
    }

    public void setPaymentStatusName(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
    }
    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getActualRefundTime() {
        return actualRefundTime;
    }

    public void setActualRefundTime(Date actualRefundTime) {
        this.actualRefundTime = actualRefundTime;
    }

    public BigDecimal getActualRefundAmount() {
        return actualRefundAmount;
    }

    public void setActualRefundAmount(BigDecimal actualRefundAmount) {
        this.actualRefundAmount = actualRefundAmount;
    }

    public String getPaymentModeName() {
        return paymentModeName;
    }

    public void setPaymentModeName(String paymentModeName) {
        this.paymentModeName = paymentModeName;
    }
}
