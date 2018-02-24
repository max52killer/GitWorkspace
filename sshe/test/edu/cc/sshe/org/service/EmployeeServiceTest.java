package edu.cc.sshe.org.service;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.cc.sshe.org.domain.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {

	@Autowired
	private IEmployeeService empService;
	
	
	@Test
	public void testSave() {
		Employee emp = new Employee("9527","张三", "男", "保安", new Date(), "1234567", 5000d);
		System.out.println("empService的实现类:" + empService.getClass().getName());
		empService.save(emp);
	}
	
	
	@Test
	public void testSaveMany() {
		
		String[] jobs = {"保安","经理","销售","客服","会计","出纳"};
		
		String[] telStart = {"130","131","133","136","137","138","139","158","157","186","185","188"};
		
		String[] firstName = {"张","王","李","赵","钱","孙","周","杨","常","党","袁","许"};
		
		String str = "第三两种语言在其浏览器中所执行的方式不一样的源代码在传递到客户端执行之前必须经过编译因而客户端上必须具有相应平台上的仿真器或解释器它可以通过编译器或解释器实现独立于某个特定的平台编译代码的束缚是一种解释性编程语言，其源代码在发往客户端执行之前不需经过编译而是将文本格式的字符代码发送给客户由浏览器解释执行";
		
		Random r = new Random();
		for(int i = 0;i < 136;i++) {
			String jobno = String.format("1%04d", i);
			String name = firstName[r.nextInt(firstName.length)] + str.charAt(r.nextInt(str.length())) + str.charAt(r.nextInt(str.length()));
			String sex = r.nextBoolean() ? "男" : "女";
			String job = jobs[r.nextInt(jobs.length)];
			Date hiredate = randomDate();
			double salary = 2000 + r.nextInt(20000);
			String tel = telStart[r.nextInt(telStart.length)] + String.format("%08d", r.nextInt(99999999));
			Employee emp = new Employee(jobno,name,sex, job, hiredate, tel, salary);
			empService.save(emp);
		}
		
			
	}
	
	
	private Date randomDate() {
		Random r = new Random();
		int y = 1977 + r.nextInt(40);
		int m = 1 + r.nextInt(12);
		int d = 1 + r.nextInt(28);
		
		String date = "" + y + "-"+ (m < 10 ? "0"  + m : "" + m) + "-" + (d < 10 ? "0" + d : "" + d);
		
		return java.sql.Date.valueOf(date);
	}
}
