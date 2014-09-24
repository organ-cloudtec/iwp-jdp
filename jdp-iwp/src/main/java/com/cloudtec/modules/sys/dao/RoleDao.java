/**
 * @Title: RoleDao.java
 * @Package com.lovexiao.jmp.modules.sys.dao
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午11:19:19
 * @version V1.0
 */

package com.cloudtec.modules.sys.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.Role;



/**
 * @ClassName: RoleDao
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午11:19:19
 */
@Repository("roleDao")
public interface RoleDao extends JpaRepository<Role,String> {

	/**
	 * @Title: RoleDao.findByName
	 * @Author wangqi01 2014-9-22
	 * @Description: TODO
	 * @param roleName
	 * @return Role
	 * 
	 */
	@Query("select r from Role r where r.name =?1")
	Role findByName(String roleName);

}
