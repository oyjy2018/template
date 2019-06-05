package com.ydc.cgj.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CgjAppApplicationTests {
    private static final Logger logger = LogManager.getLogger();
    @Test
    public void contextLoads() {
    }


    public static void main(String[] args) {
       logger.info(10/3);
       logger.info(10%3);
    }

}
