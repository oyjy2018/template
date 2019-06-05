package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleSaveDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2018/12/27
 */
@Service
@FeignClient(value = "service-sys")
public interface IConfigManageFeignService {

    /**
     * 查询首页配置列表
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/homeModule/queryHomeModuleList")
    String queryHomeModuleList(@RequestBody HomeModuleQueDTO homeModuleQueDTO);

    /**
     * 修改首页配置是否显示
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/homeModule/updateHomeModuleShowStatus")
    String updateHomeModuleShowStatus(@RequestBody HomeModuleQueDTO homeModuleQueDTO);
    /**
     * 获取首页配置详情
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/homeModule/getHomeModule")
    String getHomeModule(@RequestBody HomeModuleQueDTO homeModuleQueDTO);
    /**
     * 首页配置-修改
     * @author: hejiangping
     * @date: 2018/12/28
     */
    @PostMapping(value = "/homeModule/updateHomeModule")
    String updateHomeModule(@RequestBody HomeModuleSaveDTO homeModuleSaveDTO);
    /**
     * 查询广告配置列表
     * @author: hejiangping
     * @date: 2019/1/2
     */
    @PostMapping(value = "/advertConfig/queryAdvertConfigList")
    String queryAdvertConfigList(@RequestBody AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 获取广告配置详情
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/advertConfig/getAdvertConfigDetail")
    String getAdvertConfigDetail(@RequestBody AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 修改广告配置是否启用
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/advertConfig/updateAdvertConfigStatus")
    String updateAdvertConfigStatus(@RequestBody AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 删除广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/advertConfig/deleteAdvertConfig")
    String deleteAdvertConfig(@RequestBody AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 保存广告配置
     * @author: hejiangping
     * @date: 2019/1/3
     */
    @PostMapping(value = "/advertConfig/saveAdvertConfig")
    String saveAdvertConfig(@RequestBody AdvertConfigSaveDTO advertConfigSaveDTO);

    /**
     * 查询按钮配置列表
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/buttonConfig/queryButtonConfigList")
    String queryButtonConfigList(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 修改按钮配置是否开启
     * @author: hejiangping
     * @date: 2019/1/8
     */
    @PostMapping(value = "/buttonConfig/updateButtonConfigStatus")
    String updateButtonConfigStatus(AdvertConfigQueDTO advertConfigQueDTO);
    /**
     * 重新加载APP版本
     * @author: hejiangping
     * @date: 2019/1/12
     */
    @PostMapping(value = "/appVersion/reloadAppVersion")
    String reloadAppVersion();
}
