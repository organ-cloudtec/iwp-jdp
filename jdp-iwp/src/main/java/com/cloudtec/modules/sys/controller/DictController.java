/**
 * Project Name:jdp-iwp 
 * File Name:DicController.java 
 * Package Name:com.cloudtec.modules.sys.controller 
 * Date:2014-10-8下午2:35:01 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.modules.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.common.utils.CacheUtils;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.modules.sys.entity.Dict;
import com.cloudtec.modules.sys.service.DictService;
import com.cloudtec.modules.sys.utils.DictUtils;




/** 
 * @ClassName: DicController <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-10-8 下午2:35:01 <br/> 
 * 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value= "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	@Qualifier("dictService")
	private DictService dictService;
	
	/**
	 * @Title: DictController.list
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO 跳转、展示基础数据（枚举项）列表
	 * @return String
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value= {"list",""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Dict> dicts = dictService.findAll();
		model.addAttribute("dicts", dicts);
		return "modules/sys/dictList";
	}
	/**
	 * @Title: DictController.form
	 * @Author wangqi01 2014-10-8
	 * @Description: TODO 用户跳转基础数据详情页或新增页
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value="form")
	public String form(Dict dict ,Model model){
		if(StringUtils.isNotBlank(dict.getRecid())){
			dict = dictService.findByRecid(dict.getRecid());
		}
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}
	
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value="save")
	public String save(Dict dict,Model model,RedirectAttributes redirectAttributes){
		//后台判断是否可以保存,修改
		if(dict.isOk()){
			dictService.save(dict);
		}else{
			addMessage(model, "保存枚举项 "+dict.getLabel()+" 失败！");
			return form(dict, model);
		}
		addMessage(redirectAttributes, "保存枚举项 "+dict.getLabel()+" 成功！");
		//刪除dict緩存
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		return "redirect:"+Global.getAdminPath()+"/sys/dict?repage";
	}
	
	/**
	 * 检查基础数据中对应分类的标签名 和数据值 是否已存在
	 */
	@ResponseBody
	@RequestMapping("checklabelandvalue")
	public String checkLableAndValue(Dict dict){
		if(dict.isOk())
			return "true";
		else
			return "false";
	}
}
