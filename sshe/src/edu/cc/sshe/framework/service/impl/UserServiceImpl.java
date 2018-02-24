package edu.cc.sshe.framework.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.bean.SessionInfo;
import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.domain.User;
import edu.cc.sshe.framework.exception.ServiceException;
import edu.cc.sshe.framework.service.BaseServiceSupport;
import edu.cc.sshe.framework.service.IUserService;
import edu.cc.sshe.framework.util.PasswordUtil;

@Service("userSerivce")
public class UserServiceImpl extends BaseServiceSupport<User> implements IUserService {

	@Override
	public void save(User user) {
		
		if(usernameExist(user.getUsername())) {
			throw new ServiceException("用户名[" + user.getUsername() + "]已存在");
		}
		
		user.setPassword(PasswordUtil.encrypt(user.getPassword()));
		
		dao.save(user);
	}
	
	

	@Override
	public PageBean findPagedData(int page, int pageSize) {
		String hql = "from User u left join fetch u.roles";
		return dao.findPagedData(hql,page, pageSize);
	}



	@Override
	public boolean usernameExist(String username) {
		User dbUser = dao.findByProperty(User.class, "username", username);
		return dbUser != null;
	}



	@Override
	public User login(String username, String password) {
		password = PasswordUtil.encrypt(password);
		String hql = "from User u where u.username=? and u.password=?";
		
		return (User) dao.findByHql(hql,username,password);
	}



	@Override
	public SessionInfo createSessionInfo(User user) {
		SessionInfo info = new SessionInfo();
		info.setId(user.getId());
		info.setUsername(user.getUsername());
		info.setNickname(user.getNickname());
		//查询用户的权限
		String hql = "select distinct res from User u left join u.roles r left join r.resources res where u.id=? and r.enable=? order by res.sortno";
		List<Resource> resList = (List<Resource>) dao.findListByHql(hql, user.getId(),true);
		
		for(Resource res : resList) {
			if(res == null) {
				continue;
			}
			
			if(res.isMenu()) {  //菜单权限
				TreeNode node = new TreeNode();
				node.setId(res.getId());
				node.setText(res.getName());
				node.setPid(res.getPid());
				node.addAttr("url", res.getUrl());
				info.addMenuItem(node);
			} else {  //操作权限
				info.addAction(res.getCode());
			}
			
		}
		
		
		return info;
	}

	
	
}
