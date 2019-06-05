package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 分页
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private int number;

    /**
     * 页数
     */
    private int size;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Page(){}

    public Page(int number, int size) {
        this.number = number;
        this.size = size;
    }

    public Page changePage(){
        if (this.number<1){
            this.number=1;
        }
        if (this.size<1){
            this.size=10;
        }
        return new Page(((this.number - 1) * this.size), this.size);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}