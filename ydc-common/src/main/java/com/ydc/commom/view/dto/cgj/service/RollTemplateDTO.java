package com.ydc.commom.view.dto.cgj.service;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-29 18:58
 **/
public class RollTemplateDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 2497301736309750284L;

    private String rollCode;
    private Integer updateBy;//操作人

    public RollTemplateDTO() {
    }

    public RollTemplateDTO(String rollCode,Integer updateBy) {
        this.rollCode = rollCode;
        this.updateBy = updateBy;
    }

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
