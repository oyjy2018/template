package com.ydc.model.util;

import java.util.Map;

public class MapUtil {


    public static String getMapValueByKey(Map<String,String[]> map, String key){
        try {
            if (null==key || key.isEmpty()){
                return null;
            }
            if (map.containsKey(key)){
                String[] object =map.get(key);
                return object[0];
            }

        }catch (Exception e){

        }
        return null;
    }

}
