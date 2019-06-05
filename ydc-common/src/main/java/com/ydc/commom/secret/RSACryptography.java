package com.ydc.commom.secret;

import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

public class RSACryptography {
    private  final  static Logger logger=LogManager.getLogger(RSACryptography.class);
    public static String data="中DFASDFSADFDSFDSFDSAFFFFFFFFFDSADS中男女撒大声地俩商量分手就分手大嫁风尚福建省的积分时代峻峰就是的的时间里福建省的金粉世家福建师范卷式担架发生纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +"连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "连接到时间飞逝缴费基数福建省的发生纠纷讲道理福建省的来访接待室金粉世家拉萨的街坊邻居达萨罗附近的洛杉矶发送到 阿拉基的法律纠纷" +
            "文";
     public  static   RSAPrivateKey privateKey;
     public  static   RSAPublicKey publicKey;
     public  static String filePath=ProjectPath.getProjectPath();
     static {
         publicKey=loadPublicKeyByStr(filePath);
         if (null != publicKey){
             privateKey=loadPrivateKeyByStr(filePath);
         }
         if (publicKey==null || privateKey==null){
             KeyPair keyPair=genKeyPair(filePath);
             privateKey= (RSAPrivateKey) keyPair.getPrivate();
             publicKey= (RSAPublicKey) keyPair.getPublic();
         }

    }
    /**
     * 随机生成密钥对
     */
    public static KeyPair genKeyPair(String filePath) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey  privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey   publicKey = (RSAPublicKey) keyPair.getPublic();
        try ( FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
              FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
              BufferedWriter pubbw = new BufferedWriter(pubfw);
              BufferedWriter pribw = new BufferedWriter(prifw)
              ){
            // 得到公钥字符串
            String publicKeyString =Base64.getUrlEncoder().encodeToString(publicKey.getEncoded());
                    //Base64.Decoder(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.getUrlEncoder().encodeToString(privateKey.getEncoded());
            // 将密钥对写入到文件
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pribw.flush();
            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param filePath
     *
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static String loadPublicKeyByFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/publicKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            logger.error("公钥数据流读取错误",e);
        } catch (NullPointerException e) {
            logger.error("公钥输入流为空",e);
        }
        return null;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param filePath 地址
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String filePath){
        String publicKeyStr=loadPublicKeyByFile(filePath);
        if (StringUtil.isEmpty(publicKeyStr)){
            return  null;
        }
        try {
            byte[] buffer = Base64.getUrlDecoder().decode(publicKeyStr);
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

    /**
     * 从文件中加载私钥
     *
     * @param filePath
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile(String filePath){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/privateKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            logger.error("私钥数据读取错误",e);
        } catch (NullPointerException e) {
            logger.error("私钥输入流为空",e);
        }
            return null;
        }

    public static RSAPrivateKey loadPrivateKeyByStr(String filePath) {
        String privateKeyStr= loadPrivateKeyByFile(filePath);
        if (StringUtil.isEmpty(privateKeyStr)){
            return  null;
        }
        try {
            byte[] buffer = Base64.getUrlDecoder().decode(privateKeyStr);
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



    public static void main(String[] args) throws Exception {

       logger.info("加密前长度"+data.length());
        //公钥加密
        String encrypted=encrypt(data);
       logger.info("加密后："+encrypted);

        //私钥解密
        String decrypted=decrypt(encrypted);
       logger.info("解密前："+data);
       logger.info("解密后："+decrypted);
       logger.info("解密后长度："+decrypted.length());

    }






    //公钥加密，并转换成十六进制字符串打印出来
    public static String encrypt(String content) throws Exception{
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
    public static String decrypt(String content) {
        try {

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);

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
            e.printStackTrace();
            logger.info("解密失败："+e.getMessage());
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


}
