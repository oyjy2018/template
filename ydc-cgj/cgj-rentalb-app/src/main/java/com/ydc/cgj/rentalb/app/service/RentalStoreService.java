package com.ydc.cgj.rentalb.app.service;

/**
 * 门店相关
 */
public interface RentalStoreService {
    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    String myResponsibleStoreList(Integer userId);
}
