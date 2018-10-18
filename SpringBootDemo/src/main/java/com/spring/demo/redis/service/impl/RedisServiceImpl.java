package com.spring.demo.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.redis.service.RedisService;
import com.spring.demo.util.RedisUtil;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public String setTest() {

		String result = "";
		if (redisUtil.exists("testRedis")) {
			result = redisUtil.get("testRedis").toString();
		} else {
			redisUtil.set("testRedis", "this is a demo to test redis");
			result = redisUtil.get("testRedis").toString();
		}
		
		return result;
	}

}
