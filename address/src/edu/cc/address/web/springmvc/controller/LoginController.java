package edu.cc.address.web.springmvc.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.cc.address.bean.Constraint;
import edu.cc.address.domain.User;
import edu.cc.address.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login_page.action")
	public String page() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username,String password,String checkCode,HttpSession session,Model model) {
		// 校验验证码
		String realCode = (String)session.getAttribute(Constraint.RANDOMCODE_KEY);
		if (realCode == null || !realCode.equalsIgnoreCase(checkCode)) {
			model.addAttribute("msg", "验证码不正确!");
			return "login";
		}

		// 用户名密码校验
		User user = userService.login(username, password);
		if (user == null) {
			model.addAttribute("msg", "用户名或密码不正确!");
			return "login";
		}

		// 将登录信息存入Session
		session.setAttribute(Constraint.SESSION_USER_KEY, user);
		
		//去列表页面
		
		return "redirect:address/list/page-1";  //重定向至另一个控制器
	}

	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {		
		session.invalidate();
		return "login";
	}
	
	/**
	 * 生成验证码
	 * 
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/randomCode")
	public void randomCode(HttpSession session, HttpServletResponse response) throws Exception {
		int width = 100;
		int height = 25;
		// 画布
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 画笔
		Graphics2D g = image.createGraphics();

		g.setColor(Color.WHITE); // 设置画笔的颜色

		g.fillRect(0, 0, width - 1, height - 1); // 将背景刷成白色
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width, height); // 画边框

		Random r = new Random();
		// 画点
		for (int i = 0; i < 100; i++) {
			g.drawOval(r.nextInt(width), r.nextInt(height), 1, 1);
		}

		// 写文字
		Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		g.setColor(color);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		// g.drawString("Hello", 10,height - 10); //写文字
		String str = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder builder = new StringBuilder(); // 记住生成的字符
		for (int i = 0; i < 4; i++) {
			String ch = String.valueOf(str.charAt(r.nextInt(str.length()))).toUpperCase();
			builder.append(ch);
			g.drawString(ch, i * (width / 4) + 5, height - r.nextInt(10));
		}

		// 将验证码存入session
		session.setAttribute(Constraint.RANDOMCODE_KEY, builder.toString());

		// 禁止缓存
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		try {
			ImageIO.write(image, "PNG", out); // 将Image用PNG格式编码，并将编码后的数据写入指定输出流
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.flush();
	}

}
