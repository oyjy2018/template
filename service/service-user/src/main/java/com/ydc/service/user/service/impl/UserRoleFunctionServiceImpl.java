package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.user.UserRoleFunctionDao;
import com.ydc.service.user.service.UserRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author
 * @create 2018-11-14 17:54
 **/
@Service
public class UserRoleFunctionServiceImpl implements UserRoleFunctionService {

    @Autowired
    UserRoleFunctionDao userRoleFunctionDao;

    @Override
    public Set<String> getFunInfoById(Integer roleId) {
        return userRoleFunctionDao.getFunInfoById(roleId);
    }
}
