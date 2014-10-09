/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.cloudtec.modules.sys.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloudtec.common.persistence.BaseEntity;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.modules.sys.utils.DictUtils;


/**
 * 字典Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "RBAC_DICT")
@DynamicInsert @DynamicUpdate
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends BaseEntity<Dict> {

	private static final long serialVersionUID = 1L;
	private String label;	// 标签名
	private String value;	// 数据值
	private String type;	// 类型
	private String description;// 描述
	private Integer sort;	// 排序

	public Dict() {
		super();
	}
	
	public Dict(String recid) {
		this();
		this.recid = recid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @Title: Dict.isOk
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO
	 * @return boolean
	 */
	@Transient
	public boolean isOk() {
		if(StringUtils.isBlank(type)||StringUtils.isBlank(label)||StringUtils.isBlank(value)){
			return false;
		}
		List<Dict> dicts = DictUtils.getDictList(type);
		for(Dict dict : dicts){
			if(dict.getLabel().equals(label) && dict.getValue().equals(value) && !dict.getRecid().equals(recid)){
				return false;
			}
		}
		return true;
	}
	
}