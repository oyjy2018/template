package com.ydc.beans.shiro.filter;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义的过滤规则，在自动扫描的时候用，自定义过滤的时候不在使用默认的规则了
 * 拦截app
 * @author
 * @create 2018-12-05 11:47
 **/
public class WebFilterCustom implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取当前类注解的信息
        // AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类路径的信息
        // Resource resource = metadataReader.getResource();
        if (classMetadata.getClassName().startsWith("AppShiro")) {
            return true;
        }
        return false;
    }
}
