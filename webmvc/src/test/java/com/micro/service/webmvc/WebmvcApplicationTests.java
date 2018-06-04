package com.micro.service.webmvc;

import com.rabbitmq.client.AMQP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebmvcApplicationTests {

	private final static String QUEUE_NAME = "test_queue";
	@Test
	public void contextLoads() {
	}

	@Test
	public void mqSender(){
		// 获取到连接以及mq通道

	}
	@Test
	public void myComsure(){

	}
}
