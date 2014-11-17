package com.cloudtec.modules.warnInfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.warnInfo.entity.Warn;

@Repository("warnDao")
public interface WarnDao extends JpaRepository<Warn,String>,JpaSpecificationExecutor<Warn>{

	/**
	 * @Title: WarnDao.findWarnById
	 * @Author wangqi01 2014-11-17
	 * @return Warn
	 */
	@Query("select w from Warn w where w.recid=?")
	Warn findWarnById(String recid);

}
