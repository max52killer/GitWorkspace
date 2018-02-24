package edu.cc.address.web.struts2.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;

@SuppressWarnings("serial")
public class PermissionInterceptor extends AbstractInterceptor {

	private String excludes; // 哪些Action不拦截

	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	/**
	 * 判断指定的Action是否不需要抖截
	 * 
	 * @param actionName
	 * @return
	 */
	public boolean isExclude(String actionName) {
        if(excludes == null || "".equals(excludes)) {
        	return false;
        }
        
        String[] strs = excludes.split(",");
        for(String str : strs) {
        	str = str.trim();
        	
        	if(str.endsWith("*")) {
        		if(actionName.startsWith(str.substring(0, str.length() - 1))) {
        			return true;
        		}
        	} else {
        		if(actionName.equals(str)) {
        			return true;
        		}
        	}
        }
		
		return false;
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String actionName = ActionContext.getContext().getName();

		if (isExclude(actionName)) {

			return ai.invoke();
		}

		//需拦截
		User user = (User) ActionContext.getContext().getSession().get(Constraint.SESSION_USER_KEY);

		if (user == null) { // 未登录
			ActionContext.getContext().put("msg", "请先登录!");
			return "login";
		}

		return ai.invoke(); // 放行s

	}

}
