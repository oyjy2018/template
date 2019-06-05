package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2019-01-07 14:29
 **/
public class RentalOrderFileVO implements Serializable {
    private static final long serialVersionUID = 3627405340832486923L;

    private Integer id;

    /**
     * 文件ID
     */
    private Integer fileId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 文件编码
     */
    private String fileCode;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件名简称
     */
    private String fileShortName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件路径
     */
    private String fileUrl;


    private String browseFileUrl;

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

    /**
     * 创建人
     */
    private String userName;

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

    public String getBrowseFileUrl() {
        return browseFileUrl;
    }

    public void setBrowseFileUrl(String browseFileUrl) {
        this.browseFileUrl = browseFileUrl;
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

    @JSONField(format = DateUtil.DATATIMEF_STR)
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
    @JSONField(format = DateUtil.DATATIMEF_STR)
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
