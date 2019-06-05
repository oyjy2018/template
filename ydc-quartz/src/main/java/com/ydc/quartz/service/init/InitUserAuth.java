package com.ydc.quartz.service.init;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.web.ShiroAuthUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.Function;
import com.ydc.model.cgj.Menus;
import com.ydc.quartz.service.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 初始化用户权限缓存
 *
 * @author
 * @create 2018-12-03 18:38
 **/
@Component
public class InitUserAuth {

    private static final Logger logger = LogManager.getLogger(InitCache.class);

    @Autowired
    UserRoleService userRoleService;

    /**
     * 初始化任务
     * @return
     */
    public String initRun(){
        try{
            this.initAuthCache();
            this.intiMenus();
            return Result.success("初始化角色权限缓存成功").toJSON();
        }catch (Exception e){
            logger.error("初始化角色权限缓存异常",e);
            return Result.failure("初始化角色权限缓存异常").toJSON();
        }
    }

    /**
     * 角色权限缓存
     *
     * {
     *     "RENTAL:ROLE:“
     * }
     *
     *
     *
     *
     *
     *
     *
     */
    private void initAuthCache(){
        List<Map<String,Object>> mapList = userRoleService.getAllRoleFun();
        logger.info("subject:{},size:{}","角色功能集合",(mapList == null || mapList.isEmpty() ? 0 : mapList.size()));
        if(mapList == null || mapList.isEmpty())return;
        mapList.forEach((item) -> item.put("functions",userRoleService.getAllFunctions().stream().filter((fun) -> fun.get("id").toString().equals(item.get("funId").toString())).collect(Collectors.toList())));
       logger.info(JsonUtil.gsonStr(mapList));
        Map<String,List<Function>> linkMap = new LinkedHashMap<>();
        List<Function> functions;
        for(Map<String,Object> map : mapList){
            if(linkMap.containsKey(map.get("roleId").toString())){
                functions = linkMap.get(map.get("roleId").toString());
                functions.add(JsonUtil.jsonToList(JsonUtil.gsonStr(map.get("functions")),Function.class).get(0));
                linkMap.put(map.get("roleId").toString(),functions);
            }else{
                functions = new ArrayList<>();
                functions.add(JsonUtil.jsonToList(JsonUtil.gsonStr(map.get("functions")),Function.class).get(0));
                linkMap.put(map.get("roleId").toString(),functions);
            }
        }
//        for(String key : linkMap.keySet()){
//            RedisUtil.getRedisTemplate().opsForHash().put(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+linkMap.get(key).get(0).getServiceIdentifying(),key,linkMap.get(key));
//        }
        linkMap.keySet().stream().forEach(key ->{
            RedisUtil.getRedisTemplate().opsForHash().put(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+linkMap.get(key).get(0).getServiceIdentifying(),key,linkMap.get(key));
        });

        RedisUtil.redisSet(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+ShiroAuthUtil.SHIRO_ROLE_AUTH_VERSION_KEY,userRoleService.getVersion(),null);




        //返回前端
//        List<Function> functions = (List<Function>)RedisUtil.redisGet(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+"RENTAL:"+1);
//       logger.info("-----:"+JsonUtil.gsonStr(functions));
//        Map<String,List<String>> linkedHashMap = new LinkedHashMap<>();
//        for(Function function : functions){
//            if(linkedHashMap.containsKey(function.getMenuCode())){
//                List<String> functionsl = linkedHashMap.get(function.getMenuCode());
//                functionsl.add(function.getFunCode());
//                linkedHashMap.put(function.getMenuCode(),functionsl);
//            }else {
//                List<String> functionsl = new ArrayList<>();
//                functionsl.add(function.getFunCode());
//                linkedHashMap.put(function.getMenuCode(),functionsl);
//            }
//        }
//       logger.info("返回前端:"+JsonUtil.gsonStr(linkedHashMap));
        //前端文档
//        List<Function> functions = (List<Function>)RedisUtil.redisGet(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+"RENTAL:"+1);
//       logger.info("-----:"+JsonUtil.gsonStr(functions));
//        Map<String,List<String>> linkedHashMap = new LinkedHashMap<>();
//        for(Function function : functions){
//            if(linkedHashMap.containsKey(function.getMenuCode())){
//                List<String> functionsl = linkedHashMap.get(function.getMenuCode());
//                functionsl.add(function.getFunCode()+":{funName:"+function.getFunName()+"}");
//                linkedHashMap.put(function.getMenuCode(),functionsl);
//            }else {
//                List<String> functionsl = new ArrayList<>();
//                functionsl.add(function.getFunCode()+":{funName:"+function.getFunName()+"}");
//                linkedHashMap.put(function.getMenuCode(),functionsl);
//            }
//        }
//       logger.info("前端文档:"+JsonUtil.gsonStr(linkedHashMap));
//
//        //存入shiro
//        List<Function> functions = (List<Function>)RedisUtil.redisGet(ShiroAuthUtil.SHIRO_ROLE_AUTH_KEY+"RENTAL:1");
//        Set<String> roles = new HashSet<>();
//        roles = functions.stream()
//                .map(Function::getFunCode)
//                .collect(Collectors.toSet());
//       logger.info("存入shiro:"+JsonUtil.gsonStr(roles));
    }

