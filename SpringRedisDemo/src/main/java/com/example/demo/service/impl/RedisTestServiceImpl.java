package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        for (int i = 0; i < 10000; i++) {
            String keyString = new StringBuffer("key").append("-").append(String.valueOf(i)).toString();
            System.out.println(redisUtil.get(keyString));
        }
        return string;
    }

    @Override
    public void saveMany() {
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String keyString = new StringBuffer("key").append("-").append(String.valueOf(i)).toString();
            redisUtil.add(keyString, String.valueOf(i));
        }
        Long endTime = System.currentTimeMillis();
        Long time = (endTime - startTime) / 1000;

        System.out.println("插入10000条数据一共耗时：" + time + "s");

    }

}
