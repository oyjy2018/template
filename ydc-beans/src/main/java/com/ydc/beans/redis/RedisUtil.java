package com.ydc.beans.redis;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 *
 * @author gongjin
 * @create 2018-09-04 13:01
 **/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    private static Logger logger = LogManager.getLogger(RedisUtil.class);

    private static RedisTemplate template;
    private static HashOperations<String, String, Object> hashOperations;
    private static SetOperations<String, Object> setOperations;

    @PostConstruct
    public void init() {
        template = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
        setOperations = redisTemplate.opsForSet();
    }


    /**
     * set缓存
     *
     * @param key
     * @param value
     * @param l
     * @return
     */
    public static boolean redisSet(String key, Object value, Long l) {
        try {
            if (l == null || l == 0) {
                template.opsForValue().set(key, value);
            } else {
                template.opsForValue().set(key, value, l, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("subject:{},e:{}","set异常",e);
            return false;
        }
    }

    /**
     * @param key
     * @return
     */
    public static Object redisGet(String key) {
        if (template.hasKey(key)) {
            return template.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 获取key是否存在
     *
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        return template.hasKey(key);
    }

    /**
     * 刷新缓存
     *
     * @param key
     * @param time
     */
    public static void setExpireTime(String key, Long time) {
        if (exists(key)) {
            template.opsForValue().set(key, redisGet(key), time, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除key
     *
     * @param key
     */
    public static void remove(String key) {
        if (exists(key)) {
            template.delete(key);
        }
    }

    /**
     * hash get操作
     * @param key
     * @param hashKey
     * @return
     */
    public static Object redisHashGet(String key, String hashKey){
        if(hashOperations.hasKey(key, hashKey)){
            return hashOperations.get(key, hashKey);
        }
        return null;
    }

    /**
     * hash get All操作
     * @param key
     * @return
     */
    public static Object redisHashGet(String key){
        return hashOperations.entries(key);
    }

    /**
     * hash set操作
     * @param key
     * @param hashKey
     * @param value
     */
    public static void redisHashPut(String key, String hashKey, Object value){
        hashOperations.put(key, hashKey, value);
    }

    /**
     * hash setAll操作
     * @param key
     * @param map
     */
    public static void redisHashPut(String key, Map<String, Object> map){
        hashOperations.putAll(key, map);
    }

    /**
     * hash del操作
     * @param key
     * @param hashKey
     * @return
     */
    public static void redisHashDel(String key, String hashKey){
        hashOperations.delete(key, hashKey);
    }

    /**
     * 返回redis模板
     * @return
     */
    public static RedisTemplate getRedisTemplate(){
        return template;
    }

    /**
     * 获取token的有效期---秒
     * @param key
     * @return
     */
    public static Long getExpireTimeType(String key){
        Long time = template.getExpire(key,TimeUnit.SECONDS);
        return time;
    }

    /**
     * 自增长
     * @param key
     * @param expireTime
     * @return
     */
    public static long generate(String key, Date expireTime){
        long count = template.opsForValue().increment(key, 1L);
        if(count == 1L && expireTime != null){
            template.expireAt(key, expireTime);
        }
        return count;
    }

    /**
     * set get操作
     * @param key
     * @return
     */
    public static Set<Object> redisSetGet(String key){
        return setOperations.members(key);
    }

    /**
     * set set操作
     * @param key
     * @param value
     */
    public static void redisSetSet(String key, Object... value){
        setOperations.add(key, value);
    }

    /**
     * set del操作
     * @param key
     * @param values
     */
    public static void redisSetDel(String key, Object... values){
        setOperations.remove(key, values);
    }

    /**
     * 模糊匹配所有的key
     * @param keyPattern
     * @return
     */
    public static Set<Object> getKeys(Object keyPattern){
        return template.keys(keyPattern);
    }
}
