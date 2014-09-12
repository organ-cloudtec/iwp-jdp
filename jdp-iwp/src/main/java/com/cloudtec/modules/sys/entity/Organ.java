package com.cloudtec.modules.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloudtec.common.persistence.BaseEntity;


@Entity
@DynamicInsert @DynamicUpdate
@Table(name="RBAC_ORG")
public class Organ extends BaseEntity<Organ>{

	private static final long serialVersionUID = 2108403849665981662L;

	private String code;
	private String name;
	private Integer sort;
	private Integer grade;
	
	private Organ parent;
	private String parentIds;
	private List<Organ> childList = new ArrayList<Organ>();
	private List<User> userList = new ArrayList<User>();
	
	public Organ(){
		
	}
	public Organ(String recid) {
		this();
		this.recid = recid;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public Integer getSort() {
		return sort;
	}
	public Integer getGrade() {
		return grade;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PARENT_ID")
	public Organ getParent() {
		return parent;
	}
	@OneToMany(mappedBy = "parent", fetch=FetchType.LAZY)
	public List<Organ> getChildList() {
		return childList;
	}
	@OneToMany(mappedBy = "organ", fetch=FetchType.LAZY)
	public List<User> getUserList() {
		return userList;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public void setParent(Organ parent) {
		this.parent = parent;
	}
	public void setChildList(List<Organ> childList) {
		this.childList = childList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@JoinColumn(name="PARENTIDS")
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
}
