package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.model.cgj.LogLogin;
import com.ydc.model.cgj.ServiceUserRole;
import com.ydc.model.cgj.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author
 * @create 2018-11-19 13:56
 **/
@Service
@FeignClient(value = "service-store")
public interface RentalStoreFeignService {


    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    @PostMapping(value = "/rentalStore/myResponsibleStoreList")
    String myResponsibleStoreList(@RequestParam("userId") Integer userId);
}
