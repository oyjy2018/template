package com.ydc.cgj.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.cgj.app.feignService.IBCarServiceShopFeignService;
import com.ydc.cgj.app.service.BCarServiceShopService;
import com.ydc.cgj.app.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.*;
import com.ydc.commom.util.site.DistanceUtil;
import com.ydc.commom.util.site.GetLocation;
import com.ydc.commom.util.site.Position;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.commom.view.vo.cgj.RecommendCarStoreVO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author
 * @create 2018-10-31 11:58
 **/
@Service
public class BCarServiceShopServiceImpl implements BCarServiceShopService {

    private static final Logger logger = LogManager.getLogger(BCarServiceShopService.class);

    @Autowired
    IBCarServiceShopFeignService iBCarServiceShopFeignService;
    @Autowired
    DictionaryDetailService dictionaryDetailService;

    @Override
    public String getH5StoreList(PositionDTO positionDTO, HttpServletRequest request) {
        return iBCarServiceShopFeignService.getH5StoreList(this.getCarStoreListCondition(positionDTO,request));
    }

    @Override
    public String getH5StoreSalesVolumeList(PositionDTO positionDTO, HttpServletRequest request) {
        return iBCarServiceShopFeignService.getH5StoreSalesVolumeList(this.getCarStoreListCondition(positionDTO,request));
    }

