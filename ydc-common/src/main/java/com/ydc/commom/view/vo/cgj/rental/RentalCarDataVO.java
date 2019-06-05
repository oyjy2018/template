package com.ydc.commom.view.vo.cgj.rental;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderImgDTO;
import com.ydc.model.cgj.User;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @create 2018-11-22 11:13
 **/
public class RentalCarDataVO implements Serializable {

    private static final long serialVersionUID = -6683355081516834364L;
    private Integer appointmentStoreId ;//预约取车门店id
    private String appointmentStoreName;//预约取车门店名
    private List<CarPlateVO> carPlateVOS;//可选车辆集合
    private List<RentalStoreVO> rentalStoreVOS;//门店集合
    private Integer comeWarehouseStoreId;//出库所在门店id
    private String comeWarehouseStoreName;//出库所在门店
    private String carNumber;//车牌号
    private String repayCarMileage;//还车里程最后一次
    private String repayCarOilAmount;//还车油量最后一次

    private ComeCarOrderVO comeCarOrderVO;//出车信息
    private List<User> users;//用户集合

    public static void main(String[] args) {
        RentalCarDataVO rentalCarDataVO = new RentalCarDataVO();
        rentalCarDataVO.setAppointmentStoreName("福田");
        rentalCarDataVO.setAppointmentStoreId(1);
        rentalCarDataVO.setComeWarehouseStoreId(1);
        rentalCarDataVO.setComeWarehouseStoreName("福特");
        rentalCarDataVO.setCarNumber("faFaFaFa");
//        List<CarPlateVO> carPlateVOS = Lists.newArrayList();
//        CarPlateVO carPlateVO = new CarPlateVO();
//        carPlateVO.setCarPlateId(1);
//        carPlateVO.setCarPlate("宝马");
//        carPlateVOS.add(carPlateVO);
//        carPlateVO = new CarPlateVO();
//        carPlateVO.setCarPlateId(2);
//        carPlateVO.setCarPlate("奔驰");
//        carPlateVOS.add(carPlateVO);
//        rentalCarDataVO.setCarPlateVOS(carPlateVOS);

        List<RentalStoreVO> rentalStoreVOS = Lists.newArrayList();
        RentalStoreVO rentalStoreVO = new RentalStoreVO();
        rentalStoreVO.setStoreId(1);
        rentalStoreVO.setStoreName("福田");
        rentalStoreVOS.add(rentalStoreVO);
        rentalStoreVO = new RentalStoreVO();
        rentalStoreVO.setStoreId(2);
        rentalStoreVO.setStoreName("龙岗");
        rentalStoreVOS.add(rentalStoreVO);
        rentalCarDataVO.setRentalStoreVOS(rentalStoreVOS);
        List<User> users = Lists.newArrayList();
        User user = new User();
        user.setId(1);
        user.setUserName("李四");
        users.add(user);
        user = new User();
        user.setId(2);
        user.setUserName("王五");
        users.add(user);
        rentalCarDataVO.setUsers(users);
        ComeCarOrderVO comeCarOrderVO = new ComeCarOrderVO();
        comeCarOrderVO.setCarId(1);
        comeCarOrderVO.setCarNumber("粤B");
        List<ComeCarOrderImgDTO> comeCarOrderImgVOS = Lists.newArrayList();
        ComeCarOrderImgDTO comeCarOrderImgDTO = new ComeCarOrderImgDTO();
        comeCarOrderImgDTO.setFileName("088656ce-05c6-4e68-b0dd-a907db87931c_ydcd.png");
        comeCarOrderImgDTO.setFileUrl("/usr/local/upload/cgj/rc-upload-1542799300424-12");
        comeCarOrderImgDTO.setFileType("png");
        comeCarOrderImgVOS.add(comeCarOrderImgDTO);
        comeCarOrderImgDTO = new ComeCarOrderImgDTO();
        comeCarOrderImgDTO.setFileName("088656ce-05c6-4e68-b0dd-a907db87931c_ydcd2.png");
        comeCarOrderImgDTO.setFileUrl("/usr/local/upload/cgj/rc-upload-1542799300424-122");
        comeCarOrderImgDTO.setFileType("2png");
        comeCarOrderImgVOS.add(comeCarOrderImgDTO);

        comeCarOrderVO.setComeCarOrderImgDTOS(comeCarOrderImgVOS);
        rentalCarDataVO.setComeCarOrderVO(comeCarOrderVO);
        System.out.println(JSONObject.toJSON(rentalCarDataVO));
    }

    public Integer getAppointmentStoreId() {
        return appointmentStoreId;
    }

    public void setAppointmentStoreId(Integer appointmentStoreId) {
        this.appointmentStoreId = appointmentStoreId;
    }

    public String getAppointmentStoreName() {
        return appointmentStoreName;
    }

    public void setAppointmentStoreName(String appointmentStoreName) {
        this.appointmentStoreName = appointmentStoreName;
    }

    public List<CarPlateVO> getCarPlateVOS() {
        return carPlateVOS;
    }

    public void setCarPlateVOS(List<CarPlateVO> carPlateVOS) {
        this.carPlateVOS = carPlateVOS;
    }

    public List<RentalStoreVO> getRentalStoreVOS() {
        return rentalStoreVOS;
    }

    public void setRentalStoreVOS(List<RentalStoreVO> rentalStoreVOS) {
        this.rentalStoreVOS = rentalStoreVOS;
    }

    public Integer getComeWarehouseStoreId() {
        return comeWarehouseStoreId;
    }

    public void setComeWarehouseStoreId(Integer comeWarehouseStoreId) {
        this.comeWarehouseStoreId = comeWarehouseStoreId;
    }

    public String getComeWarehouseStoreName() {
        return comeWarehouseStoreName;
    }

    public void setComeWarehouseStoreName(String comeWarehouseStoreName) {
        this.comeWarehouseStoreName = comeWarehouseStoreName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public ComeCarOrderVO getComeCarOrderVO() {
        return comeCarOrderVO;
    }

    public void setComeCarOrderVO(ComeCarOrderVO comeCarOrderVO) {
        this.comeCarOrderVO = comeCarOrderVO;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRepayCarMileage() {
        return repayCarMileage;
    }

    public void setRepayCarMileage(String repayCarMileage) {
        this.repayCarMileage = repayCarMileage;
    }

    public String getRepayCarOilAmount() {
        return repayCarOilAmount;
    }

    public void setRepayCarOilAmount(String repayCarOilAmount) {
        this.repayCarOilAmount = repayCarOilAmount;
    }
}
