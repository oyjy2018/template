package com.ydc.mongodb.cgj.dao;

import com.ydc.model.mongodb.common.ServerCarLog;

public interface ServerCarLogDao {

    void save(ServerCarLog serverCarLog);

    void remove(Long id);

    void update(ServerCarLog serverCarLog);

    ServerCarLog findById(Long id);


}
