package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.ObjectUtil;
import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业租车结算
 *
 * @author
 * @create 2019-01-05 13:03
 **/
public class RentalEnterpriseSettlementDTO implements Serializable {
    private static final long serialVersionUID = -5162320498540017560L;

    @Attribute(name = "订单ID",required = true,emptyStringVerify = true)
    private Integer orderId;

    /**
     * 需求方ID
     */
    @Attribute(name = "需求方ID",required = true,emptyStringVerify = true)
    private Integer demandSideId;

    /**
     * 结算金额
     */
    @Attribute(name = "结算金额",required = true,emptyStringVerify = true,isDigit = true,decimalLength = 2 ,maxLength = 10)
    private BigDecimal settleMoney;

    /**
     * 结算方式（1：押金；2：信用卡；3：转账）
     */
    @Attribute(name = "结算方式",required = true,emptyStringVerify = true)
    private Integer settleWay;

    /**
     * 结算时间
     */
    @Attribute(name = "结算时间",required = true,emptyStringVerify = true)
    private Date settleDate;

    /**
     * 结算凭证文件名
     */
    private String voucherImgName;

    /**
     * 结算凭证文件地址
     */
    private String voucherImgUrl;

    public static void main(String[] args){
        RentalEnterpriseSettlementDTO dto = new RentalEnterpriseSettlementDTO();
        dto.settleMoney = BigDecimal.valueOf(100);
        dto.settleWay = 1;
        dto.setOrderId(1);
        RentalEnterpriseSettlement rentalEnterpriseSettlement = new RentalEnterpriseSettlement();
        ObjectUtil.copyProperties(rentalEnterpriseSettlement,dto);
        System.out.println(JsonUtil.gsonStr(rentalEnterpriseSettlement));
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public RentalEnterpriseSettlementDTO() {
    }

    public RentalEnterpriseSettlementDTO(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(Integer demandSideId) {
        this.demandSideId = demandSideId;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Integer getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(Integer settleWay) {
        this.settleWay = settleWay;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getVoucherImgName() {
        return voucherImgName;
    }

    public void setVoucherImgName(String voucherImgName) {
        this.voucherImgName = voucherImgName;
    }

    public String getVoucherImgUrl() {
        return voucherImgUrl;
    }

    public void setVoucherImgUrl(String voucherImgUrl) {
        this.voucherImgUrl = voucherImgUrl;
    }


}
