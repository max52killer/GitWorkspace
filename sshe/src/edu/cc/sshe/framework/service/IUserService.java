package edu.cc.sshe.framework.service;

import edu.cc.sshe.framework.bean.SessionInfo;
import edu.cc.sshe.framework.domain.User;

public interface IUserService extends IBaseService<User> {

	public boolean usernameExist(String username);

	public User login(String username, String password);

	public SessionInfo createSessionInfo(User user);
	
}
