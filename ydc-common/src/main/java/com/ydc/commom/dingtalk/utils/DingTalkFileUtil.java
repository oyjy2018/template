package com.ydc.commom.dingtalk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DingTalkFileUtil {
    /**
     * 保存文件名，保存accesstoken
     */
    public static final String FILEPATH = "dingding_Data";

    private static Logger logger = LogManager.getLogger(DingTalkFileUtil.class);

    // json写入文件
    public synchronized static void write2File(Object json, String fileName) {
        BufferedWriter writer = null;
        File filePath = new File(FILEPATH);
        JSONObject eJSON = null;

        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }

        File file = new File(FILEPATH + File.separator + fileName + ".xml");
        logger.error("subject:{};path:{};abspath:{}","写入文件",file.getPath(), file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                logger.error("subject:{},e:{}","createNewFile，出现异常",e);
            }
        } else {
            eJSON = (JSONObject) read2JSON(fileName);
        }
        try {
            writer = new BufferedWriter(new FileWriter(file));
            if (eJSON == null) {
                writer.write(json.toString());
            } else {
                Object[] array = ((JSONObject) json).keySet().toArray();
                for (int i = 0; i < array.length; i++) {
                    eJSON.put(array[i].toString(), ((JSONObject) json).get(array[i].toString()));
                }
                writer.write(eJSON.toString());
            }

        } catch (IOException e) {
            logger.error("subject:{},e:{}","json写入文件异常",e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.error("subject:{},e:{}","关流异常",e);
            }
        }
    }

    // 读文件到json
    public static JSONObject read2JSON(String fileName) {
        File file = new File(FILEPATH + File.separator + fileName + ".xml");
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            logger.error("subject:{},e:{}","读文件到json异常",e);
        }
        return (JSONObject) JSON.parse(laststr);
    }

    // 通过key值获取文件中的value
    public static Object getValue(String fileName, String key) {
        JSONObject eJSON = null;
        eJSON = (JSONObject) read2JSON(fileName);
        if (null != eJSON && eJSON.containsKey(key)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> values = JSON.parseObject(eJSON.toString(), Map.class);
            return values.get(key);
        } else {
            return null;
        }
    }

    public static HashMap<Long, Long> toHashMap(JSONObject js) {
        if (js == null) {
            return null;
        }
        HashMap<Long, Long> data = new HashMap<Long, Long>();
        // 将json字符串转换成jsonObject
        Set<String> set = js.keySet();
        // 遍历jsonObject数据，添加到Map对象
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Long keyLong = Long.valueOf(key);

            String value = js.getString(key);
            Long valueLong;
            if (TextUtils.isEmpty(value)) {
                valueLong = js.getLong(key);
            } else {
                valueLong = Long.valueOf(value);
            }
            data.put(keyLong, valueLong);
        }
        return data;
    }
}
