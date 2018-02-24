package edu.cc.sshe.framework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.domain.Role;
import edu.cc.sshe.framework.exception.ServiceException;
import edu.cc.sshe.framework.service.BaseServiceSupport;
import edu.cc.sshe.framework.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceSupport<Role> implements IRoleService {

	@Override
	public void grant(String roleid, String[] resourceIds) {
		Role role = findById(roleid);
		if(role == null) {
			throw new ServiceException("角色不存在!");
		}
		
		List<Resource> resList = new ArrayList<Resource>();
		if(resourceIds != null) {
			for(String resid : resourceIds) {
				resList.add(new Resource(resid));
			}
		}
		
		//设置角色的权限
		role.setResources(resList);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findRoleResources(String roleid) {
		String hql = "select res from Role r left join r.resources res where r.id=?";
		return (List<Resource>) dao.findListByHql(hql, roleid);
	}

	/*@Override
	public PageBean findPagedData(int page, int pageSize) {
		String hql = "from Role r left join fetch r.resources";
		return dao.findPagedData(hql,page, pageSize);
	}*/

	
	
}
