package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RedisTestService;

@RestController
@RequestMapping(value = "/v1/redis")
public class RedisTestController {

    @Autowired
    RedisTestService redisTestService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveData() {
        redisTestService.save();
        return "save successfully";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getData() {
        return redisTestService.get();
    }

    @RequestMapping(value = "/save/many", method = RequestMethod.GET)
    public String saveData2() {
        redisTestService.saveMany();
        return "Successfully";
    }
}
