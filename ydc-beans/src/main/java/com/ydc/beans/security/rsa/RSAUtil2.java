package com.ydc.beans.security.rsa;

import com.ydc.beans.config.SystemPropertiesConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author
 * @create 2018-11-07 16:02
 **/
public class RSAUtil2 {

    private  final  static Logger logger=LogManager.getLogger(RSAUtil2.class);

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 转换私钥
     * @param  key to PrivateKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * 使用getPublicKey得到公钥,返回类型为PublicKey
     * @param  key to PublicKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes =(new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 公钥加密
     * @param rsaPublicKey
     * @param src
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] publicEncryption(String rsaPublicKey,String src) throws Exception {
        PublicKey publicKey = getPublicKey(rsaPublicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] data = src.getBytes();
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        logger.info("subject:{},src:{}","公钥加密，私钥解密------加密",Base64.encodeBase64String(encryptedData));
       logger.info("公钥加密，私钥解密------加密 : " + Base64.encodeBase64String(encryptedData));
        return encryptedData;
    }

    /**
     * 私钥解密
     * @param rsaPrivateKey
     * @param encryptedData
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] privateDecode(String rsaPrivateKey,byte[] encryptedData) throws Exception {
        PrivateKey privateKey = getPrivateKey(rsaPrivateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        logger.info("subject:{},src:{}","公钥加密，私钥解密------解密 ",new String(decryptedData));
       logger.info("公钥加密，私钥解密------解密 : " + new String(decryptedData));
        return decryptedData;

    }

    public static void main(String [] args) throws Exception {

       /* String src = "{\"cusManagerMobilePhone\":\"18888888888\",\"productCode\":\"102\",\"mobilePhone\":\"18811879947\",\"idCard\":\"511721197405186759\",\"applyMoney\":12521,\"fullName\":\"押证押车\",\"vehicleNumber\":\"粤B·123454\",\"cusManagerId\":\"885\",\"customerName\":\"李四李四\",\"vehicleFrameNumber\":\"FAFQ4654F65QA4564Q\"}";
*/

         String src="U38qymj79YQuE0S+PGdy6GlkGim1KHTwdr92toSQkj5NHGDz+S0bKWcXJ4PyO/9bRRyJE/IGOFMUZJGSifURxrIFvN+l4WVJTvzXqYQ8ktIt+NYiMseQvbpOuvJRbEfUMyHtEmpFlOJj45hVOmGqy75zb/VzpvaYcNn02Jc1t40bHbgojdLuFBcB7pNtglsR/oR1TfMP5B7UxB0Xvt9XMYKeDMa3jo962QkKxPc/D6qMgAjjV8IfIQWCF+WUWcEWcgYB0la9QEFtDp6S6P7xMZjOreHxSOZdjd/M0ppz2Woj/kMatSqRdSJW34V72AuyGUN1tYT9V5mMZth2+72DTyY7e8hLmfENJt3BrvkdnNVnLg7bswTG4WPga4a3QL7etkJ1K9W+DJE361EM61ChU/6TPEjEZolT4s/FldPLHuQqWHz6CFV9uuMIlje4A8MOpVyB89yHDgrqwfALkX40kaZgOZH5yOao+KXhKoY6K07c+JwMAt3tvm8mQX5BeE+MR0tfk0xyd3EQSgnNY7wJEFOuy7hnZpN6U9UbPRL1zPT1YfoOcM/6AXvvZeeRK6KCJhHWaHWI2m+8w2hHhr6Ij/38gUAjc6JuMzYki5ExGu1DVHKca8swFDWTnPFxcDYeoLVPBbz552gjx3GXal8ddU24uOJd7zKc9QLtM+rfDEU=";
        //公钥
        String rsaPublicKey = SystemPropertiesConfig.publicKeyString;
        //私钥
        String rsaPrivateKey =  SystemPropertiesConfig.privateKeyString;
        byte[] result = publicEncryption(rsaPublicKey, src);
       logger.info(new String (privateDecode(rsaPrivateKey,result)));

    }

}
