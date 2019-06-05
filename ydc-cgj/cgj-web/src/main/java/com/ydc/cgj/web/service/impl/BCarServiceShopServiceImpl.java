package com.ydc.cgj.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.commom.CarShopCommon;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.web.feignService.IBCarServiceShopFeignService;
import com.ydc.cgj.web.service.BCarServiceShopService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.model.cgj.BCarServiceShop;
import com.ydc.model.cgj.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-10-30 10:27
 **/
@Service
public class BCarServiceShopServiceImpl implements BCarServiceShopService {
    private static final Logger logger = LogManager.getLogger(BCarServiceShopService.class);

    @Autowired
    IBCarServiceShopFeignService iBCarServiceShopFeignService;

    @Override
    public String getBStoreList(CarStoreDTO carStoreDTO) {
        return iBCarServiceShopFeignService.getBStoreList(carStoreDTO);
    }

    @Override
    public String updateStoreWhetherPutaway(CarStoreDTO carStoreDTO) {
        return iBCarServiceShopFeignService.updateStoreWhetherPutaway(carStoreDTO);
    }

    @Override
    public String refreshCarStoreTemplate(CarStoreDTO carStoreDTO) {
        Result result = CarShopCommon.getStoreList(carStoreDTO);
        if(ResultConstant.RESULT_CODE_FAILURE == result.getCode()){
            return result.toJSON();
        }
        List<BCarServiceShop> list = (List<BCarServiceShop>)result.getData();
        return iBCarServiceShopFeignService.insertCarServiceBatch(list);
//        try{
//            Map<String,Object> dataMap = new HashMap<>();
//            dataMap.put("key","nuhcuienncuewfe");
////            dataMap.put("channel","15156165");
//            String response = UrlHttpUtil.doPost(SystemPropertiesConfig.B_SERVICE_URL+"store/storeList",dataMap,null);
//            logger.info("subject:{},response:{}","刷新门店响应",response);
//            JSONObject jsonObject = JSON.parseObject(response);
//            if(Integer.valueOf(jsonObject.get("status").toString()).intValue() != 1){
//                return Result.failure(jsonObject.get("msg").toString()).toJSON();
//            }
//            List<Map<String,Object>> data = JSONObject.parseObject(jsonObject.get("data").toString(),List.class);
//            logger.info("subject:{},data:{}","获取门店数据",JsonUtil.gsonStr(data));
//            if(data == null || data.isEmpty()){
//                return Result.failure("获取券模板数据为空").toJSON();
//            }
//            Date date = new Date();
//            List<BCarServiceShop> list = data.stream().map(item ->{
//                BCarServiceShop bCarServiceShop = new BCarServiceShop();
//                bCarServiceShop.setStoreName(StringUtil.objToStr(item.get("storeName")));
//                bCarServiceShop.setStoreIdentifying(null);
//                JSONArray jsonArray = JSON.parseArray(item.get("photoList").toString());
//                if(jsonArray != null && jsonArray.size()>0){
//                    bCarServiceShop.setStoreLogo(jsonArray.get(0).toString());
//                }else{
//                    bCarServiceShop.setStoreLogo(null);
//                }
////                        Iterator<Object> iterator= jsonArray.iterator();
////                        List<String> photoList = new ArrayList<>();
////                        while (iterator.hasNext()){
////                            photoList.add(iterator.next().toString());
////                        }
////                        bCarServiceShop.setStoreLogo(String.join(",",photoList));
//                bCarServiceShop.setStoreCode(StringUtil.objToStr(item.get("storeNo")));
//                bCarServiceShop.setServicePhone(StringUtil.objToStr(item.get("servicePhone")));
//                bCarServiceShop.setStoreRegisterProvince(StringUtil.objToStr(item.get("province")));
//                bCarServiceShop.setStoreRegisterCity(StringUtil.objToStr(item.get("city")));
//                bCarServiceShop.setStoreRegisterRegion(StringUtil.objToStr(item.get("area")));
//                bCarServiceShop.setDetailsAddress(StringUtil.objToStr(item.get("storeAdd")));
//                String serviceTime = StringUtil.objToStr(item.get("serviceTime"));
//                if(serviceTime == null){
//                    bCarServiceShop.setBusinessHoursStartTime(null);
//                    bCarServiceShop.setBusinessHoursEndTime(null);
//                }else{
//                    bCarServiceShop.setBusinessHoursStartTime(serviceTime.substring(0,serviceTime.indexOf("-")));
//                    bCarServiceShop.setBusinessHoursEndTime(serviceTime.substring(serviceTime.indexOf("-")+1,serviceTime.length()));
//                }
//                JSONArray goodsArr = JSON.parseArray(item.get("goodsList").toString());
//                List<String> goodsList =  new ArrayList<>();
//                Iterator<Object> iterator= goodsArr.iterator();
//                while (iterator.hasNext()){
//                    goodsList.add(iterator.next().toString());
//                }
//                bCarServiceShop.setServeGather(String.join(",",goodsList));
//                Integer status = StringUtil.retInt(item.get("status"));
//                bCarServiceShop.setStatus(status);
//                bCarServiceShop.setWhetherPutaway((status == null || status == 0) ? 0 : 1);
//                bCarServiceShop.setLongitude(StringUtil.objToBigDecimal(item.get("longitude")));
//                bCarServiceShop.setLatitude(StringUtil.objToBigDecimal(item.get("latitude")));
//                bCarServiceShop.setCreateTime(date);
//                bCarServiceShop.setCreateBy(carStoreDTO.getUpdateBy());
//                bCarServiceShop.setUpdateTime(date);
//                bCarServiceShop.setUpdateBy(carStoreDTO.getUpdateBy());
//                return bCarServiceShop;
//            }).collect(Collectors.toList());
//            logger.info("subject:{},list:{}","封装空券数据",JsonUtil.gsonStr(list));
//            return iBCarServiceShopFeignService.insertCarServiceBatch(list);
//        }catch (Exception e){
//            logger.error("subject:{},e:{}","刷新门店异常",e);
//            return Result.failure().toJSON();
//        }
    }

    @Override
    public Result getPriorityCarStore(String storeName, Pagination pagination) {
        return iBCarServiceShopFeignService.getPriorityCarStore(storeName, pagination);
    }

    @Override
    public Result updatePriorityCarStore(String carStoreIds, int priority, Integer userId) {
        return iBCarServiceShopFeignService.updatePriorityCarStore(carStoreIds, priority, userId);
    }
}
