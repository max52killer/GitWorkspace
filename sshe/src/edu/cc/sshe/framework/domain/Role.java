package edu.cc.sshe.framework.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="base_role")
@JsonIgnoreProperties({"resources"})  //将role对象转为json串时，不转换resources属性
public class Role extends IdEntity {

	@Column(name="name",length=40)
	private String name; // 名称
	
	@Column(name="remark",length=200)
	private String remark; // 描述
	
	@Column(name="enabled")
	private boolean enable = true; // 角色是否启用

	
	@ManyToMany
	@JoinTable(name="base_role_resource",
	           joinColumns=@JoinColumn(name="role_id",columnDefinition="varchar(40)"),
	           inverseJoinColumns=@JoinColumn(name="resource_id",columnDefinition="varchar(40)"))
	private List<Resource> resources; // 角色拥有的权限

	
	
	public Role() {
		super();
	}
	
	public Role(String id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
