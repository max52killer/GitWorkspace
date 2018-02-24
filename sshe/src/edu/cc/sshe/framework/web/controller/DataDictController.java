package edu.cc.sshe.framework.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cc.sshe.framework.bean.GridData;
import edu.cc.sshe.framework.bean.Result;
import edu.cc.sshe.framework.bean.TreeNode;
import edu.cc.sshe.framework.domain.DataDict;
import edu.cc.sshe.framework.service.IDataDictService;

@RestController
@RequestMapping("/dataDict")
public class DataDictController {

	@Autowired
	private IDataDictService dictService;
	/**
	 * 返回数据字典类型树数据
	 */
	@RequestMapping("/type/tree")
	public Result typeTree() {
		List<DataDict> typeList = dictService.findDictTypes();
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for(DataDict dd : typeList) {
			TreeNode node = new TreeNode();
			node.setId(dd.getId());
			node.setText(dd.getName());
			//node.setAttributes(dd);
			node.addAttr("code", dd.getCode());
			nodes.add(node);
		}
		
		return Result.success(nodes);
	}
	
	
	@RequestMapping("/values")
	public Result dictValues(String pcode) throws Exception {
		if(pcode == null || "".equals(pcode)) {
			return Result.success();
		}
		
		List<DataDict> values = dictService.findByType(pcode);
		
		return Result.success(new GridData(values.size(), values));
		
	}
	
	
	@RequestMapping("/itemValues")
	public Result itemValues(String pcode) throws Exception {
		if(pcode == null || "".equals(pcode)) {
			return Result.success();
		}
		
		List<DataDict> values = dictService.findByType(pcode);
		
		return Result.success(values);
		
	}
	
	
	@RequestMapping("/add")
	public Result add(DataDict dict) throws Exception {
		 dictService.save(dict);
		
		return Result.success();
		
	}
	
}
