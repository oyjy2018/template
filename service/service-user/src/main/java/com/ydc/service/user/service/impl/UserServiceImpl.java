package com.ydc.service.user.service.impl;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.UserDetailVO;
import com.ydc.commom.view.vo.cgj.UserQueryVO;
import com.ydc.dao.cgj.rental.RentalStoreDao;
import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.service.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author gongjin
 * @create 2018-09-05 14:49
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    private RentalStoreDao rentalStoreDao;

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public Integer updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }

    @Override
    public Integer insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public User getUserByMobilePhoneNoStatus(String mobilePhone) {
        return userDao.getUserByMobilePhoneNoStatus(mobilePhone);
    }

    /**
     * 返回所有有效用户
     *
     * @return
     */
    @Override
    public String getValidUser() {
        return Result.success(userDao.getValidUser()).toJSON();
    }

    /**
     * 查询人员列表
     *
     * @param userQueryDTO
     * @return
     */
    @Override
    public List<UserQueryVO> getUserList(UserQueryDTO userQueryDTO) {
        List<UserQueryVO> userQueryVOList = PaginationUtil.paginationQuery(userQueryDTO, (userQueryDto) -> userDao.getUserList(userQueryDto));
        //获取机构名
        userQueryVOList.forEach(userQueryVO -> {
            if (StringUtil.isNotEmpty(userQueryVO.getViewOrgId())) {
                String viewOrgName = getViewOrgName(userQueryVO.getViewOrgId());
                //logger.info("viewOrgName:{}", viewOrgName);
                userQueryVO.setViewOrgName(viewOrgName);
            }
        });
        return userQueryVOList;
    }

    /**
     * 查询人员详情
     *
     * @param id
     * @return
     */
    @Override
    public UserDetailVO getUserInfo(Integer id) {
        UserDetailVO userDetailVO = userDao.getUserInfo(id);
        String viewOrgId = userDetailVO.getViewOrgId();
        String viewOrgName = "";
        //机构权限id不为空时查询机构名称
        if (StringUtil.isNotEmpty(viewOrgId)) {
            viewOrgName = getViewOrgName(viewOrgId);
        }
        userDetailVO.setViewOrgName(viewOrgName);
        return userDetailVO;
    }

    //通过机构id查询机构名称
    private String getViewOrgName(String viewOrgId) {
        Map param = new HashMap();
        param.put("ids", viewOrgId);
        List<Map<String, Object>> storeList = rentalStoreDao.getRentalStoreByIds(param);
        if (storeList == null || storeList.isEmpty()) {
            return "";
        }
        String viewOrgName = storeList.stream().map(storeMap -> {
            return (String) storeMap.get("storeName");
        }).collect(Collectors.joining(","));
        return viewOrgName;
    }

    /**
     * 修改人员
     *
     * @param userUpdateDTO
     * @return
     */
    @Override
    public String updateUser(UserUpdateDTO userUpdateDTO) {
        //手机号唯一性验证
        String mobilePhone = userUpdateDTO.getMobilePhone();
        User user = userDao.getUserByMobilePhoneNoStatus(mobilePhone);
        if (user != null && user.getId() != userUpdateDTO.getId()) {
            return Result.failure("手机号已被使用").toJSON();
        }
        //登录名唯一性验证
        String loginName = userUpdateDTO.getLoginName();
        logger.info("loginName:{}",loginName);
        if (StringUtil.isNotEmpty(loginName)) {
            user = userDao.getUserByLoginName(loginName);
            if (user != null && user.getId() != userUpdateDTO.getId()) {
                return Result.failure("登录名已被使用").toJSON();
            }
        }
        int effect = userDao.updateUser(userUpdateDTO);
        if (effect < 1) {
            return Result.failure("修改失败").toJSON();
        }
        return Result.success("修改成功").toJSON();
    }

    /**
     * 增加人员
     *
     * @param userInsertDTO
     * @return
     */
    @Override
    public String insertUser(UserInsertDTO userInsertDTO) {
        //手机号唯一性验证
        String mobilePhone = userInsertDTO.getMobilePhone();
        User user = userDao.getUserByMobilePhoneNoStatus(mobilePhone);
        if (user != null) {
            return Result.failure("手机号已被使用").toJSON();
        }
        //登录名唯一性验证
        String loginName = userInsertDTO.getLoginName();
        logger.info("loginName:{}",loginName);
        if (StringUtil.isNotEmpty(loginName)) {
            user = userDao.getUserByLoginName(loginName);
            if (user != null) {
                return Result.failure("登录名已被使用").toJSON();
            }
        }
        int effect = userDao.insertUser(userInsertDTO);
        if (effect < 1) {
            return Result.failure("新增失败").toJSON();
        }
        return Result.success("新增成功").toJSON();
    }

    /**
     * 获取机构权限树及已选机构
     *
     * @param userId
     * @return
     */
    @Override
    public String getViewOrgTreeAndChecked(Integer userId) {
        //查询所有企业和门店
        List<Map<String, Object>> orgList = rentalStoreDao.getOrgList();
        if (orgList == null || orgList.isEmpty()) {
            return Result.failure("未查询到机构数据").toJSON();
        }

        for (int i = 0; i < orgList.size(); i++) {
            Map orgMap = orgList.get(i);
            orgMap.put("checked",false);//修改是否授权为  未授权
            orgMap.put("sort",i+1); //修改排序值
        }
        //无用户直接返回全部
        if (userId == null) {
            return Result.success(orgList).toJSON();
        }
        //通过userId查询用户
        UserDetailVO userDetailVO = userDao.getUserInfo(userId);
        if (userDetailVO == null) {
            return Result.success(orgList).toJSON();
        }
        String viewOrgId = userDetailVO.getViewOrgId();
        //用户无权限机构直接返回
        if (StringUtil.isEmpty(viewOrgId)) {
            return Result.success(orgList).toJSON();
        }
        Map<String, Boolean> parentCheckedMap = new HashMap();
        //判断门店是否勾选  并纪录门店所属公司的所有门店是否全部勾选
        for (int i = 0; i < orgList.size(); i++) {
            Map orgMap = orgList.get(i);
            String parentId = orgMap.get("parentId").toString();
            if (!"0".equals(parentId)) { //不是公司
                String id = orgMap.get("id").toString();
                Boolean oldParentChecked = parentCheckedMap.get(parentId);
                if (("," + viewOrgId + ",").contains("," + id + ",")) {
                    orgMap.put("checked", true);
                    if (oldParentChecked == null) {
                        parentCheckedMap.put(parentId, true);
                    }
                } else {
                    orgMap.put("checked", false);
                    parentCheckedMap.put(parentId, false);
                }
            }
        }

        //修改公司的勾选
        for (Map orgMap : orgList) {
            String parentId = orgMap.get("parentId").toString();
            if ("0".equals(parentId)) { //是公司
                String id = orgMap.get("id").toString();
                Boolean checked = parentCheckedMap.get(id);
                orgMap.put("checked",checked == null?false:checked);
            }
        }

        return Result.success(orgList).toJSON();
    }
}
