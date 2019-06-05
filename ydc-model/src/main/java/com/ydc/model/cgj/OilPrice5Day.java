package com.ydc.model.cgj;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;

/**
 * 近5天油价
 *
 * @author Antoneo
 * @create 2018-09-08 11:03
 **/
public class OilPrice5Day implements Serializable {
    private static final long serialVersionUID = -1069055092595194139L;

    /**
     * 主键
     */
    @JSONField(serialize = false)
    private Integer id;

    /**
     * 油价总表id
     */
    @JSONField(serialize = false)
    private Integer oilId;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 89号汽油价格
     */
    private String n89Price;

    /**
     * 92号汽油价格
     */
    private String n92Price;

    /**
     * 95号汽油价格
     */
    private String n95Price;

    /**
     * 0号柴油价格
     */
    private String n0Price;

    /**
     * 单位
     */
    private String unit;

    /**
     * 采集日期
     */
    private Date date;

    /**
     * 采集链接
     */
    @JSONField(serialize = false)
    private String spiderUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
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

    public String getN89Price() {
        return n89Price;
    }

    public void setN89Price(String n89Price) {
        this.n89Price = n89Price;
    }

    public String getN92Price() {
        return n92Price;
    }

    public void setN92Price(String n92Price) {
        this.n92Price = n92Price;
    }

    public String getN95Price() {
        return n95Price;
    }

    public void setN95Price(String n95Price) {
        this.n95Price = n95Price;
    }

    public String getN0Price() {
        return n0Price;
    }

    public void setN0Price(String n0Price) {
        this.n0Price = n0Price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSpiderUrl() {
        return spiderUrl;
    }

    public void setSpiderUrl(String spiderUrl) {
        this.spiderUrl = spiderUrl;
    }
}
