package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalEnterpriseCarVO;

import java.util.List;

/**
 * @author
 * @create 2019-01-04 17:33
 **/
public interface CommCarSeriesService {

    /**
     * 车辆品牌
     * @return
     */
    List<RentalEnterpriseCarVO> getCarBrand();

    /**
     * 车辆车型
     * @return
     */
    List<RentalEnterpriseCarVO> getCarSeries(CommCarSeriesDTO dto);

    /**
     * 车辆车系
     * @return
     */
    List<RentalEnterpriseCarVO> getCarModel(CommCarSeriesDTO dto);
}
