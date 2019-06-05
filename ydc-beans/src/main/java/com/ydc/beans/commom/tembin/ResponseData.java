package com.ydc.beans.commom.tembin;

import java.io.Serializable;
import java.util.List;

public class ResponseData<T> implements Serializable{

    
    /** @Fields serialVersionUID: */
      	
    private static final long serialVersionUID = -7368822648954933396L;

    private ResponseHead header;
    
    private T businessData;
    
    private List<T> businessList;
    
    public ResponseData() {}
    
    public ResponseData(ResponseHead header, T businessData, List<T> businessList) {
        this.header = header;
        this.businessData = businessData;
        this.businessList = businessList;
    }

    public ResponseHead getHeader() {
        return header;
    }

    public void setHeader(ResponseHead header) {
        this.header = header;
    }

    public T getBusinessData() {
        return businessData;
    }

    public void setBusinessData(T businessData) {
        this.businessData = businessData;
    }

    public List<T> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<T> businessList) {
        this.businessList = businessList;
    }
    
    

}
