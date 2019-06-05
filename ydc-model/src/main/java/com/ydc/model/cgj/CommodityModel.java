package com.ydc.model.cgj;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 商品型号表
 */
public class CommodityModel implements Serializable {
    private static final long serialVersionUID = 7538816456992729745L;
    /**
     * t_c_commodity_model.id
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer id;

    /**
     * t_c_commodity_model.commodity_id (商品表ID)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer commodityId;

    /**
     * t_c_commodity_model.model (商品型号)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String model;

    /**
     * t_c_commodity_model.sell_price (出售价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal sellPrice;

    /**
     * t_c_commodity_model.market_price (市场价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal marketPrice;

    /**
     * t_c_commodity_model.inventory (库存)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer inventory;

    /**
     * t_c_commodity_model.sold_number (已售数量)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer soldNumber;

    /**
     * t_c_commodity_model.status (有效状态)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer status;

    /**
     * t_c_commodity_model.version (版本号)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer version;

    /**
     * t_c_commodity_model.create_time
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date createTime;

    /**
     * t_c_commodity_model.create_by
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer createBy;

    /**
     * t_c_commodity_model.update_time
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date updateTime;

    /**
     * t_c_commodity_model.update_by
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public CommodityModel(){

    }

    public CommodityModel(Map<String, String> param) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.id = StringUtils.isEmpty(param.get("id")) ? null : Integer.valueOf(param.get("id"));
        this.commodityId = StringUtils.isEmpty(param.get("commodityId")) ? null : Integer.valueOf(param.get("commodityId"));
        this.model = param.get("model");
        this.sellPrice = StringUtils.isEmpty(param.get("sellPrice")) ? null : new BigDecimal(param.get("sellPrice"));
        this.marketPrice = StringUtils.isEmpty(param.get("marketPrice")) ? null : new BigDecimal(param.get("marketPrice"));
        this.inventory = StringUtils.isEmpty(param.get("inventory")) ? null : Integer.valueOf(param.get("inventory"));
        this.soldNumber = StringUtils.isEmpty(param.get("soldNumber")) ? null : Integer.valueOf(param.get("soldNumber"));
        this.status = StringUtils.isEmpty(param.get("status")) ? null : Integer.valueOf(param.get("status"));
        this.version = StringUtils.isEmpty(param.get("version")) ? null : Integer.valueOf(param.get("version"));
        this.createTime = StringUtils.isEmpty(param.get("createTime")) ? null:sdf.parse(param.get("createTime"));
        this.createBy = StringUtils.isEmpty(param.get("createBy")) ? null : Integer.valueOf(param.get("createBy"));
        this.updateTime = StringUtils.isEmpty(param.get("updateTime")) ? null:sdf.parse(param.get("updateTime"));
        this.updateBy = StringUtils.isEmpty(param.get("updateBy")) ? null : Integer.valueOf(param.get("updateBy"));
    }
}