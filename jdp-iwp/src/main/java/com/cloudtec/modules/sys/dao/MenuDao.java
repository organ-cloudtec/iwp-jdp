/**
 * @Title: MenuDao.java
 * @Package com.lovexiao.jmp.modules.sys.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午10:58:14
 * @version V1.0
 */

package com.cloudtec.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.Menu;



/**
 * @ClassName: MenuDao
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:58:14
 */
@Repository("menuDao")
public interface MenuDao extends JpaRepository<Menu, String>{
	
	List<Menu> findByRecid(String recid);
	

}
