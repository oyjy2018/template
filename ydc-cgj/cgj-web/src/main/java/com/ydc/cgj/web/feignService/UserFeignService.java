package com.ydc.cgj.web.feignService;

import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import com.ydc.commom.view.dto.cgj.MemberDTO;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.*;
import com.ydc.model.cgj.entity.IntegralEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Set;

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
     * 积分列表
     *
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/webintegral/getIntegralList")
    String getIntegralList(@RequestBody IntegralManageDTO integralManageDTO);

    /**
     * 积分充值
     *
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/webintegral/saveOrUpdateIntegral")
    String saveOrUpdateIntegral(@RequestBody IntegralManageDTO integralManageDTO);

    /**
     * 积分明细列表
     *
     * @param integralManageDTO
     * @return
     */
    @PostMapping(value = "/webintegral/getIntegralDetailList")
    String getIntegralDetailList(@RequestBody IntegralManageDTO integralManageDTO);

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
     * 积分批量充值
     *
     * @param list
     * @return
     */
    @PostMapping(value = "/webintegral/batchRecharge")
    String batchRecharge(@RequestBody List<IntegralEntity> list);

    /**
     * 获取钉钉配置
     *
     * @return
     */
    @PostMapping(value = "/user/getDdConfigByServiceIdentifying")
    Dd getDdConfigByServiceIdentifying(@RequestParam(value = "serviceIdentifying") String serviceIdentifying);

    /**
     * 保存登录日志
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/user/saveLogLogin")
    String saveLogLogin(LogLogin record);


    /**
     * 查询会员列表
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/member/getMemberList")
    String getMemberList(@RequestBody MemberDTO memberDTO);

    /**
     * 后台-新增会员
     *
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/member/insertMember")
    String insertMember(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 更新会员状态
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "/member/updateMemberStatus")
    String updateMemberStatus(@RequestBody MemberDTO memberDTO);

    /**
     * 放款派卷
     *
     * @param lendingCustomerDTO
     * @return
     */
    @PostMapping(value = "/member/getLendingCustomerList")
    String getLendingCustomerList(@RequestBody LendingCustomerDTO lendingCustomerDTO);


    /**
     * 角色id查询功能code
     * @param roleId
     * @return
     */
    @PostMapping(value = "/user/getFunInfoById")
    Set<String> getFunInfoById(@RequestParam("roleId") Integer roleId);

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
     * 新增服务类型
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/insertDictionaryDatail")

    String insertDictionaryDatail(@RequestBody DictionaryDetailDTO dictionaryDetail);

    /**
     * 修改服务类型
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/updateDictionaryDatail")
    String updateDictionaryDatail(@RequestBody DictionaryDetailDTO dictionaryDetail);

    /**
     * 得到服务
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/getDictionaryDatail")
    String getDictionaryDatail(DictionaryDetail dictionaryDetail);

    /**
     * 删除服务
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/dictionaryDetail/delDictionaryDatail")
    String delDictionaryDatail(DictionaryDetail dictionaryDetail);
}
