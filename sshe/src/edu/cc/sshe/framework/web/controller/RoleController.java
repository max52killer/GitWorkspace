package edu.cc.sshe.framework.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cc.sshe.framework.anno.Permission;
import edu.cc.sshe.framework.bean.GridData;
import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.domain.Role;
import edu.cc.sshe.framework.service.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@Permission("role_query")
	@RequestMapping("/list")
	public Result list(int page,int rows) {
		
		PageBean pb = roleService.findPagedData(page, rows);
		
		return Result.success(new GridData(pb.getTotal(), pb.getRows()));
	}
	
	
	@Permission("role_add")
	@RequestMapping("/add")
	public Result add(Role role) {
		
		roleService.save(role);
		
		return Result.success();
	}
	
	
	@Permission("role_grant")
	@RequestMapping("/grant")
	public Result grant(String roleid,String[] resourceIds) {
		
		roleService.grant(roleid,resourceIds);
		
		return Result.success();
	}
	
	@Permission("role_query")
	@RequestMapping("/{roleid}/resources")
	public Result findRoleResources(@PathVariable("roleid") String roleid) {
		
		List<Resource> resList = roleService.findRoleResources(roleid);
		
		return Result.success(resList);
	}
	
	
	@Permission("role_query")
	@RequestMapping("/all")
	public Result findAll() {
		List<Role> roles = roleService.findAll();
		
		return Result.success(roles);
	}
	
}
