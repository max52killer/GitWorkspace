package edu.cc.sshe.framework.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="base_user")
public class User extends IdEntity {

	@Column(name="username",length=30)
	private String username;
	
	@Column(name="nickname",length=30)
	private String nickname;
	
	@Column(name="password",length=40)
	private String password;
	
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
	private Date createTime = new Date();
	
	@Column(name="enabled")
	private boolean enable = true;

	@ManyToMany
	@JoinTable(name="base_user_role",
	           joinColumns=@JoinColumn(name="user_id",columnDefinition="varchar(40)"),
	           inverseJoinColumns=@JoinColumn(name="role_id",columnDefinition="varchar(40)"))
	private List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
