package com.ydc.beans.config;

import com.ydc.beans.commom.EnvironmentFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 *
 */
@Configuration
@DependsOn(value = "environmentFactory")
public class OrderNoConfig {


    private  String keyPrefix;

    private String orderNoPrefix;

    private String orderNoSuffix;


    //商家订单号
    @Bean(name = "orderNoMerchantConfig",initMethod = "initMethodOrderNoMerchant")
    public OrderNoConfig initMethodOrderNoMerchant() {
        OrderNoConfig orderNoMerchantConfig=new OrderNoConfig();
        orderNoMerchantConfig.setKeyPrefix(EnvironmentFactory.getProperty("orderNo.merchant.key.prefix"));
        orderNoMerchantConfig.setOrderNoPrefix(EnvironmentFactory.getProperty("orderNo.merchant.no.prefix"));
        orderNoMerchantConfig.setOrderNoSuffix(EnvironmentFactory.getProperty("orderNo.merchant.no.suffix"));
        return orderNoMerchantConfig;
    }

    //用户订单号
    @Bean(name = "orderNoMemberConfig",initMethod = "initMethodOrderNoMember")
    public OrderNoConfig initMethodOrderNoMember() {
        OrderNoConfig orderNoMemberConfig=new OrderNoConfig();
        orderNoMemberConfig.setKeyPrefix(EnvironmentFactory.getProperty("orderNo.member.key.prefix"));
        orderNoMemberConfig.setOrderNoPrefix(EnvironmentFactory.getProperty("orderNo.member.no.prefix"));
        orderNoMemberConfig.setOrderNoSuffix(EnvironmentFactory.getProperty("orderNo.member.no.suffix"));
        return orderNoMemberConfig;
    }


    //订单流水号
    @Bean(name = "orderNoPayWaterConfig",initMethod = "initMethodOrderNoPayWater")
    public OrderNoConfig initMethodOrderNoPayWater() {
        OrderNoConfig orderNoPayWaterConfig=new OrderNoConfig();
        orderNoPayWaterConfig.setKeyPrefix(EnvironmentFactory.getProperty("orderNo.payWater.key.prefix"));
        orderNoPayWaterConfig.setOrderNoPrefix(EnvironmentFactory.getProperty("orderNo.payWater.no.prefix"));
        orderNoPayWaterConfig.setOrderNoSuffix(EnvironmentFactory.getProperty("orderNo.payWater.no.suffix"));
        return orderNoPayWaterConfig;
    }

    //预约订单号
    @Bean(name = "appointOrderNoConfig",initMethod = "initMethodAppointOrderNo")
    public OrderNoConfig initMethodAppointOrderNo() {
        OrderNoConfig orderNoPayWaterConfig=new OrderNoConfig();
        orderNoPayWaterConfig.setKeyPrefix(EnvironmentFactory.getProperty("orderNo.appointment.key.prefix"));
        orderNoPayWaterConfig.setOrderNoPrefix(EnvironmentFactory.getProperty("orderNo.appointment.no.prefix"));
        orderNoPayWaterConfig.setOrderNoSuffix(EnvironmentFactory.getProperty("orderNo.appointment.no.suffix"));
        return orderNoPayWaterConfig;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String getOrderNoPrefix() {
        return orderNoPrefix;
    }

    public void setOrderNoPrefix(String orderNoPrefix) {
        this.orderNoPrefix = orderNoPrefix;
    }

    public String getOrderNoSuffix() {
        return orderNoSuffix;
    }

    public void setOrderNoSuffix(String orderNoSuffix) {
        this.orderNoSuffix = orderNoSuffix;
    }
}
