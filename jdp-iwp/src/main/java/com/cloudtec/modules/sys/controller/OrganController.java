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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.common.Constants;
import com.cloudtec.modules.sys.entity.Organ;
import com.cloudtec.modules.sys.service.OrganService;
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
@RequestMapping(value="${adminPath}/sys/organ")
public class OrganController extends BaseController {
	
	@Autowired
	@Qualifier(value="organService")
	private OrganService organService;
	
	
	/**
	 * 
	 * @Title: OrganController.list
	 * @Author wangqi01 2014-9-18
	 * @Description: TODO 单位列表分页展示和进入单位管理页面
	 * @Permissions shiro sys:organ:view
	 */
	@RequiresPermissions("sys:organ:view")
	@RequestMapping(value={"list",""})
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
			HttpServletRequest request,HttpServletResponse response,Model model,Organ organ){
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("like_name", organ.getName());
		searchMap.put("like_code", organ.getCode());
		Page<Organ> page = organService.findOrgans(searchMap,pageNumber,pageSize,null);
		model.addAttribute("organs", page);
		model.addAttribute("organ", organ);
		return "modules/sys/organList";
	}
	
	@RequestMapping(value="import")
	public String imprtOrgans(MultipartFile file,RedirectAttributes redirectAttributes){
		addMessage(redirectAttributes, "单位信息导入成功，共导入");
		return "redirect:"+Global.getAdminPath()+"/sys/organ/?repage";
	}
	
	
	
	
	
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
