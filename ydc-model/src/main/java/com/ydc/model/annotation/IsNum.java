package com.ydc.model.annotation;

import java.lang.annotation.*;

/**
 * 校验是否为整数
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNum {
    boolean isNum() default true;
    String message() default "数值必须为整数";
}
