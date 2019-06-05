package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.model.cgj.rental.RentalStore;

/**
 * @author
 * @create 2018-11-16 19:07
 **/
public interface RentalStoreService {

    /**
     * 新增门店
     * @param record
     * @return
     */
    String insert(RentalStore record);

    /**
     * 更新门店
     * @param record
     * @return
     */
    String updateRentalStore( RentalStore record);


    /**
     * 查询门店
     * @param storeId
     * @return
     */
    String getRentalStoreByStoreId( Integer storeId);


    /**
     * 查询门店列表
     * @param rentalStoreDTO
     * @return
     */
    String getRentalStoreList( RentalStoreDTO rentalStoreDTO);

    /**
     * 更新门店状态
     * @param rentalStoreDTO
     * @return
     */
    String updateRentalStoreStatus(RentalStoreDTO rentalStoreDTO);

    /**
     * 获取所有门店
     * @return
     */
    String getAllRentalStore();

    /**
     * 获取门店树结构
     * @return
     */
    String getStoreTree();
}
