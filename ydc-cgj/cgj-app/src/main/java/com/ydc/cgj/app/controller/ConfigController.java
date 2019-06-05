package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.RedisConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.app.service.ConfigService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjAppVersionQueDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置
 * @author hejiangping
 * @date 2019/1/3
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    protected static final Logger logger = LogManager.getLogger(ConfigController.class);
    @Autowired
    ConfigService configService;
    /**
     * 查询首页模块
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/queryHomeModules")
    public String queryHomeModules() {
        logger.info("subject:{}","查询首页模块");
        return configService.queryHomeModules().toJSON();
    }
    /**
     * 查询广告
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/queryAdverts")
    public String queryAdverts(@RequestParam(value = "data") String data) {
        logger.info("subject:{},param:{}","查询广告",data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data,AdvertConfigQueDTO.class);
        if (advertConfigQueDTO == null) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (StringUtil.isEmpty(advertConfigQueDTO.getVersion())) {
            return Result.failure("版本号不能为空").toJSON();
        }
        if (StringUtil.isEmpty(advertConfigQueDTO.getClientDictKey())) {
            return Result.failure("客户端不能为空").toJSON();
        }
        Result result = null;
        // 读缓存
        if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_1)){
            // APP
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_APP_ADVERT_VERSION);
        }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_2)){
            // H5
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_H5_ADVERT_VERSION);
        }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_3)){
            // 小程序
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_MINIPROGRAM_ADVERT_VERSION);
        }
        if(result == null){
            result = configService.queryAdverts(advertConfigQueDTO);
        }
        Map<String, Object> dataMap = (Map<String, Object>) result.getData();
        if(dataMap != null){
            // 如果版本号一致就不返回数据
            if(advertConfigQueDTO.getVersion().equals(dataMap.get("version").toString())){
                return Result.success().toJSON();
            }
        }
        return result.toJSON();
    }
    /**
     * 查询启动页
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/startupPages")
    public String startupPages(@RequestParam(value = "data") String data) {
        logger.info("subject:{},param:{}","查询启动页",data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data,AdvertConfigQueDTO.class);
        if (advertConfigQueDTO == null) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (StringUtil.isEmpty(advertConfigQueDTO.getClientDictKey())) {
            advertConfigQueDTO.setClientDictKey(DictionaryConstant.CGJ_CLIENT_1);
        }
        Result result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_APP_STARTUP_PAGE);
        if(result == null){
            result = configService.queryAdverts(advertConfigQueDTO);
        }
        return result.toJSON();
    }
    /**
     * 查看缓存数据是否需要更新
     * @author: hejiangping
     * @date: 2019/1/5
     */
    @PostMapping(value = "/queryHasUpdates")
    public String queryHasUpdates(@RequestParam(value = "data") String data) {
        logger.info("subject:{},param:{}","查看缓存数据是否需要更新",data);
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data);
        if(!dataMap.containsKey("version")){
            return Result.failure("版本号不能为空").toJSON();
        }
        Map<String, Object> commonKey = (Map<String, Object>) RedisUtil.redisGet(RedisConstant.CGJ_COMMON_VERSION);
        if(commonKey != null){
            if(!dataMap.get("version").equals(commonKey.get("version"))){
                return Result.success(commonKey).toJSON();
            }
        }else{
            return Result.getResult(ResultConstant.RESULT_CODE_COMMON_VERSION_FAILURE, "公共数据版本还未生成").toJSON();
        }
        return Result.success().toJSON();
    }
    /**
     * 查询按钮
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/queryButtonConfigs")
    public String queryButtonConfigs(@RequestParam(value = "data") String data) {
        logger.info("subject:{},param:{}","查询按钮",data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data,AdvertConfigQueDTO.class);
        if (advertConfigQueDTO == null) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (StringUtil.isEmpty(advertConfigQueDTO.getClientDictKey())) {
            return Result.failure("客户端不能为空").toJSON();
        }
        Result result = null;
        // 读缓存
        if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_1)){
            // APP
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_APP_BUTTON);
        }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_2)){
            // H5
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_H5_BUTTON);
        }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_3)){
            // 小程序
            result = (Result) RedisUtil.redisGet(RedisConstant.CGJ_MINIPROGRAM_BUTTON);
        }
        if(result == null){
            result = configService.queryButtonConfigs(advertConfigQueDTO);
        }
        return result.toJSON();
    }
    /**
     * 检查APP是否更新
     * @author: hejiangping
     * @date: 2019/1/5
     */
    @PostMapping(value = "/getNewestVersion")
    public String getNewestVersion(@RequestParam(value = "data") String data) {
        logger.info("subject:{},param:{}","查询最新APP版本",data);
        CgjAppVersionQueDTO cgjAppVersionQueDTO = JSONObject.parseObject(data,CgjAppVersionQueDTO.class);
        if(StringUtil.isEmpty(cgjAppVersionQueDTO.getPlatform())){
            return Result.failure("客户端平台不能为空").toJSON();
        }
        if(StringUtil.isEmpty(cgjAppVersionQueDTO.getPlatform())){
            return Result.failure("客户端平台不能为空").toJSON();
        }
        if(StringUtil.isEmpty(cgjAppVersionQueDTO.getVersion())){
            return Result.failure("版本号不能为空").toJSON();
        }
        String versionRedis = null;
        if(cgjAppVersionQueDTO.getPlatform().equals(DictionaryConstant.CGJ_APP_IOS)){
            versionRedis = RedisConstant.CGJ_APP_IOS_VERSION;
        }else if(cgjAppVersionQueDTO.getPlatform().equals(DictionaryConstant.CGJ_APP_ANDROID)){
            versionRedis = RedisConstant.CGJ_APP_ANDROID_VERSION;
        }
        if(StringUtil.isEmpty(versionRedis)){
            return Result.failure("没有该客户端平台").toJSON();
        }
        Result result = (Result) RedisUtil.redisGet(versionRedis);
        if(result == null){
            result = configService.getNewestVersion(cgjAppVersionQueDTO);
        }
        if(result != null){
            Map<String, Object> dataMap = (Map<String, Object>) result.getData();
            if(dataMap != null && dataMap.containsKey("version") && !dataMap.get("version").toString().equals(cgjAppVersionQueDTO.getVersion())){
                return result.toJSON();
            }
        }else{
            return Result.failure("检查更新失败").toJSON();
        }
        return Result.success("已经是最新版本").toJSON();
    }
}
