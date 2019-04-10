package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		//字符串操作
		redisUtil.add("hello", "helloworld");
		//集合操作
		Set<String> aSet = new HashSet<>();
		aSet.add("set1");
		aSet.add("set2");
		aSet.add("set3");
		redisUtil.setAdd("set", aSet);
		//list操作
		List<String> list = new ArrayList<>();
		list.add("list1");
		list.add("list2");
		list.add("list3");
		redisUtil.listAdd("a", list);
	}

	@Override
	public String get() {
		String string = redisUtil.get("hello");
		System.out.println(redisUtil.setMembers("set"));
		System.out.println(redisUtil.listRange("a"));
		return string;
	}
	
}
