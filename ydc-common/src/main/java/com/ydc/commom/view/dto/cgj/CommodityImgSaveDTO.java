package com.ydc.commom.view.dto.cgj;

import com.ydc.model.annotation.Attribute;

public class CommodityImgSaveDTO {
    @Attribute(name = "商品图片ID", isNum = true)
    private Integer id;
    @Attribute(name = "图片类型", required = true)
    private String imgType;
    @Attribute(name = "文件名", required = true)
    private String fileName;
    @Attribute(name = "文件路径", required = true)
    private String fileUrl;
    @Attribute(name = "缩略图文件名", required = true)
    private String littleFileName;
    @Attribute(name = "缩略图文件路径", required = true)
    private String littleFileUrl;
    @Attribute(name = "文件后缀", required = true)
    private String fileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
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
}
