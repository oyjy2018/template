package com.ydc.beans.commom.tembin;

import java.io.Serializable;
import java.util.List;

public class RequestData<T> implements Serializable {

    
    /** @Fields serialVersionUID: */
      	
    private static final long serialVersionUID = -6670194463939562707L;

    private RequestHead header;
    
    private T businessData;
    
    private List<T> businessList;
    
    public RequestData() {}
    
    public RequestData(RequestHead header, T businessData) {
        this.header = header;
        this.businessData = businessData;
    }
    
    public RequestData(RequestHead header, List<T> businessList) {
        this.header = header;
        this.businessList = businessList;
    }
    
    public RequestData(RequestHead header, T businessData, List<T> businessList) {
        this.header = header;
        this.businessData = businessData;
        this.businessList = businessList;
    }

    public RequestHead getHeader() {
        return header;
    }

    public void setHeader(RequestHead header) {
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
