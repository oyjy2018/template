package com.ydc.beans.shiro.web;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.Function;
import com.ydc.model.cgj.Menus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * shiro权限查询
 *
 * @author
 * @create 2018-12-04 11:03
 **/
public class ShiroAuthUtil {

    private static final Logger logger = LogManager.getLogger(ShiroAuthUtil.class);

    public static final String SHIRO_ROLE_AUTH_KEY = "ROLES:";

    public static final String SHIRO_ROLE_AUTH_VERSION_KEY = "VERSION";

    public static final String SHIRO_ROLE_MENU_AUTH_KEY = "MENUS:";


    /**
     * 存入shiro
     * @param roleId
     * @return
     */
    public static Set<String> stringPermissions(Integer roleId,String serviceIdentifying){
        Set<String> stringPermissions = new HashSet<>();
        if(roleId == null)return stringPermissions;
        try{
            logger.info("subject:{}","shiro取权限缓存",roleId,serviceIdentifying);
            List<Function> functions = (List<Function>)RedisUtil.getRedisTemplate().opsForHash().get(SHIRO_ROLE_AUTH_KEY+serviceIdentifying,String.valueOf(roleId));
            stringPermissions = functions.stream().map(Function::getFunCode).collect(Collectors.toSet());
            logger.info("subject:{},roleId:{},serviceIdentifying:{},stringPermissions:{}","权限存入shiro",roleId,serviceIdentifying,JsonUtil.gsonStr(stringPermissions));
            return stringPermissions;
        }catch (Exception e){
            logger.error("subject:{},roleId:{},serviceIdentifying:{}","角色存入shiro缓存异常",roleId,serviceIdentifying);
            return stringPermissions;
        }
    }

    /**
     * 响应前端
     * @param roleId
     * @param serviceIdentifying
     * @return
     */
    public static Map<String,List<String>> responsePermissions(Integer roleId,String serviceIdentifying){
        Map<String,List<String>> linkedHashMap = new LinkedHashMap<>();
        try{
            List<Function> functions = (List<Function>)RedisUtil.getRedisTemplate().opsForHash().get(SHIRO_ROLE_AUTH_KEY+serviceIdentifying,String.valueOf(roleId));
            functions = functions.stream().filter(item -> item.getFunCode().startsWith(serviceIdentifying)).collect(Collectors.toList());
            List<String> strings;
            for(Function function : functions){
                if(linkedHashMap.containsKey(function.getMenuCode())){
                    strings = linkedHashMap.get(function.getMenuCode());
                    strings.add(function.getFunCode());
                    linkedHashMap.put(function.getMenuCode(),strings);
                }else {
                    strings = new ArrayList<>();
                    strings.add(function.getFunCode());
                    linkedHashMap.put(function.getMenuCode(),strings);
                }
            }
            logger.info("subject:{},roleId:{},serviceIdentifying:{},linkedHashMap:{}","角色权限响应前端",roleId,serviceIdentifying,JsonUtil.gsonStr(linkedHashMap));
            return linkedHashMap;
        }catch (Exception e){
            logger.error("subject:{},roleId:{},serviceIdentifying:{}","角色权限响应前端异常",roleId,serviceIdentifying);
            return linkedHashMap;
        }
    }

    /**
     * 响应前端菜单
     * @param roleId
     * @param serviceIdentifying
     * @return
     */
    public static Map<String,List<String>> responsePermissionsMenus(Integer roleId,String serviceIdentifying){
        Map<String,List<String>> stringListMap = new LinkedHashMap<>();
        try{
            List<Menus> menusList = (List<Menus>)RedisUtil.getRedisTemplate().opsForHash().get(SHIRO_ROLE_MENU_AUTH_KEY+serviceIdentifying,String.valueOf(roleId));
            Map<String,List<Menus>> linkedHashMap = menusList.stream().collect(Collectors.groupingBy(Menus::getParentMenuCode));
            List<String> stringList;
            Menus menus1;
            for (String key : linkedHashMap.keySet()){
                menusList = linkedHashMap.get(key);
                menus1 = menusList.get(0);
                if(stringListMap.containsKey(key)){
                    stringList = stringListMap.get(key);
                    stringList.add(menus1.getFunCode());
                    stringListMap.put(key,stringList);
                }else{
                    stringList = Lists.newArrayList();
                    stringList.add(menus1.getFunCode());
                    stringListMap.put(key,stringList);
                }
            }
            return stringListMap;
        }catch (Exception e){
            logger.error("subject:{},roleId:{},serviceIdentifying:{}","角色响应前端菜单异常",roleId,serviceIdentifying);
            return stringListMap;
        }
    }

    /**
     * 响应前端(租车B端)
     * @param roleId
     * @param serviceIdentifying
     * @return
     */
    public static Map<String,List<String>> responsePermissionsB(Integer roleId,String serviceIdentifying,String param2){
        Map<String,List<String>> linkedHashMap = new LinkedHashMap<>();
        try{
            List<Function> functions = (List<Function>)RedisUtil.getRedisTemplate().opsForHash().get(SHIRO_ROLE_AUTH_KEY+serviceIdentifying,String.valueOf(roleId));
            if(StringUtil.isNotEmpty(param2))
                functions = functions.stream().filter(item ->item.getFunCode().startsWith(serviceIdentifying+param2)).collect(Collectors.toList());
            List<String> stringList;
            for(Function function : functions){
                if(linkedHashMap.containsKey(function.getMenuCode())){
                    stringList = linkedHashMap.get(function.getMenuCode());
                    stringList.add(function.getFunCode());
                    linkedHashMap.put(function.getMenuCode(),stringList);
                }else {
                    stringList= new ArrayList<>();
                    stringList.add(function.getFunCode());
                    linkedHashMap.put(function.getMenuCode(),stringList);
                }
            }
            logger.info("subject:{},roleId:{},serviceIdentifying:{},linkedHashMap:{}","角色权限响应前端(租车B端)",roleId,serviceIdentifying,JsonUtil.gsonStr(linkedHashMap));
            return linkedHashMap;
        }catch (Exception e){
            logger.error("subject:{},roleId:{},serviceIdentifying:{}","角色权限响应前端(租车B端)异常",roleId,serviceIdentifying);
            return linkedHashMap;
        }
    }

    /**
     * 返回角色版本
     * @return
     */
    public static String getRoleVersion(){
        Optional<Object> obj = Optional.ofNullable(RedisUtil.redisGet(SHIRO_ROLE_AUTH_KEY + SHIRO_ROLE_AUTH_VERSION_KEY));
        return obj.orElse(0).toString();
    }
}
