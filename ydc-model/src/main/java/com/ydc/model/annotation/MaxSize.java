package com.ydc.model.annotation;

import java.lang.annotation.*;

/**
 * 最大长度验证类
 * @author:gongjin
 * @date：2018年10月11日 下午4:31:42
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSize {
	int max() default 50;
	String message() default "长度太长";
}
