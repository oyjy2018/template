package com.ydc.commom.view.vo.cgj.rentalEnterprise;

import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.rental.RentalCar;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2019-01-10 15:08
 **/
public class RentalCarVO implements Serializable {
    private static final long serialVersionUID = -455368659243008375L;

    private Integer id;
    private String carPlate;//车牌号
    private Integer storeId;//门店id
    private String carLevel;//车等级
    private Integer carSeriesId;//车型id

    public static List<RentalCarVO> getRentalCarVO(List<RentalCar> rentalCars){
        List<RentalCarVO> rentalCarVOS = Lists.newArrayList();
        if(rentalCars == null || rentalCars.isEmpty())return rentalCarVOS;
        return rentalCars.stream().map(item ->{
            RentalCarVO rentalCarVO = new RentalCarVO();
            rentalCarVO.setId(item.getId());
            rentalCarVO.setCarPlate(item.getCarPlate());
            rentalCarVO.setStoreId(item.getStoreId());
            rentalCarVO.setCarLevel(item.getCarLevel());
            rentalCarVO.setCarSeriesId(item.getCarSeriesId());
            return rentalCarVO;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<RentalCar> rentalCars = Lists.newArrayList();
        RentalCar rentalCar = new RentalCar();
        rentalCar.setId(1);
        rentalCar.setCarPlate("粤B 123454");
        rentalCar.setStoreId(2);
        rentalCars.add(rentalCar);
        rentalCar = new RentalCar();
        rentalCar.setId(2);
        rentalCar.setCarPlate("粤B 123452");
        rentalCar.setStoreId(3);
        rentalCars.add(rentalCar);
        System.out.println(JsonUtil.gsonStr(getRentalCarVO(rentalCars)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
