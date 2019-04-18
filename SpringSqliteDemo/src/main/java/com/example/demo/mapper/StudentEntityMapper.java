package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.StudentEntity;

@Mapper
public interface StudentEntityMapper {

	void insert(StudentEntity studentEntity);

	void insertList(@Param("studentEntities")List<StudentEntity> studentEntities);
	
}
