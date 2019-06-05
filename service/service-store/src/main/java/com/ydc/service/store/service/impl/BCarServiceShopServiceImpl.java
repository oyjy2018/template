package com.ydc.service.store.service.impl;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.util.*;
import com.ydc.commom.util.site.DistanceUtil;
import com.ydc.commom.util.site.Position;
import com.ydc.commom.view.dto.cgj.PositionDTO;
import com.ydc.commom.view.dto.cgj.service.CarStoreDTO;
import com.ydc.commom.view.vo.cgj.CarStoreVO;
import com.ydc.commom.view.vo.cgj.RecommendCarStoreVO;
import com.ydc.commom.view.vo.cgj.order.BStoreListVO;
import com.ydc.dao.cgj.service.BCarServiceShopDao;
import com.ydc.model.cgj.BCarServiceShop;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.Pagination;
import com.ydc.service.store.service.BCarServiceShopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-11-01 14:35
 **/
@Service
public class BCarServiceShopServiceImpl implements BCarServiceShopService {

    private static final Logger logger = LogManager.getLogger(BCarServiceShopService.class);

    @Autowired
    BCarServiceShopDao bCarServiceShopDao;

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void insertCarServiceBatch(List<BCarServiceShop> list) {
        bCarServiceShopDao.insertCarServiceBatch(list);
        //更新门店状态
        List<String> storeCodes = Lists.newArrayList();
        list.forEach(item->{
            storeCodes.add(item.getStoreCode());
        });
        logger.info("subject:{},storeCodes:{},size:{}","门店code集合",JsonUtil.gsonStr(storeCodes),(storeCodes== null || storeCodes.isEmpty()) ? 0 : storeCodes.size());
        if(storeCodes== null || storeCodes.isEmpty())return;
        bCarServiceShopDao.batchUpdateCarShopStatus(storeCodes,list.get(0).getUpdateBy());
    }

    @Override
    public List<BStoreListVO> getBStoreList(CarStoreDTO carStoreDTO) {
        List<BStoreListVO> list = PaginationUtil.paginationQuery(carStoreDTO ,(tempCarStoreDTO) -> bCarServiceShopDao.getBStoreList(carStoreDTO));
        if(list == null || list.isEmpty())return new ArrayList<>();
//        list.stream().map(item ->item.put("storeLogo",StringUtil.isEmpty(item.get("storeLogo")) ? "" : ServiceUtil.getBPhotoServiceUrl()+item.get("storeLogo"))).collect(Collectors.toList());
        return list;
    }

    @Override
    public Integer updateStoreWhetherPutaway(CarStoreDTO carStoreDTO) {
        return bCarServiceShopDao.updateStoreWhetherPutaway(carStoreDTO);
    }

    @Override
    public BCarServiceShop selectByPrimaryKey(Integer id) {
        return bCarServiceShopDao.selectByPrimaryKey(id);
    }

    @Override
    public List<CarStoreVO> getH5StoreList(PositionDTO positionDTO) {
        List<CarStoreVO> list;
        if(StringUtil.isEmpty(positionDTO.getLatitude()) ||
                StringUtil.isEmpty(positionDTO.getLongitude()) ||
                BigDecimal.valueOf(positionDTO.getLatitude()).compareTo(BigDecimal.valueOf(0)) == 0 ||
                BigDecimal.valueOf(positionDTO.getLongitude()).compareTo(BigDecimal.valueOf(0)) == 0){
            list = PaginationUtil.paginationQuery(positionDTO ,(tempPositionDTO) -> bCarServiceShopDao.getRecommendCarStore(positionDTO));
            logger.info("subject:{},list:{}","按销量查询门店",JsonUtil.gsonStr(list));
            return list;
        }
//            list = PaginationUtil.paginationQuery(positionDTO ,(tempPositionDTO) -> bCarServiceShopDao.getH5StoreList(positionDTO));
        logger.info("subject:{}","按经纬度查询门店");
        // 经纬度查询不分页，查2页以上直接返回空集
        if(positionDTO.getPage() > 1){
            return new ArrayList<>();
        }
        PositionDTO positionDTO1 = new PositionDTO();
        ObjectUtil.copyProperties(positionDTO1,positionDTO);
        // 先查一下10公里以内的
        int dis = 10;
        Position position = DistanceUtil.findNeighPosition(positionDTO.getLongitude(),positionDTO.getLatitude(),dis);
        positionDTO1.setMinLat(position.getMinLat());
        positionDTO1.setMaxLat(position.getMaxLat());
        positionDTO1.setMinLng(position.getMinLng());
        positionDTO1.setMaxLng(position.getMaxLng());
        list = bCarServiceShopDao.getH5StoreList(positionDTO1);
        if(list == null || list.isEmpty()){
            // 10公里内查不到再按配置公里数查一遍
            list = bCarServiceShopDao.getH5StoreList(positionDTO);
        }
        logger.info("subject:{},list:{},size:{}","按经纬度查询门店",JsonUtil.gsonStr(list),(list == null ? 0 : list.size()));
        //if(list == null || list.isEmpty())return list;
        //list.forEach((item) -> item.setDistance(DistanceUtil.getDistance(positionDTO.getLatitude(),positionDTO.getLongitude(),item.getLatitude().doubleValue(),item.getLongitude().doubleValue())));
        //Collections.sort(list, Comparator.comparing(CarStoreVO::getDistance));
        //logger.info("subject:{},paging:{}","按经纬度查询门店按距离排序",JsonUtil.gsonStr(list));
        //list = new PagingList<>(list,positionDTO.getRows(),positionDTO.getPage()).next();
        //logger.info("subject:{},list:{},size:{}","按经纬度查询门店",JsonUtil.gsonStr(list),(list == null ? 0 : list.size()));
        return list;
    }

