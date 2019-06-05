package com.ydc.commom.view.dto.cgj;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 后台商品
 *
 * @author
 * @create 2018-11-14 11:11
 **/
public class CommodityDTO implements Serializable {
    private static final long serialVersionUID = -6195785946543655019L;

    private Integer commodityId;//商品ID，以下参数接收时全部以String类型接收
    private String title;//商品名称
    private String startCreateTime;//录入时间-起
    private String endCreateTime;//录入时间-止
    private Integer releaseStatus;//发布状态,默认传0（0：未发布；1已发布；2：已下架）,必传
    private Integer page;
    private Integer rows;
    private String supplierCode;//供应商CODE
    private String sellStatus;//销售状态（0：已售罄；1销售中）



    public CommodityDTO() {
    }

    public CommodityDTO(Integer commodityId, String title, String startCreateTime, String endCreateTime,
                        Integer releaseStatus, Integer page, Integer rows,String supplierCode,String sellStatus) {
        this.commodityId = commodityId;
        this.title = title;
        this.startCreateTime = startCreateTime;
        this.endCreateTime = endCreateTime;
        this.releaseStatus = releaseStatus;
        this.page = page;
        this.rows = rows;
        this.supplierCode = supplierCode;
        this.sellStatus = sellStatus;
    }

    public CommodityDTO changeDTO(){
        return new CommodityDTO(this.commodityId,this.title,(StringUtils.isEmpty(this.startCreateTime) ? null : this.startCreateTime+" 00:00:00"),
                (StringUtils.isEmpty(this.endCreateTime) ? null : this.endCreateTime +" 23:59:59"),this.releaseStatus,
                (StringUtils.isEmpty(this.page) ? null : ((this.page - 1) * this.rows)),this.rows,this.supplierCode,this.sellStatus);
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }
}
