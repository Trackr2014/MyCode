package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.PropertiesValueBean;
import com.example.demo.singleCase.ApplicationConfig;

@RestController
@RequestMapping(value = "/v1/properties")
public class PropertiesValueController {

	@Autowired
	PropertiesValueBean propertiesValueBean;

	@Value("${http.port}")
	private String httpPort;

	@RequestMapping(value = "/bean", method = RequestMethod.GET)
	public PropertiesValueBean getProperties() {
		return propertiesValueBean;
	}

	@RequestMapping(value = "/port", method = RequestMethod.GET)
	public String getPortProperties() {
		return httpPort;
	}

	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public String getPropertyString() {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					ApplicationConfig config = ApplicationConfig.getInstance();
					System.out.println("=====" + config);
					System.out.println(config.getConfiguration()+ "======");
				}

			}.start();
			System.out.println("i======" + i);
		}
		return httpPort;
	}

}
