package com.ydc.commom.view.vo.cgj;

import com.ydc.model.cgj.ServiceFunc;

import java.io.Serializable;
import java.util.List;

public class ServiceFuncVO{
    private String parentDictCode;
    private String parentDictCodeValue;
    private String dictKey;
    private String dictValue;
    private String serverSide;
    private List<DictionaryDedailVO> serviceFuncList;
    public String getParentDictCode() {
        return parentDictCode;
    }
    public void setParentDictCode(String parentDictCode) {
        this.parentDictCode = parentDictCode;
    }
    public String getParentDictCodeValue() {
        return parentDictCodeValue;
    }
    public void setParentDictCodeValue(String parentDictCodeValue) {
        this.parentDictCodeValue = parentDictCodeValue;
    }
    public String getDictKey() {
        return dictKey;
    }
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }
    public String getDictValue() {
        return dictValue;
    }
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    public String getServerSide() {
        return serverSide;
    }
    public void setServerSide(String serverSide) {
        this.serverSide = serverSide;
    }
    public List<DictionaryDedailVO> getServiceFuncList() {
        return serviceFuncList;
    }
    public void setServiceFuncList(List<DictionaryDedailVO> serviceFuncList) {
        this.serviceFuncList = serviceFuncList;
    }
    @Override
    public String toString() {
        return "T [parentDictCode=" + parentDictCode + ", parentDictCodeValue="
                + parentDictCodeValue + ", dictKey=" + dictKey + ", dictKey=" + dictKey + ", dictValue="
                + dictValue + ", serviceFuncList=" + serviceFuncList + "]";
    }


}
