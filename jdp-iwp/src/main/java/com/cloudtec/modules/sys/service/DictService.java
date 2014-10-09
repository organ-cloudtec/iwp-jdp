/**
 * Project Name:jdp-iwp 
 * File Name:DictService.java 
 * Package Name:com.cloudtec.modules.sys.service 
 * Date:2014-10-8下午2:38:27 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cloudtec.common.service.BaseService;
import com.cloudtec.modules.sys.dao.DictDao;
import com.cloudtec.modules.sys.entity.Dict;

/** 
 * @ClassName: DictService <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-10-8 下午2:38:27 <br/> 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Service("dictService")
public class DictService extends BaseService {

	@Autowired
	@Qualifier("dictDao")
	private DictDao dictDao;

	/**
	 * @return 
	 * @Title: DictService.findAll
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO  获取所用枚举项
	 */
	public List<Dict> findAll() {
		return dictDao.findAll();
	}
	
	/**
	 * 根据分类获取，不同类型
	 * @Title: DictService.findAllByType
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO
	 * @param type
	 * @return List<Dict>
	 *
	 */
	public List<Dict> findAllByType(String type){
		return dictDao.findAllByType(type);
	}

	/**
	 * @Title: DictService.findByRecid
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO
	 * @param recid
	 * @return Dict
	 * 
	 */
	public Dict findByRecid(String recid) {
		return dictDao.finByRecid(recid);
	}

	/**
	 * @Title: DictService.save
	 * @Author wangqi01 2014-10-9
	 * @Description: TODO
	 * @param dict void
	 * 
	 */
	public void save(Dict dict) {
		dictDao.saveAndFlush(dict);
	}

	/**
	 * @Title: DictService.delete
	 * @Author wangqi01 2014-10-9
	 * @Description: TODO
	 * @param dict void
	 * 
	 */
	public boolean delete(Dict dict) {
		try{
			dictDao.delete(dict.getRecid());
		}catch(Exception e){
			logger.error(getCurrentUser().getUsername() + "删除基础数据失败，id:"+dict.getRecid()+"\n"+e.getMessage());
			return false;
		}
		logger.info(getCurrentUser().getUsername() + "删除基础数据成功，id:"+dict.getRecid());
		return true;
	}
}
