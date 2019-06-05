package com.ydc.service.sys.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.dao.cgj.sys.AdvertConfigDao;
import com.ydc.model.cgj.sys.AdvertConfig;
import com.ydc.service.sys.service.AdvertConfigService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/2
 */
@Service
public class AdvertConfigServiceImpl implements AdvertConfigService {
    protected static final Logger logger = LogManager.getLogger(AdvertConfigService.class);
    @Autowired
    AdvertConfigDao advertConfigDao;
    @Autowired
    AdvertConfigService advertConfigService;
    @Autowired
    ServiceFuncService serviceFuncService;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return advertConfigDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AdvertConfig record) {
        return advertConfigDao.insert(record);
    }

    @Override
    public int insertSelective(AdvertConfig record) {
        return advertConfigDao.insertSelective(record);
    }

    @Override
    public AdvertConfig selectByPrimaryKey(Integer id) {
        return advertConfigDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AdvertConfig record) {
        return advertConfigDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdvertConfig record) {
        return advertConfigDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryAdvertConfigList(AdvertConfigQueDTO advertConfigQueDTO) {
        return PaginationUtil.paginationQuery(advertConfigQueDTO, (temp) -> advertConfigDao.queryAdvertConfigList(temp));
    }

    @Override
    public Map<String, Object> getAdvertConfigDetail(Integer id) {
        return advertConfigDao.getAdvertConfigDetail(id);
    }

    @Override
    public List<Map<String, Object>> queryAdverts(String clientDictKey, String moduleDictKey) {
        return advertConfigDao.queryAdverts(clientDictKey, moduleDictKey);
    }

    @Override
    public void putAdvertToRedis() {
        String version = "V" + DateUtil.timeStamp();
        String[] advertVersions = {RedisConstant.CGJ_APP_ADVERT_VERSION,RedisConstant.CGJ_H5_ADVERT_VERSION,RedisConstant.CGJ_MINIPROGRAM_ADVERT_VERSION};
        Map<String, Object> versionData = null;
        String clientDictKey = null;
        String[] advertModules = {DictionaryConstant.CGJ_ADVERT_MODULE_2,DictionaryConstant.CGJ_ADVERT_MODULE_3,DictionaryConstant.CGJ_ADVERT_MODULE_4};
        for(String advertVersion : advertVersions){
            if(RedisConstant.CGJ_APP_ADVERT_VERSION.equals(advertVersion)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_1;
            }else if(RedisConstant.CGJ_H5_ADVERT_VERSION.equals(advertVersion)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_2;
            }else if(RedisConstant.CGJ_MINIPROGRAM_ADVERT_VERSION.equals(advertVersion)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_3;
            }
            versionData = new HashMap<>();
            versionData.put("version",version);
            try {
                for(String advertModule : advertModules){
                    List<Map<String, Object>> adverts = advertConfigService.queryAdverts(clientDictKey, advertModule);
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
                        versionData.put("homePageAdverts",adverts);
                    }else if(advertModule.equals(DictionaryConstant.CGJ_ADVERT_MODULE_3)){
                        // 服务
                        versionData.put("servicePageAdverts",adverts);
                    }else if(advertModule.equals(DictionaryConstant.CGJ_ADVERT_MODULE_4)){
                        // 车圈
                        versionData.put("carPageAdverts",adverts);
                    }
                }
                versionData.put("version",version);
                // 加到缓存
                RedisUtil.redisSet(advertVersion, Result.success(versionData),null);
            } catch (Exception e) {
                logger.info("更新广告到redis缓存异常", e);
            }
        }
        // 更新版本号到公共缓存
        serviceFuncService.putVesionToRedis(RedisConstant.CGJ_VERSION_TYPE_1,version,true);
    }

    @Override
    public void putStartupPageToRedis() {
        Map<String, Object> jMap = new HashMap<>();
        // 启动页
        List<Map<String, Object>> startupPageAdverts = advertConfigService.queryAdverts(DictionaryConstant.CGJ_CLIENT_1,DictionaryConstant.CGJ_ADVERT_MODULE_1);
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
    }
}
