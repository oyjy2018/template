package com.ydc.service.sys.controller;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import com.ydc.commom.view.vo.cgj.CarStoreVO;
import com.ydc.commom.view.vo.cgj.ServiceFuncVO;
import com.ydc.dao.cgj.common.DictionaryDao;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ServiceFunc;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.model.cgj.sys.HomeModule;
import com.ydc.service.sys.service.CommImgService;
import com.ydc.service.sys.service.HomeModuleService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 首页配置
 * @author hejiangping
 * @date 2018/12/27
 */
@RestController
@RequestMapping(value = "/homeModule")
public class HomeModuleController {
    private static Logger logger = LogManager.getLogger(HomeModuleController.class);

    @Autowired
    private HomeModuleService homeModuleService;
    @Autowired
    private CommImgService commImgService;

    /**
     * 查询首页配置列表
     * @author: hejiangping
     * @date: 2018/12/27
     */
    @PostMapping(value = "/queryHomeModuleList")
    public String queryHomeModuleList(@RequestBody HomeModuleQueDTO homeModuleQueDTO){
        logger.info("查询首页配置列表,param:"+ JsonUtil.gsonStr(homeModuleQueDTO));
        try{
            List<Map<String, Object>> ret = homeModuleService.getHomeModuleList(homeModuleQueDTO);
            for(Map<String, Object> img:ret){
                try {
                    if(StringUtil.isNotEmpty(img.get("fileName"))){
                        img.put("fileUrl",FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                        img.put("littleFileUrl",FileUtil.getBrowseFile(img.get("littleFileUrl").toString(),img.get("littleFileName").toString()));
                    }else{
                        img.put("fileUrl","");
                        img.put("littleFileUrl","");
                    }
                } catch (Exception e) {
                    logger.error("subject:{},e:{}","获取图片地址异常",e);
                }
            }
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows",ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询首页配置列表异常",e);
            return Result.failure("失败").toJSON();
        }
    }
    /**
     * 修改首页配置是否显示
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/updateHomeModuleShowStatus")
    public String updateHomeModuleShowStatus(@RequestBody HomeModuleQueDTO homeModuleQueDTO) {
        logger.info("subject:{},param:{}","修改首页配置是否显示",JsonUtil.gsonStr(homeModuleQueDTO));
        try {
            HomeModule homeModule = homeModuleService.selectByPrimaryKey(homeModuleQueDTO.getId());
            if(homeModule != null){
                homeModule.setShowStatus(homeModuleQueDTO.getShowStatus());
                homeModule.setUpdateTime(new Date());
                homeModule.setUpdateBy(homeModuleQueDTO.getUserId());
                homeModuleService.updateByPrimaryKeySelective(homeModule);
            }else{
                return Result.failure("数据不存在").toJSON();
            }
            // 更新服务缓存
            homeModuleService.writeRedis();
            return Result.success("修改成功").toJSON();
        } catch (Exception e) {
            logger.error("修改首页配置是否显示异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 获取首页配置详情
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/getHomeModule")
    public String getHomeModule(@RequestBody HomeModuleQueDTO homeModuleQueDTO) {
        logger.info("subject:{},param:{}","获取首页配置详情",JsonUtil.gsonStr(homeModuleQueDTO));
        Map<String, Object> result = homeModuleService.getHomeModuleDetail(homeModuleQueDTO.getId());
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
     * 首页配置-修改
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/updateHomeModule")
    public String updateHomeModule(@RequestBody HomeModuleSaveDTO homeModuleSaveDTO) {
        logger.info("subject:{},param:{}","首页配置-修改",JsonUtil.gsonStr(homeModuleSaveDTO));
        try{
            HomeModule homeModule = homeModuleService.selectByPrimaryKey(homeModuleSaveDTO.getId());
            if(homeModule != null){
                homeModule.setSonModuleId(homeModuleSaveDTO.getSonModuleId());
                homeModule.setSonModule(homeModuleSaveDTO.getSonModule());
                homeModule.setFunctionName(homeModuleSaveDTO.getFunctionName());
                homeModule.setRemark(homeModuleSaveDTO.getRemark());
                homeModule.setJumpUrl(homeModuleSaveDTO.getJumpUrl());
                homeModule.setShowStatus(homeModuleSaveDTO.getShowStatus());
                homeModule.setUpdateTime(new Date());
                homeModule.setUpdateBy(homeModuleSaveDTO.getUserId());
                homeModuleService.updateByPrimaryKeySelective(homeModule);
            }
            if(StringUtil.isNotEmpty(homeModuleSaveDTO.getFileUrl())){
                CommImg commImg = commImgService.selectByCommIdAndType(homeModuleSaveDTO.getId(), DictionaryConstant.CGJ_IMG_TYPE_1);
                // 是否新增
                boolean isAdd = false;
                if(commImg == null){
                    isAdd = true;
                    commImg = new CommImg();
                    commImg.setCreateBy(homeModuleSaveDTO.getUserId());
                    commImg.setCreateTime(new Date());
                    commImg.setCommId(homeModule.getId());
                    commImg.setImgType(DictionaryConstant.CGJ_IMG_TYPE_1);
                    commImg.setStatus(1);
                }
                commImg.setFileName(homeModuleSaveDTO.getFileName());
                commImg.setFileUrl(homeModuleSaveDTO.getFileUrl());
                commImg.setLittleFileName(homeModuleSaveDTO.getLittleFileName());
                commImg.setLittleFileUrl(homeModuleSaveDTO.getLittleFileUrl());
                commImg.setFileType(homeModuleSaveDTO.getFileType());
                commImg.setUpdateBy(homeModuleSaveDTO.getUserId());
                commImg.setUpdateTime(new Date());
                if(isAdd){
                    commImgService.insert(commImg);
                }else{
                    commImgService.updateByPrimaryKeySelective(commImg);
                }
            }
            // 更新服务缓存
            homeModuleService.writeRedis();
            return Result.success().toJSON();
        }catch (Exception e){
            logger.error("首页配置-修改异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查询首页模块
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/queryHomeModules")
    public Result queryHomeModules(){
        logger.info("查询首页模块");
        try{
            // 服务模块
            List<Map<String, Object>> serviceModules = homeModuleService.queryHomeModules(DictionaryConstant.HOME_MODULE_1);
            for(Map<String, Object> img:serviceModules){
                try {
                    if(StringUtil.isNotEmpty(img.get("fileName"))){
                        img.put("fileUrl",FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                    }
                } catch (Exception e) {
                    logger.error("subject:{},e:{}","获取图片地址异常",e);
                }
            }
            // 产品模块
            List<Map<String, Object>> productModules = homeModuleService.queryHomeModules(DictionaryConstant.HOME_MODULE_2);
            for(Map<String, Object> img:productModules){
                try {
                    if(StringUtil.isNotEmpty(img.get("fileName"))){
                        img.put("fileUrl",FileUtil.getBrowseFile(img.get("fileUrl").toString(),img.get("fileName").toString()));
                    }
                } catch (Exception e) {
                    logger.error("subject:{},e:{}","获取图片地址异常",e);
                }
            }
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("serviceModules", serviceModules);
            jMap.put("productModules",productModules);
            return Result.success(jMap);
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询首页模块异常",e);
            return Result.failure("失败");
        }
    }

}
