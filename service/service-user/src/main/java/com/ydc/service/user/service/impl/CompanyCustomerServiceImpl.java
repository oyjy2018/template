package com.ydc.service.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.dao.cgj.rental.RentalCompanyCustomerDao;
import com.ydc.dao.cgj.rental.RentalCompanyLoginAccountDao;
import com.ydc.model.cgj.rental.RentalCompanyCustomer;
import com.ydc.model.cgj.rental.RentalCompanyLoginAccount;
import com.ydc.service.user.service.CompanyCustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("companyCustomerService")
public class CompanyCustomerServiceImpl implements CompanyCustomerService {

    @Resource
    private RentalCompanyCustomerDao companyCustomerDao;

    @Resource
    private RentalCompanyLoginAccountDao companyLoginAccountDao;

    @Override
    public List<RentalCompanyCustomerVO> getCompanyCustomerList(CompanyCustomerDTO companyCustomerDTO) {
        return PaginationUtil.paginationQuery(companyCustomerDTO, (tempCompanyCustomerDTO)
                -> {
            return companyCustomerDao.getCompanyCustomerList(tempCompanyCustomerDTO);
        });
    }

    @Override
    public RentalCompanyCustomerVO getCompanyCustomerById(int id) throws Exception {
        RentalCompanyCustomerVO rentalCompanyCustomerVO = companyCustomerDao.getCompanyCustomerById(id);
        String idCardFrontImage = rentalCompanyCustomerVO.getIdCardFrontImage();
        String idCardBackImage = rentalCompanyCustomerVO.getIdCardBackImage();
        String license = rentalCompanyCustomerVO.getLicense();
        if(StringUtil.isNotEmpty(idCardFrontImage)){
            String browseFile = getBrowseFile(idCardFrontImage);
            rentalCompanyCustomerVO.setIdCardFrontImage(browseFile);
        }
        if(StringUtil.isNotEmpty(idCardBackImage)){
            String browseFile = getBrowseFile(idCardBackImage);
            rentalCompanyCustomerVO.setIdCardBackImage(browseFile);
        }
        if(StringUtil.isNotEmpty(license)){
            String browseFile = getBrowseFile(license);
            rentalCompanyCustomerVO.setLicense(browseFile);
        }
        return rentalCompanyCustomerVO;
    }

    private String getBrowseFile(String Image) throws Exception {
        JSONObject jsonObject = JSON.parseObject(Image);
        if(!jsonObject.containsKey("fileName") && !jsonObject.containsKey("fileName")){
            throw new RuntimeException("图片格式不正确,无法解析");
        }
        String fileName = (String) jsonObject.get("fileName");
        String fileUrl = (String) jsonObject.get("uploadPath");
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        return FileUtil.getBrowseFile(fileUrl, fileName + fileType);
    }

    @Override
    public int addCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        rentalCompanyLoginAccount.setDeleteStatus((byte)CodeConstant.NORMAL_STATUS);
        rentalCompanyLoginAccount.setCreateTime(new Date());
        return companyLoginAccountDao.insertSelective(rentalCompanyLoginAccount);
    }

    @Override
    public int deleteCompanyCustomerAccount(RentalCompanyLoginAccount rentalCompanyLoginAccount) {
        rentalCompanyLoginAccount.setDeleteStatus((byte)CodeConstant.DISABLE_STATUS);
        return companyLoginAccountDao.updateByPrimaryKeySelective(rentalCompanyLoginAccount);
    }

    @Override
    public List<RentalCompanyCustomerVO> getCompanyAuditedList(CompanyCustomerDTO companyCustomerDTO) {
        return PaginationUtil.paginationQuery(companyCustomerDTO, (tempCompanyCustomerDTO)
                -> companyCustomerDao.getCompanyAuditedList(tempCompanyCustomerDTO));
    }

    @Override
    public int addCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        rentalCompanyCustomer.setDeleteStatus((byte)CodeConstant.NORMAL_STATUS);
        rentalCompanyCustomer.setCreateTime(new Date());
        return companyCustomerDao.insertSelective(rentalCompanyCustomer);
    }

    @Override
    public int updateCompanyCustomer(RentalCompanyCustomer rentalCompanyCustomer) {
        return companyCustomerDao.updateByPrimaryKeySelective(rentalCompanyCustomer);
    }

    @Override
    public RentalCompanyCustomerVO getCompanyCustomerByMobilePhone(String phone) {
        return companyCustomerDao.getCompanyCustomerByMobilePhone(phone);
    }

    @Override
    public int getCompanyCustomerByCompanyName(String registeredCompanyName) {
        return companyCustomerDao.getCompanyCustomerByCompanyName(registeredCompanyName);
    }

    @Override
    public RentalCompanyCustomerVO personalCenter(int id) {
        return companyCustomerDao.personalCenter(id);
    }
}
