package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import java.io.Serializable;

/**
 * 订单详情：订单资料
 *
 * @author
 * @create 2019-01-05 16:21
 **/
public class PCRentalEnterpriseDetailsFileVO implements Serializable {
    private static final long serialVersionUID = -8668605403920969137L;

    private Integer fileId;//文件id
    private String fileDirName;//文件名
    private String fileCode;//文件code
    private Integer count;//文件数量

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileDirName() {
        return fileDirName;
    }

    public void setFileDirName(String fileDirName) {
        this.fileDirName = fileDirName;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
