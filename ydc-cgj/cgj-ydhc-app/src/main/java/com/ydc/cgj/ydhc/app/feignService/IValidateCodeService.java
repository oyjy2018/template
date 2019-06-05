package com.ydc.cgj.ydhc.app.feignService;

import com.ydc.commom.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-ydhc-user")
public interface IValidateCodeService {

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping(value = "/ydhcValidateCode/sendValidateCode")
    Result sendValidateCode(@RequestParam("mobilePhone") String mobilePhone);

    /**
     * 校验验证码
     *
     * @param mobilePhone
     * @param validateCode
     * @return
     */
    @GetMapping(value = "/ydhcValidateCode/checkValidateCode")
    Result checkValidateCode(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("validateCode") String validateCode);
}
