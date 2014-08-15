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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.sys.service.RoleService;




/**
 * @ClassName: RoleController
 * @Description: TODO
 * @author wangqi01
 * @date 2014-8-11 上午10:56:45
 */
@Controller
@RequestMapping(value="/sys/role")
public class RoleController extends BaseController {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
//	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
//        Page<User> page = systemService.findUser(new Page<User>(request, response), user); 
//        model.addAttribute("page", page);
		logger.info("RoleController -------> list");
		return "modules/sys/roleList";
	}
	
}
