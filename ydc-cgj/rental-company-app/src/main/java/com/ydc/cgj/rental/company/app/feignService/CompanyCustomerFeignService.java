package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "service-user")
public interface CompanyCustomerFeignService {

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

    /**
     * 通过手机号码查询企业客户信息
     * @param phone
     * @return
     */
    @GetMapping(value = "/companyCustomer/getCompanyCustomerByMobilePhone/{phone}")
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(@PathVariable("phone") String phone);

    /**
     * 新增企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/companyCustomer/addCompanyCustomer")
    String addCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param id
     * @return
     * @author df
     */
    @GetMapping(value = "/companyCustomer/getCompanyCustomerById/{id}")
    String getCompanyCustomerById(@PathVariable("id") int id);

    /**
     * 编辑企业客户
     *
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/companyCustomer/updateCompanyCustomer")
    String updateCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过企业公司名查询对应数量
     * @param registeredCompanyName
     * @return
     */
    @GetMapping(value = "/companyCustomer/getCompanyCustomerByCompanyName/{registeredCompanyName}")
    int getCompanyCustomerByCompanyName(@PathVariable("registeredCompanyName") String registeredCompanyName);

    /**
     * 个人中心
     * @param id
     * @return
     */
    @GetMapping(value = "/companyCustomer/personalCenter/{id}")
    String personalCenter(@PathVariable("id") Integer id);
}
