package com.cloudtec.modules.sys.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.utils.UserUtils;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 */
@Controller
@RequestMapping(value="${adminPath}")
public class LoginController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		logger.info("进入 login 方法----->get");
		// 如果已经登录，则跳转到管理首页
		if(user.getRecid() != null){
			return "redirect:"+Global.getAdminPath();
		}
		return "modules/sys/login";
	}
	//处理登陆跳转
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		logger.info("login------->fail");
		User user = UserUtils.getUser();
		if(user.getRecid() != null){
			//登陆成功,跳转 index处理
			return "redirect:"+Global.getAdminPath();
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		//登陆不成功
		return "modules/sys/login";
	}
	//
	@RequestMapping(value="")
	public String success(HttpServletRequest request, HttpServletResponse response){
		User user = UserUtils.getUser();
		logger.info("login------>success");
		if(user.getRecid() == null){
			//用户未登录，跳转登陆页
			return "redirect:"+ Global.getAdminPath()+"/login";
		}
		//已经登陆跳转管理首页
		return "modules/sys/sysIndex";
	}

}
