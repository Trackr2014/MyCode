package com.example.demo.threadPool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SingleClass;
import com.example.demo.threadPool.service.AsyncTaskService;

@RestController
@RequestMapping("/v1/async-task")
public class AsyncTaskController {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncTaskController.class);

	@Autowired
	AsyncTaskService asyncTaskService;
	
	@RequestMapping(value = "/get1", method = RequestMethod.GET)
	public String testExp1() {
		//采用对象锁的方式可以实现
		synchronized (SingleClass.getSingle()) {
			for (int i = 0; i < 10; i++) {
				System.out.println("bbbbb" + System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(10);
		}
		return "hello get1";
	}

	@RequestMapping(value = "/get2", method = RequestMethod.GET)
	public String testExp2() {
		synchronized (SingleClass.getSingle()) {
			for (int i = 0; i < 10; i++) {
				System.out.println("aaaaa" + System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(10);
		}
		return "hello get2";
	}
	
	@RequestMapping(value="/get3", method=RequestMethod.GET)
	public String testExp3() throws InterruptedException {
		for (int i = 0; i < 50; i++) {
			asyncTaskService.doTask(i);
		}
		LOG.info("All tasks are over!");
		return "testExp3 over!";
	}
	
}
