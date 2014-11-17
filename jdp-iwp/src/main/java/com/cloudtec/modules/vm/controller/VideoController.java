package com.cloudtec.modules.vm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.sys.entity.User;

@Controller
@RequestMapping("${adminPath}/vm/traffic")
public class VideoController extends BaseController{
	

	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,User user) {
		return "modules/vm/trafficList";
	}
	
	@RequestMapping(value = {"/jqjd/list", "/jqjd"})
	public String jqjdlist(HttpServletRequest request, HttpServletResponse response, Model model,User user) {
		return "modules/vm/jqjdList";
	}
	
	@RequestMapping(value = {"/tip/list", "/tip"})
	public String tiplist(HttpServletRequest request, HttpServletResponse response, Model model,User user) {
		return "modules/vm/tipList";
	}
}
