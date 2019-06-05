package com.ydc.service.user.service.impl;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.dao.cgj.common.MenuDao;
import com.ydc.dao.cgj.user.UserRoleFunctionDao;
import com.ydc.service.user.service.RoleFunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

    @Resource
    private MenuDao menuDao;
    @Resource
    private UserRoleFunctionDao userRoleFunctionDao;

    /**
     * 获取功能树
     *
     * @param serviceIdentifying
     * @param functionName
     * @return
     */
    @Override
    public String getFunctionTree(String serviceIdentifying, String functionName) {
        //查出某个服务的所有功能和菜单
        List<Map> functionList = menuDao.getAllMenuAndFunction(serviceIdentifying);
        if (functionList == null || functionList.isEmpty()) {
            return Result.failure("无功能数据").toJSON();
        }
        //functionName为空不用过滤  直接返回全部
        if (StringUtil.isEmpty(functionName)) {
            return Result.success(functionList).toJSON();
        }

        //记录直接父级的key
        Set<String> parentKeySet = new HashSet<>();
        //通过functionName和type过滤
        List<Map> resultList = functionList.stream().filter(map -> {
            String name = (String) map.get("name");
            if (name.contains(functionName) && "function".equals(map.get("type"))) {
                parentKeySet.add((String) map.get("parentKey"));
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());

        //无符合的功能直接返回
        if (resultList == null || resultList.isEmpty()) {
            return Result.failure("无符合的功能").toJSON();
        }
        //递归获取功能的上级菜单
        findParentList(functionList, resultList, parentKeySet);
        return Result.success(resultList).toJSON();
    }

    //递归获取父级菜单
    private void findParentList(List<Map> functionList, List<Map> resultList, Set<String> keySet) {
        //记录直接父级的key
        Set<String> parentKeySet = new HashSet<>();
        functionList.stream().forEach(map -> {
            if (keySet.contains(map.get("key"))) {
                keySet.remove(map.get("key")); //移除key  避免重复添加
                resultList.add(map);
                //父级不为空作记录
                if (map.get("parentKey") != null && StringUtil.isNotEmpty(map.get("parentKey"))) {
                    parentKeySet.add((String) map.get("parentKey"));
                }
            }
        });
        //父级set不为空是递归
        if (parentKeySet != null && parentKeySet.size() > 0) {
            findParentList(functionList, resultList, parentKeySet);
        }
    }

}
