package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 服务功能service
 *
 * @author wangcan
 * @create 2018-09-04 19:53
 **/
@Service
@FeignClient(value = "service-sys")
public interface ServiceFuncFeignService {

    /**
     * 新增服务功能
     * @param serviceFuncDTO
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/insertServiceFunc")
    String insertServiceFunc(DictionaryDetailDTO dictionaryDetailDTO);

    /**
     * 修改服务功能
     * @param serviceFuncDTO
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/updateServiceFunc")
    String updateServiceFunc(DictionaryDetailDTO dictionaryDetailDTO);

    /**
     * 删除服务功能
     * @param id
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/daleteServiceFunc")
    String daleteServiceFunc(Integer id);

    /**
     * 查询服务功能列表 h5  app
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/searchServiceFunc")
    String searchServiceFunc(DictionaryDetail dictionaryDetail);

    /**
     * 查询服务功能列表 pc
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/searchAllServiceFunc")
    String searchAllServiceFunc(DictionaryDetail dictionaryDetail);

    /**
     * 查询用车服务 用于首页展示
     * @return
     */
    @PostMapping(value = "/serviceFuncSys/searchServiceFuncShowHome")
    String searchServiceFuncShowHome();

    @PostMapping(value = "/serviceFuncSys/getEnumList")
    String getEnumList();

    /**
     * 更新服务缓存
     * @author: hejiangping
     * @date: 2019/1/10
     */
    @PostMapping(value = "/serviceFuncSys/writeRedis")
    String writeRedis();
}
