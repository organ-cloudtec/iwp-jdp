/**
 * @Title: MenuService.java
 * @Package com.lovexiao.jmp.modules.sys.services
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午10:57:53
 * @version V1.0
 */

package com.cloudtec.modules.sys.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cloudtec.modules.sys.dao.MenuDao;
import com.cloudtec.modules.sys.entity.Menu;
import com.cloudtec.modules.sys.entity.User;




/**
 * @ClassName: MenuService
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:57:53
 */
@Service("menuService")
public class MenuService {

	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDao;

	public Page<Menu> findMenus(Map<String, Object> searchMap, int pageNumber,
			int pageSize, Object object, Map<String, Object> searchMap2) {
		;
		return menuDao.findAll(new PageRequest(pageNumber - 1, pageSize));
	}
}
