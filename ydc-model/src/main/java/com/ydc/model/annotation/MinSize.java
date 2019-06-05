package com.ydc.model.annotation;

import java.lang.annotation.*;

/**
 * 最小长度验证类
 * @author:gongjin
 * @date：2018年10月11日 下午4:32:38
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSize {
	int minLen() default 1;
	String message() default "长度太短";
}
