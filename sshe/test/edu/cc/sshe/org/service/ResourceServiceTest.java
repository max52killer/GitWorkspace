package edu.cc.sshe.org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.cc.sshe.framework.domain.Resource;
import edu.cc.sshe.framework.service.IResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ResourceServiceTest {

	@Autowired
	private IResourceService resService;

	@Test
	public void testInit() {

		Resource res01 = new Resource("01", "操作菜单", Resource.TYPE_MENU, null, 1, null);

		Resource res0101 = new Resource("0101", "Easyui示例", Resource.TYPE_MENU, null, 2, res01);

		Resource res010101 = new Resource("010101", "基础示例", Resource.TYPE_MENU, "pages/demo_base.jsp", 3, res0101);
		Resource res010102 = new Resource("010102", "消息框示例", Resource.TYPE_MENU, "pages/demo_messager.jsp", 4, res0101);
		Resource res010103 = new Resource("010103", "Tree示例", Resource.TYPE_MENU, "pages/demo_tree.jsp", 5, res0101);
		Resource res010104 = new Resource("010104", "查看权限树", Resource.TYPE_MENU, "pages/demo_res.jsp", 5, res0101);

		Resource res0102 = new Resource("0102", "系统管理", Resource.TYPE_MENU, null, 6, res01);

		Resource res010201 = new Resource("010201", "用户管理", Resource.TYPE_MENU, "pages/user_list.jsp", 7, res0102);
		Resource res01020101 = new Resource("user_add", "添加用户", Resource.TYPE_ACTION, null, 8, res010201);
		Resource res01020102 = new Resource("user_edit", "修改用户", Resource.TYPE_ACTION, null, 9, res010201);
		Resource res01020103 = new Resource("user_delete", "删除用户", Resource.TYPE_ACTION, null, 10, res010201);
		Resource res01020104 = new Resource("user_query", "查询用户", Resource.TYPE_ACTION, null, 11, res010201);

		Resource res010202 = new Resource("010202", "角色管理", Resource.TYPE_MENU, "pages/role_list.jsp", 12, res0102);
		Resource res01020201 = new Resource("role_add", "添加角色", Resource.TYPE_ACTION, null, 13, res010202);
		Resource res01020202 = new Resource("role_edit", "修改角色", Resource.TYPE_ACTION, null, 14, res010202);
		Resource res01020203 = new Resource("role_delete", "删除角色", Resource.TYPE_ACTION, null, 15, res010202);
		Resource res01020204 = new Resource("role_query", "查询角色", Resource.TYPE_ACTION, null, 16, res010202);
		Resource res01020205 = new Resource("role_query", "角色授权", Resource.TYPE_ACTION, null, 17, res010202);

		Resource res010203 = new Resource("010203", "菜单管理", Resource.TYPE_MENU, "pages/resource_list.jsp", 17, res0102);
		Resource res01020301 = new Resource("menu_add", "添加菜单", Resource.TYPE_ACTION, null, 18, res010203);
		Resource res01020302 = new Resource("menu_edit", "修改菜单", Resource.TYPE_ACTION, null, 19, res010203);
		Resource res01020303 = new Resource("menu_delete", "删除菜单", Resource.TYPE_ACTION, null, 20, res010203);
		Resource res01020304 = new Resource("menu_query", "查询菜单", Resource.TYPE_ACTION, null, 21, res010203);

		Resource res010204 = new Resource("010204", "数据字典", Resource.TYPE_MENU, "pages/datadict_list.jsp", 22, res0102);

		Resource res0103 = new Resource("0103", "组织机构管理", Resource.TYPE_MENU, null, 1, res01);

		Resource res010301 = new Resource("010301", "员工管理", Resource.TYPE_MENU, "pages/emp_list.jsp", 1, res0103);
		Resource res01030101 = new Resource("employee_add", "添加员工", Resource.TYPE_ACTION, null, 1, res010301);
		Resource res01030102 = new Resource("employee_edit", "修改员工", Resource.TYPE_ACTION, null, 1, res010301);
		Resource res01030103 = new Resource("employee_delete", "删除员工", Resource.TYPE_ACTION, null, 1, res010301);
		Resource res01030104 = new Resource("employee_query", "查询员工", Resource.TYPE_ACTION, null, 1, res010301);

		Resource res010302 = new Resource("010302", "部门管理", Resource.TYPE_MENU, "pages/dept_list.jsp", 1, res0103);
		Resource res01030201 = new Resource("dept_add", "添加部门", Resource.TYPE_ACTION, null, 1, res010302);
		Resource res01030202 = new Resource("dept_edit", "修改部门", Resource.TYPE_ACTION, null, 1, res010302);
		Resource res01030203 = new Resource("dept_delete", "删除部门", Resource.TYPE_ACTION, null, 1, res010302);
		Resource res01030204 = new Resource("dept_query", "查询部门", Resource.TYPE_ACTION, null, 1, res010302);

		resService.save(res01);
		resService.save(res0101);
		resService.save(res010101);
		resService.save(res010102);
		resService.save(res010103);
		resService.save(res010104);
		resService.save(res0102);
		resService.save(res010201);
		resService.save(res01020101);
		resService.save(res01020102);
		resService.save(res01020103);
		resService.save(res01020104);

		resService.save(res010202);
		resService.save(res01020201);
		resService.save(res01020202);
		resService.save(res01020203);
		resService.save(res01020204);
		resService.save(res01020205);

		resService.save(res010203);
		resService.save(res01020301);
		resService.save(res01020302);
		resService.save(res01020303);
		resService.save(res01020304);
		resService.save(res010204);

		resService.save(res0103);
		resService.save(res010301);
		resService.save(res01030101);
		resService.save(res01030102);
		resService.save(res01030103);
		resService.save(res01030104);

		resService.save(res010302);

		resService.save(res01030201);
		resService.save(res01030202);
		resService.save(res01030203);
		resService.save(res01030204);

	}

}
