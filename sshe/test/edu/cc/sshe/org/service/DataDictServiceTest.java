package edu.cc.sshe.org.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.cc.sshe.framework.domain.DataDict;
import edu.cc.sshe.framework.service.IDataDictService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DataDictServiceTest {

	@Autowired
	private IDataDictService dictService;
	
	
	@Test
	public void testSave() {
		
		DataDict dd1 = new DataDict("job","工作岗位","员工的工作岗位",null);
		DataDict dd2 = new DataDict("sex","性别","员工的性别",null);
		DataDict dd3 = new DataDict("out_color","车体颜色","车辆的外体颜色",null);
		DataDict dd4 = new DataDict("inner_color","内饰颜色","",null);
		dictService.save(dd1);
		dictService.save(dd2);
		dictService.save(dd3);
		dictService.save(dd4);
		
	}
	
	@Test
	public void testSaveValues() {
		DataDict dd1 = new DataDict("j001","保安","","job");
		DataDict dd2 = new DataDict("j002","销售","","job");
		DataDict dd3 = new DataDict("red","红色","","out_color");
		DataDict dd4 = new DataDict("black","黑色","","out_color");
		dictService.save(dd1);
		dictService.save(dd2);
		dictService.save(dd3);
		dictService.save(dd4);
	}
	
	
	
}
