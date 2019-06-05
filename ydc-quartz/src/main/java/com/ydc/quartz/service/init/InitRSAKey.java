package com.ydc.quartz.service.init;


import com.ydc.beans.security.rsa.RSAUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import java.security.KeyPair;


/**
 * 初始化RAS秘钥
 *
 * @author
 * @create 2018-11-06 10:36
 **/
@Component(value = "initRSAKey")
public class InitRSAKey {

    private static final Logger logger = LogManager.getLogger(InitRSAKey.class);

    public String init(){
        logger.info("---------------------开始初始化秘钥--------------------");
        try {
            //初始化秘钥
            initSecretKey();
            return Result.success("重置秘钥成功").toJSON();
        }catch (Exception e){
            logger.error("重置秘钥异常",e);
            return Result.failure("重置秘钥异常").toJSON();
        }
    }

    /**
     * 初始化车管家APP秘钥
     */
    public KeyPair initSecretKey(){
        return RSAUtil.genKeyPair(RedisConstant.RAS_PRIVATE_KEY,RedisConstant.RAS_PUBLIC_KEY);
    }
}
