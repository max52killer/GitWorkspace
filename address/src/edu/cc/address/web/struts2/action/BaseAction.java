package edu.cc.address.web.struts2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
	
	public User getLoginUser() {
		User user = (User) ActionContext.getContext().getSession().get(Constraint.SESSION_USER_KEY);
		return user;
	}

	public String getLoginUsername() {

	    User user = getLoginUser();
	    
		if (user != null) {
			return user.getUsername();
		}

		return null;
	}
}
