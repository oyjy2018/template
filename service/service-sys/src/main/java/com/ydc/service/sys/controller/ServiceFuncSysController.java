package com.ydc.service.sys.controller;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.commom.view.vo.cgj.ServiceConfigVO;
import com.ydc.commom.view.vo.cgj.ServiceFuncVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ServiceFunc;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.service.sys.service.HomeModuleService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("serviceFuncSys")
public class ServiceFuncSysController {
    private static Logger logger = LogManager.getLogger(ServiceFuncSysController.class);

    @Autowired
    ServiceFuncService serviceFuncService;
    @Autowired
    HomeModuleService homeModuleService;

    @RequestMapping("/insertServiceFunc")
    public String insertServiceFunc(@RequestBody ServiceFuncDTO serviceFuncDTO) {
        logger.info("新增服务功能,param:" + JsonUtil.gsonStr(serviceFuncDTO));
        User user = WebShiroTokenManager.getUser();
        if(user == null){
            return Result.failure("您没有登录，请重新登录").toJSON();
        }

        try {
            CommImg img = serviceFuncDTO.getFile();
            if (StringUtil.isEmpty(serviceFuncDTO.getServiceId())
                    || StringUtil.isEmpty(serviceFuncDTO.getUrl())
                    || StringUtil.isEmpty(serviceFuncDTO.getFuncName())
                    ) {
                return Result.failure("请查看跳转链接、功能名称等是否有值").toJSON();
            }
            if (StringUtil.isEmpty(img.getFileUrl())
                    || StringUtil.isEmpty(img.getFileName())
                    || StringUtil.isEmpty(img.getLittleFileUrl())
                    || StringUtil.isEmpty(img.getLittleFileName())
                    ) {
                return Result.failure("请查看图片url及名称是否有值").toJSON();
            }
            serviceFuncDTO.setCreateBy(user.getId());
            serviceFuncService.insertServiceFunc(serviceFuncDTO);
            return Result.success("新增成功").toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("添加服务功能失败").toJSON();
        }
    }

    @RequestMapping("/updateServiceFunc")
    public String updateServiceFunc(@RequestBody ServiceFuncDTO serviceFuncDTO) {
        logger.info("修改服务功能,param:" + JsonUtil.gsonStr(serviceFuncDTO));
        User user = WebShiroTokenManager.getUser();
        if(user == null){
            return Result.failure("您没有登录，请重新登录").toJSON();
        }
        try {
            CommImg img = serviceFuncDTO.getFile();
            if (StringUtil.isEmpty(serviceFuncDTO.getServiceId())
                    || StringUtil.isEmpty(serviceFuncDTO.getUrl())
                    || StringUtil.isEmpty(serviceFuncDTO.getFuncName())
                    ) {
                return Result.failure("请查看跳转链接、功能名称等是否有值").toJSON();
            }
            serviceFuncDTO.setUpdateBy(user.getId());
            serviceFuncService.updateServiceFunc(serviceFuncDTO);
            return Result.success("新增成功").toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("添加服务功能失败").toJSON();
        }
    }

    @RequestMapping("/deleteServiceFunc")
    public String deleteServiceFunc(@RequestParam("id") Integer id) {
        logger.info("删除服务功能,param:" + id);
        User user = WebShiroTokenManager.getUser();
        if(user == null){
            return Result.failure("您没有登录，请重新登录").toJSON();
        }
        try {
            serviceFuncService.deleteServiceFunc(id);
            return Result.success("删除成功").toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("添加服务功能失败").toJSON();
        }
    }

    /**
     * 查询有效的服务功能列表，用于H5或者APP端展示
     *
     * @param
     * @return
     */
    @RequestMapping("/searchServiceFunc")
    public String searchServiceFunc(@RequestBody String data) {
        logger.info("查询有效的服务功能列表，用于H5或者APP端展示,param:");
        try {
            Map<String, Object> resList = serviceFuncService.searchServiceFunc(data);
            return Result.success(resList).toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("查询有效的服务功能列表失败").toJSON();
        }
    }

    /**
     * 查询所有的服务功能列表，用于pc端配置
     *
     * @param dictionaryDetail
     * @return
     */
    @RequestMapping("/searchAllServiceFunc")
    public String searchAllServiceFunc(@RequestBody DictionaryDetail dictionaryDetail) {
        logger.info("查询所有的服务功能列表，用于pc端配置,param:" + JsonUtil.gsonStr(dictionaryDetail));
        try {
            List<ServiceConfigVO> list = serviceFuncService.searchAllServiceFunc(dictionaryDetail);
            Map<String, Object> jMap = new HashMap<>();
//            List<DictionaryDedailVO> list = (List<DictionaryDedailVO>) map.get("list");
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(list));
            jMap.put("rows",list);
//            jMap.put("EunmsList",map.get("map"));
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("查询首页配置列表异常").toJSON();
        }
    }

    /**
     * 查询用车服务 用于首页展示
     *
     * @return
     */
    @RequestMapping("/searchServiceFuncShowHome")
    public String searchServiceFuncShowHome() {
        logger.info("查询用车服务 用于首页展示 开始");
        try {
            List<ServiceFuncVO> resList = serviceFuncService.searchServiceFuncShowHome();
            return Result.success(resList).toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "查询首页配置列表异常", e);
            return Result.failure("添加服务功能失败").toJSON();
        }
    }

    @RequestMapping("/getEnumList")
    public String getEnumList() {
        List<Map<String,String>> list = serviceFuncService.getEnumList();
      return Result.success(list).toJSON();
    }

    /**
     * 更新服务缓存
     * @author: hejiangping
     * @date: 2019/1/10
     */
    @RequestMapping("/writeRedis")
    public String writeRedis() {
        try {
            homeModuleService.writeRedis();
            return Result.success().toJSON();
        } catch (Exception e) {
            logger.error("subject:{},e:{}", "更新服务缓存异常", e);
            return Result.failure("更新服务缓存失败").toJSON();
        }
    }
}
