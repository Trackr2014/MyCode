package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.StudentEntity;

public interface SqliteDemoService {

	void insertFirst();

	void insertSec();

	List<StudentEntity> getList();

}
