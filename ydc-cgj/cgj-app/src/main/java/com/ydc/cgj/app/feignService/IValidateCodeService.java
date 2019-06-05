package com.ydc.cgj.app.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-user")
public interface IValidateCodeService {

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @param validateType
     * @return
     */
    @PostMapping(value = "/validateCode/sendValidateCode")
    Result sendValidateCode(@RequestParam("mobilePhone") String mobilePhone,
                            @RequestParam("validateType") Integer validateType,
                            @RequestParam(value = "application") Integer application);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateType
     * @param validateCode
     * @return
     */
    @GetMapping(value = "/validateCode/checkValidateCode")
    Result checkValidateCode(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("validateType") Integer validateType,
            @RequestParam("validateCode") String validateCode);
}
