package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.CompanyCustomerFeignService;
import com.ydc.cgj.rental.web.service.CompanyCustomerService;
import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyCustomerService")
public class CompanyCustomerServiceImpl implements CompanyCustomerService {

    @Autowired
    private CompanyCustomerFeignService companyCustomerFeignService;

    @Override
    public String getCompanyCustomerList(CompanyCustomerDTO companyCustomerDTO) {
        return companyCustomerFeignService.getCompanyCustomerList(companyCustomerDTO);
    }

    @Override
    public String getCompanyCustomerById(int id) {
        return companyCustomerFeignService.getCompanyCustomerById(id);
    }

    @Override
    public String addCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        return companyCustomerFeignService.addCompanyCustomerAccount(rentalCompanyLoginAccount);
    }

    @Override
    public String deleteCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        return companyCustomerFeignService.deleteCompanyCustomerAccount(rentalCompanyLoginAccount);
    }

    @Override
    public String getCompanyAuditedList(CompanyCustomerDTO companyCustomerDTO) {
        return companyCustomerFeignService.getCompanyAuditedList(companyCustomerDTO);
    }

    @Override
    public String addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        return companyCustomerFeignService.addCompanyCustomer(rentalCompanyCustomer);
    }

    @Override
    public String updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        return companyCustomerFeignService.updateCompanyCustomer(rentalCompanyCustomer);
    }

    @Override
    public RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String phone) {
        return companyCustomerFeignService.getCompanyCustomerByMobilePhone(phone);
    }
}
