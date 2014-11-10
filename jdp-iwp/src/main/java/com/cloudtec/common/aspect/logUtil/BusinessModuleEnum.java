package com.cloudtec.common.aspect.logUtil;

public enum BusinessModuleEnum {
	userManage("用户管理","userManage"),
	roleManage("角色管理","roleManage"),
	menuManage("菜单管理","menuManage"),
	loggerManage("日志管理","loggerManage"),
	organManage("单位管理","organManage"),
	others("其他模块","others");
	
	private String desc;
	private String name;
	
	private BusinessModuleEnum(String desc,String name){
		this.desc = desc;
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
