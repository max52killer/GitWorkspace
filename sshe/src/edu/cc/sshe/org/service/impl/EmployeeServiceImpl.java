package edu.cc.sshe.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.service.BaseServiceSupport;
import edu.cc.sshe.framework.util.HqlBuilder;
import edu.cc.sshe.org.domain.Employee;
import edu.cc.sshe.org.domain.EmployeeQuery;
import edu.cc.sshe.org.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceSupport<Employee> implements IEmployeeService {

	@Override
	public PageBean query(EmployeeQuery query) {
	    HqlBuilder builder = new HqlBuilder("from Employee e");
	    builder.addLikeCondition("e.jobno like ?", query.getJobno())
	           .addLikeCondition("e.name like ? ", query.getName())
	           .addLikeCondition("e.phone like ?", query.getPhone())
	           .addCondition("e.sex=?", query.getSex())
	           .addCondition("e.job=?", query.getJob())
	           .addCondition("e.hiredate=?", query.getHiredate())
	           .addOrder("e",query.getSort(), query.getOrder());
	    
	    System.out.println("to query : " + builder.builderHql());
	    
		return dao.findPagedData(builder.builderHql(), query.getPage(), query.getRows(),builder.getHqlParams());
	}
	
	
	public PageBean queryOld(EmployeeQuery query) {
		String hql = "from Employee e where '1'='1' ";
		List<Object> params = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(query.getJobno())) {
			hql += " and e.jobno like ? ";
			params.add("%" + query.getJobno() + "%");
		}
		
		if(StringUtils.isNotEmpty(query.getName())) {
			hql += " and e.name like ? ";
			params.add("%" + query.getName() + "%");
		}
		
		if(StringUtils.isNotEmpty(query.getPhone())) {
			hql += " and e.phone like ? ";
			params.add("%" + query.getPhone() + "%");
		}
		
		if(query.getHiredate() != null) {
			hql += " and e.hiredate=? ";
			params.add(query.getHiredate());
		}
		
		if(StringUtils.isNotEmpty(query.getJob())) {
			hql += " and e.job=? ";
			params.add(query.getJob());
		}
		
		if(StringUtils.isNotEmpty(query.getSex())) {
			hql += " and e.sex=? ";
			params.add(query.getSex());
		}
		
		
		if(StringUtils.isEmpty(query.getSort())) {
			hql += " order by " + query.getSort() + " " + query.getOrder();
		}
		
		System.out.println("query : " + hql);
		return dao.findPagedData(hql, query.getPage(), query.getRows(),params.toArray());
	}

}
