package com.ydc.commom.view.dto.cgj.sys;


import com.ydc.model.cgj.CgjCarzoneCfg;
import com.ydc.model.cgj.sys.CommImg;

import java.io.Serializable;
import java.util.Date;

/**
 * 车圈配置
 * 
 * @author wcyong
 * 
 * @date 2018-12-29
 */
public class CgjCarzoneCfgSaveDTO implements Serializable{
    private static final long serialVersionUID = -8744123490737567140L;
    /**
     * 车圈配置
     */
    private CgjCarzoneCfg cgjCarzoneCfg;
    /**
     * 车圈配置图标
     */
    private CommImg commImg;

    public CgjCarzoneCfgSaveDTO(CgjCarzoneCfg cgjCarzoneCfg, CommImg commImg) {
        this.cgjCarzoneCfg = cgjCarzoneCfg;
        this.commImg = commImg;
    }

    public CgjCarzoneCfgSaveDTO() {
        super();
    }

    public CgjCarzoneCfg getCgjCarzoneCfg() {
        return cgjCarzoneCfg;
    }

    public void setCgjCarzoneCfg(CgjCarzoneCfg cgjCarzoneCfg) {
        this.cgjCarzoneCfg = cgjCarzoneCfg;
    }

    public CommImg getCommImg() {
        return commImg;
    }

    public void setCommImg(CommImg commImg) {
        this.commImg = commImg;
    }
}