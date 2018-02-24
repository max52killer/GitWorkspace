package edu.cc.sshe.framework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "base_resource")
public class Resource extends IdEntity {
	public static final String TYPE_MENU = "menu";
	public static final String TYPE_ACTION = "action";

	@Column(name="rtype",length=20)
	private String type; // 类型
	@Column(name="name",length=40)
	private String name;
	@Column(name="code",length=30)
	private String code; // 编码
	
	@Column(name="url",length=120)
	private String url; // 菜单的连接地址
	
	@Column(name="remark",length=200)
	private String remark; // 描述
	
	@Column(name="sortno")
	private int sortno; // 排序值

	@ManyToOne
	@JoinColumn(name="pid",columnDefinition="varchar(40)")
	private Resource parent; // 上级权限

	public Resource() {
		super();
	}
	
	public Resource(String id) {
		setId(id);
	}

	public Resource( String code, String name,String type, String url, int sortno, Resource parent) {
		super();
		this.type = type;
		this.name = name;
		this.code = code;
		this.url = url;
		this.sortno = sortno;
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}
	
	@Transient
	public String getPid() {
		return parent == null ? null : parent.getId();
	}
	
	
	@Transient
	public boolean isMenu() {
		return TYPE_MENU.equals(type);
		
	}

	@Override
	public String toString() {
		return "Resource [type=" + type + ", name=" + name + ", code=" + code + ", url=" + url + ", remark=" + remark
				+ ", sortno=" + sortno + "]";
	}

}
