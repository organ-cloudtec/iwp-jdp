/**
 * Project Name:jdp-iwp 
 * File Name:MemcachedSessionFilter.java 
 * Package Name:com.cloudtec.common.filter 
 * Date:2014-10-27下午4:22:27 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
/** 
 * @ClassName: MemcachedSessionFilter <br/> 
 * @Description: TODO 用于实现系统无状态化，将用户session存放到 Memcache中,
 * 便于系统的水平伸缩。<待实现><br/> 
 * @date: 2014-10-27 下午4:22:27 <br/> 
 * @author wangqi01 
 */

public class MemcachedSessionFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>Title: init</p>
	 * <p>Description: </p>
	 * @param filterConfig
	 * @throws ServletException
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * wangqi01  2014-10-27
	 */

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>Title: doFilter</p>
	 * <p>Description: 实现将Session保存到</p>
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * wangqi01  2014-10-27
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		chain.doFilter(request, response);
	}

}
