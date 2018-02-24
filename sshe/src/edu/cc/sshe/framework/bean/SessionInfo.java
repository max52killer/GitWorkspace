package edu.cc.sshe.framework.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 记录登录成功后存储在Session中的用户信息
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SessionInfo implements Serializable {

	private String id;
	private String username;
	private String nickname;
	private String tel;
	private Date loginTime = new Date(); // 登录时间

	private List<TreeNode> menu = new ArrayList<TreeNode>(); // 用户的操作菜单
	private List<String> actions = new ArrayList<>(); // 用户的操作权限

	/**
	 * 添加一个操作权限
	 */
	public void addAction(String actionCode) {
		actions.add(actionCode);
	}
	
	public void addMenuItem(TreeNode node) {
		menu.add(node);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public List<TreeNode> getMenu() {
		return menu;
	}

	public void setMenu(List<TreeNode> menu) {
		this.menu = menu;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

}
