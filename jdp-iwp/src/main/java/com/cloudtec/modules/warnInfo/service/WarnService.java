package com.cloudtec.modules.warnInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cloudtec.common.service.BaseService;
import com.cloudtec.modules.sys.utils.PageBuildUtils;
import com.cloudtec.modules.warnInfo.dao.WarnDao;
import com.cloudtec.modules.warnInfo.entity.Warn;

@Service("warnService")
public class WarnService extends BaseService {

	@Autowired
	@Qualifier("warnDao")
	private WarnDao warnDao;

	/**
	 * @Title: WarnService.findEntityById
	 * @Author wangqi01 2014-11-17
	 * @Description: TODO
	 * @param recid
	 * @return Warn
	 * 
	 */
	public Warn findEntityById(String recid) {
		return warnDao.findWarnById(recid);
	}

	/**
	 * @Title: WarnService.saveWarnInfo
	 * @Author wangqi01 2014-11-17
	 * @Description: TODO
	 * @param warn
	 * @return Boolean
	 * 
	 */
	public Boolean saveWarnInfo(Warn warn) {
		try{
			warnDao.save(warn);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * @Title: WarnService.findWarns
	 * @Author wangqi01 2014-11-17
	 * @Description: TODO
	 * @param warn
	 * @param pageNo
	 * @param pageSize
	 * @param object
	 * @return Page<Warn>
	 * 
	 */
	public Page<Warn> findWarns(Warn warn, Integer pageNo, Integer pageSize,
			String sort) {
		PageBuildUtils<Warn> pageUtils = new PageBuildUtils<Warn>();
		PageRequest pageRequest = pageUtils.buildPageRequest(pageNo, pageSize, sort);
		return warnDao.findAll(pageUtils.buildSpecification(warn),pageRequest);
	}
}
