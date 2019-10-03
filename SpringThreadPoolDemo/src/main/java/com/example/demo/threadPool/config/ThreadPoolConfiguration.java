package com.example.demo.threadPool.config;


import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfiguration implements AsyncConfigurer {


    public class MySelfRejectClass implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable cmdRunnable, ThreadPoolExecutor ePoolExecutor) {
            try {
                ePoolExecutor.getQueue().put(cmdRunnable);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolConfiguration.class);

    @Bean
    public Executor getPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(5);
        executor.setKeepAliveSeconds(100);
        executor.setThreadNamePrefix("MyExcutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//		executor.setRejectedExecutionHandler(new MySelfRejectClass());
        executor.initialize();
        return executor;
    }

    public AsyncUncaughtExceptionHandler getsAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                LOG.error("任务超长！！");
                LOG.error(ex.getMessage());
            }
        };

    }
}
