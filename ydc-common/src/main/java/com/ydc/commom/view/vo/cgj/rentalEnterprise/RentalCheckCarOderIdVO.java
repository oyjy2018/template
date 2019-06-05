package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.rental.RentalCheckCar;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2019-01-04 10:47
 **/
public class RentalCheckCarOderIdVO implements Serializable {
    private static final long serialVersionUID = 3719360428299094179L;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * id
     */
    private Integer carId;

    private String carLevel;//车等级
    private Integer carSeriesId;//车型id

    public static List<RentalCheckCarOderIdVO> getRentalCheckCarOderIdVOs(List<RentalCheckCar> rentalCheckCars ){
        List<RentalCheckCarOderIdVO> rentalCheckCarOderIdVOS = Lists.newArrayList();
        if(rentalCheckCars == null || rentalCheckCars.isEmpty())return rentalCheckCarOderIdVOS;
        return rentalCheckCars.stream().map(item ->{
            RentalCheckCarOderIdVO rentalCheckCarOderIdVO = new RentalCheckCarOderIdVO();
            rentalCheckCarOderIdVO.setCarNumber(item.getCarNumber());
            rentalCheckCarOderIdVO.setCarId(item.getId());
            rentalCheckCarOderIdVO.setCarLevel(item.getCarLevel());
            rentalCheckCarOderIdVO.setCarSeriesId(item.getCarSeriesId());
            return rentalCheckCarOderIdVO;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<RentalCheckCar> rentalCheckCars = new ArrayList<>();
        RentalCheckCar rentalCheckCar = new RentalCheckCar();
        rentalCheckCar.setCarNumber("粤B 12344");
        rentalCheckCar.setId(1);
        rentalCheckCars.add(rentalCheckCar);
        rentalCheckCar = new RentalCheckCar();
        rentalCheckCar.setCarNumber("粤B 12345");
        rentalCheckCar.setId(2);
        rentalCheckCars.add(rentalCheckCar);
        System.out.println(JsonUtil.gsonStr(getRentalCheckCarOderIdVOs(rentalCheckCars)));
    }


    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(String carLevel) {
        this.carLevel = carLevel;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }
}
