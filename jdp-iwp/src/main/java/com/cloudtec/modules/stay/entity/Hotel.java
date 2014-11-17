package com.cloudtec.modules.stay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloudtec.common.persistence.BaseEntity;
import com.cloudtec.common.utils.search.annontation.SearchField;
import com.cloudtec.common.utils.search.status.Operator;
@Entity
@DynamicInsert @DynamicUpdate
@Table(name="RBAC_STAY_HOTEL")
public class Hotel extends BaseEntity<Hotel> {
	private static final long serialVersionUID = 1L;
	private String name;          //濮撳悕
	private String address;       //鍦板潃
	private String corporation;  //娉曚汉
	private String phone;
	private String flexdLine;
	private String level;
	private String description;
	private String imageUrl;
	
	@SearchField(operator = Operator.LIKE)
	public String getName() {
		return name;
	}
	@SearchField(operator = Operator.LIKE)
	public String getAddress() {
		return address;
	}
	public String getCorporation() {
		return corporation;
	}
	public String getPhone() {
		return phone;
	}
	public String getFlexdLine() {
		return flexdLine;
	}
	public String getLevel() {
		return level;
	}
	public String getDescription() {
		return description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setFlexdLine(String flexdLine) {
		this.flexdLine = flexdLine;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
