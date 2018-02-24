package edu.cc.address.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cc.address.dao.IUserDao;
import edu.cc.address.domain.User;
import edu.cc.address.exception.ServiceException;
import edu.cc.address.service.IUserService;
import edu.cc.address.util.PasswordUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) {
		
		//判断账号是否存在
//		User dbUser = userDao.findByUsername(user.getUsername());
		User dbUser=userDao.findByProperty("username", user.getUsername());
		if(dbUser != null) {
			throw new ServiceException("用户" + user.getUsername() + "已存在!");
		}
		
		//对密码加密
		user.setPassword(PasswordUtil.encrypt(user.getPassword()));
		
		userDao.save(user);
		
	}

	@Override
	public boolean exist(String username) {
//		User dbUser = userDao.findByUsername(username);
		
		User dbUser=userDao.findByProperty("username", username);
		return dbUser != null;
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void updatePassword(String username,String oldPwd, String newPwd) {
//		User dbUser = userDao.findByUsername(username);
		User dbUser=userDao.findByProperty("username", username);
		if(dbUser == null) {
			throw new ServiceException("用户[" + username + "]不存在");
		}
		
		String pwd = PasswordUtil.encrypt(oldPwd);
		if(!pwd.equals(dbUser.getPassword())) {
			throw new ServiceException("旧密码不正确");
		}
		
		dbUser.setPassword(PasswordUtil.encrypt(newPwd));
	}

	@Override
	public User login(String username, String password) {
		
		password = PasswordUtil.encrypt(password);
		
		//return userDao.findByNameAndPassword(username, password);
		return userDao.findByProperties(new String[]{"username","password"},new Object[]{username,password});
	}

	@Override
	public void updatePwd(String newpwd, String oldPwd) {
		userDao.execute("update User u set u.passwd=? where u.passwd=?",newpwd, oldPwd);
	}

}
