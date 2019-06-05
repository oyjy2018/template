package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

/**
 * t_cgj_violation_record
 * @author 
 */
public class CgjViolationRecord implements Serializable {
    private Integer id;

    /**
     * 车牌前缀
     */
    private String lsprefix;

    /**
     * 车牌剩余部分
     */
    private String lsnum;

    /**
     * 管局名称
     */
    private String carorg;

    /**
     * 车辆类型 默认小车02
     */
    private String lstype;

    /**
     * 车架号 根据管局需要输入
     */
    private String frameno;

    /**
     * 发动机号 根据管局需要输入
     */
    private String engineno;

    /**
     * 手机号 浙江直连需要手机号
     */
    private String mobile;

    /**
     * 车牌ID
     */
    private Integer usercarid;

    /**
     * 违章条数
     */
    private Integer count;

    /**
     * 违章总金额
     */
    private String totalprice;

    /**
     * 违章总扣分
     */
    private String totalscore;

    /**
     * 查询时间记录
     */
    private Date checkTime;

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

    public String getLsprefix() {
        return lsprefix;
    }

    public void setLsprefix(String lsprefix) {
        this.lsprefix = lsprefix;
    }

    public String getLsnum() {
        return lsnum;
    }

    public void setLsnum(String lsnum) {
        this.lsnum = lsnum;
    }

    public String getCarorg() {
        return carorg;
    }

    public void setCarorg(String carorg) {
        this.carorg = carorg;
    }

    public String getLstype() {
        return lstype;
    }

    public void setLstype(String lstype) {
        this.lstype = lstype;
    }

    public String getFrameno() {
        return frameno;
    }

    public void setFrameno(String frameno) {
        this.frameno = frameno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUsercarid() {
        return usercarid;
    }

    public void setUsercarid(Integer usercarid) {
        this.usercarid = usercarid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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