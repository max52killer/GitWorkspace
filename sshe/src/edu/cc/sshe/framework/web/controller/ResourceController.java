package edu.cc.sshe.framework.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cc.sshe.framework.bean.GridData;
import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.service.IResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping("/tree")
	public Result tree() {
		
		List<TreeNode> treeData = resourceService.findTreeData();
		
		return Result.success(treeData);
	}
	
	
	@RequestMapping("/list")
	public Result list() {
		
		List<Resource> resList = resourceService.findAll();
		
		return Result.success(resList);
	}
	
	
	@RequestMapping("/add")
	public Result add(Resource res) {
		
		 resourceService.save(res);
		
		return Result.success();
	}
	
	@RequestMapping("/list2")
	public Result list2(int page,int rows) {
		
		PageBean pb = resourceService.findPagedData(page, rows);
		
		return Result.success(new GridData(pb.getTotal(), pb.getRows()));
	}
}
