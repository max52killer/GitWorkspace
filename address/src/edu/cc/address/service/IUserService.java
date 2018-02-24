package edu.cc.address.service;

import edu.cc.address.domain.User;

public interface IUserService {

	public void save(User user);
	
	/**
	 * 判断用户是否存在
	 * @param username
	 */
	public boolean exist(String username);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * 修改改密码
	 * @param oldPwd
	 * @param newPwd
	 */
	public void updatePassword(String username,String oldPwd,String newPwd);
	
	
	public User login(String username,String password);
	
	public void updatePwd(String oldPwd,String newpwd);
}
