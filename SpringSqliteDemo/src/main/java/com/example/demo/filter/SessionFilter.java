package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "SessionFiler", urlPatterns = "/v1/sqlite/get*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("----------------------过滤器启动----------");
		String remoteIpString = request.getRemoteHost();
		if (remoteIpString.equalsIgnoreCase("127.0.0.1")) {
			response.setCharacterEncoding("utf-8");
			System.out.println("------已经拦截-------");
			return;
		}else {
			System.out.println("------放行-------");
			chain.doFilter(request, response);
		}
		return;
	}

}
