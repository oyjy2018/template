package com.ydc.beans.security.rsa;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.StreamUtil;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.codec.Base64;
import com.ydc.beans.config.SystemPropertiesConfig;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA2Util {

    /** RSA最大加密明文大小  */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** RSA最大解密密文大小   */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private static final String charset="UTF-8";

    public static String privateKey=SystemPropertiesConfig.privateKeyString;
    public static String publicKey=SystemPropertiesConfig.publicKeyString;




    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm,
                                                    InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = StreamUtil.readText(ins).getBytes();

        encodedKey = com.alipay.api.internal.util.codec.Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }


    public static PublicKey getPublicKeyFromX509(String algorithm,
                                                 InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = com.alipay.api.internal.util.codec.Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     * 公钥加密
     *
     * @param content   待加密内容
    / * @param publicKey 公钥
    / * @param charset   字符集，如UTF-8, GBK, GB2312
     * @return 密文内容
     * @throws AlipayApiException
     */
       /* public static String rsaEncrypt(String content, String publicKey,
                                        String charset) throws AlipayApiException {*/

    public static String rsaEncrypt(String content) throws AlipayApiException {
        try {
            PublicKey pubKey = getPublicKeyFromX509(AlipayConstants.SIGN_TYPE_RSA,
                    new ByteArrayInputStream(publicKey.getBytes()));
            Cipher cipher = Cipher.getInstance(AlipayConstants.SIGN_TYPE_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] data = StringUtils.isEmpty(charset) ? content.getBytes()
                    : content.getBytes(charset);
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
            byte[] encryptedData = com.alipay.api.internal.util.codec.Base64.encodeBase64(out.toByteArray());
            out.close();

            return StringUtils.isEmpty(charset) ? new String(encryptedData)
                    : new String(encryptedData, charset);
        } catch (Exception e) {
            throw new AlipayApiException("EncryptContent = " + content + ",charset = " + charset,
                    e);
        }
    }

    /**
     * 私钥解密
     *
     * @param content    待解密内容
    /* @param privateKey 私钥
    /* @param charset    字符集，如UTF-8, GBK, GB2312
     * @return 明文内容
     * @throws AlipayApiException
     */
       /* public static String rsaDecrypt(String content, String privateKey,
                                        String charset) throws AlipayApiException {*/
    public static String rsaDecrypt(String content) throws AlipayApiException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(AlipayConstants.SIGN_TYPE_RSA,
                    new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance(AlipayConstants.SIGN_TYPE_RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = StringUtils.isEmpty(charset)
                    ? com.alipay.api.internal.util.codec.Base64.decodeBase64(content.getBytes())
                    : Base64.decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;

            // byte[][] data=test(encryptedData);
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                    // cache = cipher.doFinal(data[i], 0, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                    // cache = cipher.doFinal(data[i], 0, MAX_DECRYPT_BLOCK);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
                System.out.println("offSet:  "+offSet);
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return StringUtils.isEmpty(charset) ? new String(decryptedData)
                    : new String(decryptedData, charset);
        } catch (Exception e) {
            byte[] decryptedData = out.toByteArray();
            System.out.println(new String(decryptedData));
            //throw new AlipayApiException("EncodeContent = " + content + ",charset = " + charset, e);
            throw new AlipayApiException("EncodeContent = " + content,e);
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static byte[][] test(byte[] bytes){
        /* System.out.println(bytes.length);
         System.out.println(bytes.length/128);
         System.out.println(bytes[0]);*/
        byte byteTemp[][]=new byte[bytes.length/128+1][128];
        int j=0,x=0;
        for (int i=0;i<bytes.length;i++){
            byteTemp[j][x]=bytes[i];
            /*  System.out.println(i);
              System.out.println((i+1)%128);
              System.out.println((i+1)/128);*/
            if ((i+1)%128==0){
                j=j+1;
                x=0;
            }else {
                x=x+1;
            }

        }
        return byteTemp;

    }

    public static void main(String[] args) {

        try {

    String content="{\"userId\":38335,\"linkName\":\"陈四十\",\"linkPhone\":15728564444,\"addressProvinceCode\":110000,\"addressCityCode\":110000,\"addressRegionCode\":110000,\"addressProvince\":\"北京市\",\"addressCity\":\"北京市市辖区\",\"addressRegion\":\"东城区\",\"addressDetail\":\"健健康康快快乐乐咯\"}";

                String  date=rsaEncrypt( content);
            System.out.println(date);
           // String data="HwQm1fwVSKAU1GefXb3db5EWrMD/RE2cPuZD8ZY+guDyp2/h/k7+mTntnp9mlPqJiCmRYKJYhcZC3fvMYRAgqrP9yZY5uzDlyEH03AZW6zZIr4R6r+H/PMnwdwC8JfvkUs3QO9FB0tUz5KKqAOlpn+Yb1sBedSZEBgLavbKF8/4EqDkW4Vym4ZLpQdpSEociNWZbKI1gV4UsNgq1ZmnGDAY2Bp6jzFtWwRp5tMQo3I4/tQgW0qC2i3XKE2AerHT/KgCRnq0ANNJMuAu8X7N9ED6eu3jIrAZI9aKgWo2r3K7VY/zKPbEq1ppAyCa36MZJOmfT2940xkAU4yPN/zdellO6p/nYsQK9vJxXB1pcETX/ZV14qDubW74DaxCBj8lArPkoP/jhHhFND4n3ku9CLHmQntsnXvw7bbARAcJmp0I6NDnsutsWogOdGcjswn0fVorcYG0qM+bzGohnPQp4+DDT8uMvUXrG7wQaivSFu2R3GWP9XRoX0XNFcep5X1ny";
            System.out.println("length"+date.length());
            System.out.println(rsaDecrypt(date));

        }catch (AlipayApiException e){
            e.printStackTrace();

        }



    }


}
