/**
 * Project Name:jdp-iwp 
 * File Name:CategoryDao.java 
 * Package Name:com.cloudtec.modules.cms.dao 
 * Date:2014-9-23下午2:21:24 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.cms.entity.Category;




/** 
 * @ClassName: CategoryDao <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-9-23 下午2:21:24 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Repository("categoryDao")
public interface CategoryDao extends JpaSpecificationExecutor<Category>,JpaRepository<Category,String>{

	/**
	 * @Title: CategoryDao.findByRecid
	 * @Author wangqi01 2014-9-24
	 * @Description: TODO
	 * @param recid
	 * @return Category
	 */
	@Query("select c from Category c where c.recid=?1")
	Category findByRecid(String recid);
}
