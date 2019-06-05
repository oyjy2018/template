package com.ydc.commom.util;


import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    private static final Logger logger = LogManager.getLogger(MapUtil.class);

    /** 
     * 合并多个map 
     * @param maps 
     * @param <K> 
     * @param <V> 
     * @return 
     * @throws Exception 
     */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K, V> Map mergeMaps(Map<K, V>... maps) {  
        Class clazz = maps[0].getClass(); // 获取传入map的类型  
        Map<K, V> map = null;  
        try {  
            map = (Map) clazz.newInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        for (int i = 0, len = maps.length; i < len; i++) {  
            map.putAll(maps[i]);  
        }  
        return map;  
    }

    public static Double getMapValueToDouble(Map<String,Object> map,String key){
	    try {
            if (StringUtil.isEmpty(key)){
                return 0.0;
            }
            if (map.containsKey(key)){
                Object object =map.get(key);
                if (object !=null){
                    StringBuilder scoreString=new StringBuilder();
                    scoreString.append(object.toString());
                    scoreString.append("000000000000");
                    return new Double(scoreString.toString());
                }

            }
            return  0.0;
        }catch (Exception e){
	        return 0.0;
        }
    }

    public static Double getIntToDouble(Integer count){
	    try {
            if ( count !=null ){
                StringBuffer sb=new StringBuffer();
                sb.append(count);
                sb.append("000000000000");
                Double loanCountDouble=new Double(sb.toString());
                return loanCountDouble;
            }
        }catch (Exception e){

        }
	    return  0.0d;

    }


    public static Object getMapValueByKey(Map<String,Object> map,String key){
        try {
            if (StringUtil.isEmpty(key)){
                return null;
            }
            if (map.containsKey(key)){
                Object object =map.get(key);
                return object;
            }

        }catch (Exception e){

        }
        return null;
    }


    public static int getMapValueToInt(Map<String,Object> map,String key){
        try {
            if (StringUtil.isEmpty(key)){
                return 0;
            }
            if (map.containsKey(key)){
                Object object =map.get(key);
                if (object !=null){
                    String s=object.toString();
                    return new Integer(s).intValue();
                }

            }
            return  0;
        }catch (Exception e){
            return 0;
        }
    }


    public static BigDecimal getMapValueToBigDecimal(Map<String,Object> map, String key){
        try {
            if (StringUtil.isEmpty(key)){
                return BigDecimal.ZERO;
            }
            if (map.containsKey(key)){
                Object object =map.get(key);
                if (object !=null){
                    String s=object.toString();
                    BigDecimal resultBigDecimal=new BigDecimal(s);
                    return resultBigDecimal;
                }

            }
            return  BigDecimal.ZERO;
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }

    /**
     * 创建一个全部选项（公共参数接口用）
     * @return
     */
    public static Map<String, Object> getAllOption(String parentDictCode){
        Map<String, Object> dictionaryDetailMap = new HashMap<>();
        dictionaryDetailMap.put("dictKey","");
        dictionaryDetailMap.put("dictValue","全部");
        dictionaryDetailMap.put("parentDictCode",parentDictCode);
        return dictionaryDetailMap;
    }

    /**
     * 创建一个无选项（公共参数接口用）
     * @return
     */
    public static Map<String, Object> getNotHaveOption(String parentDictCode){
        Map<String, Object> dictionaryDetailMap = new HashMap<>();
        dictionaryDetailMap.put("dictKey","-");
        dictionaryDetailMap.put("dictValue","无");
        dictionaryDetailMap.put("parentDictCode",parentDictCode);
        return dictionaryDetailMap;
    }

    /**
     * 封装公共参数接口返回结果
     * @param dictionaryDetailList
     * @param columns
     * @return
     */
    public static List<Map<String, Object>> packParamOption(List<DictionaryDetail> dictionaryDetailList,String...columns){
        List<Map<String, Object>> dictionaryDetailMapList = new ArrayList<>();
        if(dictionaryDetailList == null || dictionaryDetailList.size() == 0) return dictionaryDetailMapList;

        Map<String, Object> dictionaryDetailMap = null;
        Class<DictionaryDetail> c = DictionaryDetail.class;
        Field field = null;
        for(DictionaryDetail dd : dictionaryDetailList){
            dictionaryDetailMap = new HashMap<>();
            dictionaryDetailMap.put("dictKey",dd.getDictKey());
            dictionaryDetailMap.put("dictValue",dd.getDictValue());
            dictionaryDetailMap.put("parentDictCode",dd.getParentDictCode());
            if(columns != null && columns.length > 0){
                for(String column:columns){
                    try {
                        field = c.getDeclaredField(column);
                        field.setAccessible(true);
                        dictionaryDetailMap.put(column,field.get(dd));
                    } catch (Exception e) {
                        logger.info("封装公共参数结果异常，参数类型为：【"+dd.getParentDictCode()+"】,字段属性：【"+column+"】",e);
                    }
                }
            }
            dictionaryDetailMapList.add(dictionaryDetailMap);
        }

        return dictionaryDetailMapList;
    }

    /**
     * 封装公共参数接口返回结果(Key与Value 都为 dictValue)
     * @param dictionaryDetailList
     * @param columns
     * @return
     */
    public static List<Map<String, Object>> packParamOptionCH(List<DictionaryDetail> dictionaryDetailList,String...columns){
        List<Map<String, Object>> dictionaryDetailMapList = new ArrayList<>();
        if(dictionaryDetailList == null || dictionaryDetailList.size() == 0) return dictionaryDetailMapList;

        Map<String, Object> dictionaryDetailMap = null;
        Class<DictionaryDetail> c = DictionaryDetail.class;
        Field field = null;
        for(DictionaryDetail dd : dictionaryDetailList){
            dictionaryDetailMap = new HashMap<>();
            dictionaryDetailMap.put("dictKey",dd.getDictValue());
            dictionaryDetailMap.put("dictValue",dd.getDictValue());
            dictionaryDetailMap.put("parentDictCode",dd.getParentDictCode());
            if(columns != null && columns.length > 0){
                for(String column:columns){
                    try {
                        field = c.getDeclaredField(column);
                        field.setAccessible(true);
                        dictionaryDetailMap.put(column,field.get(dd));
                    } catch (Exception e) {
                        logger.info("封装公共参数结果异常，参数类型为：【"+dd.getParentDictCode()+"】,字段属性：【"+column+"】",e);
                    }
                }
            }
            dictionaryDetailMapList.add(dictionaryDetailMap);
        }

        return dictionaryDetailMapList;
    }
}
