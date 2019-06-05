package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;

import java.util.List;
import java.util.Map;

public interface CompanyCustomerService {

    /**
     * 获取企业客户列表
     * @param companyCustomerDTO
     * @return
     */
    List<RentalCompanyCustomerVO> getCompanyCustomerList(CompanyCustomerDTO companyCustomerDTO);

    /**
     * 通过企业客户id查询企业客户详情
     * @param id
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerById(int id) throws Exception;

    /**
     * 新增企业客户账号
     * @param rentalCompanyLoginAccount
     * @return
     */
    int addCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 删除登陆账号
     * @param rentalCompanyLoginAccount
     * @return
     */
    int deleteCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount);

    /**
     * 通过条件获取企业待审核列表
     * @param companyCustomerDTO
     * @return
     */
    List<RentalCompanyCustomerVO> getCompanyAuditedList(CompanyCustomerDTO companyCustomerDTO);

    /**
     * 新增企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    int addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 编辑企业客户
     * @param rentalCompanyCustomer
     * @return
     */
    int updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer);

    /**
     * 通过手机号码查询企业客户信息
     * @param phone
     * @return
     */
    RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String phone);

    /**
     * 通过企业公司名查询对应数量
     * @param registeredCompanyName
     * @return
     */
    int getCompanyCustomerByCompanyName(String registeredCompanyName);

    /**
     * 个人中心
     * @param id
     * @return
     */
    RentalCompanyCustomerVO personalCenter(int id);
}
