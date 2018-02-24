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
import edu.cc.address.service.IGroupService;
import edu.cc.address.service.IUserService;
import edu.cc.address.util.SessionUtil;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private IGroupService groupService;
	
	
	@RequestMapping("/list/page-{page}")
	public String list(@PathVariable("page") int page,HttpSession session,Model model)throws Exception {
		
		PageBean pb = groupService.queryByPaged(SessionUtil.getLoginUsername(session), page, 10);
		
		model.addAttribute("pb", pb);
		return "group_list";
	}
	
	/**
	 * 添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model)throws Exception {
		return "group_add";
	}
	
	
	/**
	 * 添加操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Group group,HttpSession session,Model model)throws Exception {
		
		group.setUser(SessionUtil.getLoginUser(session));
		groupService.save(group);
		
		model.addAttribute("msg", "添加成功!");
		model.addAttribute("url", "group/list/page-1");
		
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
		groupService.delete(SessionUtil.getLoginUsername(session), id);
		model.addAttribute("msg", "删除成功");
		model.addAttribute("url", "group/list/page-1");
		return "message";
	}
	
	/**
	 * 修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id,HttpSession session,Model model)throws Exception {
		
		Group group = groupService.findById(id);
		
		model.addAttribute("group", group);
		return "group_edit";
	}
	
	
	/**
	 * 修改操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.POST)
	public String edit(Group group,HttpSession session,Model model)throws Exception {
		
		group.setUser(SessionUtil.getLoginUser(session));
		groupService.update(group);
		
		model.addAttribute("msg", "修改成功!");
		model.addAttribute("url", "group/list/page-1");
		
		return "message";
	}

}
