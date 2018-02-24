package edu.cc.sshe.framework.service;

import java.util.List;

import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.Resource;

public interface IResourceService extends IBaseService<Resource> {

	
	public List<TreeNode>  findTreeData() ;

	public List<Resource> findAll();
}
