package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.UserInsertDTO;
import com.ydc.commom.view.dto.cgj.UserQueryDTO;
import com.ydc.commom.view.dto.cgj.UserUpdateDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2018-11-16 14:01
 **/
@Service
@FeignClient(value = "service-user")
public interface UserFeignService {

    /**
     * 获取用户服务角色
     * @param userId
     * @return
     */
    @PostMapping(value = "/user/getServiceUserRoleByUserId")
    ServiceUserRole getServiceUserRoleByUserId(@RequestParam("userId") Integer userId);

    /**
     * 更新用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/user/updateServiceUserRole")
    int updateServiceUserRole(@RequestBody ServiceUserRole record);

    /**
     * 更新或新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/user/updateOrInsert")
    int updateOrInsert(@RequestBody ServiceUserRole record);


    /**
     * 新增用户服务角色
     * @param record
     * @return
     */
    @PostMapping(value = "/user/insertServiceUserRole")
    int insertServiceUserRole(@RequestBody ServiceUserRole record);

    /**
     * 角色id查询功能code
     * @param roleId
     * @return
     */
    @PostMapping(value = "/user/getFunInfoById")
    Set<String> getFunInfoById(@RequestParam("roleId") Integer roleId);

    /**
     * 获取钉钉配置
     *
     * @return
     */
    @PostMapping(value = "/user/getDdConfigByServiceIdentifying")
    Dd getDdConfigByServiceIdentifying(String serviceIdentifying);

    /**
     * 保存登录日志
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/user/saveLogLogin")
    String saveLogLogin(LogLogin record);

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
     * 身份证查询会员信息
     * @param idCard
     * @return
     */
    @PostMapping(value = "/member/getMemberByIdCard")
    Member getMemberByIdCard(@RequestParam("idCard") String idCard);

    /**
     * 返回所有有效用户
     * @return
     */
    @PostMapping(value = "/user/getValidUser")
    String getValidUser();

    /**
     * 会员身份认证
     * @param member
     * @return
     */
    @PostMapping(value = "/member/certificateIdentity")
    Result certificateIdentity(@RequestBody Member member);


    /**
     * 租车会员身份认证
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/member/rentalOrderMember")
    Result rentalOrderMember(@RequestBody MemberApplicationVO memberApplicationVO);


    /**
     * 新增租车订，身份证认证通过，更新实名认证状态
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/member/updateMemberWhetherRealNameStatus")
    Result updateMemberWhetherRealNameStatus(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 查询人员列表
     * @param userQueryDTO
     * @return
     */
    @PostMapping(value = "/user/getUserList")
    String getUserList(@RequestBody UserQueryDTO userQueryDTO);

    /**
     * 查询人员详情
     * @param id
     * @return
     */
    @PostMapping(value = "/user/getUserInfo")
    String getUserInfo(@RequestParam("id") Integer id);

    /**
     * 修改人员
     * @param userUpdateDTO
     * @return
     */
    @PostMapping(value = "/user/modifyUser")
    String updateUser(@RequestBody UserUpdateDTO userUpdateDTO);

    /**
     * 增加人员
     * @param userInsertDTO
     * @return
     */
    @PostMapping(value = "/user/insertUser")
    String insertUser(@RequestBody UserInsertDTO userInsertDTO);

    /**
     * 获取机构权限树及已选机构
     * @param userId
     * @return
     */
    @PostMapping(value = "/user/getViewOrgTreeAndChecked")
    String getViewOrgTreeAndChecked(@RequestParam("userId") Integer userId);

    /**
     * 通过手机号查询会员姓名和身份证号
     * @param mobilePhone  手机号
     * @return
     */
    @PostMapping(value = "/member/getMemberNameAndIdCardByMobilePhone")
    String getMemberNameAndIdCardByMobilePhone(@RequestParam("mobilePhone")String mobilePhone);
}
