package com.ydc.service.sys.controller;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import com.ydc.model.cgj.sys.AdvertConfig;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.model.cgj.sys.HomeModule;
import com.ydc.service.sys.service.AdvertConfigService;
import com.ydc.service.sys.service.CommImgService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告配置
 * @author hejiangping
 * @date 2019/1/2
 */
@RestController
@RequestMapping(value = "/advertConfig")
public class AdvertConfigController {
    private static Logger logger = LogManager.getLogger(AdvertConfigController.class);
    @Autowired
    AdvertConfigService advertConfigService;
    @Autowired
    CommImgService commImgService;
    @Autowired
    ServiceFuncService serviceFuncService;

    /**
     * 查询广告配置列表
     * @author: hejiangping
     * @date: 2018/12/27
     */
    @PostMapping(value = "/queryAdvertConfigList")
    public String queryAdvertConfigList(@RequestBody AdvertConfigQueDTO advertConfigQueDTO){
        logger.info("查询广告配置列表,param:"+ JsonUtil.gsonStr(advertConfigQueDTO));
        try{
            List<Map<String, Object>> ret = advertConfigService.queryAdvertConfigList(advertConfigQueDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows",ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询广告配置列表异常",e);
            return Result.failure("失败").toJSON();
        }
    }
    /**
     * 获取广告配置详情
     * @author: hejiangping
     * @date: 2019/1/2
     */
    @PostMapping(value = "/getAdvertConfigDetail")
    public String getAdvertConfigDetail(@RequestBody AdvertConfigQueDTO advertConfigQueDTO) {
        logger.info("subject:{},param:{}","获取广告配置详情",JsonUtil.gsonStr(advertConfigQueDTO));
        Map<String, Object> result = advertConfigService.getAdvertConfigDetail(advertConfigQueDTO.getId());
        if(StringUtil.isNotEmpty(result.get("fileName"))){
            try {
                result.put("fileUrl",FileUtil.getBrowseFile(result.get("fileUrl").toString(),result.get("fileName").toString()));
                result.put("littleFileUrl",FileUtil.getBrowseFile(result.get("littleFileUrl").toString(),result.get("littleFileName").toString()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","获取图片地址异常",e);
            }
        }
        return Result.success(result).toJSON();
    }
    /**
     * 修改广告配置是否启用
     * @author: hejiangping
     * @date: 2019/1/2
     */
    @PostMapping(value = "/updateAdvertConfigStatus")
    public String updateAdvertConfigStatus(@RequestBody AdvertConfigQueDTO advertConfigQueDTO) {
        logger.info("subject:{},param:{}","修改广告配置是否启用",JsonUtil.gsonStr(advertConfigQueDTO));
        try {
            AdvertConfig advertConfig = advertConfigService.selectByPrimaryKey(advertConfigQueDTO.getId());
            if(advertConfig != null){
                advertConfig.setSwitchStatus(advertConfigQueDTO.getSwitchStatus());
                advertConfig.setUpdateTime(new Date());
                advertConfig.setUpdateBy(advertConfigQueDTO.getUserId());
                advertConfigService.updateByPrimaryKeySelective(advertConfig);
            }else{
                return Result.failure("不存在广告配置记录").toJSON();
            }
            if(advertConfig.getModuleDictKey().equals(DictionaryConstant.CGJ_ADVERT_MODULE_1)){
                // 指引页
                advertConfigService.putStartupPageToRedis();
            }else{
                // 更新到缓存
                advertConfigService.putAdvertToRedis();
            }
            return Result.success("修改成功").toJSON();
        } catch (Exception e) {
            logger.error("修改广告配置是否启用异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 删除广告配置
     * @author: hejiangping
     * @date: 2019/1/2
     */
    @PostMapping(value = "/deleteAdvertConfig")
    public String deleteAdvertConfig(@RequestBody AdvertConfigQueDTO advertConfigQueDTO) {
        logger.info("subject:{},param:{}","删除广告配置",JsonUtil.gsonStr(advertConfigQueDTO));
        try {
            AdvertConfig advertConfig = advertConfigService.selectByPrimaryKey(advertConfigQueDTO.getId());
            if(advertConfig != null){
                advertConfig.setStatus(0);
                advertConfig.setUpdateTime(new Date());
                advertConfig.setUpdateBy(advertConfigQueDTO.getUserId());
                advertConfigService.updateByPrimaryKeySelective(advertConfig);
            }else{
                return Result.failure("不存在广告配置记录").toJSON();
            }
            if(advertConfig.getModuleDictKey().equals(DictionaryConstant.CGJ_ADVERT_MODULE_1)){
                // 指引页
                advertConfigService.putStartupPageToRedis();
            }else{
                // 更新到缓存
                advertConfigService.putAdvertToRedis();
            }
            return Result.success("删除成功").toJSON();
        } catch (Exception e) {
            logger.error("删除广告配置异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 保存广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/saveAdvertConfig")
    public String saveAdvertConfig(@RequestBody AdvertConfigSaveDTO advertConfigSaveDTO) {
        logger.info("subject:{},param:{}","广告配置-提交",JsonUtil.gsonStr(advertConfigSaveDTO));
        try{
            AdvertConfig advertConfig = null;
            // 是否新增
            boolean isAdd = false;
            if(advertConfigSaveDTO.getId() != null && advertConfigSaveDTO.getId() > 0){
                advertConfig = advertConfigService.selectByPrimaryKey(advertConfigSaveDTO.getId());
            }else{
                isAdd = true;
                advertConfig = new AdvertConfig();
                advertConfig.setCreateBy(advertConfigSaveDTO.getUserId());
                advertConfig.setCreateTime(new Date());
                advertConfig.setStatus(1);

            }
            // 新增时没有排序默认给1
            if(StringUtil.isEmpty(advertConfigSaveDTO.getSort())){
                advertConfig.setSort(1);
            }else{
                advertConfig.setSort(advertConfigSaveDTO.getSort());
            }
            advertConfig.setClientDictKey(advertConfigSaveDTO.getClientDictKey());
            advertConfig.setModuleDictKey(advertConfigSaveDTO.getModuleDictKey());
            advertConfig.setTitle(advertConfigSaveDTO.getTitle());
            advertConfig.setRemark(advertConfigSaveDTO.getRemark());
            advertConfig.setJumpUrl(advertConfigSaveDTO.getJumpUrl());
            advertConfig.setSwitchStatus(advertConfigSaveDTO.getSwitchStatus());
            advertConfig.setUpdateTime(new Date());
            advertConfig.setUpdateBy(advertConfigSaveDTO.getUserId());
            if(isAdd){
                advertConfigService.insert(advertConfig);
            }else{
                advertConfigService.updateByPrimaryKeySelective(advertConfig);
            }
            if(StringUtil.isNotEmpty(advertConfigSaveDTO.getFileUrl())){
                CommImg commImg = commImgService.selectByCommIdAndType(advertConfigSaveDTO.getId(), DictionaryConstant.CGJ_IMG_TYPE_4);
                if(commImg == null){
                    isAdd = true;
                    commImg = new CommImg();
                    commImg.setCreateBy(advertConfigSaveDTO.getUserId());
                    commImg.setCreateTime(new Date());
                    commImg.setCommId(advertConfig.getId());
                    commImg.setImgType(DictionaryConstant.CGJ_IMG_TYPE_4);
                    commImg.setStatus(1);
                }
                commImg.setFileName(advertConfigSaveDTO.getFileName());
                commImg.setFileUrl(advertConfigSaveDTO.getFileUrl());
                commImg.setLittleFileName(advertConfigSaveDTO.getLittleFileName());
                commImg.setLittleFileUrl(advertConfigSaveDTO.getLittleFileUrl());
                commImg.setFileType(advertConfigSaveDTO.getFileType());
                commImg.setUpdateBy(advertConfigSaveDTO.getUserId());
                commImg.setUpdateTime(new Date());
                if(isAdd){
                    commImgService.insert(commImg);
                }else{
                    commImgService.updateByPrimaryKeySelective(commImg);
                }
            }
            // 指引页
            advertConfigService.putStartupPageToRedis();
            // 更新到缓存
            advertConfigService.putAdvertToRedis();
            return Result.success().toJSON();
        }catch (Exception e){
            logger.error("广告配置-提交异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 查询启动页、广告
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/queryAdverts")
    public Result queryAdverts(@RequestBody AdvertConfigQueDTO advertConfigQueDTO){
        logger.info("查询启动页、广告,param:"+ JsonUtil.gsonStr(advertConfigQueDTO));
        try{
            Map<String, Object> jMap = new HashMap<>();
            if(DictionaryConstant.CGJ_ADVERT_MODULE_1.equals(advertConfigQueDTO.getModuleDictKey())){
                // 启动页
                List<Map<String, Object>> startupPageAdverts = advertConfigService.queryAdverts(advertConfigQueDTO.getClientDictKey(),DictionaryConstant.CGJ_ADVERT_MODULE_1);
                if (startupPageAdverts != null && startupPageAdverts.size() > 0) {
                    startupPageAdverts.forEach(img -> {
                        try {
                            if(StringUtil.isNotEmpty(img.get("fileName"))){
                                img.put("fileUrl",FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                            }
                        } catch (Exception e) {
                            logger.info("获取图片地址异常", e);
                        }
                    });
                }
                jMap.put("startupPageAdverts", startupPageAdverts);
                // 加到缓存
                RedisUtil.redisSet(RedisConstant.CGJ_APP_STARTUP_PAGE, Result.success(jMap),null);
            }else{
                String advertVersion = null;
                if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_1)){
                    advertVersion = RedisConstant.CGJ_APP_ADVERT_VERSION;
                }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_2)){
                    advertVersion = RedisConstant.CGJ_H5_ADVERT_VERSION;
                }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_3)){
                    advertVersion = RedisConstant.CGJ_MINIPROGRAM_ADVERT_VERSION;
                }
                // 读缓存
                Map<String, Object> commonVersion = (Map<String, Object>) RedisUtil.redisGet(RedisConstant.CGJ_COMMON_VERSION);
                String version = null;
                if(commonVersion == null){
                    // 如果没有公共缓存，就生成公共缓存
                    version = "V" + DateUtil.timeStamp();
                    serviceFuncService.putVesionToRedis(RedisConstant.CGJ_VERSION_TYPE_1,version,true);
                }else{
                    // 有公共缓存，就从公共缓存拿广告的版本号
                    Map<String, Object> versionData = (Map<String, Object>) commonVersion.get("versionData");
                    if(StringUtil.isEmpty(versionData.get("advertVersion"))){
                        version = "V" + DateUtil.timeStamp();
                    }else{
                        version = versionData.get("advertVersion").toString();
                    }
                }

                String[] advertModules = {DictionaryConstant.CGJ_ADVERT_MODULE_2,DictionaryConstant.CGJ_ADVERT_MODULE_3,DictionaryConstant.CGJ_ADVERT_MODULE_4};
                for(String advertModule : advertModules){
                    List<Map<String, Object>> adverts = advertConfigService.queryAdverts(advertConfigQueDTO.getClientDictKey(), advertModule);
                    if (adverts != null && adverts.size() > 0) {
                        adverts.forEach(img -> {
                            try {
                                if(StringUtil.isNotEmpty(img.get("fileName"))){
                                    img.put("fileUrl", FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                                }
                            } catch (Exception e) {
                                logger.info("获取图片地址异常", e);
                            }
                        });
                    }
                    if(advertModule.equals(DictionaryConstant.CGJ_ADVERT_MODULE_2)){
                        // 首页
                        jMap.put("homePageAdverts",adverts);
                    }else if(advertModule.equals(DictionaryConstant.CGJ_ADVERT_MODULE_3)){
                        // 服务
                        jMap.put("servicePageAdverts",adverts);
                    }else if(advertModule.equals(DictionaryConstant.CGJ_ADVERT_MODULE_4)){
                        // 车圈
                        jMap.put("carPageAdverts",adverts);
                    }
                }
                jMap.put("version",version);
                // 加到缓存
                RedisUtil.redisSet(advertVersion, Result.success(jMap),null);
            }
            return Result.success(jMap);
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询启动页、广告异常",e);
            return Result.failure("失败");
        }
    }
}
