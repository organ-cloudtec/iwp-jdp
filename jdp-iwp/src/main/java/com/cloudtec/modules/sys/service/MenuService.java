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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cloudtec.common.service.BaseService;
import com.cloudtec.common.service.ServiceException;
import com.cloudtec.modules.sys.dao.MenuDao;
import com.cloudtec.modules.sys.entity.Menu;
import com.cloudtec.modules.sys.entity.Role;

/**
 * @ClassName: MenuService
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:57:53
 */
@Service("menuService")
public class MenuService  extends BaseService {

	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDao;

	public Page<Menu> findMenus(Map<String, Object> searchMap, int pageNumber,
			int pageSize, Object sort) {
		return menuDao.findAll(new PageRequest(pageNumber - 1, pageSize));
	}

	/**
	 * @Title: MenuService.findAllExpectNoShow
	 * @Author wangqi01 2014-9-22
	 * @Description: TODO 默认显示
	 * @return List<Menu>
	 */
	public List<Menu> findAllExpectNoShow() {
		return menuDao.findAllShow();
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

	/**
	 * @Title: MenuService.save
	 * @Author wangqi01 2014-9-15
	 * @Description: TODO
	 * @param menu void
	 * 
	 */
	public void save(Menu menu) {
		menuDao.save(menu);
	}

	/**
	 * @Title: MenuService.findAllByRecids
	 * @Author wangqi01 2014-9-16
	 * @Description: TODO
	 * @param menuIds
	 * @return List<Menu>
	 * 
	 */
	public List<Menu> findAllByRecids(String menuIds) {
		return menuDao.findByRecids(menuIds);
	}

	/**
	 * @Title: MenuService.delete
	 * @Author wangqi01 2014-9-19
	 * @Description: TODO
	 * @param menu void
	 * 
	 */
	public boolean delete(Menu menu) {
		try{
			menu = menuDao.findByRecid(menu.getRecid());
			for(Role role : menu.getRoleList()){
				role.getMenuList().remove(menu);
			}
			menuDao.delete(menu.getRecid());
		}catch(Exception e){
			logger.error("删除菜单失败，菜单ID :"+ menu.getRecid(), e);
			throw new ServiceException(e.getMessage());
		}
		return true;
	}

	/**
	 * @Title: MenuService.findAll
	 * @Author wangqi01 2014-9-22
	 * @Description: TODO
	 * @return List<Menu>
	 * 
	 */
	public List<Menu> findAll() {
		//默认按照 sort升序排序
		return menuDao.findAll(new Sort("sort"));
	}
}