    /**
     * 角色菜单
     */
    private void intiMenus(){
        List<Map<String,Object>> mapList = userRoleService.getRoleFunMenusList();
        List<Map<String,Object>> menusList = userRoleService.getAllMenus();
        List<String> stringList = menusList.stream().map(menus -> {
            String menuCode = (String)menus.get("menuCode");
            return menuCode;
        }).collect(Collectors.toList());
        List<Menus> menusFunList = userRoleService.getAllFunMenusList(stringList);
        for(Menus menus : menusFunList){
            for(Map<String,Object> map : menusList){
                if(menus.getMenuCode().equals(map.get("menuCode"))){
                    menus.setParentMenuCode(map.get("parentMenuCode").toString());
                    menus.setMenuName(map.get("menuName").toString());
                    break;
                }
            }
        }
        logger.info("menusFunList:{}",JsonUtil.gsonStr(menusFunList));
       logger.info("----"+JsonUtil.gsonStr(menusFunList));
        mapList.forEach((item) -> item.put("menus",menusFunList.stream().filter((fun) -> fun.getId() == Integer.valueOf(item.get("funId").toString())).collect(Collectors.toList())));
        logger.info("mapList:{}",JsonUtil.gsonStr(mapList));
       logger.info("++++"+JsonUtil.gsonStr(mapList));
        Map<String,List<Menus>> linkMap = new LinkedHashMap<>();
        List<Menus> menus;
        for(Map<String,Object> map : mapList){
            if(linkMap.containsKey(map.get("roleId").toString())){
                menus = linkMap.get(map.get("roleId").toString());
                menus.add(JsonUtil.jsonToList(JsonUtil.gsonStr(map.get("menus")),Menus.class).get(0));
                linkMap.put(map.get("roleId").toString(),menus);
            }else{
                menus = new ArrayList<>();
                menus.add(JsonUtil.jsonToList(JsonUtil.gsonStr( map.get("menus")),Menus.class).get(0));
                linkMap.put(map.get("roleId").toString(),menus);
            }
        }
//        for(String key : linkMap.keySet()){
//            RedisUtil.getRedisTemplate().opsForHash().put(ShiroAuthUtil.SHIRO_ROLE_MENU_AUTH_KEY+linkMap.get(key).get(0).getServiceIdentifying(),key,linkMap.get(key));
//        }
        linkMap.keySet().stream().forEach(item -> {
            RedisUtil.getRedisTemplate().opsForHash().put(ShiroAuthUtil.SHIRO_ROLE_MENU_AUTH_KEY+linkMap.get(item).get(0).getServiceIdentifying(),item,linkMap.get(item));
        });
    }
}



























