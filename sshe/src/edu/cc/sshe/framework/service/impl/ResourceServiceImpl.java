package edu.cc.sshe.framework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.service.BaseServiceSupport;
import edu.cc.sshe.framework.service.IResourceService;

@Service
public class ResourceServiceImpl extends BaseServiceSupport<Resource> implements IResourceService {

	@SuppressWarnings("unchecked")
	@Override
	public List<TreeNode> findTreeData() {
		String hql = "from Resource r order by r.sortno";
		List<Resource> resList = (List<Resource>) dao.findListByHql(hql);
		List<TreeNode> treeData = new ArrayList<TreeNode>();
		for(Resource res : resList) {
			TreeNode node = new TreeNode();
			node.setId(res.getId());
			node.setText(res.getName() + "<span style='color:#ddd'>[" + (Resource.TYPE_MENU.equals(res.getType()) ? "菜单" : "按钮") + "]</span>");
			node.addAttr("url", res.getUrl());
			node.addAttr("code", res.getCode());
			if(res.getParent() != null) {
				node.setPid(res.getParent().getId());
			}
			treeData.add(node);
		}
		
		return treeData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findAll() {
		return (List<Resource>) dao.findListByHql("from Resource r order by r.sortno");
	}

}
