package com.ydc.model.annotation;


import java.lang.annotation.*;

/**
 * 空指针验证类
 * @author:gongjin
 * @date：2018年10月11日 下午4:31:09
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsEmpty {
    boolean isEmpty() default true;
    String message() default "字段不能为空！";
}
