package com.cloudtec.common.aspect.logUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务模块元数据,用于填充查询下拉框
 * @author wangqi01
 *
 */
public class BusinessModule implements Serializable {
	private static final long serialVersionUID = 1L;
	private String moduleMame;
	private String moduleDesc;
	private List<BusinessTarget> businessTargetList=new ArrayList<BusinessTarget>();
	
	public String getModuleMame() {
		return moduleMame;
	}
	public void setModuleMame(String moduleMame) {
		this.moduleMame = moduleMame;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public List<BusinessTarget> getBusinessTargetList() {
		return businessTargetList;
	}
	public void setBusinessTargetList(List<BusinessTarget> businessTargetList) {
		this.businessTargetList = businessTargetList;
	}
}
