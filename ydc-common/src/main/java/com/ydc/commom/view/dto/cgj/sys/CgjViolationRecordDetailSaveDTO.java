package com.ydc.commom.view.dto.cgj.sys;

import java.util.Date;

/**
 * 违章记录详情
 * 
 * @author wcyong
 * 
 * @date 2019-01-02
 */
public class CgjViolationRecordDetailSaveDTO {
    private Integer id;

    /**
     * 关联违章记录id
     */
    private String recordId;

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

    public CgjViolationRecordDetailSaveDTO(Integer id, String recordId, String time, String address, String content, String legalnum, String price, String score, Integer illegalid, String number, String agency, String province, String city, String town, String lat, String lng, Integer canhandle, String handlefee, Integer dealStatus, Date createTime, Integer createBy, Date updateTime, Integer updateBy) {
        this.id = id;
        this.recordId = recordId;
        this.time = time;
        this.address = address;
        this.content = content;
        this.legalnum = legalnum;
        this.price = price;
        this.score = score;
        this.illegalid = illegalid;
        this.number = number;
        this.agency = agency;
        this.province = province;
        this.city = city;
        this.town = town;
        this.lat = lat;
        this.lng = lng;
        this.canhandle = canhandle;
        this.handlefee = handlefee;
        this.dealStatus = dealStatus;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public CgjViolationRecordDetailSaveDTO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLegalnum() {
        return legalnum;
    }

    public void setLegalnum(String legalnum) {
        this.legalnum = legalnum == null ? null : legalnum.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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
        this.number = number == null ? null : number.trim();
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
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
        this.handlefee = handlefee == null ? null : handlefee.trim();
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