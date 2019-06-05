package com.ydc.service.car.service;

import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.CommCarQueryVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSimpleVO;
import com.ydc.model.cgj.car.CommCar;

import java.util.List;
import java.util.Map;

public interface CarService {
    Integer saveOrUpdateCar(Map<String, Object> req);

    List<CommCarQueryVO> getCarList(CommCarQueryDTO commCarQueryDTO);

    Map<String, Object> getCarListCount(Map<String, Object> req);

    String updateCarUseStatusById(CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO);

    Map<String, Object> getCarInfo(Integer id);

    CommCar selectByPlateOrVinOrEngineNo(String keyWord);

    CommCarSimpleVO getCarInfoSimple(Integer id);

    String updateCarOperationStatusById(CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO);
}
