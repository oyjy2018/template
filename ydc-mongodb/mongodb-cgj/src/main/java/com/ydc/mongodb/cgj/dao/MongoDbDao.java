package com.ydc.mongodb.cgj.dao;

import com.ydc.model.mongodb.common.ServerCarLog;

/**
 * @Title
 * @date 2019/1/24
 */
public interface MongoDbDao {
    void save(Object object);

    void remove(Long id);
}
