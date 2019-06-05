package com.ydc.commom.view.vo.cgj.order;

public class BuyRollParamVO {

    private Integer commodityId; //商品id
    private Integer commodityModelId; //商品型号id
    private int commodityNum; //商品数量
    private String  paymentMethod; //支付方式
    private int payEnvType; //支付环境（1：微信；2：H5）
    private String openId; //微信的openid
    private String createIp; //微信支付需要的ip
    private String weiXinCode; //微信授权code

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum = commodityNum;
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

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Integer getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(Integer commodityModelId) {
        this.commodityModelId = commodityModelId;
    }

    public String getWeiXinCode() {
        return weiXinCode;
    }

    public void setWeiXinCode(String weiXinCode) {
        this.weiXinCode = weiXinCode;
    }
}
