package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.service.BCarServiceShopService;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 门店
 *
 * @author
 * @create 2018-10-31 12:00
 **/
@RestController
@RequestMapping(value = "/bCarServiceShop")
public class BCarServiceShopController {

    private static final Logger logger = LogManager.getLogger(BCarServiceShopController.class);

    @Autowired
    BCarServiceShopService bCarServiceShopService;

    /**
     * H5 门店列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getH5StoreList")
    public String getH5StoreList(@RequestParam("data") String data, HttpServletRequest request){
        logger.info("subject:{},positionDTO:{}","H5 门店列表",data);
        PositionDTO positionDTO = JSONObject.parseObject(data,PositionDTO.class);
        return bCarServiceShopService.getH5StoreList(positionDTO,request);
    }

    /**
     * 按销量获取门店
     * @param data
     * @return
     */
    @PostMapping(value = "/getH5StoreSalesVolumeList")
    public String getH5StoreSalesVolumeList(@RequestParam("data") String data, HttpServletRequest request){
        logger.info("subject:{},positionDTO:{}","H5 按销量获取门店",data);
        PositionDTO positionDTO = JSONObject.parseObject(data,PositionDTO.class);
        return bCarServiceShopService.getH5StoreSalesVolumeList(positionDTO,request);
    }

    /**
     * 首页推荐门店
     * @param data
     * @param request
     * @return
     */
    @PostMapping(value = "/getRecommendCarStore")
    public String getRecommendCarStore(@RequestParam("data") String data, HttpServletRequest request){
        logger.info("subject:{},positionDTO:{}","H5 首页推荐门店",data);
        PositionDTO positionDTO = JSONObject.parseObject(data,PositionDTO.class);
        return bCarServiceShopService.getRecommendCarStore(positionDTO,request).toJSON();
    }

}
