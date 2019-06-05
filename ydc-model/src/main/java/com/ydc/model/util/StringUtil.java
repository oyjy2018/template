package com.ydc.model.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

public class StringUtil {

    //字符串打码
    public static  String mosaicString(String str,int before,int after,int starNum){
        try{
            if (null==str || str.isEmpty()){
                return null;
            }
            if (str.length()<(before+after)){
                return  str;
            }
            StringBuffer sb=new StringBuffer();
            sb.append(str.substring(0,before));
            for (int i=0;i<starNum;i++){
                sb.append("*");
            }
            sb.append(str.substring(str.length()-after,str.length()));
            return  sb.toString();
        }catch ( Exception e){
            return null;
        }
    }

    public static Integer strToInteger(String str){
        if(StringUtils.isEmpty(str)) return null;
        return Integer.valueOf(str);
    }

    public static BigDecimal strToBigDecimal(String str){
        if(StringUtils.isEmpty(str)) return null;
        return new BigDecimal(str);
    }

    /**
     * Object 判断是否为空
     *
     * @author:gongjin
     * @param:
     * @date: 2017年11月27日 下午3:06:46
     * @return:boolean
     */
    public static boolean isEmpty(Object str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String str="中单手上待发送的国";
        System.out.println(mosaicString(str,3,4,4));
    }

}
