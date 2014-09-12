/**
 * Project Name:jdp-iwp 
 * File Name:UserServiceTest.java 
 * Package Name:com.cloudtec.modules.sys.service 
 * Date:2014-8-18下午4:17:12 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;




/** 
 * @ClassName: UserServiceTest <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-8-18 下午4:17:12 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */

public class UserServiceTest {

	/**
	 * Test method for {@link com.cloudtec.modules.sys.service.UserService#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() {
		String id="4bb29ebb5d8246bdac6a827fe5df6a7e";
		ClassPathXmlApplicationContext ctxApplicationContext = new ClassPathXmlApplicationContext("config/spring-config.xml");
		UserService userService = ctxApplicationContext.getBean("userService", UserService.class);
		userService.delete(id);
	}

}
