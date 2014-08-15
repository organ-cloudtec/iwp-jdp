/**
 * @Title: MenuController.java
 * @Package com.lovexiao.jmp.modules.sys.web
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午10:54:10
 * @version V1.0
 */

package com.cloudtec.modules.sys.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.sys.entity.Menu;
import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.service.MenuService;
import com.google.common.collect.Maps;




/**
 * @ClassName: MenuController
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:54:10
 */
@Controller
@RequestMapping(value = "/sys/menu")
public class MenuController extends BaseController {
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = ContantsRbac.DEFAULT_PAGE_SIZE) int pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> searchMap = Maps.newHashMap();
		Page<Menu> page = menuService.findMenus(searchMap,pageNumber,pageSize,null,searchMap);
		request.setAttribute("menus", page);
		request.setAttribute("menu", new Menu());
		return "modules/sys/menuList";
	}
	
	@RequestMapping(value="form")
	public String form(HttpServletRequest request, HttpServletResponse response, Model model){
		Menu menu = new Menu();
		model.addAttribute("menu", menu);
		return "modules/sys/menuForm";
	}
	
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request, HttpServletResponse response,Model model){
		
		return "";
	}
	
	@RequestMapping(value = "tree")
	public String tree(){
		return "modules/sys/menuTree";
	}
}
