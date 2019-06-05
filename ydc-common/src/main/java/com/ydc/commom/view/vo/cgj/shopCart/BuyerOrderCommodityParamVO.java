package com.ydc.commom.view.vo.cgj.shopCart;

import java.io.Serializable;
import java.util.List;

/**
 * 买家订单商品信息
 */
public class BuyerOrderCommodityParamVO  implements Serializable {

    private String supplierCode;

    private String buyerMessage;//买家留言

    private String deliveryMethod;//送货方式

    private List<ShopCartParamVO> shopCartParamVOS;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public List<ShopCartParamVO> getShopCartParamVOS() {
        return shopCartParamVOS;
    }

    public void setShopCartParamVOS(List<ShopCartParamVO> shopCartParamVOS) {
        this.shopCartParamVOS = shopCartParamVOS;
    }
}
