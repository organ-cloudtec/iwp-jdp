package com.cloudtec.modules.sys.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.cloudtec.common.service.BaseService;
import com.cloudtec.modules.sys.dao.UserDao;
import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.utils.PageBuildUtils;

@Service
public class UserService  extends BaseService {

	@Autowired
	private UserDao userDao;
	
	
	public User create(User user){
		User usr  = userDao.save(user);
		return usr;
	}
	
	public User findUserByLoginName(String loginName){
		return userDao.findUserByLoginName(loginName);
	}
	/**
	 * @param user 
	  * @Title: findUsers
	  * @Author wangqi01 2014-8-12
	  * @Description: TODO 分页显示
	  * Page<User>
	  * @throws
	  */
	public  Page<User> findUsers(User user, int pageNumber, int pageSize,
			String sortType) {
		PageBuildUtils<User> pageUtils = new PageBuildUtils<User>();
		PageRequest pageRequest = pageUtils.buildPageRequest(pageNumber, pageSize, sortType);
		return userDao.findAll(pageUtils.buildSpecification(user),pageRequest);
	}
	/**
	 * @Title: UserService.findAllUsers
	 * @Author wangqi01 2014-11-5
	 * @Description: TODO
	 * @return List<User>
	 */
	public List<User> findAllUsers(){
		return userDao.findAll();
	}
	/**
	  * @Title: findByRecid
	  * @Author wangqi01 2014-8-12
	  * @Description: TODO
	  * @param recid
	  * @return    
	  * User
	  * @throws
	  */
	public User findByRecid(String recid) {
		return userDao.findByRecid(recid);
	}

	public void delete(String recid) {
		userDao.deleteByRecid(recid);
	}
}
