package com.ydc.service.store.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.CommodityConstant;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CommodityDTO;
import com.ydc.commom.view.dto.cgj.CommodityShowDTO;
import com.ydc.commom.view.dto.cgj.StoreReqDTO;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.store.CommodityDao;
import com.ydc.dao.cgj.store.CommodityImgDao;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.model.cgj.*;
import com.ydc.service.store.service.CommodityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

    private static Logger logger = LogManager.getLogger(CommodityServiceImpl.class);

    @Autowired
    protected CommodityDao commodityDao;
    @Autowired
    private CommodityModelDao commodityModelDao;
    @Autowired
    private CommodityImgDao commodityImgDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commodityDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Commodity record) {
        return commodityDao.insert(record);
    }

    @Override
    public int insertSelective(Commodity record) {
        return commodityDao.insertSelective(record);
    }

    @Override
    public Commodity selectByPrimaryKey(Integer id) {
        return commodityDao.selectByPrimaryKey(id);
    }

    @Override
    public Commodity selectByTitle(String title) {
        return commodityDao.selectByTitle(title);
    }

    @Override
    public int updateByPrimaryKeySelective(Commodity record) {
        return commodityDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Commodity record) {
        return commodityDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getCommodityList(CommodityDTO commodityDTO) {
        return commodityDao.getCommodityList(commodityDTO);
    }

    @Override
    public Map<String, Object> getCommodityListCount(CommodityDTO commodityDTO) {
        return commodityDao.getCommodityListCount(commodityDTO);
    }

    @Override
    public Integer saveOrUpdateCommodity(StoreReqDTO srb) {
        logger.info("新增或修改商品,commodity:"+srb.getCommodity()+",commodityModels:"+srb.getCommodityModels()+",commodityImgs:"+srb.getCommodityImgs());
        Map<String, String> ret = new HashMap<>();
        Commodity commodity = null;
        List<CommodityModel> commodityModels = new ArrayList<>();
        List<Map<String, String>> commodityModelList = srb.getCommodityModels();
        List<CommodityImg> commodityImgs = null;
        List<Map<String, String>> commodityImgList = srb.getCommodityImgs();
        try {
            commodity = new Commodity(srb.getCommodity());

            for(Map<String, String> commodityModel:commodityModelList){
                commodityModels.add(new CommodityModel(commodityModel));
            }

            if(commodityImgList != null){
                commodityImgs = new ArrayList<>();
                for(Map<String, String> commodityImg:commodityImgList){
                    commodityImgs.add(new CommodityImg(commodityImg));
                }
            }
        } catch (Exception e) {
            throw new ServiceRuntimeException("封装商品对象异常",e);
        }

        Integer userId = Integer.valueOf(srb.getUserId());
        Date sysDate = new Date();

        if (commodity.getId() == null || commodity.getId() == 0) {// 保存
            logger.info("------》新增商品信息");
            commodity.setHasShoutui(0);
            commodity.setReleaseStatus(0);
            commodity.setSoldNumber(0);
            commodity.setSoldNumberSham(0);
            commodity.setStatus(1);
            commodity.setCreateTime(sysDate);
            commodity.setCreateBy(userId);
            commodity.setUpdateBy(userId);

            int totalInventory = 0;
            List<BigDecimal> sellPriceList = new ArrayList<>();
            List<BigDecimal> marketPriceList = new ArrayList<>();
            for (CommodityModel model : commodityModels) {
                totalInventory += model.getInventory();
                sellPriceList.add(model.getSellPrice());
                if(model.getMarketPrice() != null)
                    marketPriceList.add(model.getMarketPrice());
            }
            Collections.sort(sellPriceList);
            commodity.setLowSellPrice(sellPriceList.get(0));
            commodity.setHighSellPrice(sellPriceList.get(sellPriceList.size()-1));

            if(marketPriceList.size() > 1){
                Collections.sort(marketPriceList);
                commodity.setLowMarketPrice(marketPriceList.get(0));
                commodity.setHighMarketPrice(marketPriceList.get(marketPriceList.size()-1));
            }
            commodity.setTotalInventory(totalInventory);
            insert(commodity);

            for (CommodityModel model : commodityModels) {
                model.setCommodityId(commodity.getId());
                model.setStatus(1);
                model.setVersion(0);
                model.setCreateTime(sysDate);
                model.setCreateBy(userId);
                model.setUpdateBy(userId);
            }
            commodityModelDao.batchInsert(commodityModels);
            for (CommodityImg img : commodityImgs) {
                img.setCommodityId(commodity.getId());
                img.setStatus(1);
                img.setCreateTime(sysDate);
                img.setCreateBy(userId);
            }
            commodityImgDao.batchInsert(commodityImgs);
        } else {//修改
            logger.info("------》修改商品信息");
            commodity.setUpdateBy(userId);
            int totalInventory = 0;
            List<BigDecimal> sellPrices = new ArrayList<>();
            List<BigDecimal> marketPriceList = new ArrayList<>();
            for(CommodityModel model:commodityModels){
                model.setCommodityId(commodity.getId());
                model.setStatus(1);
                model.setVersion(0);
                if(model.getId() == null || model.getId() == 0){
                    model.setCreateTime(sysDate);
                    model.setCreateBy(userId);
                }
                model.setUpdateBy(userId);
                model.setUpdateTime(sysDate);
                totalInventory += model.getInventory();
                sellPrices.add(model.getSellPrice());
                if(model.getMarketPrice() != null)
                    marketPriceList.add(model.getMarketPrice());
            }
            /*for(CommodityImg img:commodityImgs){
                img.setCommodityId(commodity.getId());
                img.setStatus(1);
                if(img.getId() == null || img.getId() == 0){
                    img.setCreateTime(sysDate);
                    img.setCreateBy(srb.getUserId());
                }
            }*/
            commodity.setTotalInventory(totalInventory);
            Collections.sort(sellPrices);
            commodity.setLowSellPrice(sellPrices.get(0));
            commodity.setHighSellPrice(sellPrices.get(sellPrices.size()-1));

            if(marketPriceList.size() > 1){
                Collections.sort(marketPriceList);
                commodity.setLowMarketPrice(marketPriceList.get(0));
                commodity.setHighMarketPrice(marketPriceList.get(marketPriceList.size()-1));
            }
            updateByPrimaryKeySelective(commodity);
            commodityModelDao.batchInsertOrUpdate(commodityModels);
            /*commodityImgDao.batchInsertOrUpdate(commodityImgs);*/
            if(srb.getDelCommodityModelIds() != null && !"".equals(srb.getDelCommodityModelIds())){
                commodityModelDao.deleteByCommodityModelIds(srb.getDelCommodityModelIds());
            }
        }
        return commodity.getId();
    }

    @Override
    public int updateHasShoutuiByIds(StoreReqDTO srd) {
        logger.info("首页推荐,commodityIds:"+srd.getCommodityIds()+",hasShoutui:"+srd.getHasShoutui()+",userId"+srd.getUserId());
        String commodityIds = srd.getCommodityIds();
        Integer hasShoutui = Integer.valueOf(srd.getHasShoutui());
        Integer userId = Integer.valueOf(srd.getUserId());
        return commodityDao.updateHasShoutuiByIds(commodityIds, hasShoutui, userId);
    }

    @Override
    public Result updateReleaseStatusByIds(StoreReqDTO srd) {
        logger.info("更新发布状态,commodityIds:"+srd.getCommodityIds()+",releaseStatus:"+srd.getReleaseStatus()+",userId"+srd.getUserId());
        Date releaseDate = null;
        Date shelveTime = null;
        String commodityIds = srd.getCommodityIds();
        Integer releaseStatus = Integer.valueOf(srd.getReleaseStatus());
        Integer userId = Integer.valueOf(srd.getUserId());
        if (releaseStatus == 1) {
            List<Commodity> commodityList = selectZeroInventoryByIds(commodityIds);
            if(commodityList != null && commodityList.size() > 0){
                StringBuffer sb = new StringBuffer();
                sb.append("商品ID：");
                for(Commodity commodity:commodityList){
                    sb.append(commodity.getId()).append(",");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.append(" 商品总库存为0，不可发布");
                return Result.getResult(ResultConstant.FUNCTION_CODE_FAILURE, sb.toString());
            }
            releaseDate = new Date();
        } else if (releaseStatus == 2) {
            shelveTime = new Date();
        }
        int count = commodityDao.updateReleaseStatusByIds(commodityIds, releaseStatus, releaseDate, shelveTime, srd.getUserName(), userId);
        return Result.getResult(ResultConstant.FUNCTION_CODE_SUCCESS, "");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CommodityShowDTO> getCommodityList(String title, Pagination pagination) {
        List<Integer> commodityIds = PaginationUtil.paginationQuery(pagination, title, (tempTitle) ->
                commodityDao.getCommodityListByTitle(tempTitle));
        return getCommodityShowDTOList(commodityIds);
    }

    @Override
    public List<CommodityShowDTO> getHomePageCommodityList(int commodityNum) {
        return getCommodityShowDTOList(commodityDao.getHomePageCommodityList(commodityNum));
    }

    //获取商品展示信息
    private List<CommodityShowDTO> getCommodityShowDTOList(List<Integer> commodityIds){
        List<CommodityShowDTO> commodityShowDTOs = new ArrayList<>();
        if (commodityIds == null || commodityIds.size() <= 0){
            return commodityShowDTOs;
        }
        Map<String, Object> map = new HashMap<>(); //存入缓存的map
        Map<String, CommodityShowDTO> redisCommodity = (Map<String, CommodityShowDTO>)RedisUtil.redisHashGet(CommodityConstant.COMMODITY_REDIS_KEY); //redis中的商品
        commodityIds.forEach(commodityId -> {
            CommodityShowDTO commodityShowDTO = redisCommodity.get(String.valueOf(commodityId));
            //缓存没有就从数据库里取
            if (commodityShowDTO == null) {
                commodityShowDTO = getCommodityDetailById(commodityId);
                map.put(String.valueOf(commodityId), commodityShowDTO);
            }
            commodityShowDTOs.add(commodityShowDTO);
        });
        //更新缓存
        if (map.size() > 0) {
            RedisUtil.redisHashPut(CommodityConstant.COMMODITY_REDIS_KEY, map);
        }
        return commodityShowDTOs;
    }

    @Override
    public CommodityShowDTO getCommodityDetailRedis(Integer commodityId) {
        CommodityShowDTO commodityShowDTO = (CommodityShowDTO)RedisUtil.redisHashGet(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodityId));
        if (commodityShowDTO != null) {
            //销量和库存取数据库的
            Commodity commodity = commodityDao.selectByPrimaryKey(commodityId);
            commodityShowDTO.setSoldNumber(commodity.getSoldNumber());
            commodityShowDTO.setTotalInventory(commodity.getTotalInventory());
        }else{
            commodityShowDTO = getCommodityDetailById(commodityId);
            RedisUtil.redisHashPut(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodityId), commodityShowDTO);
        }
        return commodityShowDTO;
    }

    @Override
    public CommodityShowDTO getCommodityDetailById(Integer commodityId) {
        Commodity commodity = commodityDao.selectByPrimaryKey(commodityId);
        List<CommodityImg> commodityImgs = commodityImgDao.getCommodityImgByCommodityId(commodityId);
        CommodityShowDTO commodityShowDTO = new CommodityShowDTO();
        commodityShowDTO.setCommodityId(commodityId);
        if (commodity != null) {
            commodityShowDTO.setCommodityTitle(commodity.getTitle());
            commodityShowDTO.setCommodityDescription(commodity.getDescription());
            commodityShowDTO.setLowSellPrice(commodity.getLowSellPrice());
            commodityShowDTO.setHighSellPrice(commodity.getHighSellPrice());
            commodityShowDTO.setLowMarketPrice(commodity.getLowMarketPrice());
            commodityShowDTO.setHighMarketPrice(commodity.getHighMarketPrice());
            commodityShowDTO.setSoldNumber(commodity.getSoldNumber());
            commodityShowDTO.setSoldNumberSham(commodity.getSoldNumberSham());
            commodityShowDTO.setTotalInventory(commodity.getTotalInventory());
            commodityShowDTO.setSupplierCode(commodity.getSupplierCode());
            commodityShowDTO.setSupplierName(commodity.getSupplierName());
        }
        if (commodityImgs != null) {
            List<Map<String, String>> headPic = getMapPic(commodityImgs, CommodityConstant.COMMODITY_IMG_TYPE_1);
            List<Map<String, String>> carouselPic = getMapPic(commodityImgs, CommodityConstant.COMMODITY_IMG_TYPE_2);
            List<Map<String, String>> describePic = getMapPic(commodityImgs, CommodityConstant.COMMODITY_IMG_TYPE_3);
            commodityShowDTO.setHeadPic(headPic);
            commodityShowDTO.setCarouselPic(carouselPic);
            commodityShowDTO.setDescribePic(describePic);
        }
        return commodityShowDTO;
    }

    @Override
    public List<Commodity> selectZeroInventoryByIds(String commodityIds) {
        return commodityDao.selectZeroInventoryByIds(commodityIds);
    }

    @Override
    public int updateTotalInventoryById(Integer commodityId) {
        return commodityDao.updateTotalInventoryById(commodityId);
    }

    //商品图片的路径处理
    private List<Map<String, String>> getMapPic(List<CommodityImg> commodityImgs, int imgType) {
        List<Map<String, String>> list = new ArrayList<>();
        commodityImgs.stream()
                .filter(commodityImg -> imgType == commodityImg.getImgType())
                .forEach(commodityImg -> {
                    try {
                        Map<String, String> map = new HashMap<>();
                        map.put(CommodityConstant.COMMODITY_IMG_NORMAL, FileUtil.getBrowseFile(commodityImg.getFileUrl(), commodityImg.getFileName()));
                        map.put(CommodityConstant.COMMODITY_IMG_LITTLE, FileUtil.getBrowseFile(commodityImg.getLittleFileUrl(), commodityImg.getLittleFileName()));
                        list.add(map);
                    }catch (Exception e){
                        logger.error("subject:{},e:{}","获取图片在线浏览地址异常",e);
                    }
                });
        return list;
    }

    @Override
    public List<Map<String, Object>> getPriorityCommodityList(String title, Pagination pagination) {
        return PaginationUtil.paginationQuery(pagination, title, (tempTitle) ->
                commodityDao.getPriorityCommodityList(tempTitle));
    }

    @Override
    public Integer updatePriority(String commodityIds, int priority, Integer userId) {
        return commodityDao.updatePriority(commodityIds, priority, userId);
    }

    @Override
    public Map<String, Object> getRollCommodity(String sonClassifyCode) {
        Map<String, Object> map = commodityDao.getRollCommodity(sonClassifyCode);
        if (map != null){
            DictionaryDetail validDays = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.ROLL_VALID_DAYS, DictionaryConstant.PARENT_CODE_ROLL).orElseGet(() ->new DictionaryDetail());
            DictionaryDetail validTime = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.ROLL_VALID_TIME, DictionaryConstant.PARENT_CODE_ROLL).orElseGet(() ->new DictionaryDetail());
            map.put("rollValidTime", StringUtil.isEmpty(validTime.getDictValue()) ? (validDays.getDictValue() + validDays.getRemark2()) : (validTime.getDictValue()));
        }
        return map;
    }
}
