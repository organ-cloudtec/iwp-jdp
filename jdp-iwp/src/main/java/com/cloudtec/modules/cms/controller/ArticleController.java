/**
 * Project Name:jdp-iwp 
 * File Name:ArticleController.java 
 * Package Name:com.cloudtec.modules.cms.controller 
 * Date:2014-10-15下午3:48:26 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.cms.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.modules.cms.entity.Article;

/** 
 * @ClassName: ArticleController <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-10-15 下午3:48:26 <br/> 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@RequestMapping(value="${adminPath}/cms/article")
@Controller
public class ArticleController extends BaseController {

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value={"list",""})
	public String list(Article article , Model model){
		
		return "module/cms/articleList";
	}
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value="form")
	public String form(Article article,Model model){
		return "module/cms/articleForm";
	}
	@RequiresPermissions("cms:article:edit")
	@RequestMapping(value="save")
	public String save(Article article,Model model,RedirectAttributes redirectAttributes){
		
		return "redirect:"+Global.getAdminPath()+"/cms/article?repage";
	}
	//------------------- fontshow -------------------------
	
	
	
}
