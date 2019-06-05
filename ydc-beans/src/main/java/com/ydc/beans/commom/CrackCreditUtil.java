package com.ydc.beans.commom;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 失信名单查询
 *
 * @author
 * @create 2018-11-22 15:38
 **/
public class CrackCreditUtil {

    private static final Logger logger = LogManager.getLogger(CrackCreditUtil.class);


//	private static final String aliyun_appCode = "d253afeacea14852b7df6de9b04510e6";

    public enum CrackCreditEnum{
        RESULT_CODE_0(0,"查询成功"),
        RESULT_CODE_1010(1010,"缺失必要参数name"),
        RESULT_CODE_1008(1008,"请输入正确的查询人的身份证号不正确"),
        RESULT_CODE_1009(1009,"请输入正确的查询人的地区不正确"),
        RESULT_CODE_1100(1100,"经查询，暂无相关数据"),
        RESULT_CODE_2000(2000,"系统繁忙，请稍后重试"),
        RESULT_CODE_20000(20000,"系统有误，请联系客服")
        ;

        private int code;
        private String message;
        CrackCreditEnum(int code,String message){
            this.setCode(code);
            this.setMessage(message);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 0	     查询成功	操作成功
     * 1010	     缺失必要参数name	输入需要查询人的名字
     * 1008	     请输入正确的cardNum	输入的查询人的身份证号不正确
     * 1009	     请输入正确的areaName	输入的查询人的地区不正确
     * 1100	     暂无相关数据	经查询，暂无相关数据
     * 2000	     系统繁忙	系统繁忙，请稍后重试
     * 20000	 系统有误	请联系客服
     * @param name
     * @param cardNum
     * @return
     */
	public static JSONObject getCrackCredit(String name,String cardNum){
        String url = "http://shixin1.market.alicloudapi.com/clouds/query/trust";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + SystemPropertiesConfig.ALIYUN_CRACKCREDIT_APPCODE);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name",name);
        dataMap.put("cardNum",cardNum);
        JSONObject jsonObject = null;
        try {
            String response = UrlHttpUtil.doPost(url,JsonUtil.gsonStr(dataMap),headers);
            logger.info("subject:{},response:{}","失信名单查询接口响应",response);
            jsonObject = JSONObject.parseObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error("subject:{},e:{}","失信名单查询异常",e);
            return jsonObject;
        }
    }

    /**
     * 返回查询状态
     * @param name
     * @param cardNum
     * @return
     */
    public static JSONObject getJSONObject(String name,String cardNum){
        JSONObject jsonObject = getCrackCredit( name, cardNum);
        if(jsonObject == null){
            jsonObject = new JSONObject();
            jsonObject.put("code",9999);
            jsonObject.put("message","接口响应数据为空");
            return jsonObject;
        }
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("code",jsonObject.get("code"));
        jsonObject2.put("message",jsonObject.get("message"));
        return jsonObject;
    }

    /**
     * 返回查询data
     * @param name
     * @param cardNum
     * @return
     */
    public static List<Map<String,Object>> getCrackCreditList(String name,String cardNum){
        JSONObject jsonObject = getCrackCredit( name, cardNum);
        if(jsonObject == null){
            return Lists.newArrayList();
        }
        if(Integer.valueOf(jsonObject.get("code").toString()) != 0){
            return Lists.newArrayList();
        }
        List<Map<String,Object>> mapList = JsonUtil.jsonToListMap(jsonObject.getString("data"));
        return mapList;
    }

    public static void main(String[] args) {
       logger.info(getJSONObject("龚瑾",null));
       logger.info(JsonUtil.gsonStr(getCrackCreditList("龚瑾",null)));
    }
}
