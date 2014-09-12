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

import java.util.List;
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
			int pageSize, Object sort) {
		;
		return menuDao.findAll(new PageRequest(pageNumber - 1, pageSize));
	}

	/**
	 * @Title: MenuService.findAll
	 * @Author wangqi01 2014-8-19
	 * @Description: TODO
	 * @return List<Menu>
	 * 
	 */
	public List<Menu> findAll() {
		return menuDao.findAll();
	}

	/**
	 * @Title: MenuService.findByRecid
	 * @Author wangqi01 2014-8-20
	 * @Description: TODO
	 * @param recid
	 * @return Menu
	 * 
	 */
	public Menu findByRecid(String recid) {
		return menuDao.findByRecid(recid);
	}
}
