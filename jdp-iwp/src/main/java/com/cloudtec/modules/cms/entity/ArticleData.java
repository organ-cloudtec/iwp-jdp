/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.cloudtec.modules.cms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.cloudtec.common.persistence.IdEntity;
import com.cloudtec.modules.common.Constants;

/**
 * 
 * @ClassName: ArticleData <br/> 
 * @Description: TODO 文章Entity<br/> 
 * @date: 2014-9-23 上午10:42:04 <br/> 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6
 */
@Entity
@Table(name = "RBAC_CMS_ARTICLE_DATE")
@DynamicInsert @DynamicUpdate
public class ArticleData extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String content;	// 内容
	private String copyfrom;// 来源
	private String relation;// 相关文章
	private String allowComment;// 是否允许评论

	private Article article;
	
	public ArticleData() {
		super();
		this.allowComment = Constants.YES;
	}
	
	public ArticleData(String recid){
		this();
		this.recid = recid;
	}


	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min=0, max=255)
	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	@Length(min=0, max=255)
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Length(min=1, max=1)
	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},optional=false)  
	@PrimaryKeyJoinColumn
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}