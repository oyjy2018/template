package com.ydc.commom.enums.cgj;

import com.ydc.commom.util.StringUtil;

import java.util.EnumSet;
import java.util.Optional;

/**
 * B端门店表
 *
 * @author
 * @create 2018-12-07 15:14
 **/
public enum BCarServiceShopEnum {
    WHETHER_PUTAWAY_0(0,"已下架"),
    WHETHER_PUTAWAY_1(1,"已上架")
    ;

    private int code;
    private String name;

    private static EnumSet<BCarServiceShopEnum> bCarServiceShopEnum = EnumSet.allOf(BCarServiceShopEnum.class);


    BCarServiceShopEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getCodeName(Integer code){
        if(StringUtil.isEmpty(code))return "";
        Optional<String> codeName =  bCarServiceShopEnum.stream().filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
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
