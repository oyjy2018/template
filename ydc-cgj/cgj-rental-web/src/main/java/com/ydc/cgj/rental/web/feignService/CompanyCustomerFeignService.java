package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-user")
public interface CompanyCustomerFeignService {

    /**
     * 通过条件获取企业客户列表
     *
     * @param companyCustomerDTO
     * @return
     */
    @PostMapping(value = "/companyCustomer/getCompanyCustomerList")
    String getCompanyCustomerList(@RequestBody CompanyCustomerDTO companyCustomerDTO);

    /**
     * 新增企业客户
     *
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/companyCustomer/addCompanyCustomer")
    public String addCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 编辑企业客户
     *
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/companyCustomer/updateCompanyCustomer")
    public String updateCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param id
     * @return
     * @author df
     */
    @GetMapping(value = "/companyCustomer/getCompanyCustomerById/{id}")
    public String getCompanyCustomerById(@PathVariable("id") int id);

    /**
     * 新增登陆账号
     *
     * @param rentalCompanyLoginAccount
     * @return
     */
    @PostMapping(value = "/companyCustomer/addCompanyCustomerAccount")
    public String addCompanyCustomerAccount(@RequestBody RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 删除登陆账号
     *
     * @param rentalCompanyLoginAccount
     * @return
     */
    @PostMapping(value = "/companyCustomer/deleteCompanyCustomerAccount")
    public String deleteCompanyCustomerAccount(@RequestBody RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 通过条件获取企业待审核列表
     *
     * @param companyCustomerDTO
     * @return
     */
    @PostMapping(value = "/companyCustomer/getCompanyAuditedList")
    public String getCompanyAuditedList(@RequestBody CompanyCustomerDTO companyCustomerDTO);

    /**
     * 通过手机号码查询企业客户信息
     * @param phone
     * @return
     */
    @GetMapping(value = "/companyCustomer/getCompanyCustomerByMobilePhone/{phone}")
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(@PathVariable("phone") String phone);
}
