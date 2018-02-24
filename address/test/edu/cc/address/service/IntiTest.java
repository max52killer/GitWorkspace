package edu.cc.address.service;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.cc.address.domain.Address;
import edu.cc.address.domain.Group;
import edu.cc.address.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class IntiTest {
	//spring自动注入
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IAddressService addrService;
	
	@Test
	public void init(){
		User user1=new User("zhangsan","123456");
		User user2=new User("lisi","654321");
		
		userService.save(user1);
		userService.save(user2);
		
		String[] gnames1={"我的好友","陌生人","同学","家人"};
		String[] gnames2={"大学同学","高中同学","中小同学","亲戚"};
		
		for(int i=0;i<gnames1.length;i++){
			Group g=new Group(gnames1[i],"",user1);
			groupService.save(g);
		}
		for(int i=0;i<gnames2.length;i++){
			Group g=new Group(gnames2[i],"",user2);
			groupService.save(g);
		}
		
		Random r=new Random();
		
		String[] emails = {"@sohu.com","@163.com","@gmail.com","@sina.com.cn","@263.com"};
        String[] mobiles = {"130","131","133","135","138","150","158","170","188"};
        String[] firsts = {"张","王","李","赵","孙","钱","周","袁","杨","阿","邱"};
        String str = "您可以自由链接下载传播此文档或者放置在您的网站上甚至作为产品的一部分发行但前提是必须保证全文完整转载包括完整的版权信息和作译者声明并不能违反LGPL协议这里完整的含义是不能进行任何删除增添注解若有删除增添注解必须逐段明确声明那些部分并非本文档的一部分";
        for(int i = 0;i < 126;i++) {
        	Address addr = new Address();
       
        	addr.setMobile(mobiles[r.nextInt(mobiles.length)] + String.format("%08d", r.nextInt(99999999)));
        	addr.setName(firsts[r.nextInt(firsts.length)] + str.charAt(r.nextInt(str.length()))  + str.charAt(r.nextInt(str.length())));
         	addr.setEmail(addr.getName()+emails[r.nextInt(emails.length)]);
        	addr.setPhone("029-"+String.format("%08d", r.nextInt(99999999)));
        	addr.setQq( String.format("%09d", r.nextInt(99999999)));
        	addr.setUser(user1);
        	addr.setGroup(new Group(1 + r.nextInt(gnames1.length)));
        	addrService.save(addr);
        }
        
        for(int i = 0;i < 87;i++) {
        	Address addr = new Address();
       
        	addr.setMobile(mobiles[r.nextInt(mobiles.length)] + String.format("%08d", r.nextInt(99999999)));
        	addr.setName(firsts[r.nextInt(firsts.length)] + str.charAt(r.nextInt(str.length()))  + str.charAt(r.nextInt(str.length())));
         	addr.setEmail(addr.getName()+emails[r.nextInt(emails.length)]);
        	addr.setPhone("029-"+String.format("%08d", r.nextInt(99999999)));
        	addr.setQq( String.format("%09d", r.nextInt(99999999)));
        	addr.setUser(user2);
        	addr.setGroup(new Group(5 + r.nextInt(gnames2.length)));
        	addrService.save(addr);
        }
	}

}
