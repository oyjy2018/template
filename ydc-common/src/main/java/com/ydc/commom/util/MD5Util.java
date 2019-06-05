package com.ydc.commom.util;

import java.security.MessageDigest;


public class MD5Util {
    /**
     * 获取MD5字符串
     *
     * @param str
     * @return
     * @author huyueming
     * @date 2017年7月20日
     */
    public static String toMd5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = str.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取用户密码
     *
     * @param memberId
     * @param password
     * @return
     */
    public static String getPassword(Integer memberId, String password) {
        return toMd5(password.concat(String.valueOf(memberId)));
    }

    public static void main(String[] args) {
        System.out.println(getPassword(256,"djb123456"));
    }
}
