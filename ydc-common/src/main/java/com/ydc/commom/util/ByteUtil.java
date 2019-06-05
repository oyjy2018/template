package com.ydc.commom.util;

public class ByteUtil {

    public static  Byte convertIntegerToByte(Integer integer){
        try {
            if (null==integer){
                return null;
            }
            return integer.byteValue();

        }catch (Exception e){

        }
        return null;
    }
}
