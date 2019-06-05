package com.ydc.model.mongodb.common;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;


@Document(collection = "common_server_car_log")
public class ServerCarLog implements Serializable {


    private static final long serialVersionUID = 4233374759281956933L;

    @Id
    @Field("_id")
    private Long id;

    @Field("request_mapping")
    private String requestMapping;

    @Field("create_time")
    private Date createTime;

    @Field("create_by")
    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "ServerCarLog{" +
                "id=" + id +
                ", requestMapping='" + requestMapping + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
