package com.ydc.mongodb.cgj.dao.impl;

import com.ydc.mongodb.cgj.dao.MongoDbDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title
 * @date 2019/1/24
 */
@Component
public class MongoDbDaoImpl implements MongoDbDao {
    @Resource(name = "cgjMongoTemplate")
    private MongoTemplate cgjMongoTemplate;
    @Override
    public void save(Object object) {
        cgjMongoTemplate.save(object);
    }

    @Override
    public void remove(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        cgjMongoTemplate.remove(query, Object.class);
    }
}
