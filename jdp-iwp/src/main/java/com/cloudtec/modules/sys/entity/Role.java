package com.cloudtec.modules.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloudtec.common.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.common.collect.Lists;

@Entity
@DynamicInsert @DynamicUpdate
@Table(name="RBAC_ROLE")
public class Role extends BaseEntity<Role>{

	private static final long serialVersionUID = -35684964401400002L;
	
	private String name;
	private String nameMng;
	private List<Menu> menuList = new ArrayList<Menu>();
	private List<User> userList = new ArrayList<User>();
	private String menuIds;
	private String menuNames;
	
	
	@ManyToMany(mappedBy = "roleList", fetch=FetchType.LAZY)
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getName() {
		return name;
	}
	public String getNameMng() {
		return nameMng;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="RBAC_ROLE_MENU",joinColumns={@JoinColumn(name="ROLE_RECID",referencedColumnName="recid")  },inverseJoinColumns={@JoinColumn(name="MENU_RECID",referencedColumnName="recid")})
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNameMng(String nameMng) {
		this.nameMng = nameMng;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	@Transient
	public String getMenuIds() {
		StringBuilder strBuilder = new StringBuilder();
		for(Menu menu : menuList){
			strBuilder.append(menu.getRecid()).append(",");
		}
		menuNames = strBuilder.toString();
		return menuIds;
	}
	@Transient
	public String getMenuNames() {
		StringBuilder strBuilder = new StringBuilder();
		for(Menu menu : menuList){
			strBuilder.append(menu.getName()).append(",");
		}
		menuNames = strBuilder.toString();
		return menuNames;
	}
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		for(String id :  menuIds.split(",")){
			Menu menu = new Menu();
			menu.setRecid(id);
			menuList.add(menu);
		}
		this.menuIds = menuIds;
	}
	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}
	
}
