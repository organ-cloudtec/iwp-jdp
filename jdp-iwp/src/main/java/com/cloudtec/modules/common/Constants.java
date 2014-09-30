/**
 * Project Name:jdp-iwp 
 * File Name:Constants.java 
 * Package Name:com.cloudtec.modules.common 
 * Date:2014-9-22下午4:00:30 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.common;
/** 
 * @ClassName: Constants <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-9-22 下午4:00:30 <br/> 
 * 
 * @author wangqi01 
 * @version
 * @since JDK 1.6 
 */

public final class Constants {
	
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
	
	//菜单根节点ID
	public static String RECID_MENU_ROOTID="TOP_MENU_ID";
	//管理员ID
	public static String RECID_USER_ADMINID = "ADMINID";
	
	//分页默认每页记录数
	public  final static int DEFAULT_PAGE_SIZE = 10;
	
	
	//密码加密，验证密码使用
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
}
