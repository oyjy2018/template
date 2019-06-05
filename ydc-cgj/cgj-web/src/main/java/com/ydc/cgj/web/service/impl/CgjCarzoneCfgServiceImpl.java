package com.ydc.cgj.web.service.impl;

import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.feignService.CgjCarzoneCfgFeignService;
import com.ydc.cgj.web.service.CgjCarzoneCfgService;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgSaveDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;
import com.ydc.model.cgj.sys.CommImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CgjCarzoneCfgServiceImpl implements CgjCarzoneCfgService {

    @Autowired
    private CgjCarzoneCfgFeignService cgjCarzoneCfgFeignService;

    @Override
    public String queryCarzoneCfgList(CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO) {
        return cgjCarzoneCfgFeignService.queryCarzoneCfgList(cgjCarzoneCfgQueDTO);
    }

    @Override
    public String queryCarzoneCfgDetails(Integer id) {
        return cgjCarzoneCfgFeignService.queryCarzoneCfgDetails(id);
    }

    @Override
    public String saveOrUpdateCarzoneCfg(CgjCarzoneCfgSaveDTO cgjCarzoneCfgSaveDTO) {
        Integer managerId = WebShiroTokenManager.getUser().getId();
        //Integer managerId = 7;
        CgjCarzoneCfg cgjCarzoneCfg = cgjCarzoneCfgSaveDTO.getCgjCarzoneCfg();
        CommImg commImg = cgjCarzoneCfgSaveDTO.getCommImg();
        Date nowDate = new Date();
        if (cgjCarzoneCfg.getId()==null) {
            cgjCarzoneCfg.setCreateBy(managerId);
            cgjCarzoneCfg.setCreateTime(nowDate);
            commImg.setCreateBy(managerId);
            commImg.setCreateTime(nowDate);
        }
        cgjCarzoneCfg.setUpdateBy(managerId);
        cgjCarzoneCfg.setUpdateTime(nowDate);
        cgjCarzoneCfg.setShow(1);//默认展示
        cgjCarzoneCfg.setStatus(1);//默认可用
        commImg.setUpdateBy(managerId);
        commImg.setUpdateTime(nowDate);
        commImg.setStatus(1);//默认可用
        commImg.setImgType(3);//车圈配置

        return cgjCarzoneCfgFeignService.saveOrUpdateCarzoneCfg(cgjCarzoneCfgSaveDTO);
    }

    @Override
    public String deleteCarzoneCfg(Integer id) {
        Integer managerId = WebShiroTokenManager.getUser().getId();
//        Integer managerId = 8;
        CgjCarzoneCfg cgjCarzoneCfg = new CgjCarzoneCfg();
        cgjCarzoneCfg.setId(id);
        cgjCarzoneCfg.setUpdateTime(new Date());
        cgjCarzoneCfg.setUpdateBy(managerId);
        return cgjCarzoneCfgFeignService.deleteCarzoneCfg(cgjCarzoneCfg);
    }

    @Override
    public String updateShowStatus(CgjCarzoneCfg cgjCarzoneCfg) {
        Integer managerId = WebShiroTokenManager.getUser().getId();
//        Integer managerId = 8;
        cgjCarzoneCfg.setUpdateTime(new Date());
        cgjCarzoneCfg.setUpdateBy(managerId);
        return cgjCarzoneCfgFeignService.updateShowStatus(cgjCarzoneCfg);
    }

}
