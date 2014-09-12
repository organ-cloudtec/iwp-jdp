package com.cloudtec.modules.sys.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cloudtec.common.persistence.DynamicSpecifications;
import com.cloudtec.common.persistence.SearchFilter;
import com.cloudtec.common.persistence.SearchFilter.Operator;
import com.cloudtec.modules.sys.dao.UserDao;
import com.cloudtec.modules.sys.entity.User;

@Service
public class UserService {

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
	  * @Description: TODO
	  * @param page
	  * @param user
	  * @return    
	  * Page<User>
	  * @throws
	  */
	public  Page<User> findUsers(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return userDao.findAll(buildSpecification(searchParams),pageRequest);//spec,
	}
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<User> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//		filters.put("user.recid", new SearchFilter("user.recid", Operator.EQ, userId));
		if(filters.size()>0)
			return DynamicSpecifications.bySearchFilter(filters.values(), User.class);
		return null;
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if (sortType == null || "auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "recid");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
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
