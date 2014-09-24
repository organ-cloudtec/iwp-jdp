/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cloudtec.common.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.cloudtec.common.utils.Reflections;
import com.cloudtec.common.utils.annotation.SearchField;
import com.google.common.collect.Maps;

public class SearchFilter {
	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0].toUpperCase());

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
	/**
	 * 提取实体对象中的@SearchField注解组合成Map
	 * @Title: SearchFilter.parse
	 * @Author wangqi01 2014-9-23
	 * @Description: TODO
	 * @param entity
	 * @return Map<String,SearchFilter>
	 *
	 */
	public static Map<String, SearchFilter> parse(Object entityObject) {
		Map<String, SearchFilter> filters = Maps.newHashMap();
		Field[] fs = entityObject.getClass().getDeclaredFields();
		Object val;
		for (Field f : fs){
			SearchField ef = f.getAnnotation(SearchField.class);
			if (ef != null && ef.operator() != null){
				val = Reflections.invokeGetter(entityObject, f.getName());
				if(val != null){
					SearchFilter filter = new SearchFilter(f.getName(), ef.operator(),val);
					filters.put(f.getName()+"_"+ef.operator(), filter);
				}
			}
		}
		// Get annotation method
		Method[] ms = entityObject.getClass().getDeclaredMethods();
		for (Method m : ms){
			SearchField ef = m.getAnnotation(SearchField.class);
			if (ef != null && ef.operator() != null){
				val = Reflections.invokeMethod(entityObject, m.getName(), new Class[] {}, new Object[] {});
				if(val != null){
					SearchFilter filter = new SearchFilter(m.getName().substring(3).toLowerCase(), ef.operator(),val);
					filters.put(m.getName().substring(3).toLowerCase()+"_"+ef.operator(), filter);
				}
			}
		}
		return filters;
	}
}
