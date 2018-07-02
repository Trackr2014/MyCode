/**
 * @author wang.pengfei
 *
 */
package TaskManager.QuartzDemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Util.RedisUtil;

@RestController
public class TestController {
	private static final Logger logger = LogManager.getLogger(TestController.class);
	@RequestMapping("/helloworld")
    public String helloworld() {
		for(int i=0; i<50000; i++){
			logger.info("你好 hello日志");
			logger.error("错误");
		}
	    return "hello";
    }	
	@RequestMapping("/springBoot")
	public String springBoot() {
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
		RedisUtil redisUtil=(RedisUtil) context.getBean("redisUtil");
		redisUtil.set("001", "wang.pengfei");
		redisUtil.get("001");
		System.out.println(redisUtil.get("001"));
		logger.info("执行了springBoot");
		return "this a demo for springBoot";		
	}
}