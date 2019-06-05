package com.ydc.commom.view.dto.ydhc;

import com.ydc.model.annotation.Attribute;

public class YdhcVehicleImgDTO {
    @Attribute(name = "图片类型", isNum = true)
    private String imgType;
    @Attribute(name = "图片描述类型", preAttr = "imgType", preAttrVal = "2")
    private String describeType;
    @Attribute(name = "文件名", required = true)
    private String fileName;
    @Attribute(name = "文件路径", required = true)
    private String fileUrl;
    @Attribute(name = "缩略图文件名", required = true)
    private String littleFileName;
    @Attribute(name = "缩略图文件路径", required = true)
    private String littleFileUrl;
    @Attribute(name = "文件类型", required = true)
    private String fileType;

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getDescribeType() {
        return describeType;
    }

    public void setDescribeType(String describeType) {
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
}
