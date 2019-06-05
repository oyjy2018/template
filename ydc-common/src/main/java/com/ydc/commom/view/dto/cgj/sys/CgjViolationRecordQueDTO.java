package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;
import java.util.Date;

/**
 * 违章记录
 * 
 * @author wcyong
 * 
 * @date 2018-12-29
 */
public class CgjViolationRecordQueDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1360298220922294598L;
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
     * 	是否返回城市 1返回 默认0不返回 不一定100%返回结果，准确度90% town、lat、lng仅供参考
     */
    private Integer iscity;
    /**
     * 手机号 浙江直连需要手机号
     */
    private String mobile;

    /**
     * 查询时间记录
     */
    private Date checkTime;

    private Integer recordId;

    private Integer dealStatus;

    public CgjViolationRecordQueDTO(int page, int rows, Integer id, String lsprefix, String lsnum, String carorg, String lstype, String frameno, String engineno, Integer iscity, String mobile, Date checkTime,Integer recordId,Integer dealStatus) {
        super(page, rows);
        this.id = id;
        this.lsprefix = lsprefix;
        this.lsnum = lsnum;
        this.carorg = carorg;
        this.lstype = lstype;
        this.frameno = frameno;
        this.engineno = engineno;
        this.iscity = iscity;
        this.mobile = mobile;
        this.checkTime = checkTime;
        this.recordId = recordId;
        this.dealStatus = dealStatus;
    }

    public CgjViolationRecordQueDTO() {
        super();
    }

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
        this.lsprefix = lsprefix == null ? null : lsprefix.trim();
    }

    public String getLsnum() {
        return lsnum;
    }

    public void setLsnum(String lsnum) {
        this.lsnum = lsnum == null ? null : lsnum.trim();
    }

    public String getCarorg() {
        return carorg;
    }

    public void setCarorg(String carorg) {
        this.carorg = carorg == null ? null : carorg.trim();
    }

    public String getLstype() {
        return lstype;
    }

    public void setLstype(String lstype) {
        this.lstype = lstype == null ? null : lstype.trim();
    }

    public String getFrameno() {
        return frameno;
    }

    public void setFrameno(String frameno) {
        this.frameno = frameno == null ? null : frameno.trim();
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno == null ? null : engineno.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getIscity() {
        return iscity;
    }

    public void setIscity(Integer iscity) {
        this.iscity = iscity;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }
}