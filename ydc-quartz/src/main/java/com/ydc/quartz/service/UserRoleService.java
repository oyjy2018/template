package com.ydc.quartz.service;

import com.ydc.dao.cgj.user.UserRoleFunctionDao;
import com.ydc.model.cgj.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户角色权限缓存
 *
 * @author
 * @create 2018-12-03 18:36
 **/
@Service
public class UserRoleService {


    @Autowired
    UserRoleFunctionDao userRoleFunctionDao;

    public List<Map<String,Object>> getAllFunctions(){
        return userRoleFunctionDao.getAllFunctions();
    }

    public List<Map<String,Object>> getAllRoles(){
        return userRoleFunctionDao.getAllRoles();
    }

    public  List<Map<String,Object>> getAllRoleFun(){
        return userRoleFunctionDao.getAllRoleFun();
    }

    public Double getVersion(){
        return userRoleFunctionDao.getVersion();
    }

    public List<Map<String,Object>> getAllMenus(){
        return userRoleFunctionDao.getAllMenus();
    }
    public List<Menus> getAllFunMenusList(List<String> list){
        return userRoleFunctionDao.getAllFunMenusList(list);
    }

    public List<Map<String,Object>> getRoleFunMenusList(){
        return userRoleFunctionDao.getRoleFunMenusList();
    }
}
