package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.util.DateUtil;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算信息
 *
 * @author
 * @create 2019-01-05 13:01
 **/
public class PCRentalEnterpriseDetailsSettlementVO implements Serializable {
    private static final long serialVersionUID = -6351322259166332324L;

    /**
     * 结算金额
     */
    private BigDecimal settleMoney;

    /**
     * 结算方式（1：押金；2：信用卡；3：转账）
     */
    private Integer settleWay;

    private String settleWayCH;

    /**
     * 结算时间
     */
    private Date settleDate;

    /**
     * 结算凭证文件名
     */
    private String voucherImgName;

    /**
     * 结算凭证文件地址
     */
    private String voucherImgUrl;

    private String voucherImgBrowse;

    public static PCRentalEnterpriseDetailsSettlementVO getPCRentalEnterpriseDetailsSettlementVO(RentalEnterpriseSettlement rentalEnterpriseSettlement){
        if(rentalEnterpriseSettlement == null)return new PCRentalEnterpriseDetailsSettlementVO();
        PCRentalEnterpriseDetailsSettlementVO rentalEnterpriseDetailsSettlementVO = new PCRentalEnterpriseDetailsSettlementVO();
        rentalEnterpriseDetailsSettlementVO.settleMoney = rentalEnterpriseSettlement.getSettleMoney();
        rentalEnterpriseDetailsSettlementVO.settleDate = rentalEnterpriseSettlement.getSettleDate();
        rentalEnterpriseDetailsSettlementVO.settleWay = rentalEnterpriseSettlement.getSettleWay();
        rentalEnterpriseDetailsSettlementVO.settleWayCH = rentalEnterpriseSettlement.getSettleWayCH();
        rentalEnterpriseDetailsSettlementVO.voucherImgName = rentalEnterpriseSettlement.getVoucherImgName();
        rentalEnterpriseDetailsSettlementVO.voucherImgUrl = rentalEnterpriseSettlement.getVoucherImgUrl();
        rentalEnterpriseDetailsSettlementVO.voucherImgBrowse = rentalEnterpriseSettlement.getVoucherImgBrowse();
        return rentalEnterpriseDetailsSettlementVO;
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

    @JSONField(format = DateUtil.DATAFORMAT_STR)
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

    public String getVoucherImgBrowse() {
        return voucherImgBrowse;
    }

    public void setVoucherImgBrowse(String voucherImgBrowse) {
        this.voucherImgBrowse = voucherImgBrowse;
    }

    public String getSettleWayCH() {
        return settleWayCH;
    }

    public void setSettleWayCH(String settleWayCH) {
        this.settleWayCH = settleWayCH;
    }
}
