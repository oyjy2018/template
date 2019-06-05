package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgSaveDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;


@Service
@FeignClient(value = "service-sys")
public interface CgjCarzoneCfgFeignService {

    /**
     * 获取车圈配置列表
     *
     * @param cgjCarzoneCfgQueDTO
     * @return
     */
    @PostMapping(value = "/carzone/queryCarzoneCfgList")
    public String queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);

    /**
     * 获取车圈配置详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/carzone/queryCarzoneCfgDetails")
    public String queryCarzoneCfgDetails(Integer id);

    /**
     * 保存或修改车圈配置信息
     *
     * @param cgjCarzoneCfgSaveDTO
     * @return
     */
    @PostMapping(value = "/carzone/saveOrUpdateCarzoneCfg")

    public String saveOrUpdateCarzoneCfg(CgjCarzoneCfgSaveDTO cgjCarzoneCfgSaveDTO);
    /**
     * 删除车圈配置信息
     *
     * @param cgjCarzoneCfg
     * @return
     */
    @PostMapping(value = "/carzone/deleteCarzoneCfg")
    String deleteCarzoneCfg(CgjCarzoneCfg cgjCarzoneCfg);

    /**
     * 更改是否展示状态
     *
     * @param cgjCarzoneCfg
     * @return
     */
    @PostMapping(value = "/carzone/updateShowStatus")
    String updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg);
}
