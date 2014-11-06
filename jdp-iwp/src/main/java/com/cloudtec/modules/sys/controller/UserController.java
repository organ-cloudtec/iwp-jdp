package com.cloudtec.modules.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudtec.common.config.Global;
import com.cloudtec.common.controller.BaseController;
import com.cloudtec.common.utils.DateUtils;
import com.cloudtec.common.utils.StringUtils;
import com.cloudtec.common.utils.excel.ExportExcel;
import com.cloudtec.modules.sys.entity.Role;
import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.service.RoleService;
import com.cloudtec.modules.sys.service.UserService;
import com.cloudtec.modules.sys.utils.PasswordEncodeUtils;
import com.cloudtec.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
/**
  * @ClassName: UserController
  * @Description: TODO 用户管理Controller,进行的操作有，查询、修改、删除、新增、验证的登录名是否唯一
  * @author wangqi01
  * @date 2014-8-13 下午4:25:12
 */
@Controller
@RequestMapping("${adminPath}/sys/user")
public class UserController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	//展示用户列表
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,User user) {
		Page<User> userPage = userService.findUsers(user,user.getPageNo(),user.getPageSize(),null);
		model.addAttribute("users", userPage);
		model.addAttribute("user", user);
		return "modules/sys/userList";
	}
	
	/**
	 * 
	  * @Title: form
	  * @Author wangqi01 2014-8-7
	  * @Description: TODO：修改，新增 ，展示 用户界面
	  * @return    
	  * String
	  * @throws
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user,Model model){
		if(user.getRecid() != null)
			user = userService.findByRecid(user.getRecid());
		model.addAttribute("user", user);
		//获取所有用户角色
		model.addAttribute("allRoles", roleService.findAllRole());
		return "modules/sys/userForm";
	}
	//增
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user,String oldusername,String newpassword, Model model, RedirectAttributes redirectAttributes){
		
		if(StringUtils.isEmpty(user.getRecid()) && StringUtils.isEmpty(newpassword)){
			addMessage(model, "新增用户，密码不可为空。");
			return form(user, model);
		}
		User befUser = null;
		if(StringUtils.isNotBlank(user.getRecid())){
			befUser = userService.findByRecid(user.getRecid());
			if(befUser == null){
				addMessage(model, "要修改信息的用户不存在。");
				logger.error("要修改信息的用户不存在,修改的用户ID为："+user.getRecid()+"");
				return form(user, model);
			}
		}
		
		
		//密码不为空，则是修改密码操作
		if(StringUtils.isNotBlank(newpassword)){
			//修改密码
			user.setPassword(PasswordEncodeUtils.entryptPassword(newpassword));
		}else if(befUser != null){
			user.setPassword(befUser.getPassword());
		}
		/**
		 * 1.给用户组织机构赋值<br><p>获取user中已存在
		 * 2.服务端验证参数有效性
		 * 3.验证登陆名是否可用
		 * 4.验证选择角色是否合法
		 * 5.保存用户
		 */
		//step 2
		if(!beanValidator(model, user)){
			//服务端验证参数失败
			return form(user, model);
		}
		//验证登陆名
		if(!"true".equals(checkLoginName(oldusername,user.getUsername()))){
			addMessage(model, "保存用户"+user.getUsername()+"失败，登陆名已存在");
			return form(user, model);
		}
		//验证角色是否合法
		List<Role> roles = Lists.newArrayList();
		//前台选择用户角色ID
		List<String> userRoles = user.getRoleIdList();
		List<Role> allRoles = roleService.findAllRole();
		for(Role role : allRoles){
			if(userRoles.contains(role.getRecid())){
				roles.add(role);
			}
		}
		user.setRoleList(roles);
		userService.create(user);
		// 清除当前用户缓存
		if (user.getUsername().equals(UserUtils.getUser().getUsername())){
			UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getUsername() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/sys/user/?repage";
	}
	/**
	  * @Author wangqi01 2014-8-7
	  * @Description: TODO 根据userId删除用户
	  * @param redirectAttributes
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(String recid, RedirectAttributes redirectAttributes){
		logger.info(UserUtils.getUser().getName()+"执行删除用户操作，删除用户为"+recid);
		userService.delete(recid);
		addMessage(redirectAttributes, "删除用户成功!");
		return "redirect:"+Global.getAdminPath()+"/sys/user/?repage";
	}
	@RequestMapping(value="import")
	public String imprtUsers(MultipartFile file,RedirectAttributes redirectAttributes){
		addMessage(redirectAttributes, "用户导入成功，共导入");
		return "redirect:"+Global.getAdminPath()+"/sys/user/?repage";
	}	
	/**
	 * @Title: UserController.exportUsers
	 * @Author wangqi01 2014-11-6
	 * @Description: TODO 用户导出功能，测试导出实现
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
	public String exportUsers(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
		try {
            String fileName = "用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx"; 
            List<User> pageUser = userService.findAllUsers();
    		new ExportExcel("用户数据", User.class).setDataList(pageUser).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sys/user/?repage";
	}
	//界面右上角进入展示保存个人信息
	@RequiresUser
	@RequestMapping(value = "info")
	public String info(User user, Model model) {
		if(user.getRecid() == null){
			user = UserUtils.getUser();
		}else{
			user = userService.findByRecid(user.getRecid());
		}
		model.addAttribute("user", user);
		return "modules/sys/userInfo";
	}
	//检查登陆名是否存在
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkUsername")
	public String checkLoginName(String oldusername, String username) {
		if (username !=null && username.equals(oldusername)) {
			return "true";
		} else if (username !=null && userService.findUserByLoginName(username) == null) {
			return "true";
		}
		return "false";
	}
}
