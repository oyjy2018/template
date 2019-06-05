package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgSaveDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;

public interface CgjCarzoneCfgService {

    /**
     * 获取车圈配置列表
     *
     * @param cgjCarzoneCfgQueDTO
     * @return
     */
    public String queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO);

    /**
     * 获取车圈配置详情
     *
     * @param id
     * @return
     */
     String queryCarzoneCfgDetails(Integer id);

    /**
     * 保存或修改车圈配置信息
     *
     * @param cgjCarzoneCfgSaveDTO
     * @return
     */
    public String saveOrUpdateCarzoneCfg(CgjCarzoneCfgSaveDTO cgjCarzoneCfgSaveDTO);

    /**
     * 删除车圈配置详情
     *
     * @param id
     * @return
     */
    public String deleteCarzoneCfg(Integer id);

    /**
     * 更改是否展示状态
     *
     * @param cgjCarzoneCfg
     * @return
     */
    String updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg);
}
