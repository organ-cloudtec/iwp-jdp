/**
 * 
 */
package com.cloudtec.modules.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import com.cloudtec.common.persistence.BaseEntity;
import com.cloudtec.common.utils.annotation.ExeclField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

@Entity
@DynamicInsert @DynamicUpdate
@Table(name="RBAC_USER")
public class User extends BaseEntity<User>{
	private static final long serialVersionUID = 581598688846851054L;
	private String name;
	private String username;
	private String password;
	private Organ organ;
	private List<Role> roleList = new ArrayList<Role>();
	private List<String> roleIdList;
	
	@ManyToOne
	@JoinColumn(name="ORG_RECID")
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "RBAC_USER_ROLE",joinColumns={@JoinColumn(name="USER_RECID")},inverseJoinColumns={@JoinColumn(name="ROLE_RECID")})
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	@Column(name="USR_NAME",nullable=false)
	public String getName() {
		return name;
	}
	@Column(name="USR_ID",nullable=false,unique=true)
	public String getUsername() {
		return username;
	}
	@Column(name="PWD",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public boolean isAdmin() {
		return true;
	}
	@Transient
	@JsonIgnore
	public List<String> getRoleIdList() {
		roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getRecid());
		}
		return roleIdList;
	}
	@Transient
	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setRecid(roleId);
			roleList.add(role);
		}
	}
}
