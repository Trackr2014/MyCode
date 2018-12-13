package com.spring.demo.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.service.AlarmService;

public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private ApplicationContext applicationContext;

	private AlarmService proxy;

	@PostConstruct
	public void init() {
		proxy = applicationContext.getBean(AlarmService.class);
	}

	public void blockingAlarm() {
		proxy.moveCurrent2History();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void moveCurrent2History() {
		// TODO Auto-generated method stub

	}

}
