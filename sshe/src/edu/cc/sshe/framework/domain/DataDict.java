package edu.cc.sshe.framework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="base_datadict")
public class DataDict extends IdEntity {

	@Column(name="code",length=30)
	private String code; // 编码

	@Column(name="name",length=40)
	private String name; // 名称

	@Column(name="remark",length=100)
	private String remark; // 描述

	@Column(name="pcode",length=10)
	private String pcode; // 上级编码

	public DataDict() {
		super();
	}

	public DataDict(String code, String name, String remark, String pcode) {
		super();
		this.code = code;
		this.name = name;
		this.remark = remark;
		this.pcode = pcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Override
	public String toString() {
		return "DataDict [code=" + code + ", name=" + name + ", remark=" + remark + ", pcode=" + pcode + "]";
	}

}
