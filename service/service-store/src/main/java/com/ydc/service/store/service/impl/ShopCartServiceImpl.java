package com.ydc.service.store.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.MapUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.shopCart.MemberShopCartDTO;
import com.ydc.commom.view.dto.cgj.shopCart.ShopCartCommodityDTO;
import com.ydc.dao.cgj.store.CommodityDao;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.dao.cgj.store.StoreShopCartCommodityDao;
import com.ydc.model.cgj.Commodity;
import com.ydc.model.cgj.CommodityModel;
import com.ydc.model.cgj.StoreShopCartCommodity;
import com.ydc.service.store.service.ShopCartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ShopCartServiceImpl implements ShopCartService {
    private static Logger logger=LogManager.getLogger(ShopCartServiceImpl.class);

    @Resource
    StoreShopCartCommodityDao storeShopCartCommodityDao;
    @Resource
    CommodityModelDao commodityModelDao;
    @Resource
    CommodityDao commodityDao;

    /**
     *  根据会员ID 查询用户购物车
     */

   public MemberShopCartDTO getMemberShopCartDTO(Integer memberId){
       logger.info("memberId:"+memberId);
       Map<String,Object> param=new HashMap<>();
       param.put("memberId",memberId);
       List<ShopCartCommodityDTO> validShopCartList= storeShopCartCommodityDao.getMemberValidationShopCartCommodity(param);
       validShopCartList=addFileUrl(validShopCartList);
       List<ShopCartCommodityDTO> invalidShopCartList=storeShopCartCommodityDao.getMemberInvalidationShopCartCommodity(param);
       invalidShopCartList=addFileUrl(invalidShopCartList);
       Map<String,Object> numMap=storeShopCartCommodityDao.getMemberShopCartNum(param);
       MemberShopCartDTO memberShopCartDTO=new MemberShopCartDTO();
        if (null!=numMap){
            memberShopCartDTO.setTotalCommodityNum(MapUtil.getMapValueToInt(numMap,"totalCommodityNum"));
            memberShopCartDTO.setTotalSellPrice(MapUtil.getMapValueToBigDecimal(numMap,"totalSellPrice"));
            memberShopCartDTO.setTotalMarketPrice(MapUtil.getMapValueToBigDecimal(numMap,"totalMarketPrice"));
        }
       memberShopCartDTO.setShopCartCommodityDTOS(validShopCartList);
       memberShopCartDTO.setInvalidationShopCartCommodityDTOS(invalidShopCartList);
       return memberShopCartDTO;
   }
   private  List<ShopCartCommodityDTO> addFileUrl(List<ShopCartCommodityDTO> shopCartCommodityDTOS){
       List<ShopCartCommodityDTO> shopCartCommodityDTOList=new ArrayList<>();
       if (null==shopCartCommodityDTOS || shopCartCommodityDTOS.isEmpty()){
           return new ArrayList<>();
       }

       for (ShopCartCommodityDTO shopCartCommodityDTO: shopCartCommodityDTOS){
           if (StringUtil.isEmpty(shopCartCommodityDTO.getLittleFileUrl()) || StringUtil.isEmpty(shopCartCommodityDTO.getLittleFileName())){
               shopCartCommodityDTO.setLittleFileUrl(null);
           }else {
               try {
                   shopCartCommodityDTO.setLittleFileUrl(FileUtil.getBrowseFile(shopCartCommodityDTO.getLittleFileUrl(),shopCartCommodityDTO.getLittleFileName()));
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
           if (StringUtil.isEmpty(shopCartCommodityDTO.getFileName()) || StringUtil.isEmpty(shopCartCommodityDTO.getFileUrl())){
               shopCartCommodityDTO.setFileUrl(null);
           }else {
               try {
                   shopCartCommodityDTO.setFileUrl(FileUtil.getBrowseFile(shopCartCommodityDTO.getFileUrl(),shopCartCommodityDTO.getFileName()));
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }

           shopCartCommodityDTOList.add(shopCartCommodityDTO);
       }
       return shopCartCommodityDTOList;
   }

    @Override
    public int updateMemberShopCart() {
        return 0;
    }


    @Transactional(value="cgjTransactionManager")
    @Override
    public int deleteMemberShopCart(Integer memberId, List<Integer> ids) {
       logger.info("删除购物车  memberId:"+memberId +"ids"+ids.toString());
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("memberId",memberId);
        paramMap.put("ids",ids);
        paramMap.put("deleteStatus",0);
       int count= storeShopCartCommodityDao.updateByIds(paramMap);
       if (ids.size()!=count){
           throw  new ServiceRuntimeException("购物车失效，请刷新");
       }
       return  count;
    }

    @Override
    public Result addMemberShopCart(Integer memberId, Integer commodityId, Integer modelId, Integer number,String type) {
        logger.info("memberId:"+memberId+" commodityId:"+ commodityId+" modelId:"+  modelId+" number"+  number+" type:"+type);
       Result result=new Result();
       //根据模型查询商品
        CommodityModel commodityModel= commodityModelDao.selectByPrimaryKey(modelId);
        if (null==commodityModel){
           logger.info("商品不存在");
            return Result.failure("商品不存在");
        }
        if (StringUtil.isEmpty(commodityModel.getInventory())){
           logger.info("库存不足");
            return Result.failure("库存不足");
        }
        if (commodityModel.getInventory().compareTo(number)<0){
           logger.info("库存不足");
            return Result.failure("库存不足");
        }
        Commodity commodity=commodityDao.selectByPrimaryKey(commodityId);
        if (null==commodity || StringUtil.isEmpty(commodity.getSupplierCode()) ||StringUtil.isEmpty(commodity.getSupplierName()) ){
            return Result.failure("商品不存在");
        }
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("memberId",memberId);
        paramMap.put("commodityModelId",modelId);
        paramMap.put("commodityId",commodityId);
        paramMap.put("deleteStatus",1);
        List<StoreShopCartCommodity> storeShopCartCommodities= storeShopCartCommodityDao.selectStoreShopCart(paramMap);
        StoreShopCartCommodity storeShopCartCommodity;
        if (null!=storeShopCartCommodities && !storeShopCartCommodities.isEmpty()){
            //更新
            storeShopCartCommodity=storeShopCartCommodities.get(0);
            if (type.equals("+")){
                number=number+storeShopCartCommodity.getCommodityNumber();
            }
            if (commodityModel.getInventory().compareTo(number)<0){
                logger.info("库存不足");
                return Result.failure("库存不足");
            }
            storeShopCartCommodity.setSupplierCode(commodity.getSupplierCode());
            storeShopCartCommodity.setSupplierName(commodity.getSupplierName());
            storeShopCartCommodity.setCommodityNumber(number);
           logger.info("更新数量 number："+number);
            storeShopCartCommodityDao.updateByPrimaryKeySelective(storeShopCartCommodity);
           logger.info("更新成功");
            return Result.success("更新成功");
        }else {
            //添加
            storeShopCartCommodity=new StoreShopCartCommodity();
            storeShopCartCommodity.setCommodityNumber(number);
            storeShopCartCommodity.setCommodityId(commodityId);
            storeShopCartCommodity.setCommodityModelId(modelId);
            storeShopCartCommodity.setMemberId(memberId);
            storeShopCartCommodity.setSupplierCode(commodity.getSupplierCode());
            storeShopCartCommodity.setSupplierName(commodity.getSupplierName());
            storeShopCartCommodity.setDeleteStatus(1);
            storeShopCartCommodity.setUpdateTime(new Date());
            storeShopCartCommodity.setUpdateBy(memberId);
            storeShopCartCommodity.setCreateBy(memberId);
            storeShopCartCommodity.setCreateTime(new Date());
           logger.info("更新数量 number："+number);
            storeShopCartCommodityDao.insertSelective(storeShopCartCommodity);
           logger.info("更新成功");
            return Result.success("添加成功");
        }
    }

    @Override
    public Result updateMemberShopCartNumber(Integer id, Integer memberId,Integer number) {
        logger.info("memberId:"+memberId+" id:"+ id+ "number"+  number);
        StoreShopCartCommodity storeShopCartCommodity= storeShopCartCommodityDao.selectByPrimaryKey(id);

        if (null ==storeShopCartCommodity){
            logger.info("商品为空");
            return Result.failure("购物车失效，请刷新");
        }

        if (storeShopCartCommodity.getMemberId().compareTo(memberId)!=0){
            logger.info("用户不匹配");
            return Result.failure("用户不匹配");
        }

        //根据模型查询商品
        CommodityModel commodityModel= commodityModelDao.selectByPrimaryKey(storeShopCartCommodity.getCommodityModelId());
        if (null==commodityModel){
            logger.info("商品不存在");
            return Result.failure("商品不存在");
        }
        if (StringUtil.isEmpty(commodityModel.getInventory())){
            logger.info("库存不足");
            return Result.failure("库存不足");
        }
        if (commodityModel.getInventory().compareTo(number)<0){
           logger.info("库存不足");
            return Result.failure("库存不足");
        }
        storeShopCartCommodity.setCommodityNumber(number);
        storeShopCartCommodity.setUpdateBy(memberId);
        storeShopCartCommodity.setUpdateTime(new Date());
        int count=storeShopCartCommodityDao.updateByPrimaryKeySelective(storeShopCartCommodity);
        logger.info(count);
        return Result.success();
    }

    @Override
    public int getMemberShopCartCount(Integer memberId) {
        return 0;
    }


}
