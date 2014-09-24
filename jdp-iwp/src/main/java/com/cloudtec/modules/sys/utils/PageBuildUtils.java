/**
 * Project Name:jdp-iwp 
 * File Name:PageBuildUtils.java 
 * Package Name:com.cloudtec.modules.sys.utils 
 * Date:2014-9-18下午4:05:09 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.cloudtec.common.persistence.DynamicSpecifications;
import com.cloudtec.common.persistence.SearchFilter;


/** 
 * @ClassName: PageBuildUtils <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-9-18 下午4:05:09 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */

public class PageBuildUtils<T> {
	private Class<T> entityClass;
	@SuppressWarnings({"unchecked", "rawtypes"})
	public PageBuildUtils(){
		this.entityClass = null;
		Class objClass = getClass();
		Type objType = objClass.getGenericSuperclass();
		if (objType instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) objType).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	/**
	 * 创建动态查询条件组合.
	 */
	public Specification<T> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		if(filters.size()>0)
			return DynamicSpecifications.bySearchFilter(filters.values(), entityClass);
		return null;
	}
	/**
	 * 创建动态查询条件组合.
	 */
	public Specification<T> buildSpecification(T entity) {
		Map<String, SearchFilter> filters = SearchFilter.parse(entity);
		if(filters.size()>0)
			return DynamicSpecifications.bySearchFilter(filters.values(), entityClass);
		return null;
	}
	
	/**
	 * 创建分页请求.
	 */
	public PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if (sortType == null || "auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "recid");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
}
