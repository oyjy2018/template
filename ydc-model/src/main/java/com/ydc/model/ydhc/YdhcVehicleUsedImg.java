package com.ydc.model.ydhc;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * t_ydhc_vehicle_img
 *
 * @author
 */
public class YdhcVehicleUsedImg implements Serializable {
    private Integer id;

    private Integer vehicleId;

    /**
     * 图片类型（1：主图图片；2：描述图片）
     */
    private Integer imgType;

    /**
     * 图片描述类型（图片类型为2时使用）（1：左前45度照；2：正面照；3：主驾驶门叶照；4：车后照；5：仪表盘；6：中控台音响近照；7：左方向盘照；8：轮胎近照）
     */
    private Integer describeType;

    private String imgDescribe;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    private String viewFileUrl;

    /**
     * 缩略图文件名
     */
    private String littleFileName;

    /**
     * 缩略图文件路径
     */
    private String littleFileUrl;

    private String viewLittleFileUrl;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 有效状态（0：无效；1：有效）
     */
    private Integer status;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Integer getDescribeType() {
        return describeType;
    }

    public void setDescribeType(Integer describeType) {
        this.describeType = describeType;
    }

    public String getImgDescribe() {
        return imgDescribe;
    }

    public void setImgDescribe(String imgDescribe) {
        this.imgDescribe = imgDescribe;
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

    public YdhcVehicleUsedImg() {

    }

    public YdhcVehicleUsedImg(Map<String, Object> param) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.id =  (param.get("id") == null) ? null: Integer.valueOf(String.valueOf(param.get("id")));
            this.imgType = (param.get("imgType") == null) ? null:Integer.valueOf(param.get("imgType")+"");
            this.describeType = (param.get("describeType") == null) ? null:Integer.valueOf(param.get("describeType")+"");
            this.fileName = (String) param.get("fileName");
            this.fileUrl = (String) param.get("fileUrl");
            this.littleFileName = (String) param.get("littleFileName");
            this.littleFileUrl = (String) param.get("littleFileUrl");
            this.fileType = (String)param.get("fileType");
            this.status = StringUtils.isEmpty((String)param.get("status")) ? null : Integer.valueOf((String)param.get("status"));
            this.imgDescribe = (String)param.get("imgDescribe");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间格式化异常");
        }
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
}