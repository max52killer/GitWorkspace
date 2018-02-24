package edu.cc.sshe.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.cc.sshe.framework.anno.Permission;
import edu.cc.sshe.framework.bean.SessionInfo;
import edu.cc.sshe.framework.exception.ServiceException;
import edu.cc.sshe.framework.util.SessionUtil;

/**
 * 权限拦截器
 * @author Administrator
 *
 */
public class PermissionInterceptor implements HandlerInterceptor {

	/**
	 * 在控制器处理方法前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("拦截一个请求,请求路径为：" + request.getRequestURI());
		
		if(!(handler instanceof HandlerMethod)) {
		   return true;
		}
		
		HandlerMethod hm = (HandlerMethod)handler;
		System.out.println("该请求由" + hm.getBean().getClass().getName() + "的" + hm.getMethod().getName() + "方法处理");
		
		if(hm.getMethod().isAnnotationPresent(Permission.class)) {  //检查控制器方法上有没有@Permission注解
			Permission  p = hm.getMethod().getAnnotation(Permission.class); //取出方法上的注解实例
			String pvalue = p.value();  //取出注解上value属性的值
			System.out.println(hm.getMethod().getName() + "需要" + pvalue + "权限");
			//检查用户是否拥有方法声明的权限
			SessionInfo info = SessionUtil.getSessionInfo(request.getSession());
			if(info == null) {
				throw new ServiceException("没有登录或登录超时，请重新登录");
			}
			
			if(! info.getActions().contains(pvalue)) {
				throw new ServiceException("没有执行该操作的权限");
			}
			
		}
		
		
		
		return true;
	}

	
	/**
	 * 在控制器处理方法后执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	
	/**
	 * 在响应返回给客户端之前
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}



	
}
