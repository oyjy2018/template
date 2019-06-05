package com.ydc.quartz.service;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.dao.cgj.rental.RentalOrderCarImgDao;
import com.ydc.model.cgj.rental.RentalOrderCarImg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 删除无效文件
 *
 * @author
 * @create 2018-12-24 10:52
 **/
@Service("deleteInvalidFileService")
public class DeleteInvalidFileService {

    private static final Logger logger = LogManager.getLogger(DeleteInvalidFileService.class);


    @Autowired
    RentalOrderCarImgDao rentalOrderCarImgDao;

    public void deleteInvalidFile(){
        List<RentalOrderCarImg> rentalOrderCarImgs = rentalOrderCarImgDao.getAllInvalidImg();
        logger.info("subject:{},rentalOrderCarImgs:{}","删除无效文件",(rentalOrderCarImgs == null || rentalOrderCarImgs.isEmpty() ? 0 : rentalOrderCarImgs.size()));
        if(rentalOrderCarImgs == null || rentalOrderCarImgs.isEmpty())return;
        final List<Integer> list = Lists.newArrayList();
        rentalOrderCarImgs.forEach(item ->{
            String result = FileUtil.deleteFile(item.getFileName(),item.getFileUrl());
            if(StringUtil.isNotEmpty(result)){
                Map<String, Object> retMap = JsonUtil.jsonToMap(result);
                if (retMap != null && "1".equals(retMap.get("code").toString())) {
                    list.add(item.getId());
                }
            }
        });
        logger.info("subject:{},size:{}","删除后id封装",JsonUtil.gsonStr(list));
        if(list.size() == 0)return;
        List<Integer> newList = Lists.newArrayList();
        for(Integer integer : list){
            newList.add(integer);
            if(newList.size() % 50 == 0){
                rentalOrderCarImgDao.batchDeleteInvalidImg(newList);
                newList.clear();
            }
        }
        if(newList.size() == 0)return;
        rentalOrderCarImgDao.batchDeleteInvalidImg(newList);
    }
}
