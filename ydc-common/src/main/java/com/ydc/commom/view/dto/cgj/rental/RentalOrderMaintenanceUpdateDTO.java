package com.ydc.commom.view.dto.cgj.rental;

import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.rental.*;

import java.io.Serializable;

/**
 * 机务单更新
 */
public class RentalOrderMaintenanceUpdateDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @Attribute(name = "用户id",required = true,isNum = true)
    private Integer userId;

    /**
     * 用户姓名
     */
    @Attribute(name = "用户姓名",required = true,maxLength = 20)
    private String userName;

    /**
     * 机务单信息
     */
    @Attribute(name = "机务信息",required = true)
    private RentalOrderMaintenance rentalOrderMaintenance;

    /**
     * 洗车信息
     */
    @Attribute(name = "洗车信息")
    private RentalCarWashInfo rentalCarWashInfo;

    /**
     * 加油信息
     */
    @Attribute(name = "加油信息")
    private RentalRefuelInfo rentalRefuelInfo;

    /**
     * 维修信息
     */
    @Attribute(name = "维修信息")
    private RentalMaintenance rentalMaintenance;

    /**
     * 事故维修信息
     */
    @Attribute(name = "事故维修信息")
    private RentalAccidentMaintenance rentalAccidentMaintenance;

    /**
     * 调度信息
     */
    @Attribute(name = "调度信息")
    private RentalDispatch rentalDispatch;

}
