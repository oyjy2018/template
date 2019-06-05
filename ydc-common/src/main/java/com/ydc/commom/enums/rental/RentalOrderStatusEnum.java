package com.ydc.commom.enums.rental;

import java.util.HashMap;
import java.util.Map;

public enum RentalOrderStatusEnum {

    status10("10","1","0",null,null)
    ,status15("15","1","9",null,null)
    ,status20("20","1","10,96",null,null)
    ,status30("30","1","100","0,1",null)
    ,status40("40","1","100","100",null)
    ,status50("50","2",null,null,null)
    ,status60("60","3",null,null,null)
    ,status70("70","4","1",null,null)
    ,status80("80","4","100",null,null)
    ,status90("90","98",null,null,null)
    ,status100("100","99",null,null,null)
    ,status110("110","100",null,null,null)
    ;

    String status;
    String realStatus;
    String flowOneStatus;
    String flowTwoStatus;
    String flowThreeStatus;

    private RentalOrderStatusEnum(String status,String realStatus,String flowOneStatus,String flowTwoStatus,String flowThreeStatus){
        this.status = status;
        this.realStatus = realStatus;
        this.flowOneStatus = flowOneStatus;
        this.flowTwoStatus = flowTwoStatus;
        this.flowThreeStatus = flowThreeStatus;
    }

    public static RentalOrderStatusEnum getEnumByStatus(String status){
        for(RentalOrderStatusEnum rose:RentalOrderStatusEnum.values()){
            if(rose.status.equals(status))
                return rose;
        }
        return null;
    }

    public Map<String, Object> getSearchParamMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("status",this.realStatus);
        map.put("flowOneStatus",this.flowOneStatus);
        map.put("flowTwoStatus",this.flowTwoStatus);
        return map;
    }

    public Map<String, Object> getUpdateParamMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("status",this.realStatus);
        if(this.flowOneStatus != null && this.flowOneStatus.indexOf(",") != -1){
            map.put("flowOneStatus",this.flowOneStatus.substring(0,this.flowOneStatus.indexOf(",")));
        }else{
            map.put("flowOneStatus",this.flowOneStatus);
        }
        if(this.flowTwoStatus != null && this.flowTwoStatus.indexOf(",") != -1){
            map.put("flowTwoStatus",this.flowTwoStatus.substring(0,this.flowTwoStatus.indexOf(",")));
        }else{
            map.put("flowTwoStatus",this.flowTwoStatus);
        }
        if(this.flowThreeStatus != null && this.flowThreeStatus.indexOf(",") != -1){
            map.put("flowThreeStatus",this.flowThreeStatus.substring(0,this.flowThreeStatus.indexOf(",")));
        }else{
            map.put("flowThreeStatus",this.flowThreeStatus);
        }
        return map;
    }
}
