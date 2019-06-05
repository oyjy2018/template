package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderImgDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderImgDTO;
import com.ydc.dao.cgj.rental.RentalOrderCarImgDao;
import com.ydc.model.cgj.rental.RentalOrderCarImg;
import com.ydc.service.order.service.RentalOrderCarImgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-11-22 18:55
 **/
@Service
public class RentalOrderCarImgServiceImpl implements RentalOrderCarImgService {

    private final Logger logger = LogManager.getLogger(RentalOrderCarImgService.class);
    @Autowired
    RentalOrderCarImgDao rentalOrderCarImgDao;


    @Override
    public void insertComeCarImgBatch(List<ComeCarOrderImgDTO> list) {
        rentalOrderCarImgDao.insertComeCarImgBatch(list);
    }

    @Override
    public void insertRepayCarImgBatch(List<RepayCarOrderImgDTO> list) {
        rentalOrderCarImgDao.insertRepayCarImgBatch(list);
    }

    @Override
    public List<RentalOrderCarImg> getRentalOrderCarImgByOrderId(Integer orderId) {
        return rentalOrderCarImgDao.getRentalOrderCarImgByOrderId(orderId);
    }

    @Override
    public List<ComeCarOrderImgDTO> getComeCarOrderImgData(Integer orderId) {
        List<ComeCarOrderImgDTO> comeCarOrderImgVOS = rentalOrderCarImgDao.getComeCarOrderImgData(orderId);
        if(comeCarOrderImgVOS == null || comeCarOrderImgVOS.isEmpty())return Lists.newArrayList();
        comeCarOrderImgVOS.stream().forEach(item -> {
            try {
                item.setBrowseFileUrl(FileUtil.getBrowseFile(item.getFileUrl(),item.getFileName()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","封装图片浏览地址异常",e);
            }
        });
        return comeCarOrderImgVOS;
    }

    @Override
    public Integer updateRentalCarImg(Integer orderId, Integer describeType) {
        return rentalOrderCarImgDao.updateRentalCarImg(orderId,describeType);
    }
}
