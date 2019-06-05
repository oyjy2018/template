package com.ydc.cgj.rentalb.app.controller;

import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rentalb.app.service.RentalStoreService;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店相关
 */
@RestController
@RequestMapping("/store")
public class RentalStoreController {
    private Logger logger = LogManager.getLogger(RentalStoreController.class);

    @Autowired
    private RentalStoreService rentalStoreService;

    /**
     * 我负责的门店
     * @return
     */
    @PostMapping("/myResponsibleStoreList")
    public String myResponsibleStoreList(){
        logger.info("subject:{}","我负责的门店");
        User user =  WebShiroTokenManager.getUser();
        if (user == null){
            return Result.failure("未获取到用户信息").toJSON();
        }
        return rentalStoreService.myResponsibleStoreList(user.getId());
    }
}
