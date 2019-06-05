package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.CompanyCustomerService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  企业管理
 */
@RestController
@RequestMapping(value = "/companyCustomer")
public class CompanyCustomerController {

    private static final Logger logger = LogManager.getLogger(CompanyCustomerController.class);

    @Autowired
    private CompanyCustomerService companyCustomerService;

    /**
     * 通过条件获取企业客户列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getCompanyCustomerList")
    public String getCompanyCustomerList(@RequestParam("data") String data) {
        CompanyCustomerDTO companyCustomerDTO = JSONObject.parseObject(data, CompanyCustomerDTO.class);
        return companyCustomerService.getCompanyCustomerList(companyCustomerDTO);
    }

    /**
     * 新增企业客户
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/addCompanyCustomer")
    public String addCompanyCustomer(@RequestParam("data") String data) {
        RentalCompanyCustomer rentalCompanyCustomer = JSONObject.parseObject(data, RentalCompanyCustomer.class);
        return companyCustomerService.addCompanyCustomer(rentalCompanyCustomer);
    }

    /**
     * 编辑企业客户
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateCompanyCustomer")
    public String updateCompanyCustomer(@RequestParam("data") String data) {
        RentalCompanyCustomer rentalCompanyCustomer = JSONObject.parseObject(data, RentalCompanyCustomer.class);
        User user = WebShiroTokenManager.getUser();
        rentalCompanyCustomer.setUpdateBy(user.getId());
        return companyCustomerService.updateCompanyCustomer(rentalCompanyCustomer);
    }

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param data
     * @return
     * @author df
     */
    @PostMapping(value = "/getCompanyCustomerById")
    public String getCompanyCustomerById(@RequestParam("data") String data) {
        Map dataMap = JsonUtil.jsonToMap(data);
        Integer id  = (Integer) dataMap.get("id");
        if (id == null) {
            return Result.failure("企业客户id为空").toJSON();
        }
        return companyCustomerService.getCompanyCustomerById(id);
    }


    /**
     * 新增登陆账号
     * @param data
     * @return
     */
    @PostMapping(value = "/addCompanyCustomerAccount")
    public String addCompanyCustomerAccount(@RequestParam("data") String data) {
        RentalCompanyLoginAccount rentalCompanyLoginAccount = JSONObject.parseObject(data, RentalCompanyLoginAccount.class);
        //验证手机号是否存在
        RentalCompanyCustomerVO rentalCompanyCustomerVO = companyCustomerService.getCompanyCustomerByMobilePhone(rentalCompanyLoginAccount.getPhone());
        if(rentalCompanyCustomerVO != null){
            return Result.failure("手机号码已存在").toJSON();
        }
        User user = WebShiroTokenManager.getUser();
        rentalCompanyLoginAccount.setUpdateBy(user.getId());
        return companyCustomerService.addCompanyCustomerAccount(rentalCompanyLoginAccount);
    }

    /**
     * 删除登陆账号
     * @param data
     * @return
     */
    @PostMapping(value = "/deleteCompanyCustomerAccount")
    public String deleteCompanyCustomerAccount(@RequestParam("data") String data) {
        RentalCompanyLoginAccount rentalCompanyLoginAccount = JSONObject.parseObject(data, RentalCompanyLoginAccount.class);
        User user = WebShiroTokenManager.getUser();
        rentalCompanyLoginAccount.setUpdateBy(user.getId());
        return companyCustomerService.deleteCompanyCustomerAccount(rentalCompanyLoginAccount);
    }

    /**
     * 通过条件获取企业待审核列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getCompanyAuditedList")
    public String getCompanyAuditedList(@RequestParam("data") String data) {
        CompanyCustomerDTO companyCustomerDTO = JSONObject.parseObject(data, CompanyCustomerDTO.class);
        return companyCustomerService.getCompanyAuditedList(companyCustomerDTO);
    }

}
