package com.ydc.model.cgj.car;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * t_comm_car_img
 * @author 
 */
public class CommCarImg implements Serializable {

    public CommCarImg() {
    }

    public CommCarImg(Map<String, Object> param) {
        try {
            this.id =  (param.get("id") == null) ? null: Integer.valueOf(String.valueOf(param.get("id")));
            this.businessType = (String) param.get("businessType");
            this.describeType = (param.get("describeType") == null) ? null:Integer.valueOf(String.valueOf(param.get("describeType")));
            this.fileName = (String) param.get("fileName");
            this.fileUrl = (String) param.get("fileUrl");
            this.littleFileName = (String) param.get("littleFileName");
            this.littleFileUrl = (String) param.get("littleFileUrl");
            this.fileType = (String)param.get("fileType");
            this.imgDescribe = (String)param.get("imgDescribe");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("构造图片异常");
        }
    }
    private Integer id;

    private Integer carId;

    /**
     * 图片业务类型（zuche:租车）
     */
    private String businessType;

    /**
     * 图片描述类型（1:交强险照片,2:商业险照片,3:行驶证正页照片,4:行驶证背页照片,5:车辆登记证照片,6:车辆正前照片,7:车辆左前照片,8:车辆右前照片,9:车量左后照片,10:车辆右后照片,11:车辆仪表盘照片,12:车辆其他照片）
     */
    private Integer describeType;

    /**
     * 图片文字性描述
     */
    private String imgDescribe;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 缩略图文件名
     */
    private String littleFileName;

    /**
     * 缩略图文件路径
     */
    private String littleFileUrl;

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

    //图片在线浏览 无对应数据库字段
    private String viewFileUrl;

    //缩略图在线浏览 无对应数据库字段
    private String viewLittleFileUrl;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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