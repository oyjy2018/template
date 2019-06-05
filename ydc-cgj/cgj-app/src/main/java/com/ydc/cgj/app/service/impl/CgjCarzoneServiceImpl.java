package com.ydc.cgj.app.service.impl;

import com.ydc.cgj.app.feignService.CgjCarzoneCfgFeignService;
import com.ydc.cgj.app.service.CgjCarzoneCfgService;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CgjCarzoneServiceImpl implements CgjCarzoneCfgService {

    @Autowired
    private CgjCarzoneCfgFeignService cgjCarzoneCfgFeignService;
    @Override
    public String getCarzoneListByType(CgjCarzoneCfgQueDTO cgjViolationRecordQueDTO) {
        return cgjCarzoneCfgFeignService.getCarzoneListByType(cgjViolationRecordQueDTO);
    }
}
