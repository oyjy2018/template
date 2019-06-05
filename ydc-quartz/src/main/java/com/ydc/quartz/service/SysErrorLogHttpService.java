package com.ydc.quartz.service;


import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.dao.cgj.common.SysErrorLogHttpDao;
import com.ydc.model.cgj.SysErrorLogHttp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "sysErrorLogHttpService")
public class SysErrorLogHttpService {
    private static final Logger logger = LogManager.getLogger(SysErrorLogHttpService.class);

    @Autowired
    private SysErrorLogHttpDao sysErrorLogHttpDao;

    public void job(){
        List<SysErrorLogHttp> list = sysErrorLogHttpDao.getReSysErrorLogHttp();
        if (list == null || list.size() <= 0) return;
        list.parallelStream().forEach(this::reSysErrorLogHttp);
    }

    private void reSysErrorLogHttp(SysErrorLogHttp sysErrorLogHttp){
        logger.info("subject: {}, logId: {}", "定时任务http请求异常重试", sysErrorLogHttp.getId());
        try {
            UrlHttpUtil.doPost(sysErrorLogHttp.getCallBackUrl(), JsonUtil.jsonStr(sysErrorLogHttp));
        }catch (Exception e){
            logger.error("subject: {}, logId: {}", "定时任务http请求异常重试异常", sysErrorLogHttp.getId(), e);
        }
    }


}
