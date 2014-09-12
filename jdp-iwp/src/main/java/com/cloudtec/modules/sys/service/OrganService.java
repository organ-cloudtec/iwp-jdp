/**
 * Project Name:jdp-iwp 
 * File Name:OrganService.java 
 * Package Name:com.cloudtec.modules.sys.service 
 * Date:2014-8-19下午3:16:48 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cloudtec.modules.sys.dao.OrganDao;
import com.cloudtec.modules.sys.entity.Organ;




/** 
 * @ClassName: OrganService <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-8-19 下午3:16:48 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Service("organService")
public class OrganService {

	@Autowired
	@Qualifier(value="organDao")
	private OrganDao organDao;

	/**
	 * @Title: OrganService.findAll
	 * @Author wangqi01 2014-8-19
	 * @Description: TODO
	 * @return List<Organ>
	 * 
	 */
	public List<Organ> findAll() {
		return organDao.findAll();
	}
}
