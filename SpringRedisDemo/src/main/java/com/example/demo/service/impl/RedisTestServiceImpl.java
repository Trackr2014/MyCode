package com.example.demo.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.RedisUtils;
import com.example.demo.service.RedisTestService;

@Service
public class RedisTestServiceImpl implements RedisTestService {

	@Autowired
	RedisUtils redisUtil;
	
	@Override
	public void save() {
		redisUtil.set("hello", "helloworld");
		
	}

	@Override
	public String get() {
		String string = redisUtil.get("hello");
		return string;
	}
	
}
