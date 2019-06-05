package com.ydc.service.sys.controller;


import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.sys.CgjViolationRecordDetail;
import com.ydc.service.sys.service.CgjViolationRecordDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/violationRecordDetail")
public class CgjViolationRecordDetailController {
    private static Logger logger = LogManager.getLogger(CgjViolationRecordDetailController.class);

    @Autowired
    private CgjViolationRecordDetailService cgjViolationRecordDetailService;

    /**
     * 保存违章记录详情
     *
     */
    @PostMapping(value = "/insert")
    public int insert(@RequestBody CgjViolationRecordDetail cgjViolationRecordDetail) {
        logger.info("subject:{},param:{}","保存违章记录详情", JsonUtil.gsonStr(cgjViolationRecordDetail));
        try {
            int result = cgjViolationRecordDetailService.insert(cgjViolationRecordDetail);
            return result;
        } catch (Exception e) {
            logger.error("保存违章记录异常",e);
            return 0;
        }
    }
    /**
     * 根据违章记录id查询违章记录详情列表
     *
     */
    @PostMapping(value = "/getRecordDetailListByRecordId")
    public List<CgjViolationRecordDetail> getRecordDetailListByRecordId(@RequestBody CgjViolationRecordQueDTO cgjViolationRecordQueDTO) {
        logger.info("subject:{},param:{}","违章记录cgjViolationRecordQueDTO", JsonUtil.gsonStr(cgjViolationRecordQueDTO));
        try {
            List<CgjViolationRecordDetail> recordDetailList = cgjViolationRecordDetailService.selectRecordDetailListByRecordId(cgjViolationRecordQueDTO);
            return recordDetailList;
        } catch (Exception e) {
            logger.error("根据违章记录id查询违章记录详情列表异常",e);
            return null;
        }
    }
    /**
     * 根据id更新处理状态为已处理
     *
     */
    @PostMapping(value = "/updateDealStatusById")
    public int updateDealStatusById(@RequestBody CgjViolationRecordDetail cgjViolationRecordDetail) {
        logger.info("subject:{},param:{}","违章记录详情", JsonUtil.gsonStr(cgjViolationRecordDetail));
        try {
            int result = cgjViolationRecordDetailService.updateDealStatusById(cgjViolationRecordDetail);
            return result;
        } catch (Exception e) {
            logger.error("根据id更新处理状态异常",e);
            return 0;
        }
    }


}
