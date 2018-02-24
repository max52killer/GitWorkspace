package edu.cc.address.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_addrs")
public class Address {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", length = 30)
	private String name;
	@Column(length = 30)
	private String phone;
	@Column(length = 30)
	private String mobile;
	@Column(length = 30)
	private String qq;
	@Column(length = 80)
	private String email;
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group; // 所属分组

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 所属用户

	public Address() {
		super();
	}

	public Address(String name, String phone, String mobile, String qq, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.qq = qq;
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", phone=" + phone + ", mobile=" + mobile + ", qq=" + qq
				+ ", email=" + email + ", group=" + group + ", user=" + user + "]";
	}

}
