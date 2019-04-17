package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.StudentEntityMapper;
import com.example.demo.service.SqliteDemoService;

@Service
public class SqliteDemoServiceImpl implements SqliteDemoService {
	
	long Number = 10000;
	
	@Autowired
	StudentEntityMapper studentEntityMapper;
	
	@Override
	public void insertFirst() {
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		for (int i = 0; i < Number; i++) {
			StudentEntity studentEntity = new StudentEntity(UUID.randomUUID().toString(), "TOM", "12", "SHANGHAI");
			studentEntities.add(studentEntity);
		}
		long startTime = System.currentTimeMillis();
		for (StudentEntity studentEntity : studentEntities) {
			studentEntityMapper.insert(studentEntity);
		}
		long endTime = System.currentTimeMillis();
		long timeDef = endTime - startTime;
		System.out.println("逐条插入" + Number + "条耗时：" + timeDef);
	}

	@Override
	public void insertSec() {
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		for (int i = 0; i < 10000; i++) {
			StudentEntity studentEntity = new StudentEntity(UUID.randomUUID().toString(), "TOM", "12", "SHANGHAI");
			studentEntities.add(studentEntity);
		}
		long startTime = System.currentTimeMillis();
		studentEntityMapper.insertList(studentEntities);
		long endTime = System.currentTimeMillis();
		long timeDef = endTime - startTime;
		System.out.println("批量插入" + Number + "条耗时：" + timeDef);
	}

}
