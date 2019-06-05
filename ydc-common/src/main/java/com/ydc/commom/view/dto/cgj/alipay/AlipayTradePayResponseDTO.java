package com.ydc.commom.view.dto.cgj.alipay;

import com.ydc.model.cgj.alipay.ApiField;

import java.io.Serializable;
import java.util.Date;

public class AlipayTradePayResponseDTO implements Serializable {


    private static final long serialVersionUID = 8156315365557914228L;

    /**
     * 买家支付宝账号
     */
    @ApiField("buyer_logon_id")
    private String buyerLogonId;

    /**
     * 买家付款的金额
     */
    @ApiField("buyer_pay_amount")
    private String buyerPayAmount;

    /**
     * 买家在支付宝的用户id
     */
    @ApiField("buyer_user_id")
    private String buyerUserId;


    /**
     * 交易支付时间
     */
    @ApiField("gmt_payment")
    private Date gmtPayment;



    /**
     * 商户订单号
     */
    @ApiField("out_trade_no")
    private String outTradeNo;

    /**
     * 支付币种订单金额
     */
    @ApiField("pay_amount")
    private String payAmount;


    /**
     *  实收金额
     */
    @ApiField("receipt_amount")
    private String receiptAmount;


    /**
     * 支付清算编号，用于清算对账使用；
     只在银行间联交易场景下返回该信息；
     */
    @ApiField("settlement_id")
    private String settlementId;



    /**
     * 交易金额
     */
    @ApiField("total_amount")
    private String totalAmount;

    /**
     * 支付宝交易号
     */
    @ApiField("trade_no")
    private String tradeNo;


    /**
     * t_rental_pay_watercourse.payee_account (收款人员账号)
     * @ibatorgenerated 2018-11-27 10:05:34
     */
    private String payeeAccount;


    /**
     * 类型
     */
    private Byte type; //1：租金扣除   2：违章扣除

    private Byte depositType;//流水类型 1:租车冻结 2：违章冻结 3:租车押金结算 4：违章押金结算  8：机务


    /**
     * t_rental_deposit.payment_mode (支付方式 1：芝麻 2：信用卡 3：现金)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Byte paymentMode;


    /**
     * t_rental_deposit.payment_status (押金状态 1：未支付，2：已支付，3：已退还，9：退还失败)
     * @ibatorgenerated 2018-11-26 15:49:59
     */
    private Integer paymentStatus;


    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "AlipayTradePayResponseDTO{" +
                "buyerLogonId='" + buyerLogonId + '\'' +
                ", buyerPayAmount='" + buyerPayAmount + '\'' +
                ", buyerUserId='" + buyerUserId + '\'' +
                ", gmtPayment=" + gmtPayment +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", receiptAmount='" + receiptAmount + '\'' +
                ", settlementId='" + settlementId + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", payeeAccount='" + payeeAccount + '\'' +
                ", type=" + type +
                ", depositType=" + depositType +
                ", paymentMode=" + paymentMode +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
