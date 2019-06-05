package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.OpinionFeedbackService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.model.cgj.OpinionFeedback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/opinionFeedback")
public class OpinionFeedbackController {

    private static Logger logger = LogManager.getLogger(OpinionFeedbackController.class);

    @Autowired
    private OpinionFeedbackService opinionFeedbackService;

    /**
     * 添加意见反馈
     *
     * @param data
     * @return
     */
    @PostMapping("/addOpinionFeedback")
    public String addOpinionFeedback(@RequestParam(value = "data") String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        OpinionFeedback opinionFeedback = jsonObject.getObject("opinionFeedback", OpinionFeedback.class);
        opinionFeedback.setUserId(SubjectUtil.getMemberId());
        opinionFeedback.setUserName(SubjectUtil.getMemberName());
        String images = jsonObject.getString("images");
        logger.info("添加意见反馈, opinionFeedback: " + opinionFeedback);
        //处理图片
        if (StringUtil.isNotEmpty(images)) {
            List<Map<String, Object>> maps = JsonUtil.jsonToListMap(images);
            String opinionContent = opinionFeedback.getOpinionContent() == null ? "" : opinionFeedback.getOpinionContent();
            for (Map<String, Object> image : maps) {
                try {
                    opinionContent = opinionContent.concat("<p><img src='")
                            .concat(FileUtil.getBrowseFile((String) image.get("fileUrl"), (String) image.get("fileName")))
                            .concat("'></p>");
                } catch (Exception e) {
                    logger.error("图片处理成String异常", e);
                }
            }
            opinionFeedback.setOpinionContent(opinionContent);
        }
        return opinionFeedbackService.opinionFeedbackAdd(opinionFeedback).toJSON();
    }
}
