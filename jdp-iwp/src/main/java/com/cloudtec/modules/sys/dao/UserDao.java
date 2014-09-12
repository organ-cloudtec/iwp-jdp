package com.cloudtec.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	@Query("select u from User u where u.username=?1")
	User findUserByLoginName(String userName);

	/**
	  * @Title: findByRecid
	  * @Author wangqi01 2014-8-12
	  * @Description: TODO(根据ID获取用户信息)
	  * @param recid
	  * @return User
	  */
	@Query("select u from User u where u.recid =?1")
	User findByRecid(String recid);
	
	@Modifying
	@Query("delete from User u where u.recid = ?1")
	void deleteByRecid(String recid);
}
