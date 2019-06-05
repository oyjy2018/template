package com.ydc.service.order.service.impl;

import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalEnterpriseCarVO;
import com.ydc.dao.cgj.car.CommCarSeriesDAO;
import com.ydc.service.order.service.CommCarSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2019-01-04 17:33
 **/
@Service
public class CommCarSeriesServiceImpl implements CommCarSeriesService {

    @Autowired
    CommCarSeriesDAO commCarSeriesDAO;
    @Override
    public List<RentalEnterpriseCarVO> getCarBrand() {
        return commCarSeriesDAO.getCarBrand();
    }

    @Override
    public List<RentalEnterpriseCarVO> getCarSeries(CommCarSeriesDTO dto) {
        return commCarSeriesDAO.getCarSeries(dto);
    }

    @Override
    public List<RentalEnterpriseCarVO> getCarModel(CommCarSeriesDTO dto) {
        return commCarSeriesDAO.getCarModel(dto);
    }
}
