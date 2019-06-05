package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalOrderFileVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsFileVO;
import com.ydc.dao.cgj.rental.RentalOrderFileDao;
import com.ydc.model.cgj.rental.RentalOrderFile;
import com.ydc.service.order.service.RentalOrderFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2019-01-05 16:24
 **/
@Service
public class RentalOrderFileServiceImpl implements RentalOrderFileService {

    private final Logger logger = LogManager.getLogger(RentalOrderFileService.class);

    @Autowired
    RentalOrderFileDao rentalOrderFileDao;

    @Override
    public List<PCRentalEnterpriseDetailsFileVO> getRentalEnterpriseOrderFile(Integer orderId) {
        return rentalOrderFileDao.getRentalEnterpriseOrderFile(orderId);
    }

    @Override
    public int insert(RentalOrderFile record) {
        return rentalOrderFileDao.insert(record);
    }

    @Override
    public int updateByPrimaryKey(RentalOrderFile record) {
        return rentalOrderFileDao.updateByPrimaryKey(record);
    }

    @Override
    public List<RentalOrderFileVO> getRentalOrderFiles(RentalOrderFile rentalOrderFile) {
        List<RentalOrderFileVO> rentalOrderFileVOS = rentalOrderFileDao.getRentalOrderFiles(rentalOrderFile);
        rentalOrderFileVOS.forEach(item->{
            try{
                item.setBrowseFileUrl(FileUtil.getBrowseFile(item.getFileUrl(),item.getFileName(),item.getFileType()));
            }catch (Exception e){
                logger.error("subject:{},e:{}","订单资料浏览",e);
            }
        });
        return rentalOrderFileVOS;
    }
}
