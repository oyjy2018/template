package com.ydc.model.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalFormat {

    boolean format() default true;
    String message() default "金额不能为负数！";
}
