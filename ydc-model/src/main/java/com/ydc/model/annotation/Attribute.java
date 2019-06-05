package com.ydc.model.annotation;


import java.lang.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {
    // 字段属性说明
    String name() default "";

    // 字段是否必填
    boolean required() default false;

    //字符串是否为空
    boolean emptyStringVerify() default false;

    //字符串是否去空格
    boolean isStringTrim() default true;

    //是否是数字（整数小数）
    boolean isDigit() default false;

    //整数长度(前置条件：isDigit属性必须为true)
    int intLength() default 0;

    //小数长度(前置条件：isDigit属性必须为true)
    int decimalLength() default 0;

    //是否是整数
    boolean isNum() default false;

    // 字段固定长度限制
    int length() default 0;

    // 字段最大长度限制
    int maxLength() default 0;

    // 日期类型格式限制
    String dateFormat() default "";

    // 赋值限制列表，用于下拉列表
    String[] valueSetLimit() default {};

    //前置属性
    String preAttr() default "";

    String preAttrVal() default "";
}
