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

import com.cloudtec.modules.common.Constants;
import com.cloudtec.modules.sys.entity.Menu;



/**
 * @ClassName: MenuDao
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:58:14
 */
@Repository("menuDao")
public interface MenuDao extends JpaRepository<Menu, String>{
	
	Menu findByRecid(String recid);

	/**
	 * @Title: MenuDao.findByUserRecid
	 * @Author wangqi01 2014-8-20
	 * @Description: TODO
	 * @param recid
	 * @return List<Menu>
	 * 根据用户获取对应的菜单信息
	 */
	@Query("select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)" +
			" and u.recid=:recid order by m.sort")
	List<Menu> findByUserRecid(String recid);

	/**
	 * @Title: MenuDao.findByRecids
	 * @Author wangqi01 2014-9-22
	 * @Description: TODO
	 * @param menuIds
	 * @return List<Menu>
	 * 
	 */
	@Query("select m from Menu m where m.recid in (?1) " +
			"and m.isShow = "+ Constants.SHOW +
			" order by m.sort")
	List<Menu> findByRecids(String menuIds);

	/**
	 * @Title: MenuDao.findAllShow
	 * @Author wangqi01 2014-9-22
	 * @Description: TODO
	 * @return List<Menu>
	 */
	@Query("select m from Menu m where " +
			"m.isShow = "+ Constants.SHOW +
			" order by m.sort")
	List<Menu> findAllShow();

}
