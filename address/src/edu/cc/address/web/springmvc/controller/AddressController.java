package edu.cc.address.web.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cc.address.bean.PageBean;
import edu.cc.address.domain.Address;
import edu.cc.address.domain.Group;
import edu.cc.address.service.IAddressService;
import edu.cc.address.service.IGroupService;
import edu.cc.address.util.SessionUtil;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IAddressService addrService;
	
	@RequestMapping("/list/page-{page}")
	public String list(@PathVariable("page") int page,HttpSession session,Model model)throws Exception {
		
		PageBean pb = addrService.queryPaged(SessionUtil.getLoginUsername(session), page, 10);
		
		model.addAttribute("pb", pb);
		return "addr_list";
	}
	
	/**
	 * 添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model)throws Exception {
		
		List<Group> groups = groupService.findByUser(SessionUtil.getLoginUsername(session));
		
		model.addAttribute("groups", groups);
		return "addr_add";
	}
	
	
	/**
	 * 添加操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Address addr,HttpSession session,Model model)throws Exception {
		
		addr.setUser(SessionUtil.getLoginUser(session));
		addrService.save(addr);
		
		model.addAttribute("msg", "添加成功!");
		model.addAttribute("url", "address/list/page-1");
		
		return "message";
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id,HttpSession session,Model model)throws Exception {
		addrService.delete(SessionUtil.getLoginUsername(session), id);
		model.addAttribute("msg", "删除成功");
		model.addAttribute("url", "address/list/page-1");
		return "message";
	}
	

	/**
	 * 修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id,HttpSession session,Model model)throws Exception {
		
		Address addr = addrService.findById(id);
		
		List<Group> groups = groupService.findByUser(SessionUtil.getLoginUsername(session));
		model.addAttribute("groups", groups);
		model.addAttribute("addr", addr);
		return "addr_edit";
	}
	
	
	/**
	 * 修改操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.POST)
	public String edit(Address addr,HttpSession session,Model model)throws Exception {
		
		addr.setUser(SessionUtil.getLoginUser(session));
		addrService.update(addr);
		
		model.addAttribute("msg", "修改成功!");
		model.addAttribute("url", "address/list/page-1");
		
		return "message";
	}
	
	
	
}
