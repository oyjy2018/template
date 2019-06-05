package com.ydc.model.cgj.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * t_cgj_violation_record_detail
 * @author 
 */
public class CgjViolationRecordDetail implements Serializable {
    private Integer id;

    /**
     * 关联违章记录id
     */
    private Integer recordId;

    /**
     * 时间
     */
    private String time;

    /**
     * 地点
     */
    private String address;

    /**
     * 违章内容
     */
    private String content;

    /**
     * 违章代码
     */
    private String legalnum;

    /**
     * 罚款金额
     */
    private String price;

    /**
     * 扣分
     */
    private String score;

    /**
     * 违章ID
     */
    private Integer illegalid;

    /**
     * 违章编号
     */
    private String number;

    /**
     * 采集机关
     */
    private String agency;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String town;

    /**
     * 纬度 参考，有误差
     */
    private String lat;

    /**
     * 经度 参考，有误差
     */
    private String lng;

    /**
     * 是否可以代办 0不可以 1可以
     */
    private Integer canhandle;

    /**
     * 代办费用
     */
    private String handlefee;

    /**
     * 处理状态  0未处理 1已处理
     */
    private Integer dealStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLegalnum() {
        return legalnum;
    }

    public void setLegalnum(String legalnum) {
        this.legalnum = legalnum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getIllegalid() {
        return illegalid;
    }

    public void setIllegalid(Integer illegalid) {
        this.illegalid = illegalid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getCanhandle() {
        return canhandle;
    }

    public void setCanhandle(Integer canhandle) {
        this.canhandle = canhandle;
    }

    public String getHandlefee() {
        return handlefee;
    }

    public void setHandlefee(String handlefee) {
        this.handlefee = handlefee;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
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
}