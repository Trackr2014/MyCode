package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.PropertiesValueBean;

@RestController
@RequestMapping(value = "/v1/properties")
public class PropertiesValueController {
	
	@Autowired
	PropertiesValueBean propertiesValueBean;
	
	@Value("${server.port}")
	private String serverPort;
	
	@RequestMapping(value = "/bean",method = RequestMethod.GET)
	public PropertiesValueBean getProperties() {
		return propertiesValueBean;	
	}
	
	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public String getPropertyString() {
		return serverPort;
	}
	
	
}
