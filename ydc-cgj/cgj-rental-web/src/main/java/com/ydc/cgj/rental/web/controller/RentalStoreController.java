package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.service.CompanyCustomerService;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
import com.ydc.cgj.rental.web.service.OrganizationService;
import com.ydc.cgj.rental.web.service.RentalStoreService;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.CompanyCustomerDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyCustomerVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 租车-门店
 * @author
 * @create 2018-11-16 19:10
 **/
@RestController
@RequestMapping(value = "/rentalStore")
public class RentalStoreController {


    private static final Logger logger = LogManager.getLogger(RentalStoreController.class);


    @Autowired
    RentalStoreService rentalStoreService;
    @Autowired
    DictionaryDetailService dictionaryDetailService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CompanyCustomerService companyCustomerService;

    /**
     * 新增门店
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:store:manage:store:view:add"})
    @PostMapping(value = "/insert")
    public String insert(@RequestParam("data") String data){
        logger.info("subject:{},record:{}","新增门店",data);
        RentalStore record = JSONObject.parseObject(data,RentalStore.class);
        return rentalStoreService.insert(record);
    }

    /**
     * 更新门店
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:store:manage:store:view:update"})
    @PostMapping(value = "/updateRentalStore")
    public String updateRentalStore(@RequestParam("data") String data){
        logger.info("subject:{},record:{}","更新门店",data);
        RentalStore record = JSONObject.parseObject(data,RentalStore.class);
        return rentalStoreService.updateRentalStore(record);
    }

    /**
     * 查询门店
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:store:manage:store:view:detail"})
    @PostMapping(value = "/getRentalStoreByStoreId")
    public String getRentalStoreByStoreId(@RequestParam("data") String data){
        logger.info("subject:{},rentalStoreDTO:{}","查询门店",data);
        RentalStoreDTO rentalStoreDTO  = JSONObject.parseObject(data,RentalStoreDTO.class);
        return rentalStoreService.getRentalStoreByStoreId(rentalStoreDTO.getStoreId());

    }

    /**
     * 查询门店列表
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:store:manage:store:view:query"})
    @PostMapping(value = "/getRentalStoreList")
    public String getRentalStoreList(@RequestParam("data") String data){
        logger.info("subject:{},rentalStoreDTO:{}","查询门店列表",data);
        RentalStoreDTO rentalStoreDTO  = JSONObject.parseObject(data,RentalStoreDTO.class);
        return rentalStoreService.getRentalStoreList(rentalStoreDTO);
    }

    /**
     * 更新门店状态
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rental:store:manage:store:view:disable","rental:store:manage:store:view:enabled"})
    @PostMapping(value = "/updateRentalStoreStatus")
    public String updateRentalStoreStatus(@RequestParam("data") String data) {
        logger.info("subject:{},rentalStoreDTO:{}","更新门店状态",data);
        RentalStoreDTO rentalStoreDTO = JSONObject.parseObject(data,RentalStoreDTO.class);
        return rentalStoreService.updateRentalStoreStatus(rentalStoreDTO);
    }


    /**
     * 合作关系
     * @return
     */
    @PostMapping(value = "/getSymbiosis")
    public String getSymbiosis(){
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_SYMBIOSIS)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_SYMBIOSIS)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(Lists.newArrayList()).toJSON();
    }

    /**
     * 机构类型
     * @return
     */
    @PostMapping(value = "/getOrganizationType")
    public String getOrganizationType(){
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_ORG_TYPE)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_ORG_TYPE)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(Lists.newArrayList()).toJSON();
    }
    /**
     * 所属企业
     * @return
     */
    @PostMapping(value = "/getTheirEnterprise")
    public String getTheirEnterprise(){
        logger.info("subject:{}","所属企业");
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_THEIR_ENTERPRISE)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(RentalDictionaryConstant.RENTAL_STORE_CONFIG_THEIR_ENTERPRISE)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(Lists.newArrayList()).toJSON();
        /*CompanyCustomerDTO companyCustomerDTO = new CompanyCustomerDTO();
        companyCustomerDTO.setRegisteredCompanyName("一点车");
        //查询注册名为一点车的企业列表
        String resultString = companyCustomerService.getCompanyCustomerList(companyCustomerDTO);
        //解析结果集
        Result result = JsonUtil.jsonToBean(resultString,Result.class);
        logger.info("result code:{}",result.getCode());
        if (ResultConstant.RESULT_CODE_SUCCESS != result.getCode()){
            return resultString;
        }
        Map<String,Object> dataMap = (Map<String, Object>) result.getData();
        if (dataMap == null || dataMap.isEmpty()) {
            return Result.failure("无数据").toJSON();
        }
        List<Map> rentalCompanyCustomerVOList = (List<Map>) dataMap.get("rows");
        List resultList = new ArrayList();
        rentalCompanyCustomerVOList.forEach(rentalCompanyCustomerVO -> {
            Map map = new HashMap();
            map.put("id",rentalCompanyCustomerVO.get("id"));
            map.put("dictKey",rentalCompanyCustomerVO.get("registeredCompanyName"));
            map.put("dictValue",rentalCompanyCustomerVO.get("registeredCompanyName"));
            resultList.add(map);
        });
        return Result.success(resultList).toJSON();*/
    }

    /**
     * 上级机构
     * @return
     */
    @PostMapping(value = "/getSuperiorOrganization")
    public String getSuperiorOrganization() {
        return organizationService.getOrganization();
    }

    /**
     * 获取门店树结构
     * @return
     */
    @PostMapping("/getStoreTree")
    public String getStoreTree(){
        logger.info("subject:{}","获取门店树结构");
        return rentalStoreService.getStoreTree();
    }
}
