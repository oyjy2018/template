package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2018/12/27
 */
public interface ConfigManageService {

    /**
     * 查询首页配置列表
     * @author: hejiangping
     * @date: 2018/12/27
     */
    String queryHomeModuleList(HomeModuleQueDTO homeModuleQueDTO);

    /**
     * 修改首页配置是否显示
     * @author: hejiangping
     * @date: 2018/12/28
     */
    String updateHomeModuleShowStatus(HomeModuleQueDTO homeModuleQueDTO);
    /**
     * 获取首页配置详情
     * @author: hejiangping
     * @date: 2018/12/28
     */
    String getHomeModule(HomeModuleQueDTO homeModuleQueDTO);
    /**
     * 首页配置-修改
     * @author: hejiangping
     * @date: 2018/12/28
     */
    String updateHomeModule(HomeModuleSaveDTO homeModuleSaveDTO);
    /**
     * 查询广告配置列表
     * @author: hejiangping
     * @date: 2019/1/2
     */
    String queryAdvertConfigList(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 获取广告配置详情
     * @author: hejiangping
     * @date: 2019/1/3
     */
    String getAdvertConfigDetail(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 修改广告配置是否启用
     * @author: hejiangping
     * @date: 2019/1/3
     */
    String updateAdvertConfigStatus(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 删除广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    String deleteAdvertConfig(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 保存广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    String saveAdvertConfig(AdvertConfigSaveDTO advertConfigSaveDTO);

    /**
     * 查询按钮配置列表
     * @author: hejiangping
     * @date: 2019/1/8
     */
    String queryButtonConfigList(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 修改按钮配置是否开启
     * @author: hejiangping
     * @date: 2019/1/8
     */
    String updateButtonConfigStatus(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 重新加载APP版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    String reloadAppVersion();
}
