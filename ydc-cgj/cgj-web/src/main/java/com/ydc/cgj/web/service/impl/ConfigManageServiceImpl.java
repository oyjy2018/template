package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.IConfigManageFeignService;
import com.ydc.cgj.web.service.ConfigManageService;
import com.ydc.cgj.web.service.UserService;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2018/12/27
 */
@Service
public class ConfigManageServiceImpl implements ConfigManageService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    @Autowired
    IConfigManageFeignService iConfigManageFeignService;
    @Override
    public String queryHomeModuleList(HomeModuleQueDTO homeModuleQueDTO) {
        return iConfigManageFeignService.queryHomeModuleList(homeModuleQueDTO);
    }

    @Override
    public String updateHomeModuleShowStatus(HomeModuleQueDTO homeModuleQueDTO) {
        return iConfigManageFeignService.updateHomeModuleShowStatus(homeModuleQueDTO);
    }

    @Override
    public String getHomeModule(HomeModuleQueDTO homeModuleQueDTO) {
        return iConfigManageFeignService.getHomeModule(homeModuleQueDTO);
    }

    @Override
    public String updateHomeModule(HomeModuleSaveDTO homeModuleSaveDTO) {
        return iConfigManageFeignService.updateHomeModule(homeModuleSaveDTO);
    }

    @Override
    public String queryAdvertConfigList(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.queryAdvertConfigList(advertConfigQueDTO);
    }

    @Override
    public String getAdvertConfigDetail(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.getAdvertConfigDetail(advertConfigQueDTO);
    }

    @Override
    public String updateAdvertConfigStatus(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.updateAdvertConfigStatus(advertConfigQueDTO);
    }

    @Override
    public String deleteAdvertConfig(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.deleteAdvertConfig(advertConfigQueDTO);
    }

    @Override
    public String saveAdvertConfig(AdvertConfigSaveDTO advertConfigSaveDTO) {
        return iConfigManageFeignService.saveAdvertConfig(advertConfigSaveDTO);
    }

    @Override
    public String queryButtonConfigList(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.queryButtonConfigList(advertConfigQueDTO);
    }

    @Override
    public String updateButtonConfigStatus(AdvertConfigQueDTO advertConfigQueDTO) {
        return iConfigManageFeignService.updateButtonConfigStatus(advertConfigQueDTO);
    }

    @Override
    public String reloadAppVersion() {
        return iConfigManageFeignService.reloadAppVersion();
    }
}
