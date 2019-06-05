package com.ydc.cgj.rentalc.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.MemberApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-user")
public interface MemberApplicationFeignService {

    /**
     * 获取客户应用信息
     * @param mobilePhone
     * @return
     */
    @GetMapping(value = "/memberApplication/getMemberApplicationByPhone")
    MemberApplication getMemberApplicationByMobilePhone(@RequestParam(value = "mobilePhone") String mobilePhone,
                                                        @RequestParam(value = "application") Integer application);

    /**
     * 获取客户应用信息
     * @param loginName
     * @return
     */
    @GetMapping(value = "/memberApplication/getMemberApplicationByLoginName")
    MemberApplication getMemberApplicationByLoginName(@RequestParam(value = "loginName") String loginName,
                                                      @RequestParam(value = "application") Integer application);

    /**
     * 身份证认证校验
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/memberApplication/checkIdentityCertificate")
    Result checkIdentityCertificate(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 驾驶证认证校验
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/memberApplication/checkDriversLicenseCertificate")
    Result checkDriversLicenseCertificate(@RequestBody MemberApplicationVO memberApplicationVO);

    /**
     * 认证客户
     * @param memberApplicationVO
     * @return
     */
    @PostMapping(value = "/memberApplication/certificateMember")
    Result certificateMember(@RequestBody MemberApplicationVO memberApplicationVO,
                             @RequestParam(value = "isIdentity") int isIdentity);
}
