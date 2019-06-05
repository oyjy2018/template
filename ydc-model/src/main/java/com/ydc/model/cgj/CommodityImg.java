package com.ydc.model.cgj;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 商品图片信息
 */
public class CommodityImg implements Serializable {
    private static final long serialVersionUID = 8094446437408140633L;
    /**
     * t_c_commodity_img.id
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer id;

    /**
     * t_c_commodity_img.commodity_id
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer commodityId;

    /**
     * t_c_commodity_img.img_type (图片类型（1：首页图片；2：轮播图片；3：描述图片）)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer imgType;

    /**
     * t_c_commodity_img.file_name (文件名)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String fileName;

    /**
     * t_c_commodity_img.file_url (文件路径)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String fileUrl;

    /**
     * t_c_commodity_img.little_file_name (缩略图文件名)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String littleFileName;

    /**
     * t_c_commodity_img.little_file_url (缩略图文件路径)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String littleFileUrl;

    /**
     * t_c_commodity_img.file_type (文件类型)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private String fileType;

    /**
     * t_c_commodity_img.status (有效状态)
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer status;

    /**
     * t_c_commodity_img.create_time
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Date createTime;

    /**
     * t_c_commodity_img.create_by
     *
     * @ibatorgenerated 2018-09-04 16:28:24
     */
    private Integer createBy;

    private String viewFileUrl;

    private String viewLittleFileUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getViewFileUrl() {
        return viewFileUrl;
    }

    public void setViewFileUrl(String viewFileUrl) {
        this.viewFileUrl = viewFileUrl;
    }

    public String getViewLittleFileUrl() {
        return viewLittleFileUrl;
    }

    public void setViewLittleFileUrl(String viewLittleFileUrl) {
        this.viewLittleFileUrl = viewLittleFileUrl;
    }

    public CommodityImg(){

    }

    public CommodityImg(Map<String, String> param) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.id = StringUtils.isEmpty(param.get("id")) ? null : Integer.valueOf(param.get("id"));
        this.commodityId = StringUtils.isEmpty(param.get("commodityId")) ? null : Integer.valueOf(param.get("commodityId"));
        this.imgType = StringUtils.isEmpty(param.get("imgType")) ? null : Integer.valueOf(param.get("imgType"));
        this.fileName = param.get("fileName");
        this.fileUrl = param.get("fileUrl");
        this.littleFileName = param.get("littleFileName");
        this.littleFileUrl = param.get("littleFileUrl");
        this.fileType = param.get("fileType");
        this.status = StringUtils.isEmpty(param.get("status")) ? null : Integer.valueOf(param.get("status"));
        this.createTime = StringUtils.isEmpty(param.get("createTime")) ? null:sdf.parse(param.get("createTime"));
        this.createBy = StringUtils.isEmpty(param.get("createBy")) ? null : Integer.valueOf(param.get("createBy"));
    }
}