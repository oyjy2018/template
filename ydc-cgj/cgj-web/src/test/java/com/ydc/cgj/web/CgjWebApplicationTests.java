package com.ydc.cgj.web;

import com.ydc.commom.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CgjWebApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        System.out.print(Result.success(true).toJSON());
    }

}
