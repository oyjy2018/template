package com.ydc.service.store.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyStoreQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreListVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreVO;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.rental.Organization;
import com.ydc.model.cgj.rental.RentalStore;
import com.ydc.service.store.service.OrganizationService;
import com.ydc.service.store.service.RentalStoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租车-门店
 *
 * @author
 * @create 2018-11-16 18:24
 **/
@RestController
@RequestMapping(value = "/rentalStore")
public class RentalStoreController {

    private static final Logger logger = LogManager.getLogger(RentalStoreController.class);


    @Autowired
    RentalStoreService rentalStoreService;
    @Autowired
    OrganizationService organizationService;

    /**
     * 新增门店
     * @param record
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(@RequestBody RentalStore record){
        logger.info("subject:{},record:{}","新增门店",JsonUtil.gsonStr(record));
        try{
            return rentalStoreService.insert(record) > 0 ? Result.success("新增成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","新增门店异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 更新门店
     * @param record
     * @return
     */
    @PostMapping(value = "/updateRentalStore")
    public String updateRentalStore(@RequestBody RentalStore record){
        logger.info("subject:{},record:{}","更新门店",JsonUtil.gsonStr(record));
        try{
            return rentalStoreService.updateRentalStore(record) > 0 ? Result.success("更新成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","新增门店异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 查询门店
     * @param storeId
     * @return
     */
    @PostMapping(value = "/getRentalStoreByStoreId")
    public String getRentalStoreByStoreId(@RequestParam("storeId") Integer storeId){
        logger.info("subject:{},storeId:{}","查询门店",storeId);
        try{
            RentalStoreVO rentalStoreVO = rentalStoreService.getRentalStoreByStoreId(storeId);
            return Result.success(rentalStoreVO).toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","查询门店异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 查询门店列表
     * @param rentalStoreDTO
     * @return
     */
    @PostMapping(value = "/getRentalStoreList")
    public String getRentalStoreList(@RequestBody RentalStoreDTO rentalStoreDTO){
        logger.info("subject:{},rentalStoreDTO:{}","查询门店列表",JsonUtil.gsonStr(rentalStoreDTO));
        try {
            List<RentalStoreListVO> ret = rentalStoreService.getRentalStoreList(rentalStoreDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        } catch (Exception e) {
            logger.error("查询门店列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 更新门店状态
     * @param rentalStoreDTO
     * @return
     */
    @PostMapping(value = "/updateRentalStoreStatus")
    public String updateRentalStoreStatus(@RequestBody RentalStoreDTO rentalStoreDTO) {
        logger.info("subject:{},rentalStoreDTO:{}","更新门店状态",JsonUtil.gsonStr(rentalStoreDTO));
        try {
            CommCarDTO commCarDTO = new CommCarDTO();
            commCarDTO.setStoreId(rentalStoreDTO.getStoreId());
            List<CommCar> commCarList = rentalStoreService.getEnabledCarByStoreId(commCarDTO);
            if(commCarList != null && commCarList.size() > 0){
                return Result.failure("该门店有启用中的车辆，不允许禁用").toJSON();
            }
            return rentalStoreService.updateRentalStoreStatus(rentalStoreDTO) > 0 ? Result.success("更新成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("更新门店状态异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 查询上级机构
     * @param
     * @return
     */
    @PostMapping(value = "/getOrganization")
    public List<Organization> getOrganization(){
        logger.info("subject:{}","查询上级机构");
        try {
            return organizationService.getOrganization();
        }catch (Exception e){
            logger.error("查询上级机构异常",e);
            return Lists.newArrayList();
        }
    }

    /**
     * 查询所有门店
     * @param
     * @return
     */
    @PostMapping(value = "/getAllRentalStore")
    public String getAllRentalStore(){
        logger.info("subject:{}","查询所有门店");
        return rentalStoreService.getAllRentalStore();
    }

    /**
     * 车商端新增门店
     * @param rentalCompanyStoreInsertDTO
     * @return
     */
    @PostMapping(value = "/rentalCompanyStoreInsert")
    public String rentalCompanyStoreInsert(@RequestBody RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO){
        logger.info("subject:{},rentalCompanyStoreInsertDTO:{}","车商端新增门店",JsonUtil.gsonStr(rentalCompanyStoreInsertDTO));
        try{
            return rentalStoreService.rentalCompanyStoreInsert(rentalCompanyStoreInsertDTO) > 0 ? Result.success("新增成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","新增门店异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 车商端编辑门店
     * @param rentalCompanyStoreUpdateDTO
     * @return
     */
    @PostMapping(value = "/rentalCompanyStoreUpdate")
    public String rentalCompanyStoreUpdate(@RequestBody RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO){
        logger.info("subject:{},rentalCompanyStoreUpdateDTO:{}","编辑门店",JsonUtil.gsonStr(rentalCompanyStoreUpdateDTO));
        try{
            return rentalStoreService.rentalCompanyStoreUpdate(rentalCompanyStoreUpdateDTO) > 0 ? Result.success("更新成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("subject:{},e:{}","编辑门店异常",e);
            return Result.failure("失败").toJSON();
        }
    }

    /**
     * 车商端我的门店列表
     * @param theirEnterpriseId
     * @return
     */
    @PostMapping(value = "/rentalCompanyStoreList")
    public String rentalCompanyStoreList(Integer theirEnterpriseId){
        logger.info("subject:{},theirEnterpriseId:{}","车商端我的门店列表",theirEnterpriseId);
        try {
            List<RentalCompanyStoreQueryVO> list = rentalStoreService.rentalCompanyStoreList(theirEnterpriseId);
            return Result.success(list).toJSON();
        } catch (Exception e) {
            logger.error("查询门店列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 车商端我的门店详情
     * @param id
     * @return
     */
    @PostMapping(value = "/rentalCompanyStoreDetail")
    public String rentalCompanyStoreDetail(Integer id){
        logger.info("subject:{},id:{}","车商端我的门店详情",id);
        RentalCompanyStoreQueryVO rentalCompanyStoreQueryVO = rentalStoreService.rentalCompanyStoreDetail(id);
        if (rentalCompanyStoreQueryVO == null){
            return Result.failure("未查询到门店信息").toJSON();
        }
        return Result.success(rentalCompanyStoreQueryVO).toJSON();
    }

    /**
     * 获取门店树结构
     * @return
     */
    @PostMapping(value = "/getStoreTree")
    public String getStoreTree(){
        return rentalStoreService.getStoreTree();
    }

    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    @PostMapping(value = "/myResponsibleStoreList")
    public String myResponsibleStoreList(@RequestParam("userId") Integer userId){
        logger.info("subject:{},userId:{}","我负责的门店",userId);
        return rentalStoreService.myResponsibleStoreList(userId);
    }
}
