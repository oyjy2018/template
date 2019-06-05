package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * @Title 申请类型
 * @date 2019/1/24
 */
public enum ApplyEnum {
    APPLY_TYPE_1(1,"车主贷"),
    APPLY_TYPE_2(2,"车抵贷"),
    APPLY_TYPE_3(3,"我要出租"),
    APPLY_TYPE_4(4,"我要租车"),
    APPLY_TYPE_5(5,"购车分期"),
    APPLY_TYPE_6(6,"我要买车"),
    APPLY_TYPE_7(7,"我要卖车"),
    APPLY_TYPE_100(100,"车辆估价")
    ;
    private int code;
    private String name;
    public static EnumSet<ApplyEnum> applyEnums = EnumSet.allOf(ApplyEnum.class);


    ApplyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getCodeName(Integer code){
        if(StringUtil.isEmpty(code))return "";
        Optional<String> codeName =  applyEnums.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
        return codeName.orElse("");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
