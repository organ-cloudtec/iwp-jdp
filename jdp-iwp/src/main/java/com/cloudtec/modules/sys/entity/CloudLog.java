/**
 * Project Name:jdp-iwp 
 * File Name:CloudLog.java 
 * Package Name:com.cloudtec.modules.sys.entity 
 * Date:2014-11-10上午11:04:29 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.entity;

import java.util.Date;


/** 
 * @ClassName: CloudLog <br/> 
 * @Description: TODO 日志实体类<br/> 
 * @date: 2014-11-10 上午11:04:29 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
public class CloudLog {
	 
	private Date startAt;       //操作日期
	private String className;   //访问类名
	private String methodName;  //访问方法名
	private String argsType;    //参数类型
	private Object[] argsValues;//参数
	private String clientIp;   //访问IP
	private User user;         //用户
	private int resultStatus;  //执行结果  成功：1，失败：0
	private String exception;  //错误信息
	private Long costTime;     //执行耗时
	private String moduleName; //模块名称
	public Date getStartAt() {
		return startAt;
	}
	public String getClassName() {
		return className;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getArgsType() {
		return argsType;
	}
	public Object[] getArgsValues() {
		return argsValues;
	}
	public String getClientIp() {
		return clientIp;
	}
	public User getUser() {
		return user;
	}
	public int getResultStatus() {
		return resultStatus;
	}
	public String getException() {
		return exception;
	}
	public Long getCostTime() {
		return costTime;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setStartAt(Date date) {
		this.startAt = date;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public void setArgsType(String argsType) {
		this.argsType = argsType;
	}
	public void setArgsValues(Object[] argsValues) {
		this.argsValues = argsValues;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
