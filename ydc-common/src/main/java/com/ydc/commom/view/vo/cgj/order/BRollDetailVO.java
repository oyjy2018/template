package com.ydc.commom.view.vo.cgj.order;

import com.ydc.commom.constant.RollConstant;
import com.ydc.model.cgj.BRollDetail;

public class BRollDetailVO extends BRollDetail {

    private String rollStatusStr;

    public String getRollStatusStr() {
        return RollConstant.RollStatusEnum.getValueByKey(getStatus());
    }

    public void setRollStatusStr(String rollStatusStr) {
        this.rollStatusStr = rollStatusStr;
    }
}
