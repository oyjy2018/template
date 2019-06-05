package com.ydc.cgj.app.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.app.feignService.IConfigFeignService;
import com.ydc.cgj.app.service.ConfigService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjAppVersionQueDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/3
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource
    IConfigFeignService iConfigFeignService;
    @Override
    public Result queryHomeModules() {
        return iConfigFeignService.queryHomeModules();
    }

    @Override
    public Result queryAdverts(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigFeignService.queryAdverts(advertConfigQueDTO);
    }

    @Override
    public Result queryButtonConfigs(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigFeignService.queryButtonConfigs(advertConfigQueDTO);
    }

    @Override
    public Result getNewestVersion(CgjAppVersionQueDTO cgjAppVersionQueDTO) {
        return iConfigFeignService.getNewestVersion(cgjAppVersionQueDTO);
    }
}
