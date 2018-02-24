package edu.cc.address.web.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;
import edu.cc.address.service.IUserService;
import edu.cc.address.util.PasswordUtil;
import edu.cc.address.util.SessionUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	private IUserService userService;
	
	@RequestMapping(value="/edit/pwd",method=RequestMethod.GET)
	public String edit_view(HttpSession session,Model model)throws Exception{
		User user=(User) session.getAttribute(Constraint.SESSION_USER_KEY);
		model.addAttribute("user",user);
		return "user_edit_pwd";
	}

	/**
	 * 修改操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/edit/pwdup",method=RequestMethod.POST)
	public String edit(String oldpwd,String newpwd,String newpwd1,HttpSession session,Model model)throws Exception {
		
//		if(oldpwd==null||oldpwd.equals(""))
//		{
//			model.addAttribute("msg", "原密码不能为空！");
//			return "user_edit_pwd";
//		}
//		oldpwd=PasswordUtil.encrypt(oldpwd);
//		if(oldpwd!=user.getPassword())
//		{
//			model.addAttribute("msg", "原始密码不匹配！");
//			return "user_edit_pwd";
//		}
//		if(newpwd==null||newpwd.equals(""))
//		{
//			model.addAttribute("msg", "新密码不能为空！");
//			return "user_edit_pwd";
//		}
//		if(newpwd1==null||newpwd1.equals(""))
//		{
//			model.addAttribute("msg", "确认密码不能为空！");
//			return "user_edit_pwd";
//		}
		
		System.out.println(oldpwd+"  "+newpwd+" "+newpwd1);
//		if(newpwd!=newpwd1)
//		{
//			model.addAttribute("msg", "两次输入密码不一致！");
//			return "user_edit_pwd";
//		}
		System.out.println(SessionUtil.getLoginUsername(session));
		oldpwd=PasswordUtil.encrypt(oldpwd);
		newpwd=PasswordUtil.encrypt(newpwd);
		userService.updatePwd(newpwd, oldpwd);
		model.addAttribute("msg", "修改成功,请重新登录!");
		model.addAttribute("url", "login_page.action");
		
		return "message";
	}
	
}
