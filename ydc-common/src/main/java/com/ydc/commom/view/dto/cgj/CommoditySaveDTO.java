package com.ydc.commom.view.dto.cgj;

import com.ydc.model.annotation.Attribute;

public class CommoditySaveDTO {
    @Attribute(name = "商品ID", isNum = true)
    private String id;
    @Attribute(name = "商品名称", required = true)
    private String title;
    @Attribute(name = "供应商code", required = true)
    private String supplierCode;
    @Attribute(name = "供应商名称", required = true)
    private String supplierName;
    @Attribute(name = "产品主分类", required = true)
    private String mainClassifyCode;
    @Attribute(name = "产品主分类名称", required = true)
    private String mainClassifyName;
    @Attribute(name = "产品子分类code", required = true)
    private String sonClassifyCode;
    @Attribute(name = "产品子分类名称", required = true)
    private String sonClassifyName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMainClassifyCode() {
        return mainClassifyCode;
    }

    public void setMainClassifyCode(String mainClassifyCode) {
        this.mainClassifyCode = mainClassifyCode;
    }

    public String getMainClassifyName() {
        return mainClassifyName;
    }

    public void setMainClassifyName(String mainClassifyName) {
        this.mainClassifyName = mainClassifyName;
    }

    public String getSonClassifyCode() {
        return sonClassifyCode;
    }

    public void setSonClassifyCode(String sonClassifyCode) {
        this.sonClassifyCode = sonClassifyCode;
    }

    public String getSonClassifyName() {
        return sonClassifyName;
    }

    public void setSonClassifyName(String sonClassifyName) {
        this.sonClassifyName = sonClassifyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
