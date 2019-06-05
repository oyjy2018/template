package com.ydc.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;
import com.ydc.service.user.service.CompanyCustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业客户表
 *
 * @author df
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
     * @param companyCustomerDTO
     * @return
     */
    @PostMapping(value = "/getCompanyCustomerList")
    public String getCompanyCustomerList(@RequestBody CompanyCustomerDTO companyCustomerDTO) {
        Result result = Result.success();
        List<RentalCompanyCustomerVO> list = companyCustomerService.getCompanyCustomerList(companyCustomerDTO.changeEndTime(" 23:59:59"));
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", list == null ? 0 : new PageInfo<>(list).getTotal());
        result.setData(data);
        return result.toJSON();
    }

    /**
     * 新增企业客户
     *
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/addCompanyCustomer")
    public String addCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer) {
        try {
            if (companyCustomerService.addCompanyCustomer(rentalCompanyCustomer) > 0) {
                return Result.success("成功").toJSON();
            } else {
                return Result.failure("新增企业客户失败").toJSON();
            }
        } catch (Exception e) {
            logger.error("新增企业客户异常", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 编辑企业客户
     *
     * @param rentalCompanyCustomer
     * @return
     */
    @PostMapping(value = "/updateCompanyCustomer")
    public String updateCompanyCustomer(@RequestBody RentalCompanyCustomer rentalCompanyCustomer) {
        try {
            if (companyCustomerService.updateCompanyCustomer(rentalCompanyCustomer) > 0) {
                return Result.success("成功").toJSON();
            } else {
                return Result.failure("编辑企业客户失败").toJSON();
            }
        } catch (Exception e) {
            logger.error("编辑企业客户异常", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 通过企业客户id查询企业客户详情
     *
     * @param id
     * @return
     * @author df
     */
    @GetMapping(value = "/getCompanyCustomerById/{id}")
    public String getCompanyCustomerById(@PathVariable("id") int id) throws Exception {
        Result result = Result.success();
        result.setData(companyCustomerService.getCompanyCustomerById(id));
        return result.toJSON();
    }


    /**
     * 新增登陆账号
     *
     * @param rentalCompanyLoginAccount
     * @return
     */
    @PostMapping(value = "/addCompanyCustomerAccount")
    public String addCompanyCustomerAccount(@RequestBody RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        try {
            if (companyCustomerService.addCompanyCustomerAccount(rentalCompanyLoginAccount) > 0) {
                return Result.success("成功").toJSON();
            } else {
                return Result.failure("新增登陆账号失败").toJSON();
            }
        } catch (Exception e) {
            logger.error("新增登陆账号异常", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 删除登陆账号
     *
     * @param rentalCompanyLoginAccount
     * @return
     */
    @PostMapping(value = "/deleteCompanyCustomerAccount")
    public String deleteCompanyCustomerAccount(@RequestBody RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        try {
            if (companyCustomerService.deleteCompanyCustomerAccount(rentalCompanyLoginAccount) > 0) {
                return Result.success("成功").toJSON();
            } else {
                return Result.failure("删除登陆账号失败").toJSON();
            }
        } catch (Exception e) {
            logger.error("删除登陆账号异常", e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 通过条件获取企业待审核列表
     *
     * @param companyCustomerDTO
     * @return
     */
    @PostMapping(value = "/getCompanyAuditedList")
    public String getCompanyAuditedList(@RequestBody CompanyCustomerDTO companyCustomerDTO) {
        Result result = Result.success();
        List<RentalCompanyCustomerVO> list = companyCustomerService.getCompanyAuditedList(companyCustomerDTO.changeEndTime(" 23:59:59"));
        Map<String, Object> data = new HashMap<>();
        data.put("rows", list);
        data.put("totalCount", list == null ? 0 : new PageInfo<>(list).getTotal());
        result.setData(data);
        return result.toJSON();
    }

    /**
     * 通过企业客户手机号码查询企业客户详情
     * @param phone
     * @return
     * @author df
     */
    @GetMapping(value = "/getCompanyCustomerByMobilePhone/{phone}")
    public RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(@PathVariable("phone") String phone) {
        return companyCustomerService.getCompanyCustomerByMobilePhone(phone);
    }

    /**
     * 通过企业公司名查询对应数量
     * @param registeredCompanyName
     * @return
     * @author df
     */
    @GetMapping(value = "/getCompanyCustomerByCompanyName/{registeredCompanyName}")
    public int getCompanyCustomerByCompanyName(@PathVariable("registeredCompanyName") String registeredCompanyName) {
        return companyCustomerService.getCompanyCustomerByCompanyName(registeredCompanyName);
    }

    /**
     * 个人中心
     *
     * @param id
     * @return
     * @author df
     */
    @GetMapping(value = "/personalCenter/{id}")
    public String personalCenter(@PathVariable("id") int id) {
        Result result = Result.success();
        result.setData(companyCustomerService.personalCenter(id));
        return result.toJSON();
    }
}
