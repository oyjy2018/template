package com.ydc.cgj.rentalc.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.Member;
import com.ydc.model.cgj.MemberFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Service
@FeignClient(value = "service-user")
public interface MemberFeignService {

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
     * 通过客户id和类型获取图片信息
     * @param memberId
     * @param types
     * @return
     */
    @GetMapping(value = "/member/getMemberFileByMemberIdAndType")
    List<MemberFile> getMemberFileByMemberIdAndType(@RequestParam(value = "memberId") Integer memberId,
                                                    @RequestParam(value = "types") String types);
}
