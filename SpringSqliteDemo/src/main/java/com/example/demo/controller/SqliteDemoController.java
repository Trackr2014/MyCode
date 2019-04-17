package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SqliteDemoService;

@RestController("/v1/sqlite")
public class SqliteDemoController {
	
	@Autowired
	SqliteDemoService sqliteDemoService;
	
	@RequestMapping(value="/insert1", method=RequestMethod.GET)
	public void insertData1() {
		sqliteDemoService.insertFirst();
	}
	
	@RequestMapping(value="/insert2", method=RequestMethod.GET)
	public void insertData2() {
		sqliteDemoService.insertSec();
	}
}
