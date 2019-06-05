package com.ydc.commom.view.dto.cgj;

import com.ydc.model.annotation.Attribute;

public class CommodityModelSaveDTO {
    @Attribute(name = "商品型号ID", isNum = true)
    private Integer id;
    @Attribute(name = "商品类型名称", required = true)
    private String model;
    @Attribute(name = "售卖价格", required = true, isDigit = true, intLength = 9, decimalLength = 2)
    private String sellPrice;
    @Attribute(name = "市场价格", isDigit = true, intLength = 9, decimalLength = 2)
    private String marketPrice;
    @Attribute(name = "库存", required = true, isNum = true)
    private String inventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
