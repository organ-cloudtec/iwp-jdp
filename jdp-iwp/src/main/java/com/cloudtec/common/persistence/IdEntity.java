/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.cloudtec.common.persistence;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.cloudtec.common.utils.Identities;
import com.cloudtec.common.utils.search.annontation.SearchField;
import com.cloudtec.common.utils.search.status.Operator;

@MappedSuperclass
public abstract class IdEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String recid;		// 编号
	
	protected String remarks;   //备注
	
	protected String del_flag;  //删除标识 0有效，1删除
	
	public IdEntity() {
		super();
	}
	
	@PrePersist
	public void prePersist(){
		this.recid = Identities.uuid2();
	}

	@SearchField(operator=Operator.EQ)
	@Id
	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
}
