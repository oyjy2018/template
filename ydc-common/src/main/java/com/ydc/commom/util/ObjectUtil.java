package com.ydc.commom.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectUtil {

    /**
     * 复制对象（复制属性名一样的对象）
     *
     * @param t
     * @param r
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> void copyObject(T t, R r) {
        Field[] tFields = t.getClass().getDeclaredFields();
        Field[] rFields = r.getClass().getDeclaredFields();
        if (tFields != null && rFields != null && tFields.length > 0 && rFields.length > 0) {
            //获取t的field，fieldName
            List<Field> tFieldList = Arrays.asList(tFields);
            List<String> tFieldNameList = tFieldList.stream()
                    .map(Field::getName)
                    .collect(Collectors.toList());
            Map<String, Field> tFieldMap = new HashMap<>();
            tFieldList.forEach(field -> tFieldMap.put(field.getName(), field));

            //获取r的field，fieldName
            List<Field> rFieldList = Arrays.asList(rFields);
            List<String> rFieldNameList = rFieldList.stream()
                    .map(Field::getName)
                    .collect(Collectors.toList());
            Map<String, Field> rFieldMap = new HashMap<>();
            rFieldList.forEach(field -> rFieldMap.put(field.getName(), field));

            //处理t和r相同fieldName的field的值
            rFieldNameList.retainAll(tFieldNameList);
            rFieldNameList.forEach((fieldName) -> {
                try {
                    Field rField = rFieldMap.get(fieldName);
                    Field tField = tFieldMap.get(fieldName);
                    rField.setAccessible(true);
                    tField.setAccessible(true);
                    rField.set(r, tField.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 对象copy
     * @param r 返回
     * @param t 接收
     * @param <T>
     * @param <R>
     */
    public static <T,R> void copyProperties(R r, T t){
        try{
            PropertyUtils.copyProperties(r,t);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            PropertyUtils.clearDescriptors();
        }
    }

    /**
     * 获取对象的map
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> getObjectMap(T t) {
        if (t == null) {
            return null;
        }
        Field[] tFields = t.getClass().getDeclaredFields();
        if (tFields == null || tFields.length <= 0) {
            return null;
        }
        List<Field> tFieldList = Arrays.asList(tFields);
        return tFieldList.parallelStream()
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(t) != null && field.getType() == String.class;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toMap(field -> {
                    field.setAccessible(true);
                    return field.getName();
                }, field -> {
                    try {
                        field.setAccessible(true);
                        return (String) field.get(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return "";
                }, (t1, t2) -> t2));

    }
}
