package com.ydc.commom.view.vo.cgj.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddOrderResponseVO implements Serializable {

    private Date createTime;

    private List<String> orderNOList;

    private List<Integer> orderIdList;
    private BigDecimal sellPrice;

    //微信支付请求url
    private String weixinPayUrl;
    //支付方式
    private String payType;
    //支付环境（1：微信；2：H5）
    private int payEnvType;
    //微信支付流水
    private String prepayId;
    //JS支付参数
    private Map<String, String> payParams;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public List<Integer> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public String getWeixinPayUrl() {
        return weixinPayUrl;
    }

    public void setWeixinPayUrl(String weixinPayUrl) {
        this.weixinPayUrl = weixinPayUrl;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getPayEnvType() {
        return payEnvType;
    }

    public void setPayEnvType(int payEnvType) {
        this.payEnvType = payEnvType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public Map<String, String> getPayParams() {
        return payParams;
    }

    public void setPayParams(Map<String, String> payParams) {
        this.payParams = payParams;
    }
}
