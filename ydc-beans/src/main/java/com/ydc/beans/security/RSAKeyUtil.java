package com.ydc.beans.security;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.security.rsa.RSAUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.util.StringUtil;

/**
 * 返回RAS加密key
 *
 * @author
 * @create 2018-12-12 14:38
 **/
public class RSAKeyUtil {


    /**
     * 返回加密公钥
     * @return
     */
    public static String getPublicKey(){
         /*  Object privateKey = RedisUtil.redisGet(RedisConstant.RAS_PRIVATE_KEY);
        Object publicKey= RedisUtil.redisGet(RedisConstant.RAS_PUBLIC_KEY);*/
        String publicKeyString = SystemPropertiesConfig.publicKeyString;
       /* if(StringUtil.isEmpty(privateKey) || StringUtil.isEmpty(publicKey)){
            publicKeyString = org.apache.commons.codec.binary.Base64.encodeBase64String(RSAUtil.genKeyPair(RedisConstant.RAS_PRIVATE_KEY,RedisConstant.RAS_PUBLIC_KEY).getPublic().getEncoded());
        }else{
            publicKeyString = publicKey.toString();
        }*/
        return publicKeyString;
    }
}
