package com.cloudtec.modules.sys.controller;

public final  class ContantsRbac {
	//菜单根节点ID
	public static String RECID_MENU_ROOTID="ROOTID";
	//管理员ID
	public static String RECID_USER_ADMINID = "ADMINID";
	
	//分页默认每页记录数
	public  final static String DEFAULT_PAGE_SIZE = "10";
	
	
	//密码加密，验证密码使用
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
}
