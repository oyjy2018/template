package com.ydc.commom.view.vo.cgj.rental;


/**
 * 车辆列表信息
 */
public class RentalCarImgVO {

    /**
     * 图片名称
     */
    private String fileName;
    /**
     * 图片地址
     */
    private String fileUrl;

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

    @Override
    public String toString() {
        return "RentalCarImgVO{" +
                "fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}