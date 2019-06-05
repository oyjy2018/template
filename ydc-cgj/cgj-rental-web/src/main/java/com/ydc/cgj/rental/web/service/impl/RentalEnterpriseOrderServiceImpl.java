package com.ydc.cgj.rental.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.feignService.RentalEnterpriseOrderFeignService;
import com.ydc.cgj.rental.web.service.RentalEnterpriseOrderService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.enums.rental.RentalFeeVoucherEnum;
import com.ydc.commom.enums.rental.RentalSettlementEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.ObjectUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;
import com.ydc.model.cgj.rental.RentalFeeVoucher;
import com.ydc.model.cgj.rental.RentalOrderFile;
import com.ydc.model.util.DateUtil;
import com.ydc.model.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author
 * @create 2019-01-04 16:36
 **/
@Service
public class RentalEnterpriseOrderServiceImpl implements RentalEnterpriseOrderService {

    @Autowired
    RentalEnterpriseOrderFeignService rentalEnterpriseOrderFeignService;
    @Autowired
    DictionaryDetailServiceImpl dictionaryDetailService;

    @Override
    public String getPCRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto) {
        return rentalEnterpriseOrderFeignService.getPCRentalEnterpriseOrderList(dto);
    }

    @Override
    public String getCarSeries(CommCarSeriesDTO dto) {
        return rentalEnterpriseOrderFeignService.getCarSeries(dto);
    }

    @Override
    public String getCarModel(CommCarSeriesDTO dto) {
        return rentalEnterpriseOrderFeignService.getCarModel(dto);
    }

    @Override
    public String getPCRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.getPCRentalEnterpriseOrderId(dto);
    }

    @Override
    public String insertPCRentalEnterpriseOrderFile(RentalOrderFile rentalOrderFile) {
        rentalOrderFile.setStatus(DeleteStatusConstant.STATUS_NOT_DELETE.toString());
        rentalOrderFile.setCreateTime(new Date());
        rentalOrderFile.setCreateBy(WebShiroTokenManager.getUser().getId());
        String fileName = rentalOrderFile.getFileName();
        rentalOrderFile.setFileName(fileName.substring(0,fileName.lastIndexOf(".")));
        rentalOrderFile.setFileType(fileName.substring(fileName.lastIndexOf("."),fileName.length()));
        return rentalEnterpriseOrderFeignService.insertPCRentalEnterpriseOrderFile(rentalOrderFile);
    }

    @Override
    public String getPCRentalEnterpriseOrderInit() {
        return rentalEnterpriseOrderFeignService.getPCRentalEnterpriseOrderInit();
    }

    @Override
    public String getRentalOrderFiles(RentalOrderFile rentalOrderFile) {
        if(StringUtil.isEmpty(rentalOrderFile.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        if(StringUtil.isEmpty(rentalOrderFile.getFileCode())){
            return Result.failure("fileCode不能为空").toJSON();
        }
        rentalOrderFile.setStatus(DeleteStatusConstant.STATUS_NOT_DELETE.toString());
        return rentalEnterpriseOrderFeignService.getRentalOrderFiles(rentalOrderFile);
    }

    @Override
    public String deleteRentalEnterpriseOrderFile(RentalOrderFile rentalOrderFile) {
        if(StringUtil.isEmpty(rentalOrderFile.getId())){
            return Result.failure("文件id为空").toJSON();
        }
        rentalOrderFile.setStatus(DeleteStatusConstant.STATUS_DELETE.toString());
        rentalOrderFile.setUpdateTime(new Date());
        rentalOrderFile.setUpdateBy(WebShiroTokenManager.getUser().getId());
        return rentalEnterpriseOrderFeignService.deleteRentalEnterpriseOrderFile(rentalOrderFile);
    }

    @Override
    public String paymentDepositConfiguration() {
        Map<String,Object> param = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paymentModeId",RentalSettlementEnum.STATUS2.getSettleWay());
        jsonObject.put("paymentModeName",RentalSettlementEnum.STATUS2.getSettleWayCH());
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("paymentModeId",RentalSettlementEnum.STATUS3.getSettleWay());
        jsonObject.put("paymentModeName",RentalSettlementEnum.STATUS3.getSettleWayCH());
        jsonArray.add(jsonObject);
        param.put("paymentMode",jsonArray);
//        param.put("paymentMode",Arrays.asList(Maps.newHashMap(RentalSettlementEnum.STATUS2.getSettleWay(),RentalSettlementEnum.STATUS2.getSettleWayCH()),
//                Maps.newHashMap(RentalSettlementEnum.STATUS3.getSettleWay(),RentalSettlementEnum.STATUS3.getSettleWayCH())));
//        Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_ENTERPRISE_CAR_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)
//                .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_ENTERPRISE_CAR_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)));
//        param.put("actualAmount",dic.isPresent() ? dic.get().getDictValue() : 0);
        param.put("accountTime",DateUtil.formatDate(new Date()));
        return Result.success(param).toJSON();
    }

    @Override
    public String paymentDeposit(RentalDepositDTO dto) {
        dto.setUserId(WebShiroTokenManager.getUser().getId());
        return rentalEnterpriseOrderFeignService.paymentDeposit(dto);
    }

    @Override
    public String refundDepositConfiguration() {
        Map<String,Object> param = new HashMap<>();
        param.put("actualRefundTime",DateUtil.formatDate(new Date()));
        return Result.success(param).toJSON();
    }

    @Override
    public String refundDeposit(RentalDepositDTO dto) {
        dto.setUserId(WebShiroTokenManager.getUser().getId());
        return rentalEnterpriseOrderFeignService.refundDeposit(dto);
    }

    @Override
    public String insertRentPayment(RentalFeeVoucherDTO dto) {
        RentalFeeVoucher record = new RentalFeeVoucher();
        record.setOrderId(dto.getOrderId());
        record.setVoucherType(RentalFeeVoucherEnum.VoucherType.STATUS1.getStatus());
        record.setOneMoney(dto.getOneMoney());
        record.setTwoMoney(dto.getTwoMoney());
        record.setPayTime(dto.getPayTime());
        record.setPayVoucherImgName(dto.getPayVoucherImgName());
        record.setPayVoucherImgUrl(dto.getPayVoucherImgUrl());
        record.setAccountTime(dto.getAccountTime());
        record.setAccountVoucherImgName(dto.getAccountVoucherImgName());
        record.setAccountVoucherImgUrl(dto.getAccountVoucherImgUrl());
        record.setCreateTime(new Date());
        record.setCreateBy(WebShiroTokenManager.getUser().getId());
        return rentalEnterpriseOrderFeignService.insertRentPayment(record);
    }

    @Override
    public String insertRentalCheckComeCar(List<RentalCheckCarDTO> list) {
        Date date = new Date();
        int userId = WebShiroTokenManager.getUser().getId();
        String userName = WebShiroTokenManager.getUser().getUserName();
        list.forEach(item ->{
            item.setComeCarPerson(userName);
            item.setComeCarPersonId(userId);
            item.setComeCarTime(date);
            item.setCreateBy(userId);
            item.setCreateTime(date);
        });
        return rentalEnterpriseOrderFeignService.insertRentalCheckComeCar(list);
    }

    @Override
    public String insertRentTransfer(RentalFeeVoucherDTO dto) {
        RentalFeeVoucher record = new RentalFeeVoucher();
        record.setOrderId(dto.getOrderId());
        record.setVoucherType(RentalFeeVoucherEnum.VoucherType.STATUS2.getStatus());
        record.setOneMoney(dto.getOneMoney());
        record.setTwoMoney(dto.getTwoMoney());
        record.setPayTime(dto.getPayTime());
        record.setPayVoucherImgName(dto.getPayVoucherImgName());
        record.setPayVoucherImgUrl(dto.getPayVoucherImgUrl());
        record.setAccountTime(dto.getAccountTime());
        record.setAccountVoucherImgName(dto.getAccountVoucherImgName());
        record.setAccountVoucherImgUrl(dto.getAccountVoucherImgUrl());
        record.setCreateTime(new Date());
        record.setCreateBy(WebShiroTokenManager.getUser().getId());
        return rentalEnterpriseOrderFeignService.insertRentTransfer(record);
    }

    @Override
    public String getRentalCheckRepayCar(RentalCheckCarDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.getRentalCheckRepayCar(dto);
    }

    @Override
    public String insertRentalCheckRepayCar(List<RentalCheckCarDTO> list) {
        Date date = new Date();
        User u = WebShiroTokenManager.getUser();
        int userId = u.getId();
        String userName = u.getUserName();
        list.forEach(item ->{
            item.setRepayCarPerson(userName);
            item.setRepayCarPersonId(userId);
            item.setRepayCarTime(date);
            item.setCreateBy(userId);
            item.setCreateTime(date);
        });
        return rentalEnterpriseOrderFeignService.insertRentalCheckRepayCar(list);
    }

    @Override
    public String enterpriseSettlementConfiguration() {
        Map<String,Object> param = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("settleWayId",RentalSettlementEnum.STATUS1.getSettleWay());
        jsonObject.put("settleWayName",RentalSettlementEnum.STATUS1.getSettleWatPC());
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("settleWayId",RentalSettlementEnum.STATUS2.getSettleWay());
        jsonObject.put("settleWayName",RentalSettlementEnum.STATUS2.getSettleWatPC());
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("settleWayId",RentalSettlementEnum.STATUS3.getSettleWay());
        jsonObject.put("settleWayName",RentalSettlementEnum.STATUS3.getSettleWatPC());
        jsonArray.add(jsonObject);
        param.put("settleWay",jsonArray);
        param.put("settleDate",DateUtil.formatDate(new Date()));
        return Result.success(param).toJSON();
    }

    @Override
    public String insertEnterpriseSettlement(RentalEnterpriseSettlementDTO dto) {
        User u = WebShiroTokenManager.getUser();
        int userId = u.getId();
        String userName = u.getUserName();
        RentalEnterpriseSettlement rentalEnterpriseSettlement = new RentalEnterpriseSettlement();
        ObjectUtil.copyProperties(rentalEnterpriseSettlement,dto);
//        rentalEnterpriseSettlement.setOrderId(dto.getOrderId());
//        rentalEnterpriseSettlement.setDemandSideId(dto.getDemandSideId());
//        rentalEnterpriseSettlement.setSettleMoney(dto.getSettleMoney());
//        rentalEnterpriseSettlement.setSettleWay(dto.getSettleWay());
//        rentalEnterpriseSettlement.setSettleDate(dto.getSettleDate());
//        rentalEnterpriseSettlement.setVoucherImgName(dto.getVoucherImgName());
//        rentalEnterpriseSettlement.setVoucherImgUrl(dto.getVoucherImgUrl());
        rentalEnterpriseSettlement.setSettleStatus(1);
        rentalEnterpriseSettlement.setSettleUserId(userId);
        rentalEnterpriseSettlement.setSettleUserName(userName);
        rentalEnterpriseSettlement.setCreateTime(new Date());
        rentalEnterpriseSettlement.setCreateBy(userId);
        return rentalEnterpriseOrderFeignService.insertEnterpriseSettlement(rentalEnterpriseSettlement);
    }

    @Override
    public String insertDepositRefund(List<RentalFeeVoucherDTO> list) {
        Date date = new Date();
        User u = WebShiroTokenManager.getUser();
        int userId = u.getId();
        list.forEach(item ->{
            item.setCreateTime(date);
            item.setCreateBy(userId);
        });
        list.get(0).setVoucherType(RentalFeeVoucherEnum.VoucherType.STATUS3.getStatus());
        list.get(1).setVoucherType(RentalFeeVoucherEnum.VoucherType.STATUS4.getStatus());
        return rentalEnterpriseOrderFeignService.insertDepositRefund(list);
    }

    @Override
    public String unfreezeAlipay(RentalEnterpriseOrderDTO dto) {
        return rentalEnterpriseOrderFeignService.unfreezeAlipay(dto);
    }

    @Override
    public String getRentalEnterpriseOrderFile(RentalEnterpriseOrderDTO dto) {
        if(StringUtil.isEmpty(dto.getOrderId())){
            return Result.failure("orderId不能为空").toJSON();
        }
        return rentalEnterpriseOrderFeignService.getRentalEnterpriseOrderFile(dto);
    }

    @Override
    public String getRentalCar(RentalCar rentalCar) {
        if(StringUtil.isEmpty(rentalCar.getStoreId())){
            return Result.failure("车辆门店id不能为空").toJSON();
        }
        rentalCar.setStatus(CommCarEnum.RentalCarStatusEnum.PUBLISH_SUCCESS.getCode());
        return rentalEnterpriseOrderFeignService.getRentalCar(rentalCar);
    }
}
