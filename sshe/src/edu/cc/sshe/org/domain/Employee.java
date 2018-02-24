package edu.cc.sshe.org.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.cc.sshe.framework.domain.IdEntity;

@Entity
@Table(name="org_emps")
public class Employee extends IdEntity {

	@Column(name="jobno",length=10)
	private String jobno;
	
	@Column(name="ename",length=20)
	private String name;
	
	@Column(name="sex",length=2)
	private String sex;
	
	@Column(name="job",length=20)
	private String job;
	
	@Temporal(TemporalType.DATE)
	private Date hiredate;
	
	@Column(length=30)
	private String phone;
	
	@Column(name="sal")
	private Double salary;
	
	
	public Employee(){
		super();
	}


	public Employee(String jobno,String name, String sex, String job, Date hiredate, String phone, Double salary) {
		this();
		this.jobno = jobno;
		this.name = name;
		this.sex = sex;
		this.job = job;
		this.hiredate = hiredate;
		this.phone = phone;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	

	public String getJobno() {
		return jobno;
	}


	public void setJobno(String jobno) {
		this.jobno = jobno;
	}


	@Override
	public String toString() {
		return "Employee [id=" + getId()+ ", jobno=" + jobno + ", name=" + name + ", sex=" + sex + ", job=" + job + ", hiredate=" + hiredate
				+ ", phone=" + phone + ", salary=" + salary + "]";
	}
	
}
