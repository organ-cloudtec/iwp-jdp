/**
 * @Title: BaseEntity.java
 * @Package com.lovexiao.jmp.common.persistence
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午11:12:59
 * @version V1.0
 */

package com.cloudtec.common.persistence;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.utils.UserUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 * @ClassName: BaseEntity
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午11:12:59
 */

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity<T> extends IdEntity {
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	@JsonIgnore
	@XmlTransient
	@Transient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	@Transient
	public Page<T> getPage() {
		if (page == null){
			page = new PageImpl<T>(null);
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}
	
	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";

	// 删除标记（0：正常；1：删除；2：审核；）
	public static final String FIELD_DEL_FLAG = "delFlag";
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
}
