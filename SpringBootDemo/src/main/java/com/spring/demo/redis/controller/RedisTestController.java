/**
 * @author wang.pengfei
 * */
package com.spring.demo.redis.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.util.RedisUtil;

@RestController
public class RedisTestController {
	private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);
	
    @Autowired
    private RedisUtil redisUtil;
    
	@RequestMapping("/helloworld")
    public String helloworld() {
		for(int i=0; i<10; i++){
			logger.info("info");
			logger.debug("debug");
			logger.error("error");
		}
	    return "hello";
    }	
	@RequestMapping("/springBoot")
	public String springBoot() {

		redisUtil.get("001");
		System.out.println(redisUtil.get("001"));
		redisUtil.set("key", "王朋");
		System.out.println(redisUtil.get("key"));
		return "this a demo for springBoot";		
	}
	
	@RequestMapping("/springBoot2")
	public String springBoot2() {

		redisUtil.set("key", "wang.pengfei");
		redisUtil.get("001");
		System.out.println(redisUtil.get("001"));
		redisUtil.set("key", "王朋");
		System.out.println(redisUtil.get("key"));
		return "this a demo for springBoot";		
	}
}