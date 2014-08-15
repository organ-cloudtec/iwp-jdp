/**
 * @Title: RoleService.java
 * @Package com.lovexiao.jmp.modules.sys.services
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午10:57:31
 * @version V1.0
 */

package com.cloudtec.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.cloudtec.modules.sys.dao.RoleDao;
import com.cloudtec.modules.sys.entity.Role;




/**
 * @ClassName: RoleService
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:57:31
 */
@Service("roleService")
public class RoleService  {
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	/**
	  * @Title: RoleService.findAllRole
	  * @Author wangqi01 2014-8-12
	  * @Description: TODO
	  * @return List<Role>
	  *
	 */
	public List<Role> findAllRole() {
		return roleDao.findAll();
	}
}
