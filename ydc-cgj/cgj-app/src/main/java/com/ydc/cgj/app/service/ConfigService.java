package com.ydc.cgj.app.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.sys.AdvertConfigQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjAppVersionQueDTO;

import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2019/1/3
 */
public interface ConfigService {
    Result queryHomeModules();
    Result queryAdverts(AdvertConfigQueDTO advertConfigQueDTO);
    Result queryButtonConfigs(AdvertConfigQueDTO advertConfigQueDTO);
    Result getNewestVersion(CgjAppVersionQueDTO cgjAppVersionQueDTO);
}