    private PositionDTO getCarStoreListCondition(PositionDTO positionDTO, HttpServletRequest request){
        if(StringUtil.isNotEmpty(positionDTO.getSearchContent())){
            positionDTO.setLongitude(null);
            positionDTO.setLatitude(null);
        }else if(StringUtil.isEmpty(positionDTO.getLatitude()) || StringUtil.isEmpty(positionDTO.getLongitude())){
            this.getIpCommon(positionDTO,request);
        }else{
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.PARENT_DICT_CODE_MDFW, DictionaryConstant.PARENT_DICT_CODE_MDFW_CFG)
                    .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.PARENT_DICT_CODE_MDFW, DictionaryConstant.PARENT_DICT_CODE_MDFW_CFG)));
            int dis = 30;
            if(dic.isPresent())dis= Integer.valueOf(dic.get().getDictValue());
            Position position = DistanceUtil.findNeighPosition(positionDTO.getLongitude(),positionDTO.getLatitude(),dis);
            positionDTO.setMinLat(position.getMinLat());
            positionDTO.setMaxLat(position.getMaxLat());
            positionDTO.setMinLng(position.getMinLng());
            positionDTO.setMaxLng(position.getMaxLng());
        }
        return positionDTO;
    }

    @Override
    public Result getRecommendCarStore(PositionDTO positionDTO, HttpServletRequest request) {
        Result result = iBCarServiceShopFeignService.getRecommendCarStore(this.getCarServiceLocation(positionDTO, request));
        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE)return result;
        Object obj = result.getData();
        RecommendCarStoreVO recommendCarStoreVO =JSONObject.parseObject(JsonUtil.gsonStr(obj),RecommendCarStoreVO.class);
        this.getLocationAddress(positionDTO,request,recommendCarStoreVO);
        return Result.success(recommendCarStoreVO);
    }

    @Override
    public Result getHomePageCarStore(PositionDTO positionDTO, HttpServletRequest request) {
        Result result = iBCarServiceShopFeignService.getHomePageCarStore(this.getCarServiceLocation(positionDTO, request));
        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE)return result;
        Object obj = result.getData();
        RecommendCarStoreVO recommendCarStoreVO =JSONObject.parseObject(JsonUtil.gsonStr(obj),RecommendCarStoreVO.class);
        this.getLocationAddress(positionDTO,request,recommendCarStoreVO);
        return Result.success(recommendCarStoreVO);
    }

    private void getLocationAddress(PositionDTO positionDTO,HttpServletRequest request,RecommendCarStoreVO recommendCarStoreVO){
        if(StringUtil.isNotEmpty(positionDTO.getSearchContent())){
            recommendCarStoreVO.setStoreRegisterCity(positionDTO.getSearchContent());
            return;
        }
        if(StringUtil.isEmpty(positionDTO.getLatitude()) || StringUtil.isEmpty(positionDTO.getLongitude())){
            this.getLocationAddressByIP(request,recommendCarStoreVO);
            return;
        }
        String add = GetLocation.getAdd(positionDTO.getLongitude().toString(),positionDTO.getLatitude().toString());
        if(StringUtil.isEmpty(add)){
            this.getLocationAddressByIP(request,recommendCarStoreVO);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(add);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));
        Optional<Object> admNameObj,nameObj;
        if(StringUtil.isEmpty(jsonArray.get(0))){
            this.getLocationAddressByIP(request,recommendCarStoreVO);
            return;
        }
        JSONObject object= (JSONObject) jsonArray.get(0);
        admNameObj = Optional.ofNullable(object.get("admName"));
        nameObj = Optional.ofNullable(object.get("name"));
        if(admNameObj.isPresent()){
            String admName = admNameObj.get().toString();
            String [] arrAdmName = admName.split(",");
            List<String> strings = Arrays.asList(arrAdmName);
            recommendCarStoreVO.setStoreRegisterProvince(Optional.ofNullable(strings.get(0)).orElse(""));
            recommendCarStoreVO.setStoreRegisterCity(Optional.ofNullable(strings.size() > 1 ? strings.get(1) : null).orElse(""));
            recommendCarStoreVO.setStoreRegisterRegion(Optional.ofNullable(strings.size() > 2 ? strings.get(2) : null).orElse(""));
            if(nameObj.isPresent()){
                recommendCarStoreVO.setDetailsAddress(nameObj.get().toString());
            }
            return;
        }
        this.getLocationAddressByIP(request,recommendCarStoreVO);
        return;
    }

    private void getLocationAddressByIP(HttpServletRequest request,RecommendCarStoreVO recommendCarStoreVO){
        String ip,city;
        IPAddress ipAddress;
        try{
            ip = SystemUtil.getIpAddress(request);
            if(StringUtil.isEmpty(ip)){
                recommendCarStoreVO.setStoreRegisterProvince("");
                recommendCarStoreVO.setStoreRegisterCity("深圳市");
                recommendCarStoreVO.setStoreRegisterRegion("");
                return;
            }
            ipAddress = IPUtil.getAddressByIP(ip);
            logger.info("subject:{},ipAddress:{}","通过ip获取到的城市",JsonUtil.gsonStr(ipAddress));
            if(ipAddress == null){
                recommendCarStoreVO.setStoreRegisterProvince("");
                recommendCarStoreVO.setStoreRegisterCity("深圳市");
                recommendCarStoreVO.setStoreRegisterRegion("");
                return;
            }
            city = ipAddress.getCity();
            if(StringUtil.isEmpty(city)){
                recommendCarStoreVO.setStoreRegisterProvince("");
                recommendCarStoreVO.setStoreRegisterCity("深圳市");
                recommendCarStoreVO.setStoreRegisterRegion("");
                return;
            }
            recommendCarStoreVO.setStoreRegisterProvince(ipAddress.getProvince());
            recommendCarStoreVO.setStoreRegisterCity(ipAddress.getCity());
            recommendCarStoreVO.setStoreRegisterRegion(ipAddress.getDistrict());
            return;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取IP异常",e);
            recommendCarStoreVO.setStoreRegisterProvince("");
            recommendCarStoreVO.setStoreRegisterCity("深圳市");
            recommendCarStoreVO.setStoreRegisterRegion("");
            return;
        }
    }


    private PositionDTO getCarServiceLocation(PositionDTO positionDTO, HttpServletRequest request){
        if(StringUtil.isNotEmpty(positionDTO.getSearchContent())){
            positionDTO.setLongitude(null);
            positionDTO.setLatitude(null);
        }else if(StringUtil.isEmpty(positionDTO.getLatitude()) || StringUtil.isEmpty(positionDTO.getLongitude())){
           this.getIpCommon(positionDTO,request);
        }else{
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.PARENT_DICT_CODE_MDFW, DictionaryConstant.PARENT_DICT_CODE_MDFW_CFG)
                    .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.PARENT_DICT_CODE_MDFW, DictionaryConstant.PARENT_DICT_CODE_MDFW_CFG)));
            int dis = 30;
            if(dic.isPresent())dis= Integer.valueOf(dic.get().getDictValue());
            Position position = DistanceUtil.findNeighPosition(positionDTO.getLongitude(),positionDTO.getLatitude(),dis);
            positionDTO.setMinLat(position.getMinLat());
            positionDTO.setMaxLat(position.getMaxLat());
            positionDTO.setMinLng(position.getMinLng());
            positionDTO.setMaxLng(position.getMaxLng());
        }
        return positionDTO;
    }

    private void getIpCommon(PositionDTO positionDTO, HttpServletRequest request){
        String ip,city;
        try{
            ip = SystemUtil.getIpAddress(request);
            if(StringUtil.isEmpty(ip)){
                positionDTO.setDefaultLocation("深圳市");
                return;
            }
            IPAddress ipAddress = IPUtil.getAddressByIP(ip);
            logger.info("subject:{},ipAddress:{}","通过ip获取到的城市",JsonUtil.gsonStr(ipAddress));
            if(ipAddress == null){
                positionDTO.setDefaultLocation("深圳市");
                return;
            }
            city = ipAddress.getCity();
            if(StringUtil.isEmpty(city) || "内网IP".equals(city)){
                positionDTO.setDefaultLocation("深圳市");
                return;
            }
            positionDTO.setDefaultLocation(city);
            return;
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取IP异常",e);
            positionDTO.setDefaultLocation("深圳市");
            return;
        }
    }
}
