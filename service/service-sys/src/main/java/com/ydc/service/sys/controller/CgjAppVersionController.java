package com.ydc.service.sys.controller;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjAppVersionQueDTO;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;
import com.ydc.service.sys.service.CgjAppVersionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * APP版本
 * @author hejiangping
 * @date 2019/1/12
 */
@RestController
@RequestMapping(value = "/appVersion")
public class CgjAppVersionController {
    private static Logger logger = LogManager.getLogger(CgjAppVersionController.class);
    @Autowired
    CgjAppVersionService cgjAppVersionService;
    /**
     * 重新加载APP版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    @PostMapping(value = "/reloadAppVersion")
    public String reloadAppVersion() {
        logger.info("subject:{}","重新加载APP版本");
        try {
            // 加载到缓存
            Map<String, Object> iosMap = cgjAppVersionService.getNewestVersion(DictionaryConstant.CGJ_APP_IOS);
            Map<String, Object> androidMap = cgjAppVersionService.getNewestVersion(DictionaryConstant.CGJ_APP_ANDROID);
            RedisUtil.redisSet(RedisConstant.CGJ_APP_IOS_VERSION,Result.success(iosMap),null);
            RedisUtil.redisSet(RedisConstant.CGJ_APP_ANDROID_VERSION,Result.success(androidMap),null);
            return Result.success("重新加载APP版本成功").toJSON();
        } catch (Exception e) {
            logger.error("重新加载APP版本异常",e);
            return Result.failure("重新加载APP版本失败").toJSON();
        }
    }
    /**
     * 查询最新APP版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    @PostMapping(value = "/queryAppVersion")
    public Result queryAppVersion(@RequestBody CgjAppVersionQueDTO cgjAppVersionQueDTO) {
        logger.info("subject:{},param:{}","查询最新APP版本", JsonUtil.gsonStr(cgjAppVersionQueDTO));
        try {
            // 加载到缓存
            Map<String, Object> map = cgjAppVersionService.getNewestVersion(cgjAppVersionQueDTO.getPlatform());
            String versionRedis = RedisConstant.CGJ_APP_IOS_VERSION;
            if(cgjAppVersionQueDTO.getPlatform().equals(DictionaryConstant.CGJ_APP_ANDROID)){
                versionRedis = RedisConstant.CGJ_APP_ANDROID_VERSION;
            }
            RedisUtil.redisSet(versionRedis,Result.success(map),null);
            return Result.success(map);
        } catch (Exception e) {
            logger.error("查询最新APP版本异常",e);
            return Result.failure("查询最新APP版本失败");
        }
    }
}
