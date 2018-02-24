package edu.cc.sshe.framework.web.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.framework.exception.ServiceException;

@ControllerAdvice
public class ExceptionAdvice {

	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public Result handlerServiceException(ServiceException e) {
		
		return Result.fail(e.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handlerOtherException(Exception e) {
		
		e.printStackTrace();
		
		return Result.fail("服务器内部错误!");
	}
}
