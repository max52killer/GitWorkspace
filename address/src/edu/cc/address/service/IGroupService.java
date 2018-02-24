package edu.cc.address.service;

import java.util.List;

import edu.cc.address.bean.PageBean;
import edu.cc.address.domain.Group;

public interface IGroupService {

	public void save(Group group);
	
	public void delete(String username,int id);
	
	public void update(Group group);
	
	public Group findById(Integer id);
	
	/**
	 * 查询指定用户的分组
	 * @param username
	 * @return
	 */
	public List<Group> findByUser(String username);
	
	/**
	 * 查询用户所有分组
	 * @param username
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageBean queryByPaged(String username, int page, int pageSize);
}
