package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;

public interface CompanyCustomerService {
    /**
     * 获取企业客户列表
     * @param companyCustomerDTO
     * @return
     */
    String getCompanyCustomerList(CompanyCustomerDTO companyCustomerDTO);

    /**
     * 通过企业客户id查询企业客户详情
     * @param id
     * @return
     */
    String getCompanyCustomerById(int id);

    /**
     * 新增企业客户账号
     * @param rentalCompanyLoginAccount
     * @return
     */
    String addCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 删除登陆账号
     * @param rentalCompanyLoginAccount
     * @return
     */
    String deleteCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 通过条件获取企业待审核列表
     * @param companyCustomerDTO
     * @return
     */
    String getCompanyAuditedList(CompanyCustomerDTO companyCustomerDTO);

    /**
     * 新增企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    String addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 编辑企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    String updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过手机号查询企业信息
     * @param phone
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String phone);
}
