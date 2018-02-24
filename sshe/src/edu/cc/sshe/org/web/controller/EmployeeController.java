package edu.cc.sshe.org.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cc.sshe.framework.anno.Permission;
import edu.cc.sshe.framework.bean.GridData;
import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.org.domain.Employee;
import edu.cc.sshe.org.domain.EmployeeQuery;
import edu.cc.sshe.org.service.IEmployeeService;

//@Controller
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService empService;
	
	@Permission("employee_query")
	@RequestMapping("/query")
//	@ResponseBody
	public Result query(EmployeeQuery query)throws Exception {
		
		PageBean pb = empService.query(query);
		
		return Result.success(new GridData(pb.getTotal(),pb.getRows()));
	}
	
	@Permission("employee_delete")
	@RequestMapping("/delete")
//	@ResponseBody
	public Result delete(String[]  ids)throws Exception {
		
		Thread.sleep(4000);
				
		empService.deleteById(ids);
		
		return Result.success();
	//	return Result.fail("没有权限删除！");
	}
	
	@Permission("employee_add")
	@RequestMapping("/add")
//	@ResponseBody
	public Result add(Employee emp)throws Exception {
		
		empService.save(emp);
		
		return Result.success();
	}
	
	
	@Permission("employee_edit")
	@RequestMapping("/edit")
//	@ResponseBody
	public Result edit(Employee emp)throws Exception {
		
		empService.update(emp);
		
		return Result.success();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor cde = new CustomDateEditor(sdf, true);
		binder.registerCustomEditor(Date.class, cde);
		
	}
}
