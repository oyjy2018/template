package com.ydc.beans.file;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具
 *
 * @author gongjin
 * @create 2018-09-08 15:54
 **/
public class FileUtil {

    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    /**
     * 在线浏览图片
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月24日 上午11:02:56
     * @return:String
     */
    public static String getBrowseFile(String fileUrl, String fileName) throws Exception {
        if (StringUtil.isEmpty(fileUrl) || StringUtil.isEmpty(fileName)){
            return null;
        }
        return getBrowseFile(fileUrl, fileName.substring(0, fileName.lastIndexOf(".")),
                fileName.substring(fileName.lastIndexOf("."), fileName.length()));
    }

    /**
     * 在线浏览图片
     *
     * @author:gongjin
     * @param:
     * @date: 2017年10月24日 上午11:02:56
     * @return:String
     */
    public static String getBrowseFile(String fileUrl, String fileName, String fileType) throws Exception {
        if (StringUtil.isEmpty(fileUrl) || StringUtil.isEmpty(fileName) || StringUtil.isEmpty(fileType)){
            return null;
        }
        String param = EncryptUtils.aesEncrypt(fileUrl + "/" + fileName, EncryptUtils.KEY);
        param = URLEncoder.encode(param, "utf-8") + fileType;
        return SystemPropertiesConfig.FILE_SERVICE_PUBLIC_URL + "/file/browseFile?filename=" + param;
    }

    /**
     * 返回读取到的文件流
     * @author:gongjin
     * @param:
     * @date: 2018年1月31日 下午7:27:29
     * @return:InputStream
     */
    public static InputStream getFileStream(String fileUrl){
        try {
            URL url = new URL(fileUrl);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //得到文件的二进制数据，以二进制封装得到数据，具有通用性
            return conn.getInputStream();
        } catch (Exception e) {
            logger.error("subject:{},e:{}","调用文件服务器异常",e);
        }
        return null;
    }

    /**
     * 将InputStream转Base64
     * @author:gongjin
     * @param:
     * @date: 2018年4月9日 下午4:16:22
     * @return:String
     */
    public static String getBase64Content(InputStream input) throws IOException {
        byte[] output = steamToByte(input);
        BASE64Encoder encoder = new BASE64Encoder();
        String outstr = encoder.encode(output);
        return outstr;
    }

    /**
     * InputStream转byte
     * @author:gongjin
     * @param:
     * @date: 2018年4月9日 下午4:14:22
     * @return:byte[]
     */
    public static byte[] steamToByte(InputStream input) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = input.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] buffer =  baos.toByteArray();
        return buffer;
    }


    /**
     * 删除文件
     * @author huyueming
     * @date 2018年2月13日
     * @param filename 文件名
     * @param path 文件路径
     */
    public static String deleteFile(String filename, String path) {
        String ret = null;
        try {
            String url = SystemPropertiesConfig.FILE_SERVICE_PUBLIC_URL +  "/file/deleteFile";
            Map<String, Object> param = new HashMap<>();
            param.put("filename", EncryptUtils.base64Encode(filename.getBytes("UTF-8")));
            param.put("path", EncryptUtils.base64Encode(path.getBytes("UTF-8")));
            ret = UrlHttpUtil.doPost(url,JsonUtil.gsonStr(param));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","删除["+filename+"]文件异常",e);
        }
        return ret;
    }

}
