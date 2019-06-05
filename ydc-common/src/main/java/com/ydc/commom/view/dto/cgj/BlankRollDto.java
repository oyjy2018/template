package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 空白券查询类
 */
public class BlankRollDto extends Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 券码
     */
    private String rollCode;
    /**
     * 失效开始时间
     */
    private String startInvalidTime;
    /**
     * 失效结束时间
     */
    private String endInvalidTime;

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public String getStartInvalidTime() {
        return startInvalidTime;
    }

    public void setStartInvalidTime(String startInvalidTime) {
        this.startInvalidTime = startInvalidTime;
    }

    public String getEndInvalidTime() {
        return endInvalidTime;
    }

    public void setEndInvalidTime(String endInvalidTime) {
        this.endInvalidTime = endInvalidTime;
    }

    public void changeEndTime(String time){
        if (this.endInvalidTime != null && !("").equals(this.endInvalidTime)){
            this.endInvalidTime = this.endInvalidTime + time;
        }
    }
}
