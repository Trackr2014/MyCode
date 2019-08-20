package com.example.demo.singleCase;

/**
 * @author wang.pengfei
 *   单例模式，读取配置信息
 */
public class ApplicationConfig {
	private static volatile ApplicationConfig config;
	
	private ApplicationConfig() {};
	/**
	 *  采用双层校验锁
	 */
	public static ApplicationConfig getInstance() {
		if (null == config) {
			synchronized (ApplicationConfig.class) {
				if (null == config) {
					config = new ApplicationConfig();
				}
			}
		}
		return config;
	}
	
	public String getConfiguration() {
		return "112233";
	}
}
