package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @create 2019-01-05 18:47
 **/
public class StoreRentalEnterpriseSettlementVO implements Serializable {
    private static final long serialVersionUID = 6460733800163133757L;

    /**
     * 结算金额
     */
    private BigDecimal settleMoney;

    /**
     * 结算时间
     */
    private Date settleDate;

    public static StoreRentalEnterpriseSettlementVO getStoreRentalEnterpriseSettlementVO(RentalEnterpriseSettlement rentalEnterpriseSettlement){
        if(rentalEnterpriseSettlement == null)return new StoreRentalEnterpriseSettlementVO();
        StoreRentalEnterpriseSettlementVO storeRentalEnterpriseSettlementVO = new StoreRentalEnterpriseSettlementVO();
        storeRentalEnterpriseSettlementVO.settleMoney = rentalEnterpriseSettlement.getSettleMoney();
        storeRentalEnterpriseSettlementVO.settleDate = rentalEnterpriseSettlement.getSettleDate();
        return storeRentalEnterpriseSettlementVO;
    }



    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}
