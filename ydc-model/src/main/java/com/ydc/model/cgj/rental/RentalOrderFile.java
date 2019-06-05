package com.ydc.model.cgj.rental;

import com.ydc.model.annotation.Attribute;

import java.io.Serializable;
import java.util.Date;

/**
 * t_rental_order_file
 * @author 
 */
public class RentalOrderFile implements Serializable {
    private Integer id;

    /**
     * 文件ID
     */
    @Attribute(name = "文件ID",required = true,emptyStringVerify = true)
    private Integer fileId;

    /**
     * 订单ID
     */
    @Attribute(name = "订单ID",required = true,emptyStringVerify = true)
    private Integer orderId;

    /**
     * 文件编码
     */
    @Attribute(name = "文件编码",required = true,emptyStringVerify = true)
    private String fileCode;

    /**
     * 文件名
     */
    @Attribute(name = "文件名",required = true,emptyStringVerify = true)
    private String fileName;

    /**
     * 文件名简称
     */
    @Attribute(name = "文件名简称",required = true,emptyStringVerify = true)
    private String fileShortName;

    /**
     * 文件类型
     */
//    @Attribute(name = "文件类型",required = true,emptyStringVerify = true)
    private String fileType;

    /**
     * 文件路径
     */
    @Attribute(name = "文件路径",required = true,emptyStringVerify = true)
    private String fileUrl;

    /**
     * 缩略图
     */
    private String thumbnailUrl;

    /**
     * 状态（1：正常；0：禁用）
     */
    private String status;

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

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileShortName() {
        return fileShortName;
    }

    public void setFileShortName(String fileShortName) {
        this.fileShortName = fileShortName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}