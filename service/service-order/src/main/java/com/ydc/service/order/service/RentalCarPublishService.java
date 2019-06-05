package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.vo.cgj.rental.PublishInfoVO;
import com.ydc.model.cgj.rental.RentalCarPublish;

/**
 * @author
 * @create 2019-01-09 9:51
 **/
public interface RentalCarPublishService {

    RentalCarPublish selectByPrimaryKey(Integer id);

    /**
     * 获取发布信息
     * @param dto
     * @return
     */
    PublishInfoVO getPublishInfo(RentalCarPublishDTO dto);
}
