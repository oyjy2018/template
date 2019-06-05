package com.ydc.quartz.service;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.enums.cgj.ApplyEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

/**
 * @Title
 * @date 2019/1/24
 */
@Service("autoAddApplyNumService")
public class AutoAddApplyNumService {
    private static final Logger logger = LogManager.getLogger(AutoAddApplyNumService.class);
    public void addApplyNum(){
        EnumSet<ApplyEnum> applyEnums = ApplyEnum.applyEnums;
        Integer applyNum = 0;
        int random=0;
        for(ApplyEnum applyEnum : applyEnums){
            random=(int)(Math.random()*5+1);
            applyNum = (Integer) RedisUtil.redisGet(RedisConstant.CGJ_APPLY_SHOW_NUM + applyEnum.getCode());
            if(applyNum == null){
                applyNum = 1000;
            }
            logger.info("【"+applyEnum.getName()+"】当前数量"+applyNum+",加"+random+"后得"+(applyNum + random));
            applyNum = applyNum + random;
            RedisUtil.redisSet(RedisConstant.CGJ_APPLY_SHOW_NUM + applyEnum.getCode(),applyNum,null);
        }
    }
}
