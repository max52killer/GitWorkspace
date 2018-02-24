package edu.cc.sshe.framework.bean;


/**
 * 请求执行的返回结果
 * 用于返回JSON数据结果
 * @author song
 *
 */
public class Result {

	private boolean success;//标注成功或者失败
	private String msg;//存储成功或者失败的原因
	private Object data;//存储响应的数据 
	
	public static Result success(){
		return new Result(true,"执行成功!",null);
	}
	public static Result success(Object obj){
		return new Result(true,"执行成功！",obj);
	}
	public static Result fail(String msg){
		return new Result(false,msg,null);
	}
	
	public Result() {
		super();
	}
	public Result(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
