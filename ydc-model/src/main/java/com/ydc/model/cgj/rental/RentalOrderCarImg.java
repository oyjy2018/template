package com.ydc.model.cgj.rental;

import java.io.Serializable;
import java.util.Date;

public class RentalOrderCarImg implements Serializable {
    private static final long serialVersionUID = -5357088372167098425L;
    /**
     * t_rental_order_car_img.id
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Integer id;

    /**
     * t_rental_order_car_img.order_id (主订单id)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Integer orderId;

    /**
     * t_rental_order_car_img.describe_type (图片描述类型（1:出车,2:还车）)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Integer describeType;

    /**
     * t_rental_order_car_img.file_name (文件名)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private String fileName;

    /**
     * t_rental_order_car_img.file_url (文件路径)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private String fileUrl;

    /**
     * t_rental_order_car_img.file_type (文件类型)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private String fileType;

    /**
     * 文件code
     */
    private String fileCode;

    private String viewFileUrl;

    /**
     * t_rental_order_car_img.status (有效状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Integer status;

    /**
     * t_rental_order_car_img.create_time
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Date createTime;

    /**
     * t_rental_order_car_img.create_by
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDescribeType() {
        return describeType;
    }

    public void setDescribeType(Integer describeType) {
        this.describeType = describeType;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getViewFileUrl() {
        return viewFileUrl;
    }

    public void setViewFileUrl(String viewFileUrl) {
        this.viewFileUrl = viewFileUrl;
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
}