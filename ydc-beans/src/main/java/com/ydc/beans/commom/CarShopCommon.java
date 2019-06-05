package com.ydc.beans.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.model.cgj.BCarServiceShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拉取B端汽车门店
 *
 * @author
 * @create 2019-01-21 11:19
 **/
public class CarShopCommon {

    private static final Logger logger = LogManager.getLogger(CarShopCommon.class);


    /**
     * 拉取B端汽修门店
     * @return
     */
    public static Result getStoreList(CarStoreDTO carStoreDTO){
        try{
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("key","nuhcuienncuewfe");
            String response = UrlHttpUtil.doPost(SystemPropertiesConfig.B_SERVICE_URL+"store/storeList",dataMap,null);
            logger.info("subject:{},response:{}","刷新门店响应",response);
            JSONObject jsonObject = JSON.parseObject(response);
            if(Integer.valueOf(jsonObject.get("status").toString()).intValue() != 1){
                return Result.failure(jsonObject.get("msg").toString());
            }
            List<Map<String,Object>> data = JSONObject.parseObject(jsonObject.get("data").toString(),List.class);
            logger.info("subject:{},data:{}","获取门店数据",JsonUtil.gsonStr(data));
            if(data == null || data.isEmpty()){
                return Result.failure("获取门店数据为空");
            }
            Date date = new Date();
            List<BCarServiceShop> list = data.stream().map(item ->{
                BCarServiceShop bCarServiceShop = new BCarServiceShop();
                bCarServiceShop.setStoreName(StringUtil.objToStr(item.get("storeName")));
                bCarServiceShop.setStoreIdentifying(null);
                JSONArray jsonArray = JSON.parseArray(item.get("photoList").toString());
                if(jsonArray != null && jsonArray.size()>0){
                    bCarServiceShop.setStoreLogo(jsonArray.get(0).toString());
                }else{
                    bCarServiceShop.setStoreLogo(null);
                }
//                        Iterator<Object> iterator= jsonArray.iterator();
//                        List<String> photoList = new ArrayList<>();
//                        while (iterator.hasNext()){
//                            photoList.add(iterator.next().toString());
//                        }
//                        bCarServiceShop.setStoreLogo(String.join(",",photoList));
                bCarServiceShop.setStoreCode(StringUtil.objToStr(item.get("storeNo")));
                bCarServiceShop.setServicePhone(StringUtil.objToStr(item.get("servicePhone")));
                bCarServiceShop.setStoreRegisterProvince(StringUtil.objToStr(item.get("province")));
                bCarServiceShop.setStoreRegisterCity(StringUtil.objToStr(item.get("city")));
                bCarServiceShop.setStoreRegisterRegion(StringUtil.objToStr(item.get("area")));
                bCarServiceShop.setDetailsAddress(StringUtil.objToStr(item.get("storeAdd")));
                String serviceTime = StringUtil.objToStr(item.get("serviceTime"));
                if(serviceTime == null){
                    bCarServiceShop.setBusinessHoursStartTime(null);
                    bCarServiceShop.setBusinessHoursEndTime(null);
                }else{
                    bCarServiceShop.setBusinessHoursStartTime(serviceTime.substring(0,serviceTime.indexOf("-")));
                    bCarServiceShop.setBusinessHoursEndTime(serviceTime.substring(serviceTime.indexOf("-")+1,serviceTime.length()));
                }
                JSONArray goodsArr = JSON.parseArray(item.get("goodsList").toString());
                List<String> goodsList =  new ArrayList<>();
                Iterator<Object> iterator= goodsArr.iterator();
                while (iterator.hasNext()){
                    goodsList.add(iterator.next().toString());
                }
                bCarServiceShop.setServeGather(String.join(",",goodsList));
                Integer status = StringUtil.retInt(item.get("status"));
                bCarServiceShop.setStatus(status);
                bCarServiceShop.setWhetherPutaway((status == null || status == 0) ? 0 : 1);
                bCarServiceShop.setCategory(StringUtil.objToStr(item.get("doBusi")));
                bCarServiceShop.setLongitude(StringUtil.objToBigDecimal(item.get("longitude")));
                bCarServiceShop.setLatitude(StringUtil.objToBigDecimal(item.get("latitude")));
                bCarServiceShop.setCreateTime(date);
                bCarServiceShop.setCreateBy(carStoreDTO.getUpdateBy());
                bCarServiceShop.setUpdateTime(date);
                bCarServiceShop.setUpdateBy(carStoreDTO.getUpdateBy());
                return bCarServiceShop;
            }).collect(Collectors.toList());
            logger.info("subject:{},list:{}","封装门店数据",JsonUtil.gsonStr(list));
            return Result.success(list);
        }catch (Exception e){
            logger.error("subject:{},e:{}","刷新门店异常",e);
            return Result.failure("刷新门店异常");
        }
    }
}
