package com.ydc.commom.view.vo.cgj.shopCart;

import java.io.Serializable;

/**
 *  购物车前端请求参数
 */
public class ShopCartParamVO  implements Serializable {

    private Integer id;//购物车ID
    private Integer memberId;
    private Integer modelId;
    private Integer commodityId;
    private Integer number;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
