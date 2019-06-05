package com.ydc.service.user.service;

import com.ydc.model.cgj.Dd;
import org.apache.ibatis.annotations.Param;



/**
 * @author gongjin
 * @create 2018-09-04 14:52
 **/
public interface DdService {

    /**
     * 根据服务标识查询钉钉配置
     * @param serviceIdentifying
     * @return
     */
    Dd getDdConfigByServiceIdentifying(@Param("serviceIdentifying") String serviceIdentifying);
}