    @Override
    public List<CarStoreVO> getH5StoreSalesVolumeList(PositionDTO positionDTO) {
        List<CarStoreVO> list = PaginationUtil.paginationQuery(positionDTO ,(tempPositionDTO) -> bCarServiceShopDao.getRecommendCarStore(positionDTO));
        /*if(list == null || list.isEmpty())return new ArrayList<>();
        if(StringUtil.isNotEmpty(positionDTO.getLatitude())
                && StringUtil.isNotEmpty(positionDTO.getLongitude())
                && BigDecimal.valueOf(positionDTO.getLatitude()).compareTo(BigDecimal.valueOf(0)) != 0
                && BigDecimal.valueOf(positionDTO.getLongitude()).compareTo(BigDecimal.valueOf(0)) != 0){
            list.forEach((item) -> item.setDistance(DistanceUtil.getDistance(positionDTO.getLatitude(),positionDTO.getLongitude(),item.getLatitude().doubleValue(),item.getLongitude().doubleValue())));
            list = list.stream().sorted(Comparator.comparing(CarStoreVO::getSalesVolume).reversed().thenComparing(CarStoreVO::getDistance)).collect(Collectors.toList());
        }*/
        return list;
    }

    @Override
    public RecommendCarStoreVO getRecommendCarStore(PositionDTO positionDTO) {
        List<CarStoreVO> list = PaginationUtil.paginationQuery(positionDTO ,(tempPositionDTO) -> bCarServiceShopDao.getRecommendCarStore(positionDTO));
        RecommendCarStoreVO recommendCarStoreVO = new RecommendCarStoreVO();
        /*if(list == null || list.isEmpty())return recommendCarStoreVO;
        if(StringUtil.isNotEmpty(positionDTO.getLatitude())
                && StringUtil.isNotEmpty(positionDTO.getLongitude())
                && BigDecimal.valueOf(positionDTO.getLatitude()).compareTo(BigDecimal.valueOf(0)) != 0
                && BigDecimal.valueOf(positionDTO.getLongitude()).compareTo(BigDecimal.valueOf(0)) != 0){
            list.forEach((item) -> item.setDistance(DistanceUtil.getDistance(positionDTO.getLatitude(),positionDTO.getLongitude(),item.getLatitude().doubleValue(),item.getLongitude().doubleValue())));
            Collections.sort(list, Comparator.comparing(CarStoreVO::getDistance));
        }*/
        recommendCarStoreVO.setCarStoreVOList(list);
        return recommendCarStoreVO;
    }

    @Override
    public RecommendCarStoreVO getHomePageCarStore(PositionDTO positionDTO) {
        List<CarStoreVO> list = bCarServiceShopDao.getHomePageCarStore(positionDTO);
        RecommendCarStoreVO recommendCarStoreVO = new RecommendCarStoreVO();
        recommendCarStoreVO.setCarStoreVOList(list);
        return recommendCarStoreVO;
    }

    @Override
    public List<Map<String, Object>> getPriorityCarStore(String storeName, Pagination pagination) {
        return PaginationUtil.paginationQuery(pagination, storeName, (tempStoreName) ->
                bCarServiceShopDao.getPriorityCarStore(tempStoreName));
    }

    @Override
    public Integer updatePriorityCarStore(String carStoreIds, int priority, Integer userId) {
        return bCarServiceShopDao.updatePriorityCarStore(carStoreIds, priority, userId);
    }
}
