package com.baizhi.test;


import com.baizhi.CmfzApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CmfzApplication.class)  //该注解用来启动入口类 （springboot的容器）
@RunWith(SpringRunner.class)  //springboot 整合junit
public class BasicTest {
}
