package edu.cc.address.web.struts2.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;
import edu.cc.address.service.IUserService;

@SuppressWarnings("serial")
@Controller("loginAction")  //让Spring将该对象放入容器
@Scope("prototype")    //指定该对象在Spring容器中作用域
public class LoginAction extends ActionSupport {

	private String username;

	private String password;

	private String checkCode;

	@Autowired   //让Spring注入该属性的值
	private IUserService userService;
	/**
	 * 登录页面
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception {

		return INPUT;
	}

	/**
	 * 登录操作
	 */
	public String execute() throws Exception {

		//校验验证码
		String realCode = (String)ActionContext.getContext().getSession().get(Constraint.RANDOMCODE_KEY);
	    if(realCode == null || !realCode.equalsIgnoreCase(checkCode)) {
	    	
	    	ActionContext.getContext().put("msg", "验证码不正确!");
	    	
	    	return INPUT;
	    }
	    
	    //用户名密码校验
	    User user = userService.login(username, password);
		if(user == null) {
			ActionContext.getContext().put("msg", "用户名或密码不正确!");
			return INPUT;
		}
	    
		//将登录信息存入Session
		ActionContext.getContext().getSession().put(Constraint.SESSION_USER_KEY, user);
		
		return SUCCESS;
	}
	
	
	/**
	 * 注销
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
       HttpSession session = ServletActionContext.getRequest().getSession(false);
       if(session != null) {
    	   session.invalidate();
       }
		
		return INPUT;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	
}
