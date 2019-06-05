package com.ydc.cgj.rental.company.app.controller;

import com.ydc.cgj.rental.company.app.service.StoreService;
import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  门店相关
 */
@RestController
@RequestMapping(value = "/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    private final Logger logger = LogManager.getLogger(StoreController.class);
    /**
     * 车商端新增门店
     * @param data
     * @return
     */
     @PostMapping("/insert")
     public String insert(@RequestParam("data") String data){
         logger.info("subject:{},data:{}", "车商端新增门店", data);
         Map validMap = ParamVaildateUtil.vaildateAndTransfer(data, RentalCompanyStoreInsertDTO.class);
         if ("1".equals(validMap.get("code"))){
             return Result.failure(validMap.get("message").toString()).toJSON();
         }
         RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO = (RentalCompanyStoreInsertDTO) validMap.get("object");

         rentalCompanyStoreInsertDTO.setStatus(CommonEnum.DeleteStatusEnum.STATUS_1.getCode());
         rentalCompanyStoreInsertDTO.setSymbiosisName("外部企业");
         rentalCompanyStoreInsertDTO.setOrganizationTypeName("门店");

         //获取企业信息
         RentalCompanyCustomerVO rentalCompanyCustomerVO = (RentalCompanyCustomerVO) SecurityUtils.getSubject().getPrincipal();
         rentalCompanyStoreInsertDTO.setTheirEnterpriseId(rentalCompanyCustomerVO.getId().toString());
         rentalCompanyStoreInsertDTO.setTheirEnterpriseName(rentalCompanyCustomerVO.getRegisteredCompanyName());

         return storeService.insert(rentalCompanyStoreInsertDTO);
     }

    /**
     * 车商端门店编辑
     */
    @PostMapping("/update")
    public String update(@RequestParam("data") String data){
        logger.info("subject:{},data:{}", "车商端编辑门店", data);
        Map validMap = ParamVaildateUtil.vaildateAndTransfer(data, RentalCompanyStoreUpdateDTO.class);
        if ("1".equals(validMap.get("code"))){
            return Result.failure(validMap.get("message").toString()).toJSON();
        }
        RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO = (RentalCompanyStoreUpdateDTO) validMap.get("object");
        return storeService.update(rentalCompanyStoreUpdateDTO);
    }

    /**
     * 车商端  我的门店列表
     */
    @PostMapping("/list")
    public String list(){
        logger.info("subject:{}", "查询我的门店里列表");
        //通过shiro获取登录信息
        RentalCompanyCustomerVO rentalCompanyCustomerVO  = (RentalCompanyCustomerVO) SecurityUtils.getSubject().getPrincipal();
        if (rentalCompanyCustomerVO == null) {
            return Result.failure("未获取到登录信息").toJSON();
        }
        //获取企业id
        Integer theirEnterpriseId = rentalCompanyCustomerVO.getId();
        if (theirEnterpriseId == null){
            return  Result.failure("获取企业id失败").toJSON();
        }
        return storeService.list(theirEnterpriseId);
    }

    /**
     * 车商端门店详情
     * @param data
     * @return
     */
    @PostMapping("detail")
    public String detail(@RequestParam("data") String data){
        logger.info("subject:{},data:{}", "查询门店详情", data);
        Map dataMap = JsonUtil.jsonToMap(data);
        Integer id  = (Integer) dataMap.get("id");
        if (id == null) {
            return Result.failure("门店id为空").toJSON();
        }
        return storeService.detail(id);
    }

}
