/**
 * @Title: RoleController.java
 * @Package com.lovexiao.jmp.modules.sys.web
 * @Description: TODO
 * Copyright: Copyright (c) 2014-2017 
 * @author Comsys-wangqi01
 * @date 2014-8-11 上午10:56:45
 * @version V1.0
 */

package com.cloudtec.modules.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.modules.sys.entity.Role;
import com.cloudtec.modules.sys.service.RoleService;

/**
 * @ClassName: RoleController
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:56:45
 */
@Controller
@RequestMapping(value="${adminPath}/sys/role")
public class RoleController extends BaseController {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
//	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Role> roles = roleService.findAllRole(); 
        model.addAttribute("roles", roles);
		logger.info("RoleController -------> list");
		return "modules/sys/roleList";
	}
	//编辑，角色信息
	@RequestMapping(value="/form")
	public String form(Role role, Model model){
		//修改
		if(!StringUtils.isEmpty(role.getRecid())){
			role = roleService.findByRecid(role.getRecid());
			//根据Role 获取 角色对应的 菜单Id,已经通过实体注解实现
		}
		model.addAttribute("role", role);
		return "modules/sys/roleForm";
	}
	//删除角色
	@RequestMapping(value="/delete")
	public String delete(Role role, Model model, RedirectAttributes redirectAttributes){
		//删除角色
		//后台验证,验证ID是否为空
		if(StringUtils.isEmpty(role.getRecid())){
			addMessage(model, "要删除角色的角色ID不能为空。");
			return form(role,model);
		}
		boolean isSuccess = roleService.delete(role.getRecid());
		addMessage(redirectAttributes, "删除角色"+(isSuccess?"成功":"失败"));
		return "redirect:"+Global.getAdminPath()+"/sys/role/?repage";
	}
	
	@RequestMapping(value="/save")
	public String save(Role role, Model model, RedirectAttributes redirectAttributes){
		//获取用户角色
		if(StringUtils.isEmpty(role.getName())){
			addMessage(model,"新增角色失败,角色名称不可为空。");
			return form(role, model);
		}
		roleService.save(role);
		addMessage(redirectAttributes, "新增角色 '"+role.getName()+"' 成功。");
		return "redirect:"+Global.getAdminPath()+"/sys/role/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value="checkRolename")
	public String checkRoleName(String name,String oldrolename){
		if(!StringUtils.isEmpty(name) && name.equals(oldrolename)){
			return "true";
		}else if(!StringUtils.isEmpty(name) &&
				roleService.findByName(name) == null){
			return "true";
		}
		return "false";
	}
	
	
}
