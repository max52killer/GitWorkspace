package edu.cc.sshe.framework.util;

import org.springframework.util.DigestUtils;

public class PasswordUtil {

	/**
	 * 对密码加密
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		if(password == null) {
			return null;
		}
		
		String prefix = "!@#$%^&*(+)(*&^%$";
		
		String subfix = "))*&^%$$##@@@!@!$%^";
		
		for(int i = 0;i < 1024;i++) {
			password = prefix + password + subfix;
			
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			
		}
		
		return password;
		
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("123456"));
	}
}
