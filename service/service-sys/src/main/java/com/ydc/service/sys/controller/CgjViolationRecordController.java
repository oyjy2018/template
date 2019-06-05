package com.ydc.service.sys.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import com.ydc.model.cgj.CgjViolationRecord;
import com.ydc.service.sys.service.CgjViolationRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/violationRecord")
public class CgjViolationRecordController {
    private static Logger logger = LogManager.getLogger(CgjViolationRecordController.class);

    @Autowired
    private CgjViolationRecordService cgjViolationRecordService;

    /**
     * 保存违章记录
     *
     */
    @PostMapping(value = "/insert")
    public int insert(@RequestBody CgjViolationRecord cgjViolationRecord) {
        logger.info("subject:{},param:{}","保存违章记录", JsonUtil.gsonStr(cgjViolationRecord));
        try {
            int result = cgjViolationRecordService.insert(cgjViolationRecord);
            return result;
        } catch (Exception e) {
            logger.error("保存违章记录异常",e);
            return 0;
        }
    }

    /**
     * 更新违章记录
     *
     */
    @PostMapping(value = "/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody CgjViolationRecord cgjViolationRecord) {
        logger.info("subject:{},param:{}","更新违章记录", JsonUtil.gsonStr(cgjViolationRecord));
        try {
            cgjViolationRecord.setUpdateTime(new Date());
            int result = cgjViolationRecordService.updateByPrimaryKeySelective(cgjViolationRecord);
            return result;
        } catch (Exception e) {
            logger.error("更新违章记录异常",e);
            return 0;
        }
    }

    /**
     * 更新违章记录
     *
     */
    @PostMapping(value = "/selectOneRecord")
    public CgjViolationRecord selectOneRecord(@RequestBody CgjViolationRecordQueDTO cgjViolationRecordQueDTO) {
        logger.info("subject:{},param:{}","根据条件查询违章记录", JsonUtil.gsonStr(cgjViolationRecordQueDTO));
        try {
            List<CgjViolationRecord> list = cgjViolationRecordService.selectOneRecord(cgjViolationRecordQueDTO);
            CgjViolationRecord cgjViolationRecord = null;
            if (list!=null&&list.size()>0) {
                cgjViolationRecord = list.get(0);
            }
            return cgjViolationRecord;
        } catch (Exception e) {
            logger.error("查询单条违章记录异常",e);
        }
        return null;
    }
}
