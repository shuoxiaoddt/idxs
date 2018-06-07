package com.micro.service.webmvc;

import com.micro.service.webmvc.service.SpringTransactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xiaos 2018/6/5
 * //TODO 写点注释吧
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WebmvcApplication.class})
public class SpringTransactionalTest {

    @Resource
    private SpringTransactional springTransactional;
    @Test
    public void tryTransaction(){
        springTransactional.query();
    }


}
