package com.ydc.cgj.rentalb.app.common;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongodbConfig {

  /*  protected String host = "120.77.246.201";
    protected int port = 27017;
    protected String database = "cgj";
    protected Mongo mongo;
    protected DB db;

    public MongodbConfig() {
        this.mongo = new Mongo(host, port);
        this.db = mongo.getDB(database);
    }*/
     /* 取mongoDB应该从mongoDB的池中取出为佳，另看是否能借助 kafka之类的做批量的上传*/
    public static MongoClient getMongoClient() {
        //TODO URI存到配置文件中
        // MongoClientURI uri = new MongoClientURI(SystemPropertiesConfig.MONGODB_CGJ_URI);
         MongoClientURI uri = new MongoClientURI("mongodb://ydc:ydc321@120.77.246.201:27017/?authSource=cgj");
        return new MongoClient(uri);
    }
}
