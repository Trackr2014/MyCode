package com.example.demo.threadPool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.threadPool.service.AsyncTaskService;

@Service
public class AsyncTaskServiceImpl implements AsyncTaskService{

	private static final Logger LOG = LoggerFactory.getLogger(AsyncTaskServiceImpl.class);

	@Override
	@Async
	public void doTask(int i) {
		LOG.info("this is task" + i);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Async
	public void doMyself(int i) {
		LOG.info("I DO MYSELF" + i);
	}

	@Override
	@Async
	public void doTaskPool() {
		LOG.info("I DO ThreadPoolConfiguration2 task");
	}
}
