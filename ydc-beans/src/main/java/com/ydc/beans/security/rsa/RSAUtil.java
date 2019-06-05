package com.ydc.beans.security.rsa;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * RSA加密解密
 *
 * @author
 * @create 2018-11-06 13:05
 **/
public class RSAUtil {

    private  final  static Logger logger=LogManager.getLogger(RSAUtil.class);


    /**
     * 随机生成密钥对
     * @param redisPrivateKey 私钥key
     * @param redisPublicKey 公钥key
     */
    public static KeyPair genKeyPair(String redisPrivateKey,String redisPublicKey) {
        logger.info("subject:{},redisPrivateKey:{},redisPublicKey:{}","随机生成密钥对",redisPrivateKey,redisPublicKey);
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            logger.error("生成密钥对异常",e);
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
           /* // 得到公钥字符串
            String publicKeyString =Base64.encodeBase64String(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());*/
            String publicKeyString =SystemPropertiesConfig.publicKeyString;
            // 得到私钥字符串
            String privateKeyString = SystemPropertiesConfig.privateKeyString;


            if(!RedisUtil.exists(redisPrivateKey) || !RedisUtil.exists(redisPublicKey)){
                if(RedisUtil.exists(redisPrivateKey)){
                    RedisUtil.remove(redisPrivateKey);
                }
                if(RedisUtil.exists(redisPublicKey)){
                    RedisUtil.remove(redisPublicKey);
                }
                logger.info("subject:{}","重置秘钥");
                RedisUtil.redisSet(redisPrivateKey,privateKeyString,null);
                RedisUtil.redisSet(redisPublicKey,publicKeyString,null);
            }

            logger.info("subject:{};publicKey:{};privateKey:{},","重置秘钥",publicKeyString,privateKeyString);
            return keyPair;
        } catch (Exception e) {
            logger.error("subject:{}","生成秘钥异常:"+redisPrivateKey);
            return null;
        }
    }

    /**
     * 获取私钥
     * @param redisPrivateKey 私钥key
     * @return
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String redisPrivateKey) {
      /*  Object privateKeyStr = RedisUtil.redisGet(redisPrivateKey);
        if (StringUtil.isEmpty(privateKeyStr)){
            return  null;
        }*/
        //---

        try {
            byte[] buffer = Base64.decodeBase64(SystemPropertiesConfig.privateKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error("无此算法",e);
        } catch (InvalidKeySpecException e) {
            logger.error("私钥非法",e);
        } catch (NullPointerException e) {
            logger.error("私钥数据为空",e);
        }
        return null;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param redisPublicKey 公钥key
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String redisPublicKey){
       /* Object publicKeyStr = RedisUtil.redisGet(redisPublicKey);
        if (StringUtil.isEmpty(publicKeyStr)){
            return  null;
        }*/
       //--
        try {
            byte[] buffer = Base64.decodeBase64(SystemPropertiesConfig.publicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error("无此算法",e);
        } catch (InvalidKeySpecException e) {
            logger.error("公钥非法",e);
        } catch (NullPointerException e) {
            logger.error("公钥数据为空",e);
        }
        return null;
    }

    //公钥加密，并转换成十六进制字符串打印出来
    public static String encrypt(String content,RSAPublicKey publicKey) throws Exception{
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int splitLength=(publicKey).getModulus().bitLength()/8-11;
        byte[][] arrays=splitBytes(content.getBytes(), splitLength);
        StringBuffer sb=new StringBuffer();
        for(byte[] array : arrays){
            sb.append(bytesToHexString(cipher.doFinal(array)));
        }
        return sb.toString();
    }

    //私钥解密，并转换成十六进制字符串打印出来
    public static String decrypt(String content,RSAPrivateKey privateKey) {
        try {

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            /*Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);*/

            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            int splitLength=(privateKey).getModulus().bitLength()/8;
            byte[] contentBytes=hexString2Bytes(content);
            byte[][] arrays=splitBytes(contentBytes, splitLength);
            StringBuffer sb=new StringBuffer();
            for(byte[] array : arrays){
                sb.append(new String(cipher.doFinal(array)));
            }
            return sb.toString();
        }catch (Exception e){
            logger.info("subject:{},e:{}","解密失败",e);
        }
        return  content;

    }

    //拆分byte数组
    public static byte[][] splitBytes(byte[] bytes, int splitLength){
       logger.info(bytes.length +"----------"+splitLength);
        int x; //商，数据拆分的组数，余数不为0时+1
        int y; //余数
        y=bytes.length%splitLength;
        if(y!=0){
            x=bytes.length/splitLength+1;
        }else{
            x=bytes.length/splitLength;
        }
        byte[][] arrays=new byte[x][];
        byte[] array;
        for(int i=0; i<x; i++){

            if(i==x-1 && bytes.length%splitLength!=0){
                array=new byte[bytes.length%splitLength];
                System.arraycopy(bytes, i*splitLength, array, 0, bytes.length%splitLength);
            }else{
                array=new byte[splitLength];
                System.arraycopy(bytes, i*splitLength, array, 0, splitLength);
            }
            arrays[i]=array;
        }
        return arrays;
    }

    //byte数组转十六进制字符串
    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    //十六进制字符串转byte数组
    public static byte[] hexString2Bytes(String hex) {
        int len = (hex.length() / 2);
        hex=hex.toUpperCase();
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static String data="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";

    public static void demo(String data) throws Exception {
       logger.info("加密前长度"+data.length());
        //公钥加密
        String encrypted=encrypt(data,loadPublicKeyByStr(RedisConstant.RAS_PUBLIC_KEY));
       logger.info("加密后："+encrypted);

        //私钥解密

        String decrypted=decrypt(data,loadPrivateKeyByStr(RedisConstant.RAS_PRIVATE_KEY));
       logger.info("解密前："+data);
       logger.info("解密后："+decrypted);
       logger.info("解密后长度："+decrypted.length());
    }


    public static void main(String [] args){
        try {
            demo(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
