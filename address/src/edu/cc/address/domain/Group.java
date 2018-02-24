package edu.cc.address.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 通讯录分组
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="t_groups")
public class Group {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="gname",length=30)
	private String name;
	
	@Column(name="gdesc",length=200)
	private String descs;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; // 所属用户

	public Group() {
		super();
	
	}

	public Group(Integer id) {
		super();
		this.id = id;
	}

	public Group(String name, String descs, User user) {
		super();
		this.name = name;
		this.descs = descs;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", descs=" + descs + "]";
	}

}
