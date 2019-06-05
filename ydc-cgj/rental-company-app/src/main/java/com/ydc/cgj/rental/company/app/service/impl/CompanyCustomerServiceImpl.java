package com.ydc.cgj.rental.company.app.service.impl;

import com.ydc.cgj.rental.company.app.feignService.CompanyCustomerFeignService;
import com.ydc.cgj.rental.company.app.service.CompanyCustomerService;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyCustomerService")
public class CompanyCustomerServiceImpl implements CompanyCustomerService {

    @Autowired
    private CompanyCustomerFeignService companyCustomerFeignService;

    @Override
    public RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String mobilePhone) {
        return companyCustomerFeignService.getCompanyCustomerByMobilePhone(mobilePhone);
    }

    @Override
    public Result sendValidateCode(String mobilePhone, Integer validateType, Integer application) {
        return companyCustomerFeignService.sendValidateCode(mobilePhone, validateType, application);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, Integer validateType, String validateCode) {
        return companyCustomerFeignService.checkValidateCode(mobilePhone, validateType, validateCode);
    }

    @Override
    public String addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        return companyCustomerFeignService.addCompanyCustomer(rentalCompanyCustomer);
    }

    @Override
    public String getCompanyCustomerById(Integer id) {
        return companyCustomerFeignService.getCompanyCustomerById(id);
    }

    @Override
    public String updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        return companyCustomerFeignService.updateCompanyCustomer(rentalCompanyCustomer);
    }

    @Override
    public int getCompanyCustomerByCompanyName(String registeredCompanyName) {
        return companyCustomerFeignService.getCompanyCustomerByCompanyName(registeredCompanyName);
    }

    @Override
    public String personalCenter(Integer id) {
        return companyCustomerFeignService.personalCenter(id);
    }
}
