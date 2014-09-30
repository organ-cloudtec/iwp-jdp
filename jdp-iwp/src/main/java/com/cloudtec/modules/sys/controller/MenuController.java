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


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.modules.common.Constants;
import com.cloudtec.modules.sys.entity.Menu;
import com.cloudtec.modules.sys.service.MenuService;
import com.cloudtec.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * @ClassName: MenuController
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:54:10
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Menu> sourcelist = menuService.findAll();
		List<Menu> list = Lists.newArrayList();
		Menu.sortList(list, sourcelist, "TOP_MENU_ID");
		request.setAttribute("menus", list);
		return "modules/sys/menuList";
	}
	
	@RequestMapping(value="form")
	public String form(Menu menu, Model model){
		if(StringUtils.isNotBlank(menu.getRecid())){
			menu = menuService.findByRecid(menu.getRecid());
		}else{
			if(menu.getParent() == null ||	StringUtils.isBlank(menu.getParent().getRecid())){
				menu.setParent(new Menu(Constants.RECID_MENU_ROOTID));
			}
			menu.setParent(menuService.findByRecid(menu.getParent().getRecid()));
		}
			
		
		model.addAttribute("menu", menu);
		return "modules/sys/menuForm";
	}
	
	@RequestMapping(value = "save")
	public String save(Menu menu,Model model,RedirectAttributes redirectAttributes){
		//保存菜单信息
		menuService.save(menu);
		addMessage(redirectAttributes, "保存菜单 '"+menu.getName()+"' 成功！");
		return "redirect:"+Global.getAdminPath()+"/sys/menu/?repage";
	}
	
	@RequestMapping(value="delete")
	public String delete(Menu menu,Model model,RedirectAttributes redirectAttributes){
		if(StringUtils.isEmpty(menu.getRecid())){
			addMessage(model, "删除菜单失败，菜单ID不可为空!");
			return "redirect:"+Global.getAdminPath()+"/sys/menu/?repage";
		}
		if(menuService.delete(menu)){
			addMessage(redirectAttributes, "删除菜单信息成功。");
		}else{
			addMessage(redirectAttributes, "删除菜单失败。");
		}
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		return "redirect:"+Global.getAdminPath()+"/sys/menu/?repage";
	}
	/**
	 * 功能已完成,获取右侧菜单
	 * @Title: MenuController.tree
	 * @Author wangqi01 2014-8-20
	 * @Description: TODO
	 * @param request
	 * @return String
	 *
	 */
	@RequiresUser
	@RequestMapping(value = "tree")
	public String tree(HttpServletRequest request) {
		return "modules/sys/menuTree";
	}
	/**
	 * 菜单树
	 * @Title: MenuController.treeData
	 * @Author wangqi01 2014-8-20
	 * @Description: TODO
	 * @param extId
	 * @param response
	 * @return List<Map<String,Object>>
	 *
	 */
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) Long extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = menuService.findAll();
		for (int i=0; i<list.size(); i++){
			Menu e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getRecid()) && e.getParents().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getRecid());
				map.put("pId", e.getParent()!=null?e.getParent().getRecid():0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
