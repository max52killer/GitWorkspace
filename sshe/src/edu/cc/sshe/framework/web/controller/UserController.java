package edu.cc.sshe.framework.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.cc.sshe.framework.bean.GridData;
import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.framework.bean.SessionInfo;
import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.Role;
import edu.cc.sshe.framework.domain.User;
import edu.cc.sshe.framework.service.IUserService;
import edu.cc.sshe.framework.util.SessionUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public Result list(int page,int rows) {
		
		PageBean pb = userService.findPagedData(page, rows);
		
		return Result.success(new GridData(pb.getTotal(), pb.getRows()));
	}
	
	
	@RequestMapping("/add")
	public Result add(User user,String[] roleIds) {
		
		List<Role> roles = new ArrayList<Role>();
		if(roleIds != null && roleIds.length > 0) {
			for(String roleid : roleIds) {
				roles.add(new Role(roleid));
			}
		}
		
		user.setRoles(roles);
		userService.save(user);
		
		return Result.success();
	}
	
	
	@RequestMapping("/login")
	public Result login(String username,String password,HttpSession session) {
		
		User user = userService.login(username,password);
		
		if(user == null) {
			return Result.fail("用户名或密码不正确");
		}
		
		if(!user.isEnable()) {
			return Result.fail("账号不可用，请联系管理员");
		}
		
		//创建登录信息
		SessionInfo info = userService.createSessionInfo(user);
		
		//将用户的登录信息存入Session
		SessionUtil.saveSessionInfo(session, info);
		
		return Result.success();
	}
	
	
	@RequestMapping("/logout")
	public Result logout(HttpSession session) {
		
		if(session != null) {
			session.invalidate();
		}
		
		
		return Result.success();
	}
	
	
	@RequestMapping("/menu")
	public Result getMenu(HttpSession session) {
		
		List<TreeNode> menuData = SessionUtil.getMenuData(session);
				
		return Result.success(menuData);
	}
	
	
	@RequestMapping("/actions")
	public Result getActions(HttpSession session) {
		
		List<String> actions = SessionUtil.getActions(session);
				
		return Result.success(actions);
	}
}
