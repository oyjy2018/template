package com.ydc.service.sys.controller;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.model.cgj.sys.AdvertConfig;
import com.ydc.model.cgj.sys.CgjButtonConfig;
import com.ydc.service.sys.service.CgjButtonConfigService;
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
 * 按钮配置
 * @author hejiangping
 * @date 2019/1/8
 */
@RestController
@RequestMapping(value = "/buttonConfig")
public class CgjButtonConfigController {
    private static Logger logger = LogManager.getLogger(CgjButtonConfigController.class);
    @Autowired
    CgjButtonConfigService cgjButtonConfigService;
    /**
     * 查询按钮配置列表
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/queryButtonConfigList")
    public String queryButtonConfigList(@RequestBody AdvertConfigQueDTO advertConfigQueDTO){
        logger.info("查询按钮配置列表,param:"+ JsonUtil.gsonStr(advertConfigQueDTO));
        try{
            List<Map<String, Object>> ret = cgjButtonConfigService.queryButtonConfigList(advertConfigQueDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows",ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询按钮配置列表异常",e);
            return Result.failure("失败").toJSON();
        }
    }
    /**
     * 修改按钮配置是否启用
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/updateButtonConfigStatus")
    public String updateButtonConfigStatus(@RequestBody AdvertConfigQueDTO advertConfigQueDTO) {
        logger.info("subject:{},param:{}","修改按钮配置是否启用",JsonUtil.gsonStr(advertConfigQueDTO));
        try {
            if(StringUtil.isNotEmpty(advertConfigQueDTO.getId()) && advertConfigQueDTO.getId() > 0){
                CgjButtonConfig cgjButtonConfig = cgjButtonConfigService.selectByPrimaryKey(advertConfigQueDTO.getId());
                if(cgjButtonConfig != null){
                    cgjButtonConfig.setSwitchStatus(advertConfigQueDTO.getSwitchStatus());
                    cgjButtonConfig.setUpdateTime(new Date());
                    cgjButtonConfig.setUpdateBy(advertConfigQueDTO.getUserId());
                    cgjButtonConfigService.updateByPrimaryKeySelective(cgjButtonConfig);
                }else{
                    return Result.failure("不存在按钮配置记录").toJSON();
                }
            }else{
                cgjButtonConfigService.updateSwitchStatus(advertConfigQueDTO);
            }
            // 更新到缓存
            cgjButtonConfigService.putAdvertToRedis();
            return Result.success("修改成功").toJSON();
        } catch (Exception e) {
            logger.error("修改按钮配置是否启用异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 查询按钮配置
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/queryButtonConfigs")
    public Result queryButtonConfigs(@RequestBody AdvertConfigQueDTO advertConfigQueDTO){
        logger.info("查询按钮配置,param:"+ JsonUtil.gsonStr(advertConfigQueDTO));
        try{
            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> buttonConfigs = cgjButtonConfigService.queryButtonConfigs(advertConfigQueDTO.getClientDictKey());
            result.put("buttonConfigs",buttonConfigs);
            String buttonRedis = null;
            if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_1)){
                buttonRedis = RedisConstant.CGJ_APP_BUTTON;
            }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_2)){
                buttonRedis = RedisConstant.CGJ_H5_BUTTON;
            }else if(advertConfigQueDTO.getClientDictKey().equals(DictionaryConstant.CGJ_CLIENT_3)){
                buttonRedis = RedisConstant.CGJ_MINIPROGRAM_BUTTON;
            }
            // 加到缓存
            RedisUtil.redisSet(buttonRedis, Result.success(result),null);
            return Result.success(result);
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询按钮配置异常",e);
            return Result.failure("失败");
        }
    }
}
