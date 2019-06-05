package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.MemberAppointmentFeignService;
import com.ydc.cgj.bridge.service.MemberAppointmentService;
import com.ydc.commom.constant.cgj.appointment.BOrderResponseDTOConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.model.cgj.MemberAppointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemberAppointmentServiceImpl implements MemberAppointmentService {

    private final Logger logger = LogManager.getLogger(MemberAppointmentServiceImpl.class);
    @Autowired
    private MemberAppointmentFeignService memberAppointmentFeignService;

    @Override
    public Result updateAppointBInfo(MemberAppointment memberAppointment) {
        return memberAppointmentFeignService.updateAppointBInfo(memberAppointment);
    }



    @Override
    public Boolean updateAppointStatusByBAppointStatus(BOrderResponseDTO bOrderResponseDTO, Integer memberId) {
        Result result = Result.failure();
        if (null == bOrderResponseDTO)return false;
        if ( null!=bOrderResponseDTO.getUseType()
                && BOrderResponseDTOConstant.USE_TYPE_DIRECT.compareTo(bOrderResponseDTO.getUseType())==0){
            //直接消费
            CreateAppointmentParamVO createAppointmentParamVO=BOrderResponseDTO.convertBOrderResponseDTO(bOrderResponseDTO);
            result= memberAppointmentFeignService.createAppointByBOrder(createAppointmentParamVO);
        }else {
            result= memberAppointmentFeignService.updateAppointStatusByBAppointStatus(bOrderResponseDTO, memberId);
        }
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS){
            return true;
        }
        logger.info(result.getMessage());
        return  false;
    }
}
