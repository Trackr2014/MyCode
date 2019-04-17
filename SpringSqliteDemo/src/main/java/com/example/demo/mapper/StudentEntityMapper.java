package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.StudentEntity;

@Mapper
public interface StudentEntityMapper {

	void insert(StudentEntity studentEntity);

	void insertList(List<StudentEntity> studentEntities);
	
}
