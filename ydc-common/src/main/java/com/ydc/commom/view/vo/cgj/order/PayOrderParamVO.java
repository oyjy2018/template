package com.ydc.commom.view.vo.cgj.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class  PayOrderParamVO  implements Serializable {

    private List<String> orderNOList;

    private BigDecimal sellPrice;

    private String  paymentMethod;

    //支付环境（1：微信；2：H5）
    private int payEnvType;
    //微信的openid
    private String openId;
    //微信支付需要的ip
    private String createIp;
    //微信授权的code
    private String weiXinCode;


    public List<String> getOrderNOList() {
        return orderNOList;
    }

    public void setOrderNOList(List<String> orderNOList) {
        this.orderNOList = orderNOList;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPayEnvType() {
        return payEnvType;
    }

    public void setPayEnvType(int payEnvType) {
        this.payEnvType = payEnvType;
    }

    public String getOpenId() {
        return openId;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWeiXinCode() {
        return weiXinCode;
    }

    public void setWeiXinCode(String weiXinCode) {
        this.weiXinCode = weiXinCode;
    }
}
