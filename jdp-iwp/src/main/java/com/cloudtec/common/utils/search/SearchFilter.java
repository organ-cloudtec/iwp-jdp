/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cloudtec.common.utils.search;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.cloudtec.common.utils.Reflections;
import com.cloudtec.common.utils.search.annontation.SearchField;
import com.cloudtec.common.utils.search.status.IsInnerType;
import com.cloudtec.common.utils.search.status.Operator;
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
	public static Map<String, SearchFilter> parse(Object entityObject){
		return parse(entityObject, null);
	}
	/**
	 * 提取实体对象中的@SearchField注解组合成Map
	 * @param prefixKey 
	 * @Title: SearchFilter.parse
	 * @Author wangqi01 2014-9-23
	 * @Description: TODO
	 * @param entity
	 * @param prefixKey map.key 的前缀
	 * @return Map<String,SearchFilter>
	 *
	 */
	public static Map<String, SearchFilter> parse(Object entityObject, String prefixKey) {
		if(entityObject == null || entityObject.equals("")){
			return Maps.newHashMap();
		}
		Map<String, SearchFilter> filters = Maps.newHashMap();
		Field[] fs = entityObject.getClass().getFields();//.getDeclaredFields();
		Object val = null;
		for (Field f : fs){
			SearchField ef = f.getAnnotation(SearchField.class);
			if (ef != null && ef.operator() != null){
				val = Reflections.invokeGetter(entityObject, f.getName());
				try{
					handleVal2Filter(filters, val, ef, f.getName(),prefixKey);
				}catch(Exception  e){
					System.out.println(e.getMessage());
				}
			}
		}
		// Get annotation method
		Method[] ms = entityObject.getClass().getMethods();//.getDeclaredMethods();
		for (Method m : ms){
			SearchField ef = m.getAnnotation(SearchField.class);
			if (ef != null && ef.operator() != null){
				val = Reflections.invokeMethod(entityObject, m.getName(), new Class[] {}, new Object[] {});
				try{
					handleVal2Filter(filters, val, ef, m.getName().substring(3).toLowerCase(),prefixKey);
				}catch(Exception  e){
					System.out.println(e.getMessage());
				}
			}
		}
		return filters;
	}
	
	private static void handleVal2Filter(Map<String, SearchFilter> filters,Object val,SearchField ef,String defaultfieldName, String prefixKey) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		if (val == null || val.equals("")){
			return;
		} else if(ef.fieldType() == IsInnerType.YES){
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		}
		
		/*(val instanceof String) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		} else if (val instanceof Integer) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		} else if (val instanceof Long) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		} else if (val instanceof Double) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		} else if (val instanceof Float) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		} else if (val instanceof Date) {
			makeFilter(val, filters, ef, defaultfieldName,prefixKey);
		}*/ 
		else if (ef.fieldType() == IsInnerType.NO){
			filters.putAll(parse(val, defaultfieldName+"."));
		}
	}
	private static void makeFilter(Object val,Map<String, SearchFilter> filters,SearchField ef,String defaultfieldName, String prefixKey){
		if(val != null && !val.equals("")){
			String filedName =ef.filedName();
			if(StringUtils.isBlank(filedName)){
				filedName = defaultfieldName;
			}
			if(StringUtils.isNotBlank(prefixKey)){
				filedName = prefixKey+filedName;
			}
			SearchFilter filter = new SearchFilter(filedName, ef.operator(),val);
			filters.put(filedName+"_"+ef.operator(), filter);
		}
	}
}
