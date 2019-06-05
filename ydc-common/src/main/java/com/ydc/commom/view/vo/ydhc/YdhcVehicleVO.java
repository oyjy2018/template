package com.ydc.commom.view.vo.ydhc;

import com.ydc.commom.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class YdhcVehicleVO {
    private Integer vehicleId;
    private String title;
    private BigDecimal price;
    private String createTime;
    private String releaseDate;
    private String shelveDate;
    private String fileName;
    private String fileUrl;
    private String viewFileUrl;
    private String littleFileName;
    private String littleFileUrl;
    private String viewLittleFileUrl;
    private String fileType;
    private Integer release_status;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price.divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.formatDateAndTime(createTime);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = DateUtil.formatDateAndTime(releaseDate);
    }

    public String getShelveDate() {
        return shelveDate;
    }

    public void setShelveDate(Date shelveDate) {
        this.shelveDate = DateUtil.formatDateAndTime(shelveDate);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getViewFileUrl() {
        return viewFileUrl;
    }

    public void setViewFileUrl(String viewFileUrl) {
        this.viewFileUrl = viewFileUrl;
    }

    public String getLittleFileName() {
        return littleFileName;
    }

    public void setLittleFileName(String littleFileName) {
        this.littleFileName = littleFileName;
    }

    public String getLittleFileUrl() {
        return littleFileUrl;
    }

    public void setLittleFileUrl(String littleFileUrl) {
        this.littleFileUrl = littleFileUrl;
    }

    public String getViewLittleFileUrl() {
        return viewLittleFileUrl;
    }

    public void setViewLittleFileUrl(String viewLittleFileUrl) {
        this.viewLittleFileUrl = viewLittleFileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getRelease_status() {
        return release_status;
    }

    public void setRelease_status(Integer release_status) {
        this.release_status = release_status;
    }
}
