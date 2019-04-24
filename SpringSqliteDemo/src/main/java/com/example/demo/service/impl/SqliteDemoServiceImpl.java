package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.StudentEntityMapper;
import com.example.demo.service.SqliteDemoService;
import com.example.demo.utils.SortList;

@Service
public class SqliteDemoServiceImpl implements SqliteDemoService {
	
	long Number = 10000;
	
	@Autowired
	StudentEntityMapper studentEntityMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
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
	@Transactional(rollbackFor=Exception.class)
	public void insertSec() {
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		for (int i = 0; i < Number; i++) {
			StudentEntity studentEntity = new StudentEntity(UUID.randomUUID().toString(), "TOM", "12", "SHANGHAI");
			studentEntities.add(studentEntity);
		}
		long startTime = System.currentTimeMillis();
		studentEntityMapper.insertList(studentEntities);
		long endTime = System.currentTimeMillis();
		long timeDef = endTime - startTime;
		System.out.println("批量插入" + Number + "条耗时：" + timeDef);
	}

	@Override
	public List<StudentEntity> getList() {
		List<StudentEntity> list = studentEntityMapper.getStudent();
		SortList<StudentEntity> sortList = new SortList<>(StudentEntity.class);
		sortList.getSortList(list, "Name", "ASC");
		return list;
	}

}
