package com.ydc.service.ydhc.user.service;

import com.ydc.model.ydhc.YdhcDd;

public interface YdhcDdService {
    int deleteByPrimaryKey(Integer id);

    int insert(YdhcDd record);

    int insertSelective(YdhcDd record);

    YdhcDd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdhcDd record);

    int updateByPrimaryKey(YdhcDd record);

    /**
     * 查询钉钉有效配置
     * @return
     */
    YdhcDd getDdConfig();
}
