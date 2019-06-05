package com.ydc.service.store.service.impl;

import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.urlHttp.HttpParamsMap;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.BlankRollDto;
import com.ydc.commom.view.dto.cgj.service.RollTemplateDTO;
import com.ydc.commom.view.dto.cgj.service.TicketDTO;
import com.ydc.commom.view.vo.cgj.order.BTicketTemplateVO;
import com.ydc.dao.cgj.service.BRollDetailDao;
import com.ydc.dao.cgj.service.BRollNumberDao;
import com.ydc.dao.cgj.service.BRollTemplateDao;
import com.ydc.model.cgj.BRollDetail;
import com.ydc.model.cgj.BRollNumber;
import com.ydc.model.cgj.BRollTemplate;
import com.ydc.model.cgj.entity.TicketTemplateEntity;
import com.ydc.service.store.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-11-01 12:22
 **/
@Service
public class TicketServiceImpl implements TicketService {

    private final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    @Resource
    BRollTemplateDao bRollTemplateDao;
    @Resource
    BRollDetailDao bRollDetailDao;
    @Resource
    BRollNumberDao bRollNumberDao;

    @Override
    public void saveTicketTemplate(List<TicketTemplateEntity> list) {
        List<BRollTemplate> bRollTemplateList = new ArrayList<>();
        Date date = new Date();
        bRollTemplateList = list.stream().map(item -> {
            BRollTemplate bRollTemplate = new BRollTemplate();
            bRollTemplate.setRollName(item.getTicketName());
            bRollTemplate.setRollMoney(item.getRollMoney());
            bRollTemplate.setRollContent(item.getRollContent());
            bRollTemplate.setRollCode(item.getTicketCode());
            bRollTemplate.setChannel(item.getChannel());
            bRollTemplate.setRollType(item.getRollType());
            bRollTemplate.setStatus(item.getStatus());
            bRollTemplate.setCreateTime(date);
            bRollTemplate.setCreateBy(item.getUpdateBy());
            bRollTemplate.setUpdateTime(date);
            bRollTemplate.setUpdateBy(item.getUpdateBy());
            return bRollTemplate;
        }).collect(Collectors.toList());
        bRollTemplateDao.insertRollTemplateBatch(bRollTemplateList);
    }

    @Override
    public void saveEmptyTicket(TicketDTO ticketDTO) {
        //新增空券
        bRollDetailDao.insertRollDetailBatch(ticketDTO.getEntityList());
        //新增获取券明细
        BRollNumber bRollNumber = new BRollNumber();
        bRollNumber.setRollTemplateId(ticketDTO.getTicketId());
        bRollNumber.setRollAmount(ticketDTO.getTicketNumber());
        bRollNumber.setRollType(ticketDTO.getRollType());
        bRollNumber.setCreateTime(new Date());
        bRollNumber.setCreateBy(ticketDTO.getUpdateBy());
        bRollNumberDao.insert(bRollNumber);
    }

    @Override
    public List<BTicketTemplateVO> getTicketTemplateList(RollTemplateDTO rollTemplateDTO) {
        logger.info("subject:{},rollTemplateDTO:{}","查询券模板列表",JsonUtil.gsonStr(rollTemplateDTO));
        return PaginationUtil.paginationQuery(rollTemplateDTO ,
                (tempRollTemplateDTO) -> bRollTemplateDao.getTicketTemplateList(rollTemplateDTO));
    }

    @Override
    public Integer batchUpdateTicketStatus(List<String> rollCodes, Integer status) {
        return bRollDetailDao.batchUpdateTicketStatus(rollCodes, status);
    }

    @Override
    public List<BRollDetail> getBlankRollList(BlankRollDto blankRollDto) {
        return PaginationUtil.paginationQuery(blankRollDto, (tempBlankRollDto) ->
                bRollDetailDao.getBlankRollList(tempBlankRollDto));
    }

    @Override
    public List<BRollDetail> flushBlankRolls(List<String> rollCodes) throws Exception {
        List<BRollDetail> list = bRollDetailDao.getBlankRollsByCode(rollCodes);

        //请求B端数据
        Map<String, Object> paramMap = HttpParamsMap.getBHttpParamsMap();
        List<Map<String, String>> paramCodeList = new ArrayList<>();
        rollCodes.parallelStream().forEach(rollCode -> {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("coupon", rollCode);
            paramCodeList.add(tempMap);
        });
        paramMap.put("couponNoArr", JsonUtil.gsonStr(paramCodeList));
        String response = UrlHttpUtil.doPost(SystemPropertiesConfig.B_SERVICE_URL + "yhqg/getCouponList", paramMap);
        logger.info("subject:{}, response:{}", "刷新空券列表响应", response);

        //处理响应数据
        UrlHttpUtil.doResponse(response, (data) -> {
            List<Map<String, Object>> couponList = JsonUtil.parseToListMap(data);
            if (couponList == null || couponList.size() <= 0) {
                return;
            }
            //将结果转换成map
            Map<String, Map<String, Object>> couponMap = new HashMap<>(couponList.size());
            couponList.parallelStream().forEach(map -> couponMap.put((String) map.get("couponNo"), map));

            //将结果更新到list中
            list.parallelStream().forEach(bRollDetail -> {
                Map<String, Object> map = couponMap.get(bRollDetail.getCode());
                updateBRollDetailAttribute(map, bRollDetail);
            });
        });

        //更新到数据库
        new Thread(() ->
                list.parallelStream().forEach(bRollDetail -> bRollDetailDao.updateByPrimaryKey(bRollDetail))).start();
        return list;
    }

    //更新bRollDetail中属性的值
    private void updateBRollDetailAttribute(Map<String, Object> map, BRollDetail bRollDetail){
        if (bRollDetail == null){
            return;
        }
        if (map == null || map.size() <= 0) {
            return;
        }

        //属性转换map
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("couponName", "name"); //名称
        attributeMap.put("tempAmount", "amount"); //金额
        attributeMap.put("remark", "content"); //描述
        attributeMap.put("startTime", "validTime"); //开始时间
        attributeMap.put("endTime", "invalidTime"); //失效时间
        attributeMap.put("tempType", "attribute"); //券类型
        attributeMap.put("status", "rollStatus"); //状态
        attributeMap.put("statusCH", "statusCh"); //状态名称
        attributeMap.put("subTempType", "type"); //券模板

        //更新对象中对应的属性
        map.entrySet().parallelStream().forEach(entry -> {
            if (entry.getValue() == null || ("").equals(entry.getValue())) {
                return;
            }
            try {
                String fieldName = attributeMap.get(entry.getKey());
                if (fieldName == null){
                    return;
                }
                Class<BRollDetail> clazz = (Class<BRollDetail>) bRollDetail.getClass();
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.getType() == Date.class ? DateUtil.timeStamp2Date((String) entry.getValue()) :
                        field.getType() == BigDecimal.class ? new BigDecimal((Integer) entry.getValue()) : entry.getValue();
                field.set(bRollDetail, value);
            } catch (Exception e) {
                logger.error("刷新列表结果更新到list异常, key: {}, value: {}", entry.getKey(), entry.getValue(), e);
            }
        });
    }
}
