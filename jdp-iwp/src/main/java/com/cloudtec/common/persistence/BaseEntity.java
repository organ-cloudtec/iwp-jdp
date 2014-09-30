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


import com.cloudtec.modules.common.Constants;
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
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 传递分页参数使用
	 * pageNo 要查询第几页
	 * pageSize 每页记录条数
	 */
	protected Integer  pageNo;
	protected Integer  pageSize;
	
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

	@Transient
	public Integer getPageNo() {
		return pageNo==null?1:pageNo;
	}
	@Transient
	public Integer getPageSize() {
		return pageSize==null?Constants.DEFAULT_PAGE_SIZE:pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
