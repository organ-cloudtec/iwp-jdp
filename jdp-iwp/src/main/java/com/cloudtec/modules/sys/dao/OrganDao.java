/**
 * Project Name:jdp-iwp 
 * File Name:OrganDao.java 
 * Package Name:com.cloudtec.modules.sys.dao 
 * Date:2014-8-19下午3:17:51 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.Organ;




/** 
 * @ClassName: OrganDao <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-8-19 下午3:17:51 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Repository("organDao")
public interface OrganDao extends JpaRepository<Organ,String>,JpaSpecificationExecutor<Organ>{

}
