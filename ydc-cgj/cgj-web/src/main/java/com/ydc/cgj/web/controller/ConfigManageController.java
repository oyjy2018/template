package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.ConfigManageService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 配置管理
 * @author hejiangping
 * @date 2018/12/27
 */
@RestController
@RequestMapping(value = "/configManage")
public class ConfigManageController {
    private static final Logger logger = LogManager.getLogger(StoreController.class);
    @Autowired
    private ConfigManageService configManageService;

    /**
     * 查询首页配置列表
     * @author: hejiangping
     * @date: 2018/12/27
     */
    @RequestMapping(value = "/queryHomeModuleList", method = RequestMethod.POST)
    public String queryHomeModuleList(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "查询首页配置列表", data);
        HomeModuleQueDTO homeModuleQueDTO = JSONObject.parseObject(data, HomeModuleQueDTO.class);
        return configManageService.queryHomeModuleList(homeModuleQueDTO);
    }
    /**
     * 修改首页配置是否显示
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/updateHomeModuleShowStatus")
    public String updateHomeModuleShowStatus(@RequestParam(value = "data") String data) {
        logger.info("subject:{},data:{}","修改首页配置是否显示",data);
        HomeModuleQueDTO homeModuleQueDTO = JSONObject.parseObject(data, HomeModuleQueDTO.class);
        if(StringUtil.isEmpty(homeModuleQueDTO.getShowStatus())){
            return Result.failure("是否显示不能为空").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            homeModuleQueDTO.setUserId(user.getId());
        }
        return configManageService.updateHomeModuleShowStatus(homeModuleQueDTO);
    }
    /**
     * 获取首页配置详情
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/getHomeModule")
    public String getHomeModule(@RequestParam(value = "data") String data) {
        logger.info("subject:{},data:{}","获取首页配置详情",data);
        HomeModuleQueDTO homeModuleQueDTO = JSONObject.parseObject(data, HomeModuleQueDTO.class);
        return configManageService.getHomeModule(homeModuleQueDTO);
    }
    /**
     * 首页配置-修改
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/updateHomeModule")
    public String updateHomeModule(@RequestParam(value = "data") String data) {
        logger.info("subject:{},data:{}","首页配置-修改",data);
        //参数验证 并转为目标对象
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, HomeModuleSaveDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        HomeModuleSaveDTO homeModuleSaveDTO = JSONObject.parseObject(data, HomeModuleSaveDTO.class);
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            homeModuleSaveDTO.setUserId(user.getId());
        }
        return configManageService.updateHomeModule(homeModuleSaveDTO);
    }

    /**
     * 查询广告配置列表
     * @author: hejiangping
     * @date: 2019/1/2
     */
    @RequestMapping(value = "/queryAdvertConfigList", method = RequestMethod.POST)
    public String queryAdvertConfigList(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "查询首页配置列表", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        return configManageService.queryAdvertConfigList(advertConfigQueDTO);
    }
    /**
     * 获取广告配置详情
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @RequestMapping(value = "/getAdvertConfigDetail", method = RequestMethod.POST)
    public String getAdvertConfigDetail(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "获取广告配置详情", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        return configManageService.getAdvertConfigDetail(advertConfigQueDTO);
    }
    /**
     * 修改广告配置是否启用
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @RequestMapping(value = "/updateAdvertConfigStatus", method = RequestMethod.POST)
    public String updateAdvertConfigStatus(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "修改广告配置是否启用", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        if(StringUtil.isEmpty(advertConfigQueDTO.getSwitchStatus())){
            return Result.failure("是否启用不能为空").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            advertConfigQueDTO.setUserId(user.getId());
        }
        return configManageService.updateAdvertConfigStatus(advertConfigQueDTO);
    }
    /**
     * 删除广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @RequestMapping(value = "/deleteAdvertConfig", method = RequestMethod.POST)
    public String deleteAdvertConfig(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "删除广告配置", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            advertConfigQueDTO.setUserId(user.getId());
        }
        return configManageService.deleteAdvertConfig(advertConfigQueDTO);
    }
    /**
     * 保存广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @RequestMapping(value = "/saveAdvertConfig", method = RequestMethod.POST)
    public String saveAdvertConfig(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "保存广告配置", data);
        //参数验证 并转为目标对象
        Map vaildMap = ParamVaildateUtil.vaildateAndTransfer(data, AdvertConfigSaveDTO.class);
        if ("1".equals(vaildMap.get("code"))) {
            return Result.failure(vaildMap.get("message").toString()).toJSON();
        }
        AdvertConfigSaveDTO advertConfigSaveDTO = JSONObject.parseObject(data, AdvertConfigSaveDTO.class);
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            advertConfigSaveDTO.setUserId(user.getId());
        }
        return configManageService.saveAdvertConfig(advertConfigSaveDTO);
    }

    /**
     * 查询按钮配置列表
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @RequestMapping(value = "/queryButtonConfigList", method = RequestMethod.POST)
    public String queryButtonConfigList(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "查询按钮配置列表", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        return configManageService.queryButtonConfigList(advertConfigQueDTO);
    }
    /**
     * 修改按钮配置是否启用
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @RequestMapping(value = "/updateButtonConfigStatus", method = RequestMethod.POST)
    public String updateButtonConfigStatus(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "修改按钮配置是否启用", data);
        AdvertConfigQueDTO advertConfigQueDTO = JSONObject.parseObject(data, AdvertConfigQueDTO.class);
        if(StringUtil.isEmpty(advertConfigQueDTO.getSwitchStatus())){
            return Result.failure("是否启用不能为空").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        if(user != null){
            advertConfigQueDTO.setUserId(user.getId());
        }
        return configManageService.updateButtonConfigStatus(advertConfigQueDTO);
    }
    /**
     * 重新加载APP版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    @RequestMapping(value = "/reloadAppVersion", method = RequestMethod.POST)
    public String reloadAppVersion() {
        logger.info("subject:{}", "重新加载APP版本");
        return configManageService.reloadAppVersion();
    }
}
