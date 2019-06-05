package com.ydc.service.user.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DdConstant;
import com.ydc.dao.cgj.user.DdDao;
import com.ydc.model.cgj.Dd;
import com.ydc.service.user.service.DdService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author gongjin
 * @create 2018-09-04 14:52
 **/
@Service
public class DdServiceImpl implements DdService {
    @Autowired
    DdDao ddDao;

    @Override
    public Dd getDdConfigByServiceIdentifying(String serviceIdentifying) {
        Dd dd = ddDao.getDdConfigByServiceIdentifying(serviceIdentifying);
        List<Dd> ddList = Lists.newArrayList();
        ddList.add(dd);
        RedisUtil.redisSet(DdConstant.DINGTALK_PARAM+serviceIdentifying, ddList, null);
        return dd;
    }
}
