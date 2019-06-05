package com.ydc.commom.view.dto.cgj.sys;


import com.ydc.model.cgj.ServiceFunc;
import com.ydc.model.cgj.sys.CommImg;


public class ServiceFuncDTO extends ServiceFunc {
    /**
     * 公共文件信息
     */
    private CommImg commImg;

    private String parentDictCode;

    public CommImg getFile() {
        return commImg;
    }

    public void setFile(CommImg commImg) {
        this.commImg = commImg;
    }

    public String getParentDictCode(){return parentDictCode;}

    public void setParentDictCode(String parentDictCode){this.parentDictCode = parentDictCode;}
}
