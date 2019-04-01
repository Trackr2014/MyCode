package com.example.demo.threadPool.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @author wang.pengfei
 * @description 线程池配置类 配置 非阻塞线程池
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration2 {
	

	private ThreadPoolExecutor pool = null;
	
	public ThreadPoolExecutor getPool() {
		return pool;
	}

	public void setPool(ThreadPoolExecutor pool) {
		this.pool = pool;
	}

	public void init() {
		pool = new ThreadPoolExecutor(
				1, 
				3, 
				30, 
				TimeUnit.MINUTES, 
				new ArrayBlockingQueue<>(5),
				new CustomThreadFactory(),
				new CustomRejectedExecutionHandler());
	}
	
	public void destory() {
		if (null != pool) {
			pool.shutdown();
		}
	}
	
	private class CustomThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable arg0) {
			Thread thread = new Thread(arg0);
			thread.setName("Configuration2");
			return thread;
		}

	}

	private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			try {
				executor.getQueue().put(r);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
