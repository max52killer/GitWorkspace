package edu.cc.address.web.struts2.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.cc.address.bean.Constraint;

@SuppressWarnings("serial")
public class RandomCodeAction extends ActionSupport {

	
	public void create()throws Exception {
		int width = 100;
		int height = 25;
		//画布
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    
		//画笔
		Graphics2D  g = image.createGraphics();
		
		g.setColor(Color.WHITE);  //设置画笔的颜色
		
		g.fillRect(0,0, width - 1, height - 1);  //将背景刷成白色
		g.setColor(Color.GRAY);
		g.drawRect(0,0, width, height);  //画边框
		
		
		Random r = new Random();
		//画点
		for(int i = 0;i < 100;i++) {
			g.drawOval(r.nextInt(width), r.nextInt(height), 1, 1);
		}
	
		//写文字
		
		Color color = new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));
		g.setColor(color);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		//g.drawString("Hello", 10,height - 10);  //写文字
		String str = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder builder = new StringBuilder(); //记住生成的字符
		for(int i = 0;i < 4;i++) {
			String ch = String.valueOf(str.charAt(r.nextInt(str.length()))).toUpperCase();
		    builder.append(ch);
			g.drawString(ch, i * (width / 4) + 5, height - r.nextInt(10));
		}
		
		//将验证码存入session
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constraint.RANDOMCODE_KEY, builder.toString());
		
		//禁止缓存
	     OutputStream  out = ServletActionContext.getResponse().getOutputStream();
		try {
			ImageIO.write(image, "PNG", out); //将Image用PNG格式编码，并将编码后的数据写入指定输出流
		} catch (IOException e) {
			e.printStackTrace();
		} 
	
		out.flush();
		
	}
}
