package com.ydc.cgj.ydhc.web.feignService;

import com.ydc.model.cgj.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户service
 *
 * @author gongjin
 * @create 2018-09-04 19:53
 **/
@Service
@FeignClient(value = "service-user")
public interface UserFeignService {

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/user/updateUser")
    String updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param loginName
     * @return
     */
    @PostMapping(value = "/user/getUserByLoginName")
    User getUserByLoginName(@RequestParam(value = "loginName") String loginName);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/user/insert")
    String insert(User user);

    /**
     * 获取用户信息根据手机号码
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/user/getUserByMobilePhoneNoStatus")
    User getUserByMobilePhoneNoStatus(@RequestParam(value = "mobilePhone") String mobilePhone);

    /**
     * 获取数据字典子集
     *
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getConfigInfoByParentDictCode")
    List<DictionaryDetail> getConfigInfoByParentDictCode(@RequestParam(value = "parentDictCode") String parentDictCode);

    /**
     * parentDictCode和dictKey
     *
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getDictionaryDetailByDictKey")
    DictionaryDetail getDictionaryDetailByDictKey(@RequestParam(value = "dictKey") String dictKey, @RequestParam(value = "parentDictCode") String parentDictCode);


    /**
     * parentDictCode和dictValue
     *
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getDictionaryDetailByDictValue")
    DictionaryDetail getDictionaryDetailByDictValue(@RequestParam(value = "dictValue") String dictValue, @RequestParam(value = "parentDictCode") String parentDictCode);

    /**
     * 保存登录日志
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/user/saveLogLogin")
    String saveLogLogin(LogLogin record);


    /**
     * 获取钉钉配置
     *
     * @return
     */
    @PostMapping(value = "/user/getDdConfigByServiceIdentifying")
    Dd getDdConfigByServiceIdentifying(@RequestParam(value = "serviceIdentifying") String serviceIdentifying);


    /**
     * 获取用户服务角色
     * @param userId
     * @return
     */
    @PostMapping(value = "/user/getServiceUserRoleByUserId")
    ServiceUserRole getServiceUserRoleByUserId(@RequestParam("userId") Integer userId);

    /**
     * 新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/user/insertServiceUserRole")
    int insertServiceUserRole(@RequestBody ServiceUserRole record);


}
