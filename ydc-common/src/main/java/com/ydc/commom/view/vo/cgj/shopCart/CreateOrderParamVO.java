package com.ydc.commom.view.vo.cgj.shopCart;

import java.io.Serializable;
import java.util.List;

public class CreateOrderParamVO implements Serializable {

    private String serial;

    private Integer memberDeliveryAddressId;

    private List<BuyerOrderCommodityParamVO> buyerOrderCommodityParamVOS;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getMemberDeliveryAddressId() {
        return memberDeliveryAddressId;
    }

    public void setMemberDeliveryAddressId(Integer memberDeliveryAddressId) {
        this.memberDeliveryAddressId = memberDeliveryAddressId;
    }

    public List<BuyerOrderCommodityParamVO> getBuyerOrderCommodityParamVOS() {
        return buyerOrderCommodityParamVOS;
    }

    public void setBuyerOrderCommodityParamVOS(List<BuyerOrderCommodityParamVO> buyerOrderCommodityParamVOS) {
        this.buyerOrderCommodityParamVOS = buyerOrderCommodityParamVOS;
    }
}
