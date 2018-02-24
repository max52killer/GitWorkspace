package edu.cc.sshe.org.domain;

import java.util.Date;

import edu.cc.sshe.framework.bean.BaseQuery;

/**
 * 保存查詢条件的实体类
 * @author song
 *
 */
public class EmployeeQuery extends BaseQuery{
	
	private String jobno;
	private String name;
	private String job;
	private String sex;
	private String phone;
	private Date hiredate;
	public String getJobno() {
		return jobno;
	}
	public void setJobno(String jobno) {
		this.jobno = jobno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
}
