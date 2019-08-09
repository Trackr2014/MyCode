package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wang.pengfei
 * 测试注解获取配置文件中的值，注解获取可以通过很多方式 如 value等。
 */
@Component
@ConfigurationProperties(prefix = "test")
public class PropertiesValueBean {
	
	private String id;
	private int age;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PropertiesValueBean [id=" + id + ", age=" + age + ", name=" + name + "]";
	}
	
	
}
