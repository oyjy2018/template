package com.ydc.commom.enums.rental;

import com.ydc.commom.util.StringUtil;

import java.util.*;

/**
 * 事故单枚举
 *
 **/
public enum RentalAccidentEnum {
   ;

   //订单类型枚举
   public enum OrderTypeEnum{
       ORDER_TYPE_0(0,"租车单"),
       ORDER_TYPE_1(1,"机务单")
       ;
       private Integer code;
       private String name;

       OrderTypeEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.OrderTypeEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //事故起因枚举
   public enum AccidentCauseEnum{
       accident_cause_1(1,"碰撞"),
       accident_cause_2(2,"停放被破坏"),
       accident_cause_3(3,"盗抢")
       ;
       private Integer code;
       private String name;

       AccidentCauseEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentCauseEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //事故责任枚举
   public enum AccidentDutyEnum{
       ACCIDENT_DUTY_1(1,"全责"),
       ACCIDENT_DUTY_2(2,"主责"),
       ACCIDENT_DUTY_3(3,"次责"),
       ACCIDENT_DUTY_4(4,"对等"),
       ACCIDENT_DUTY_5(5,"无责")
       ;
       private Integer code;
       private String name;

       AccidentDutyEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentDutyEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //驾驶人类型
   public enum DriverTypeEnum{
       DRIVER_TYPE_1(1,"客户"),
       DRIVER_TYPE_2(2,"员工"),
       DRIVER_TYPE_3(3,"维修工"),
       DRIVER_TYPE_4(4,"无")
       ;
       private Integer code;
       private String name;

       DriverTypeEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.DriverTypeEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //事故类型
   public enum AccidentCategoryEnum{
       ACCIDENT_CATEGORY_1(1,"单方"),
       ACCIDENT_CATEGORY_2(2,"双方"),
       ACCIDENT_CATEGORY_3(3,"多方")
       ;
       private Integer code;
       private String name;

       AccidentCategoryEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentCategoryEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //事故等级
    public enum AccidentClassificationEnum{
       ACCIDENT_CLASSIFICATION_1(1,"小事故"),
       ACCIDENT_CLASSIFICATION_2(2,"中事故"),
       ACCIDENT_CLASSIFICATION_3(3,"大事故"),
       ACCIDENT_CLASSIFICATION_4(4,"特大事故")
       ;
       private Integer code;
       private String name;

       AccidentClassificationEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentClassificationEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //事故状态枚举
    public enum AccidentStatusEnum{
       accident_status_0(0,"未结"),
       accident_status_1(1,"已结")
       ;
       private Integer code;
       private String name;

       AccidentStatusEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentStatusEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

   //具体分类
    public enum AccidentTypeEnum{
       ACCIDENT_TYPE_1(1,"车辆碰撞第三方无损"),
       ACCIDENT_TYPE_2(2,"车辆碰撞第三方有损"),
       ACCIDENT_TYPE_3(3,"车辆停放被撞/被砸"),
       ACCIDENT_TYPE_4(4,"车辆被划"),
       ACCIDENT_TYPE_5(5,"车辆玻璃单独破碎"),
       ACCIDENT_TYPE_6(6,"车辆自燃"),
       ACCIDENT_TYPE_7(7,"车辆被盗"),
       ACCIDENT_TYPE_8(8,"车辆被盗追回有车损"),
       ACCIDENT_TYPE_9(9,"车辆被盗未遂有损"),
       ACCIDENT_TYPE_10(10,"车上人员伤/亡"),
       ACCIDENT_TYPE_11(11,"车胎漏气破损"),
       ACCIDENT_TYPE_12(12,"车辆故障"),
       ACCIDENT_TYPE_13(13,"双方车辆相撞"),
       ACCIDENT_TYPE_14(14,"双方相撞对方逃逸"),
       ACCIDENT_TYPE_15(15,"双方车辆相撞有人伤/亡"),
       ACCIDENT_TYPE_16(16,"车辆与非机动车相撞有人伤/亡"),
       ACCIDENT_TYPE_17(17,"多方车辆相撞"),
       ACCIDENT_TYPE_18(18,"多方车辆相撞有人伤/亡"),
       ACCIDENT_TYPE_19(19,"被水淹")
       ;
       private Integer code;
       private String name;

       AccidentTypeEnum(Integer code, String name) {
           this.code = code;
           this.name = name;
       }
       public static String getCodeName(int code){
           Optional<String> codeName = Arrays.stream(RentalAccidentEnum.AccidentTypeEnum.values()).filter(item -> item.getCode() == code).map(item -> item.getName()).findAny();
           return codeName.orElse("");
       }

       public Integer getCode() {
           return code;
       }

       public void setCode(Integer code) {
           this.code = code;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }
}
