
package com.cloudtec.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloudtec.modules.sys.entity.Dict;



@Repository("dictDao")
public interface DictDao extends JpaRepository<Dict, String> {

	/**
	 * @Title: DictDao.findAllByType
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO
	 * @return List<Dict>
	 */
	@Query("select d from Dict d where d.type=?1 order by sort asc")
	List<Dict> findAllByType(String type);

	/**
	 * @Title: DictDao.finByRecid
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO
	 * @param recid
	 * @return Dict
	 */
	@Query("select d from Dict d where d.recid = ?1")
	Dict finByRecid(String recid);

}
