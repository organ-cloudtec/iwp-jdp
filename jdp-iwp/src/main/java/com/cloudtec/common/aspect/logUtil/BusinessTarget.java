package com.cloudtec.common.aspect.logUtil;

import java.io.Serializable;

/**
 * 业务目标元数据(即要拦截的方法),用于填充查询下拉框
 * @author wangqi01
 *
 */
public class BusinessTarget implements Serializable {
	private static final long serialVersionUID = 1L;
	private String className;
	private String methodName;
	private String methodArgs;
	private String methodDesc;
	private String moduleName;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodArgs() {
		return methodArgs;
	}
	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}
	public String getMethodDesc() {
		return methodDesc;
	}
	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
