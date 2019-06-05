package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.IsEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * 出车订单图片
 *
 * @author
 * @create 2018-11-24 11:13
 **/
public class ComeCarOrderImgDTO implements Serializable {
    private static final long serialVersionUID = -1237279553316717559L;


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
    @IsEmpty(message = "文件名不能为空")
    private String fileName;

    /**
     * t_rental_order_car_img.file_url (文件路径)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    @IsEmpty(message = "文件路径不能为空")
    private String fileUrl;

    /**
     * t_rental_order_car_img.file_type (文件类型)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    @IsEmpty(message = "文件类型不能为空")
    private String fileType;

    /**
     * 文件code
     */
    @IsEmpty(message = "文件code不能为空")
    private String fileCode;

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

    /**
     * t_rental_order_car_img.file_url (文件访问路径)
     * @ibatorgenerated 2018-11-22 18:46:52
     */
    private String browseFileUrl;

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

    public String getBrowseFileUrl() {
        return browseFileUrl;
    }

    public void setBrowseFileUrl(String browseFileUrl) {
        this.browseFileUrl = browseFileUrl;
    }
}
