package com.ydc.commom.view.dto.cgj.sys;

import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.sys.CommImg;

public class DictionaryDetailDTO extends DictionaryDetail {

    /**
     * 公共文件信息
     */
    private CommImg commImg;

    public CommImg getCommImg() {
        return commImg;
    }
    public void setCommImg(CommImg commImg) {
        this.commImg = commImg;
    }

}
