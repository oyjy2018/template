package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-user")
public interface IMemberService {

    /**
     * 用户登录
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/member/memberLogin")
    Member memberLogin(@RequestParam("mobilePhone") String mobilePhone);

    /**
     * 用户注册
     *
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/member/memberRegister", consumes = "application/json")
    Result memberRegister(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 用户修改密码
     *
     * @param memberId
     * @param mobilePhone
     * @param password
     * @return
     */
    @PostMapping(value = "/member/memberUpdatePassword")
    Result memberUpdatePassword(@RequestParam("memberId") Integer memberId,
                                @RequestParam("mobilePhone") String mobilePhone,
                                @RequestParam("password") String password);

    /**
     * 检验密码
     *
     * @param memberId
     * @param password
     * @return
     */
    @PostMapping(value = "/member/checkMemberPassword")
    Result checkMemberPassword(@RequestParam("memberId") Integer memberId, @RequestParam("password") String password);

    /**
     * 检验用户是否注册
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping("/member/checkMobileIsRegister")
    Result checkMobileIsRegister(@RequestParam("mobilePhone") String mobilePhone,
                                 @RequestParam("application") Integer application);

    /**
     * 根据id获取用户
     * @param memberId
     * @return
     */
    @PostMapping(value = "/member/getMemberById")
    Member getMemberById(@RequestParam("memberId") Integer memberId);


    @PostMapping(value = "/member/updateWeixinInfoByMobilePhone")
    Integer updateWeixinInfoByMobilePhone(@RequestBody Member member);

    /**
     * 根据微信的openId获取用户
     * @param openId
     * @return
     */
    @GetMapping("/member/getMemberByOpenId")
    Member getMemberByOpenId(@RequestParam("openId") String openId);

}
