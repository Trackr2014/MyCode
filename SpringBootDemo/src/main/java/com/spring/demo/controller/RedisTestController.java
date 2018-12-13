/**
 * @author wang.pengfei
 * */
package com.spring.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.service.RedisService;

/**
 * @author wang.pengfei
 *
 */
@RestController
public class RedisTestController {
	private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);
	
    @Autowired
    private RedisService redisService;
    
	/**
	 * <p> test log settings </p>
	 * @return
	 */
	@RequestMapping("/LOG")
    public String helloworld() {
	
		
		for(int i=0; i<10; i++){
			logger.info("info");
			logger.debug("debug");
			logger.error("error");
		}
	    return "hello";
    }	
	
	
	/**
	 * <p> this a example to show reids </p>
	 * @return
	 */
	@RequestMapping("/redis/set")
	public String redisCotroller() {
		String result = redisService.setTest();
		return result;
	}
}