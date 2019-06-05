package com.ydc.beans.commom.tembin;


import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/** 
 * @Description AES加解密
 * @author lyf
 * @date 2018年8月11日 下午3:05:52 
 */ 
  	
public class AESUtil {

    private static final Logger logger = LogManager.getLogger(AESUtil.class);
    /** 
     * @Description AES加密
     * @author lyf
     * @param input 明文
     * @param key 密码
     * @return  String
     */
      	
    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey(); 
            byte[] enCodeFormat = secretKey.getEncoded();   
            SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");   
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
            byte[] byteContent = input.getBytes("utf-8");   
            cipher.init(Cipher.ENCRYPT_MODE, skey); 
            crypted = cipher.doFinal(byteContent);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(crypted));
    }

     
    /** 
     * @Description AES解密
     * @author lyf
     * @param input 密文
     * @param key 密码
     * @return  String
     */
      	
    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );   
            secureRandom.setSeed(key.getBytes());   
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();   
            byte[] enCodeFormat = secretKey.getEncoded();   
            SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.decodeBase64(input)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String key = "kd9sj3jd87d6@kd8dj2@20dc>2d4f90s";
        String data = "我是刘云峰，之前用RSA非对称加密，发现明文长度有限制，真JB坑，现在改用AES，吸说这个加密快，不知道是真是假，我就测试一把看看,3!,2!,1 开始  gogogog";
        long start = System.currentTimeMillis();
       logger.info(AESUtil.decrypt(AESUtil.encrypt(data, key), key));
       logger.info(AESUtil.encrypt(data, key));
        long end  = System.currentTimeMillis();
       logger.info("耗时：" + (end - start));
    }

}
