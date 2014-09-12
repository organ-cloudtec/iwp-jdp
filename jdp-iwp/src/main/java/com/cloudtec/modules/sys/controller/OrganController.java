/**
 * Project Name:jdp-iwp 
 * File Name:OrganController.java 
 * Package Name:com.cloudtec.modules.sys.controller 
 * Date:2014-8-19下午3:14:21 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.sys.entity.Organ;
import com.cloudtec.modules.sys.service.OrganService;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;




/** 
 * @ClassName: OrganController <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-8-19 下午3:14:21 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/sys/organ")
public class OrganController extends BaseController {
	
	@Autowired
	@Qualifier(value="organService")
	private OrganService organService;
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) Long extId,HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Organ> list = organService.findAll();
		for (int i=0; i<list.size(); i++){
			Organ e = list.get(i);
			if ((extId == null || (extId!=null && !extId.equals(e.getRecid()) && e.getParentIds().indexOf(","+extId+",")==-1))
				){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getRecid());
//				map.put("pId", !user.isAdmin() && e.getId().equals(user.getOffice().getId())?0:e.getParent()!=null?e.getParent().getId():0);
				map.put("pId", e.getParent()!=null?e.getParent().getRecid():0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
