package com.cloudtec.modules.warnInfo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloudtec.common.persistence.BaseEntity;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.modules.sys.entity.Dict;
import com.cloudtec.modules.sys.utils.DictUtils;
import com.google.common.collect.Lists;
@Entity
@Table(name = "RBAC_WARNINFO_WARN")
@DynamicInsert @DynamicUpdate
public class Warn extends BaseEntity<Warn>{

	private static final long serialVersionUID = -3993093245035454295L;
	public static final String ISSUEDICTTYPE ="issueClient";
	
	private String content; //文本内容
	private Date createAt;
	private String author;
	private String issueClient; //发布终端
	private String title;
	private List<Dict> issueClientDicts = Lists.newArrayList();
	private String issueClientLabel;
	
	public String getContent() {
		return content;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public String getAuthor() {
		return author;
	}
	public String getIssueClient() {
		return issueClient;
	}
	@Transient
	public List<Dict> getIssueClientDicts() {
		if(!StringUtils.isBlank(this.issueClient)){
			String[] clients = issueClient.split(",");
			for(String dictId : clients){
				issueClientDicts.add(DictUtils.getDict(dictId, ISSUEDICTTYPE));
			}
		}
		return issueClientDicts;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setIssueClient(String issueClient) {
		this.issueClient = issueClient;
	}
	public void setIssueClientDicts(List<Dict> issueClientDicts) {
		this.issueClientDicts = issueClientDicts;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient
	public String getIssueClientLabel() {
		return issueClientLabel;
	}
	public void setIssueClientLabel(String issueClientLabel) {
		this.issueClientLabel = issueClientLabel;
	}
	
}
