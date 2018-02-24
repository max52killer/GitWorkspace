package edu.cc.address.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cc.address.bean.PageBean;
import edu.cc.address.dao.IGroupDao;
import edu.cc.address.domain.Address;
import edu.cc.address.domain.Group;
import edu.cc.address.exception.ServiceException;
import edu.cc.address.service.IGroupService;

@Service("groupService")
public class GroupServiceImpl implements IGroupService {

	@Autowired
	private IGroupDao groupDao;
	
	@Override
	public void save(Group group) {
		groupDao.save(group);
	}
	
	@Override
	public void update(Group group) {
		groupDao.update(group);
	}
	
	public void delete(String username,int id){
		Group group =  groupDao.findById(id);
		if(!group.getUser().getUsername().equals(username)) {
			throw new ServiceException("只能删除自己的记录");
		}
		
		groupDao.deleteById(id);
	}
	
	public Group findById(Integer id){
		return groupDao.findById(id);
	}

	@Override
	public List<Group> findByUser(String username) {
//		return groupDao.findByProperty("user.username", username);
		return groupDao.findListByProperty("user.username", username);
	}

	@Override
	public PageBean queryByPaged(String username, int page, int pageSize) {
		
		return groupDao.findPagedData("from Group g where g.user.username=?", page, pageSize,username);
	}

	

}
