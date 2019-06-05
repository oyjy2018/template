package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.view.dto.cgj.service.OilServiceDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 油价查询
 *
 * @author Antoneo
 * @create 2018-09-08 17:28
 **/
@RestController
@RequestMapping(value = "/oil")
public class OilPriceController {
    private final Logger logger = LogManager.getLogger(OilPriceController.class);

    @Autowired
    private Environment env;
    @RequestMapping(value = "/today", method = RequestMethod.POST)
    public String getToday(@RequestParam("data") String data){
        try {
            OilServiceDTO oilServiceDTO = JSON.parseObject(data, OilServiceDTO.class);
            Map<String ,Object> param=new HashMap<>();
            param.put("province",oilServiceDTO.getProvince());
            String url=env.getProperty("spi.api.service.url")+"/oil/today";
            String resultStr =UrlHttpUtil.doPost(url,param,null);
            JSONObject jsonObject= JSON.parseObject(resultStr);
            if (new Integer(0).compareTo(jsonObject.getInteger("code"))==0){
                return  Result.success(jsonObject.get("data")).toJSON();
            }else {
                return  Result.failure(jsonObject.getString("msg")).toJSON();
            }
        } catch (Exception e) {
            logger.error("查询今日油价异常",e);
             return  Result.failure("稍后重试").toJSON();
        }

    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public String getHistory(@RequestParam("data") String data){
        try {
            OilServiceDTO oilServiceDTO = JSON.parseObject(data, OilServiceDTO.class);
            Map<String ,Object> param=new HashMap<>();
            param.put("province",oilServiceDTO.getProvince());
            String url=env.getProperty("spi.api.service.url")+"/oil/history";
            String resultStr =UrlHttpUtil.doPost(url,param,null);
            JSONObject jsonObject= JSON.parseObject(resultStr);
            if (new Integer(0).compareTo(jsonObject.getInteger("code"))==0){
                return  Result.success(jsonObject.get("data")).toJSON();
            }else {
                return  Result.failure(jsonObject.getString("msg")).toJSON();
            }
        } catch (Exception e) {
            logger.error("查询今日油价异常",e);
            return  Result.failure("稍后重试").toJSON();
        }
    }
}
