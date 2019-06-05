package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-store")
public interface StoreFeignService {
    /**
     * 车商端新增门店
     * @param rentalCompanyStoreInsertDTO
     * @return
     */
    @PostMapping(value = "/rentalStore/rentalCompanyStoreInsert")
    String insert(@RequestBody RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO);

    //车商端编辑门店
    @PostMapping(value = "/rentalStore/rentalCompanyStoreUpdate")
    String update(@RequestBody RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO);

    //车商端我的门店列表
    @PostMapping(value = "/rentalStore/rentalCompanyStoreList")
    String list(@RequestParam("theirEnterpriseId") Integer theirEnterpriseId);

    //车商端我的门店列表
    @PostMapping(value = "/rentalStore/rentalCompanyStoreDetail")
    String detail(@RequestParam("id") Integer id);
}
