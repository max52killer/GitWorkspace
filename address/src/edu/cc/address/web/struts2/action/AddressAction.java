package edu.cc.address.web.struts2.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.cc.address.bean.Constraint;
import edu.cc.address.bean.PageBean;
import edu.cc.address.domain.Address;
import edu.cc.address.domain.Group;
import edu.cc.address.domain.User;
import edu.cc.address.service.IAddressService;
import edu.cc.address.service.IGroupService;
import edu.cc.address.service.impl.AddressServiceImpl;

@SuppressWarnings("all")
@Controller("addressAction")
@Scope("prototype")
public class AddressAction extends BaseAction {

	
	
	@Autowired
	private IAddressService addrService;
	
	@Autowired
	private IGroupService groupService;
	
	private int page = 1;
	
	private int id;
	
	private Address addr;
	
	/**
	 * 列表展示
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception {
		PageBean pb = addrService.queryPaged(getLoginUsername(), page, 10);
		ActionContext.getContext().put("pb", pb);
		return INPUT;
	}

	
	public String delete()throws Exception {
		
		addrService.delete(getLoginUsername(), id);
		
		ActionContext.getContext().put("msg", "删除成功!");
		
		return "message";
		
	}
	
	
	public String preAdd()throws Exception {
		//查询当前用户的分组
		List<Group> groups = groupService.findByUser(getLoginUsername());
		
		ActionContext.getContext().put("groups", groups);
		
		return "add_input";
	}
	
	
	public String add()throws Exception {
		//关联用户
		addr.setUser(getLoginUser());
		
		addrService.save(addr);
		
		ActionContext.getContext().put("msg", "添加成功！");
		return "message";
	}

	
	public String preEdit()throws Exception {
		//查询要修改的记录
		Address addr = addrService.findById(id);
		ActionContext.getContext().put("addr", addr);
		
		//查询当前用户的分组
		List<Group> groups = groupService.findByUser(getLoginUsername());
		
		ActionContext.getContext().put("groups", groups);
		
		return "edit_input";
	}
	
	
	public String edit()throws Exception {
		//关联用户
		addr.setUser(getLoginUser());
		
		addrService.update(addr);
		
		ActionContext.getContext().put("msg", "修改成功！");
		return "message";
	}
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Address getAddr() {
		return addr;
	}


	public void setAddr(Address addr) {
		this.addr = addr;
	}
	
	
}
