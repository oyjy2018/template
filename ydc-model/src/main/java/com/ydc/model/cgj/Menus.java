package com.ydc.model.cgj;

import java.io.Serializable;

/**
 * @author
 * @create 2018-12-12 10:27
 **/
public class Menus implements Serializable {
    private static final long serialVersionUID = -5423110130314875352L;

    private Integer id;
    private String parentMenuCode;
    private String menuName;
    private String menuCode;
    private String funCode;
    private String serviceIdentifying;
    private String funName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getServiceIdentifying() {
        return serviceIdentifying;
    }

    public void setServiceIdentifying(String serviceIdentifying) {
        this.serviceIdentifying = serviceIdentifying;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
