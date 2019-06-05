package com.ydc.mongodb.cgj.dao.impl;

import com.ydc.model.mongodb.common.ServerCarLog;
import com.ydc.mongodb.cgj.dao.ServerCarLogDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServerCarLogDaoImpl implements ServerCarLogDao {

    @Resource(name = "cgjMongoTemplate")
    private MongoTemplate cgjMongoTemplate;

    @Override
    public void save(ServerCarLog serverCarLog) {
        cgjMongoTemplate.save(serverCarLog);
    }

    @Override
    public void remove(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        cgjMongoTemplate.remove(query,ServerCarLog.class);

    }

    @Override
    public void update(ServerCarLog serverCarLog) {
        Query query = new Query(Criteria.where("id").is(serverCarLog.getId()));
        Update update = new Update();
        update.set("request_mapping", serverCarLog.getRequestMapping());
        cgjMongoTemplate.updateFirst(query, update, ServerCarLog.class);
    }

    @Override
    public ServerCarLog findById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return cgjMongoTemplate.findOne(query,ServerCarLog.class);
    }
}
