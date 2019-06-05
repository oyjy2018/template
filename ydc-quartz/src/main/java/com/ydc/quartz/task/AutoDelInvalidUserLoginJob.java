package com.ydc.quartz.task;

import com.ydc.beans.redis.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.*;

/**
 * 删除redis中无效的用户登录关系
 *
 * 每个月一号 3点执行
 */
public class AutoDelInvalidUserLoginJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-删除无效文件------------------");
        try {
            //redis存储的前缀
            Map<String, String> preMap = new HashMap<>();
            preMap.put("CGJ:APP_SHIRO:USER:", "CGJ:APP_SHIRO:SESSION:");
            preMap.put("RENTAL:APPC_SHIRO:USER:", "RENTAL:APPC_SHIRO:SESSION:");

            //删除无效的登录关系
            for (Map.Entry<String, String> entry : preMap.entrySet()){
                delInvalidUserLogin(entry);
            }
        } catch (Exception e) {
            logger.error("删除无效文件异常",e);
        }
        logger.info("------------------任务结束执行-删除无效文件------------------");
    }

    //删除无效的登录关系数据
    private void delInvalidUserLogin(Map.Entry<String, String> entry){
        //模糊匹配到所有的key
        Set<Object> loginUserKeySet = RedisUtil.getKeys(entry.getKey() + "*");
        //获取无效的数据
        Map<String, List<Object>> invalidUserLoginMap = getInvalidUserLogin(loginUserKeySet, entry.getValue());
        if (invalidUserLoginMap != null && invalidUserLoginMap.size() > 0){
            invalidUserLoginMap.entrySet().parallelStream().forEach(invalidUserLoginEntry ->
                    RedisUtil.redisSetDel(invalidUserLoginEntry.getKey(), Collections.singletonList(invalidUserLoginEntry.getValue())));
        }
    }

    //获取无效的数据
    private Map<String, List<Object>> getInvalidUserLogin(Set<Object> loginUserKeySet, String loginSessionKey){
        if (loginUserKeySet == null || loginUserKeySet.size() <= 0){
            return null;
        }
        Map<String, List<Object>> invalidUserLoginMap = new HashMap<>();
        //遍历key来获取value set
        loginUserKeySet.parallelStream().forEach(loginUserKey ->{
            final String loginUserKeyStr = (String) loginUserKey;
            Set<Object> loginUserSet = RedisUtil.redisSetGet(loginUserKeyStr);
            //遍历value来校验是否是无效的数据
            loginUserSet.parallelStream().forEach(loginUser -> {
                //如果是无效的数据，则添加到无效数据的map中准备删除
                if (RedisUtil.redisGet(loginSessionKey + loginUser) == null){
                    List<Object> invalidUserLogin = invalidUserLoginMap.get(loginUserKeyStr);
                    if (invalidUserLogin == null){
                        invalidUserLogin = new ArrayList<>();
                        invalidUserLogin.add(loginUser);
                        invalidUserLoginMap.put(loginUserKeyStr, invalidUserLogin);
                    }else {
                        invalidUserLogin.add(loginUser);
                    }
                }
            });
        });
        return invalidUserLoginMap;
    }
}
