package com.ydc.cgj.app.feignService;

import com.ydc.model.cgj.MemberApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-user")
public interface IMemberApplicationService {

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
}
