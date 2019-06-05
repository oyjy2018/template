package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.model.cgj.rental.Organization;
import com.ydc.model.cgj.rental.RentalStore;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * @author
 * @create 2018-11-16 19:04
 **/
@Service
@FeignClient(value = "service-store")
public interface StoreFeignService {


    /**
     * 新增门店
     * @param record
     * @return
     */
    @PostMapping(value = "/rentalStore/insert")
    String insert(@RequestBody RentalStore record);

    /**
     * 更新门店
     * @param record
     * @return
     */
    @PostMapping(value = "/rentalStore/updateRentalStore")
    String updateRentalStore(@RequestBody RentalStore record);


    /**
     * 查询门店
     * @param storeId
     * @return
     */
    @PostMapping(value = "/rentalStore/getRentalStoreByStoreId")
    String getRentalStoreByStoreId(@RequestParam("storeId") Integer storeId);


    /**
     * 查询门店列表
     * @param rentalStoreDTO
     * @return
     */
    @PostMapping(value = "/rentalStore/getRentalStoreList")
    String getRentalStoreList(@RequestBody RentalStoreDTO rentalStoreDTO);


    /**
     * 更新门店状态
     * @param rentalStoreDTO
     * @return
     */
    @PostMapping(value = "/rentalStore/updateRentalStoreStatus")
    String updateRentalStoreStatus(@RequestBody RentalStoreDTO rentalStoreDTO);

    /**
     * 查询上级机构
     * @return
     */
    @PostMapping(value = "/rentalStore/getOrganization")
    List<Organization> getOrganization();

    /**
     * 获取所有门店
     * @return
     */
    @PostMapping(value = "/rentalStore/getAllRentalStore")
    String getAllRentalStore();

    /**
     * 获取门店树结构
     * @return
     */
    @PostMapping(value = "/rentalStore/getStoreTree")
    String getStoreTree();
}
