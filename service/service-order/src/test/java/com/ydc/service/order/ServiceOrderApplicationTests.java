package com.ydc.service.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceOrderApplicationTests {
    private static final Logger logger = LogManager.getLogger();
    @Test
    public void contextLoads() {
        List<SonOrder> sonOrderList1 = new ArrayList<>();
        SonOrder sonOrder1 = new SonOrder(1,1,"taobao1","淘宝1");
        SonOrder sonOrder2 = new SonOrder(2,1,"taobao2","淘宝2");
        SonOrder sonOrder3 = new SonOrder(3,1,"taobao3","淘宝3");
        sonOrderList1.add(sonOrder1);
        sonOrderList1.add(sonOrder2);
        sonOrderList1.add(sonOrder3);
        Order order1 = new Order(1,sonOrderList1);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);

        List<SonOrder> sonOrderList2 = new ArrayList<>();
        SonOrder sonOrder4 = new SonOrder(1,2,"taobao1","淘宝1");
        SonOrder sonOrder5 = new SonOrder(2,2,"taobao2","淘宝2");
        SonOrder sonOrder6 = new SonOrder(3,2,"taobao3","淘宝3");
        sonOrderList2.add(sonOrder4);
        sonOrderList2.add(sonOrder5);
        sonOrderList2.add(sonOrder6);
        Order order2 = new Order(2,sonOrderList2);
        orders.add(order2);

        for(Order or : orders){
           logger.info(or.getId());
            for(SonOrder son : or.getSonOrderList()){
               logger.info(son.getId()+":"+son.getOrderId()+":"+son.getSupplierCode()+":"+son.getSupplierName());
            }
        }
    }



}
class Order{
    private int id;

    List<SonOrder> sonOrderList;

    public Order(int id, List<SonOrder> sonOrderList) {
        this.id = id;
        this.sonOrderList = sonOrderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SonOrder> getSonOrderList() {
        return sonOrderList;
    }

    public void setSonOrderList(List<SonOrder> sonOrderList) {
        this.sonOrderList = sonOrderList;
    }
}
class SonOrder{
    private int id;
    private int orderId;
    private String supplierCode;
    private String supplierName;

    public SonOrder(int id, int orderId, String supplierCode, String supplierName) {
        this.id = id;
        this.orderId = orderId;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
