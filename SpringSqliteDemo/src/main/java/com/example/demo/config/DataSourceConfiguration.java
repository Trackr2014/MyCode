package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DataSourceConfiguration {

    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean<?> servletRegistrationBean() {
        ServletRegistrationBean<?> serv = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
        serv.addInitParameter("loginUserName", "admin");
        serv.addInitParameter("loginPassword", "123456a?");
        return serv;
    }

    @Bean
    public FilterRegistrationBean<?> statFilter() {
        FilterRegistrationBean<?> fileFilterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        fileFilterRegistrationBean.addUrlPatterns("/*");
        fileFilterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return fileFilterRegistrationBean;

    }
}
