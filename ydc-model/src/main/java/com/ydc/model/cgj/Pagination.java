package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 分页
 */
public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private int page;

    /**
     * 页数
     */
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Pagination(){}

    public Pagination(int page, int rows){
        this.page = page;
        this.rows = rows;
    }

    public Pagination changePage(){
        if (this.page<1){
            this.page=1;
        }
        if (this.rows<1){
            this.rows=10;
        }
       return new Pagination(((this.page - 1) * this.rows), this.rows);
    }

    /**
     * 转换成SQL形式
     */
    public void convertSQLForm(){
        if(this.page > 0) this.page = (this.page - 1) * this.rows;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
