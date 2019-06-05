package com.ydc.beans.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author
 * @create 2018-12-05 17:59
 **/
@Configuration
public class FilterInterceptConfig {


    /**
     * 配置过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(securityFilter());
        registration.addUrlPatterns("/*");//过滤所有请求
        registration.addInitParameter("exclusions", ExclusionsEnum.getExclusionsEnum());
//        registration.addInitParameter("error", "/login/error");
        registration.addInitParameter("paramName", "paramValue");

        registration.setName("securityFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }


    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean(name = "securityFilter")
    public Filter securityFilter() {
        return new SecurityFilter();
    }
}
