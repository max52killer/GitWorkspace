package edu.cc.sshe.framework.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Tree的一个Node的数据
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class TreeNode implements Serializable {

	private String id;
	private String text;
	private String iconCls;
	private boolean checked;
	private Map<String, Object> attributes;
	
	public void addAttr(String key,Object value) {
		if(attributes == null) {
			attributes = new HashMap<>();
		}
		
		attributes.put(key, value);
	}
	
	private String pid;   //上级节点的id
	
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
