/**
 * @author wang.pengfei
 *
 */
package TaskManager.QuartzDemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RestController
public class TestController {
	private static final Logger logger = LogManager.getLogger(TestController.class);
    Jedis jsJedis;
	JedisPool jedisPool;
	@RequestMapping("/helloworld")
    public String helloworld() {
		for(int i=0; i<50000; i++){
			logger.info("你好 hello日志");
//			jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
//			jsJedis = jedisPool.getResource();
//			jsJedis.auth("123456");
//			jsJedis.set("001", "helloJedis");
			logger.error("错误");
		}
	    return "hello";
    }	
	@RequestMapping("/springBoot")
	public String springBoot() {
		logger.info("执行了springBoot");
		return "this a demo for springBoot";		
	}
}