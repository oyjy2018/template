package com.ydc.commom.view.vo.cgj.sys;

import com.ydc.model.cgj.sys.CgjViolationRecordDetail;

import java.io.Serializable;
import java.util.List;

/**
 * 违章记录详情
 *
 * @author wcyong
 * @date 2018-12-29
 */
public class CgjViolationRecordVO implements Serializable {
    private static final long serialVersionUID = -2239561691979337158L;
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
     * 违章状态信息
     */
    private String ViolationStatusMsg;

    /**
     * 违章记录详情
     */
    List<CgjViolationRecordDetail> list;

    public String getViolationStatusMsg() {
        return ViolationStatusMsg;
    }

    public void setViolationStatusMsg(String violationStatusMsg) {
        ViolationStatusMsg = violationStatusMsg;
    }

    public CgjViolationRecordVO(String lsprefix, String lsnum, String carorg, Integer usercarid, Integer count, String totalprice, String totalscore, String violationStatusMsg, List<CgjViolationRecordDetail> list) {

        this.lsprefix = lsprefix;
        this.lsnum = lsnum;
        this.carorg = carorg;
        this.usercarid = usercarid;
        this.count = count;
        this.totalprice = totalprice;
        this.totalscore = totalscore;
        ViolationStatusMsg = violationStatusMsg;
        this.list = list;
    }

    public CgjViolationRecordVO() {
        super();
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

    public List<CgjViolationRecordDetail> getList() {
        return list;
    }

    public void setList(List<CgjViolationRecordDetail> list) {
        this.list = list;
    }
}