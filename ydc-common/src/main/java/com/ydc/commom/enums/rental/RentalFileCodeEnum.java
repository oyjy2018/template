package com.ydc.commom.enums.rental;

/**
 * 租车文件code
 *
 * @author
 * @create 2018-11-23 17:56
 **/
public enum RentalFileCodeEnum {

    COME_CAR_IMG("come:car:img","出车车身照片"),
    COME_CAR_IMG_ZQ("come:car:img:zq","出车车身照片:左前"),
    COME_CAR_IMG_YQ("come:car:img:yq","出车车身照片:右前"),
    COME_CAR_IMG_ZH("come:car:img:zh","出车车身照片:左后"),
    COME_CAR_IMG_YH("come:car:img:yh","出车车身照片:右后"),
    COME_CAR_IMG_YBP("come:car:img:ybp","出车车身照片:仪表盘"),
    COME_CAR_IMG_QT("come:car:img:qt","出车车身照片:其他"),
    REPAY_CAR_IMG("repay:car:img","还车车身照片"),
    REPAY_CAR_IMG_ZQ("repay:car:img","出车车身照片:左前"),
    REPAY_CAR_IMG_YQ("repay:car:img","出车车身照片:右前"),
    REPAY_CAR_IMG_ZH("repay:car:img","出车车身照片:左后"),
    REPAY_CAR_IMG_YH("repay:car:img","出车车身照片:右后"),
    REPAY_CAR_IMG_YBP("repay:car:img","出车车身照片:仪表盘"),
    REPAY_CAR_IMG_QT("repay:car:img","出车车身照片:其他")

    ;

    private String fileCode;
    private String fileName;

     RentalFileCodeEnum(String fileCode,String fileName){
        this.setFileCode(fileCode);
        this.setFileName(fileName);
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
