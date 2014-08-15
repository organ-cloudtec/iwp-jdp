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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


import com.cloudtec.common.persistence.BaseEntity;
import com.cloudtec.modules.sys.controller.ContantsRbac;

@Entity
@Table(name="RBAC_MENU")
public class Menu extends BaseEntity<Menu>{

	private static final long serialVersionUID = -3226773540248408395L;
	
	private String name; //名称
	private String nameMng;
	private String url; //链接
	private String target;//mainFrame、_blank、_self、_parent、_top
	private String permissionFlag; //权限标识
	private String icon; 	// 图标
	private Integer sort; 	// 排序
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String parents;// 所有父级编号
	private Menu parent; //父级菜单
	private List<Menu> childList = new ArrayList<Menu>();
	private List<Role> roleList = new ArrayList<Role>();
	public Menu(){
		super();
		this.sort = 30;
	}
	
	public Menu(String recid){
		this();
		this.recid = recid;
	}
	//mappedBy="menuList",
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RBAC_ROLE_MENU",joinColumns={@JoinColumn(name="ROLE_RECID")},inverseJoinColumns={@JoinColumn(name="MENU_RECID")})
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}

	@Column(nullable=false)
	public String getNameMng() {
		return nameMng;
	}

	public String getUrl() {
		return url;
	}

	public String getTarget() {
		return target;
	}
	public String getPermissionFlag() {
		return permissionFlag;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_ID")
	public Menu getParent() {
		return parent;
	}

	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	public List<Menu> getChildList() {
		return childList;
	}
	public String getParents() {
		return parents;
	}
	public String getIcon() {
		return icon;
	}
//	@NotNull
	public Integer getSort() {
		return sort;
	}
	public String getIsShow() {
		return isShow;
	}

	
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setParents(String parents) {
		this.parents = parents;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public void setNameMng(String nameMng) {
		this.nameMng = nameMng;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setPermissionFlag(String permissionFlag) {
		this.permissionFlag = permissionFlag;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

	@Transient
	public boolean isRoot(){
		return isRoot(this.recid);
	}
	
	@Transient
	public static boolean isRoot(String recid){
		return recid != null && recid.equals(ContantsRbac.RECID_MENU_ROOTID);
	}
}
