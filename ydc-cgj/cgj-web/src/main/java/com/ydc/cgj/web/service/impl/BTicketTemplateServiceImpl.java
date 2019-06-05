package com.ydc.cgj.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.cgj.web.feignService.ITicketTemplateFeignService;
import com.ydc.cgj.web.service.BTicketTemplateService;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.entity.TicketTemplateEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-10-31 15:27
 **/
@Service
public class BTicketTemplateServiceImpl implements BTicketTemplateService {

    private static final Logger logger = LogManager.getLogger(BTicketTemplateService.class);

    @Autowired
    ITicketTemplateFeignService iTicketTemplateFeignService;

    @Override
    public String getTicketTemplateList(RollTemplateDTO rollTemplateDTO) {
        return iTicketTemplateFeignService.getTicketTemplateList(rollTemplateDTO);
    }

    @Override
    public String refreshTicketTemplate(RollTemplateDTO rollTemplateDTO) {
        try {
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("key","nuhcuienncuewfe");
            /*dataMap.put("channel","15156165");*/
            String response = UrlHttpUtil.doPost(SystemPropertiesConfig.B_SERVICE_URL+"yhqg/temp/list",dataMap,null);
            logger.info("subject:{},response:{}","刷新券模板响应",response);
            JSONObject jsonObject = JSON.parseObject(response);
            if(Integer.valueOf(jsonObject.get("status").toString()).intValue() != 1){
                return Result.failure(jsonObject.get("msg").toString()).toJSON();
            }
            List<Map<String,Object>> data = JSONObject.parseObject(jsonObject.get("data").toString(),List.class);
            logger.info("subject:{},data:{}","获取券模板数据",JsonUtil.gsonStr(data));
            if(data == null || data.isEmpty()){
                return Result.failure("获取券模板数据为空").toJSON();
            }
            List<TicketTemplateEntity> list = data.stream().map(item ->{
                TicketTemplateEntity ticketTemplateEntity = new TicketTemplateEntity();
                ticketTemplateEntity.setTicketCode(StringUtil.objToStr(item.get("tempNo")));
                ticketTemplateEntity.setTicketName(StringUtil.objToStr(item.get("tempName")));
                ticketTemplateEntity.setRollMoney(StringUtil.objToBigDecimal(item.get("tempAmount")));
                ticketTemplateEntity.setRollContent(StringUtil.objToStr(item.get("remark")));
                ticketTemplateEntity.setRollType(StringUtil.retInt(item.get("subTempType")));//1：洗车劵；2：保养劵；3：车险劵
                ticketTemplateEntity.setStatus(StringUtil.retInt(item.get("status")));//状态：0-无效;1-激活'
                ticketTemplateEntity.setChannel(StringUtil.objToStr(item.get("channel")));
                ticketTemplateEntity.setUpdateBy(rollTemplateDTO.getUpdateBy());
                return ticketTemplateEntity;
            }).collect(Collectors.toList());
            logger.info("subject:{},list:{}","封装券模板数据",JsonUtil.gsonStr(list));
            return iTicketTemplateFeignService.saveTicketTemplate(list);
        }catch (Exception e){
            logger.error("subject:{},e:{}","刷新券模板异常",e);
            return Result.failure().toJSON();
        }
    }

    @Override
    public String getEmptyTicket(TicketDTO ticketDTO) {
        try{
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("key","nuhcuienncuewfe");
//            dataMap.put("channel","15156165");
            dataMap.put("tempNo",ticketDTO.getTicketCode());
            dataMap.put("couponNum",ticketDTO.getTicketNumber());
            String response = UrlHttpUtil.doPost(SystemPropertiesConfig.B_SERVICE_URL+"yhqg/getCoupon",dataMap,null);
            logger.info("subject:{},response:{}","获取空券响应",response);
            JSONObject jsonObject = JSON.parseObject(response);
            if(Integer.valueOf(jsonObject.get("status").toString()).intValue() != 1){
                return Result.failure(jsonObject.get("msg").toString()).toJSON();
            }
            List<Map<String,Object>> data = JsonUtil.jsonToListMap(jsonObject.get("data").toString());
            logger.info("subject:{},data:{}","获取空券数据",JsonUtil.gsonStr(data));
            if(data == null || data.isEmpty()){
                return Result.failure("获取券模板数据为空").toJSON();
            }
            Date date = new Date();
            List<BRollDetail> entityList = data.stream().map(item ->{
                BRollDetail bRollDetail = new BRollDetail();
                bRollDetail.setName(StringUtil.objToStr(item.get("couponName")));
                bRollDetail.setAmount(StringUtil.objToBigDecimal(item.get("bisPrice")));
                bRollDetail.setType(ticketDTO.getRollType());
                bRollDetail.setCode(StringUtil.objToStr(item.get("couponNo")));
                bRollDetail.setContent(StringUtil.objToStr(item.get("remark")));
                bRollDetail.setRollStatus(StringUtil.retInt(item.get("status")));
                bRollDetail.setValidTime(DateUtil.timeStamp2Date(StringUtil.objToStr(item.get("startTime")),DateUtil.DATATIMEF_STR));
                bRollDetail.setInvalidTime(DateUtil.timeStamp2Date(StringUtil.objToStr(item.get("endTime")),DateUtil.DATATIMEF_STR));
                bRollDetail.setTemplateId(String.valueOf(ticketDTO.getTicketId()));
                bRollDetail.setAttribute((Integer) item.get("tempType"));
                bRollDetail.setStatus((int)DeleteStatusConstant.STATUS_NOT_DELETE);//状态（0：无效；1：有效）
                bRollDetail.setStatusCh(StringUtil.objToStr(item.get("statusCH")));
                bRollDetail.setHasSend(0);//c端是否已发放（0：未发放；1：已发放）
                bRollDetail.setCreateTime(date);
                bRollDetail.setCreateBy(ticketDTO.getUpdateBy());
                return bRollDetail;
            }).collect(Collectors.toList());
            ticketDTO.setEntityList(entityList);
            logger.info("subject:{},list:{}","封装空券数据",JsonUtil.gsonStr(ticketDTO));
            return iTicketTemplateFeignService.saveEmptyTicket(ticketDTO);
        }catch (Exception e){
            logger.error("subject:{},e:{}","获取空券异常",e);
            return Result.failure().toJSON();
        }
    }
}
