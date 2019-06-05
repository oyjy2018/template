package com.ydc.service.sys.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.dao.cgj.sys.CgjButtonConfigDao;
import com.ydc.model.cgj.sys.CgjButtonConfig;
import com.ydc.service.sys.service.AdvertConfigService;
import com.ydc.service.sys.service.CgjButtonConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/8
 */
@Service
public class CgjButtonConfigServiceImpl implements CgjButtonConfigService {
    protected static final Logger logger = LogManager.getLogger(AdvertConfigService.class);
    @Autowired
    CgjButtonConfigDao cgjButtonConfigDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cgjButtonConfigDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CgjButtonConfig record) {
        return cgjButtonConfigDao.insert(record);
    }

    @Override
    public int insertSelective(CgjButtonConfig record) {
        return cgjButtonConfigDao.insertSelective(record);
    }

    @Override
    public CgjButtonConfig selectByPrimaryKey(Integer id) {
        return cgjButtonConfigDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CgjButtonConfig record) {
        return cgjButtonConfigDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CgjButtonConfig record) {
        return cgjButtonConfigDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> queryButtonConfigList(AdvertConfigQueDTO advertConfigQueDTO) {
        return PaginationUtil.paginationQuery(advertConfigQueDTO, (temp) -> cgjButtonConfigDao.queryButtonConfigList(temp));
    }

    @Override
    public int updateSwitchStatus(AdvertConfigQueDTO advertConfigQueDTO) {
        return cgjButtonConfigDao.updateSwitchStatus(advertConfigQueDTO);
    }

    @Override
    public List<Map<String, Object>> queryButtonConfigs(String clientDictKey) {
        return cgjButtonConfigDao.queryButtonConfigs(clientDictKey);
    }

    @Override
    public void putAdvertToRedis() {
        String[] buttons = {RedisConstant.CGJ_APP_BUTTON,RedisConstant.CGJ_H5_BUTTON,RedisConstant.CGJ_MINIPROGRAM_BUTTON};
        String clientDictKey = null;
        Map<String, Object> versionData = null;
        List<Map<String, Object>> buttonConfigs = null;
        for(String button : buttons){
            if(RedisConstant.CGJ_APP_BUTTON.equals(button)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_1;
            }else if(RedisConstant.CGJ_H5_BUTTON.equals(button)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_2;
            }else if(RedisConstant.CGJ_MINIPROGRAM_BUTTON.equals(button)){
                clientDictKey = DictionaryConstant.CGJ_CLIENT_3;
            }
            versionData = new HashMap<>();
            buttonConfigs = queryButtonConfigs(clientDictKey);
            versionData.put("buttonConfigs",buttonConfigs);
            // 加到缓存
            RedisUtil.redisSet(button, Result.success(versionData),null);
        }
    }
}
