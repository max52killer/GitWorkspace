package edu.cc.address.util;

import javax.servlet.http.HttpSession;

import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;

public class SessionUtil {

	public static User getLoginUser(HttpSession session) {
		
		return (User) session.getAttribute(Constraint.SESSION_USER_KEY);
	}
	
	
	public static String getLoginUsername(HttpSession session) {
		User user = getLoginUser(session);
		return user ==  null ? null : user.getUsername();
	}
}
