package com.ydc.model.cgj;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 商品表
 */
public class Commodity implements Serializable {


    private static final long serialVersionUID = 214513811145739050L;
    /**
     * t_c_commodity.id
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer id;

    /**
     * t_c_commodity.title (商品标题)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String title;

    /**
     * t_c_commodity.supplier_code (供应商编码)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String supplierCode;

    /**
     * t_c_commodity.supplier_name (供应商名称)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String supplierName;

    /**
     * t_c_commodity.main_classify_code (主分类编码)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String mainClassifyCode;

    /**
     * t_c_commodity.main_classify_name (主分类名称)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String mainClassifyName;

    /**
     * t_c_commodity.son_classify_code (子分类编码)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String sonClassifyCode;

    /**
     * t_c_commodity.son_classify_name (子分类名称)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String sonClassifyName;

    /**
     * t_c_commodity.description (商品描述)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String description;

    /**
     * t_c_commodity.has_shoutui (是否首推（1：是；0：否)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer hasShoutui;

    /**
     * t_c_commodity.shoutui_time (首推时间)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date shoutuiTime;

    /**
     * t_c_commodity.release_status (发布状态（0：未发布；1已发布；2：已下架)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer releaseStatus;

    /**
     * t_c_commodity.release_date (发布时间)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date releaseDate;

    /**
     * t_c_commodity.release_person (发布人名称)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String releasePerson;

    /**
     * t_c_commodity.release_person_id (发布人用户ID)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer releasePersonId;

    /**
     * t_c_commodity.total_inventory (总库存)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer totalInventory;

    /**
     * t_c_commodity.sold_number (已售数量)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer soldNumber;

    /**
     * t_c_commodity.sold_number_sham (虚拟已售数量)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer soldNumberSham;

    /**
     * t_c_commodity.low_sell_price (最低出售价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal lowSellPrice;

    /**
     * t_c_commodity.high_sell_price (最高出售价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal highSellPrice;

    /**
     * t_c_commodity.low_market_price (最低市场价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal lowMarketPrice;

    /**
     * t_c_commodity.high_market_price (最高市场价格)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private BigDecimal highMarketPrice;

    /**
     * t_c_commodity.shelve_time (下架时间)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date shelveTime;

    /**
     * t_c_commodity.shelve_person (下架人)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String shelvePerson;

    /**
     * t_c_commodity.shelve_person_id (下架人ID)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer shelvePersonId;

    /**
     * t_c_commodity.status (有效状态)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer status;

    /**
     * t_c_commodity.create_time
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date createTime;

    /**
     * t_c_commodity.create_by
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer createBy;

    /**
     * t_c_commodity.update_time
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date updateTime;

    /**
     * t_c_commodity.update_by
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMainClassifyCode() {
        return mainClassifyCode;
    }

    public void setMainClassifyCode(String mainClassifyCode) {
        this.mainClassifyCode = mainClassifyCode;
    }

    public String getMainClassifyName() {
        return mainClassifyName;
    }

    public void setMainClassifyName(String mainClassifyName) {
        this.mainClassifyName = mainClassifyName;
    }

    public String getSonClassifyCode() {
        return sonClassifyCode;
    }

    public void setSonClassifyCode(String sonClassifyCode) {
        this.sonClassifyCode = sonClassifyCode;
    }

    public String getSonClassifyName() {
        return sonClassifyName;
    }

    public void setSonClassifyName(String sonClassifyName) {
        this.sonClassifyName = sonClassifyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHasShoutui() {
        return hasShoutui;
    }

    public void setHasShoutui(Integer hasShoutui) {
        this.hasShoutui = hasShoutui;
    }

    public Date getShoutuiTime() {
        return shoutuiTime;
    }

    public void setShoutuiTime(Date shoutuiTime) {
        this.shoutuiTime = shoutuiTime;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public Integer getReleasePersonId() {
        return releasePersonId;
    }

    public void setReleasePersonId(Integer releasePersonId) {
        this.releasePersonId = releasePersonId;
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Integer getSoldNumberSham() {
        return soldNumberSham;
    }

    public void setSoldNumberSham(Integer soldNumberSham) {
        this.soldNumberSham = soldNumberSham;
    }

    public BigDecimal getLowSellPrice() {
        return lowSellPrice;
    }

    public void setLowSellPrice(BigDecimal lowSellPrice) {
        this.lowSellPrice = lowSellPrice;
    }

    public BigDecimal getHighSellPrice() {
        return highSellPrice;
    }

    public void setHighSellPrice(BigDecimal highSellPrice) {
        this.highSellPrice = highSellPrice;
    }

    public BigDecimal getLowMarketPrice() {
        return lowMarketPrice;
    }

    public void setLowMarketPrice(BigDecimal lowMarketPrice) {
        this.lowMarketPrice = lowMarketPrice;
    }

    public BigDecimal getHighMarketPrice() {
        return highMarketPrice;
    }

    public void setHighMarketPrice(BigDecimal highMarketPrice) {
        this.highMarketPrice = highMarketPrice;
    }

    public Date getShelveTime() {
        return shelveTime;
    }

    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }

    public String getShelvePerson() {
        return shelvePerson;
    }

    public void setShelvePerson(String shelvePerson) {
        this.shelvePerson = shelvePerson;
    }

    public Integer getShelvePersonId() {
        return shelvePersonId;
    }

    public void setShelvePersonId(Integer shelvePersonId) {
        this.shelvePersonId = shelvePersonId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Commodity(){

    }

    public Commodity(Map<String, String> param) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.id = StringUtils.isEmpty(param.get("id")) ? null : Integer.valueOf(param.get("id"));
        this.title = param.get("title");
        this.supplierCode = param.get("supplierCode");
        this.supplierName = param.get("supplierName");
        this.mainClassifyCode = param.get("mainClassifyCode");
        this.mainClassifyName = param.get("mainClassifyName");
        this.sonClassifyCode = param.get("sonClassifyCode");
        this.sonClassifyName = param.get("sonClassifyName");
        this.description = param.get("description");
        this.hasShoutui = StringUtils.isEmpty(param.get("hasShoutui")) ? null : Integer.valueOf(param.get("hasShoutui"));
//        this.shoutuiTime = StringUtils.isEmpty(param.get("shoutuiTime")) ? null:sdf.parse(param.get("shoutuiTime"));
        this.releaseStatus = StringUtils.isEmpty(param.get("releaseStatus")) ? null : Integer.valueOf(param.get("releaseStatus"));
//        this.releaseDate = StringUtils.isEmpty(param.get("releaseDate")) ? null:sdf.parse(param.get("releaseDate"));
        this.releasePerson = param.get("releasePerson");
        this.releasePersonId = StringUtils.isEmpty(param.get("releasePersonId")) ? null : Integer.valueOf(param.get("releasePersonId"));
        this.totalInventory = StringUtils.isEmpty(param.get("totalInventory")) ? null : Integer.valueOf(param.get("totalInventory"));
        this.soldNumber = StringUtils.isEmpty(param.get("soldNumber")) ? null : Integer.valueOf(param.get("soldNumber"));
        this.soldNumberSham = StringUtils.isEmpty(param.get("soldNumberSham")) ? null : Integer.valueOf(param.get("soldNumberSham"));
        this.lowSellPrice = StringUtils.isEmpty(param.get("soldNumberSham")) ? null : new BigDecimal(param.get("soldNumberSham"));
        this.highSellPrice = StringUtils.isEmpty(param.get("highSellPrice")) ? null : new BigDecimal(param.get("highSellPrice"));
        this.lowMarketPrice = StringUtils.isEmpty(param.get("lowMarketPrice")) ? null : new BigDecimal(param.get("lowMarketPrice"));
        this.highMarketPrice = StringUtils.isEmpty(param.get("highMarketPrice")) ? null : new BigDecimal(param.get("highMarketPrice"));
//        this.shelveTime = StringUtils.isEmpty(param.get("shelveTime")) ? null:sdf.parse(param.get("shelveTime"));
        this.shelvePerson = param.get("shelvePerson");
        this.shelvePersonId = StringUtils.isEmpty(param.get("shelvePersonId")) ? null : Integer.valueOf(param.get("shelvePersonId"));
//        this.status = StringUtils.isEmpty(param.get("status")) ? null : Integer.valueOf(param.get("status"));
//        this.createTime = StringUtils.isEmpty(param.get("createTime")) ? null:sdf.parse(param.get("createTime"));
//        this.createBy = StringUtils.isEmpty(param.get("createBy")) ? null : Integer.valueOf(param.get("createBy"));
//        this.updateTime = StringUtils.isEmpty(param.get("updateTime")) ? null:sdf.parse(param.get("updateTime"));
//        this.updateBy = StringUtils.isEmpty(param.get("updateBy")) ? null : Integer.valueOf(param.get("updateBy"));
    }

}