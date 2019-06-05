package com.ydc.beans.commom.tembin;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 天秤系统
 *
 * @author
 * @create 2018-11-20 19:40
 **/
public class TembinUtil {

    private static final Logger logger = LogManager.getLogger(TembinUtil.class);


    /**
     * 身份证验证
     * @author:gongjin
     * @param: realName 手机号码
     * @param: identityCard 证件证号
     * @date: 2017年12月21日 上午11:50:13
     * @return:Map<String,Object>
     */
    public static ResponseData<String> idCardVerification(String realName, String identityCard){
        try {
            Map<String, String> reqmap = new HashMap<String, String>();
            reqmap.put("idNumber", identityCard);
            reqmap.put("name", realName);
            String functionCode = "CHNL_10000001";
            String jsonString = JSONObject.toJSONString(reqmap);
            logger.info("subject:{},jsonString:{}","身份证验证请求业务数据明文",jsonString);
            return TCClient.OkHttpPost(SystemPropertiesConfig.NEW_TIANCHENG_API_URL, SystemPropertiesConfig.NEW_TIANCHENG_ID,
                    SystemPropertiesConfig.NEW_TIANCHENG_ORGNAME, SystemPropertiesConfig.NEW_TIANCHENG_API_USERNAME, SystemPropertiesConfig.NEW_TIANCHENG_API_PASSWORD, functionCode, jsonString);
        } catch (Exception e) {
            logger.error("subject:{},e:{}","身份证验证查询接口异常",e);
        }
        return null;
    }
}
