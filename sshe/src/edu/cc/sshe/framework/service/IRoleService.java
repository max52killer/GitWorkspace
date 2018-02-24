package edu.cc.sshe.framework.service;

import java.util.List;

import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.domain.Role;

public interface IRoleService extends IBaseService<Role> {

	/**
	 * 角色授权
	 * @param roleid
	 * @param resourceIds
	 */
	void grant(String roleid, String[] resourceIds);

	/**
	 * 查询指定角色的权限
	 * @param roleid
	 * @return
	 */
	List<Resource> findRoleResources(String roleid);

}
