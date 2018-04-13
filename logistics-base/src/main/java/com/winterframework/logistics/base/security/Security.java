
package com.winterframework.logistics.base.security;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.utils.MD5Util;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Service("security")
public class Security
{
	@PropertyConfig(value = "security.password.salt")
	private String salt;
	
	public String encrypt(String password){
		return MD5Util.getMD5Format(password+salt);
	}
	public boolean verify(String encrpyPwd1,String encrpyPwd2){
		return encrpyPwd1.equals(encrpyPwd2);
	}
	public static void main(String[] s){
		System.out.println(MD5Util.getMD5Format("123qwe"+"IS8b9zvn"));
	}
	
	
}