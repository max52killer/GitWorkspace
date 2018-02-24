package edu.cc.sshe.org.service;


import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.service.IBaseService;
import edu.cc.sshe.org.domain.Employee;
import edu.cc.sshe.org.domain.EmployeeQuery;

public interface IEmployeeService extends IBaseService<Employee>{

	public PageBean query(EmployeeQuery query);
}
