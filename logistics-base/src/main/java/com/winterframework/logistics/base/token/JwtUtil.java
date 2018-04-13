package com.winterframework.logistics.base.token;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.winterframework.logistics.base.utils.Base64Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil{
	public static String generate(String id, String subject,long ttlMillis, String secret) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.signWith(SignatureAlgorithm.HS256, getSecretKey(secret)); 
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp).setNotBefore(now);
		}
		return builder.compact();
	}
	public static String verify(String token, String secret) {
		try {
			Claims claims = Jwts.parser().setSigningKey(getSecretKey(secret))
					.parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (Exception ex) {
			return null;
		}
	}
	private static Key getSecretKey(String secret){
		return new SecretKeySpec(Base64Util.decode(secret), "AES");
	}
	
	public static void main(String[] s){
		String token=JwtUtil.generate("11","test",5000L,"test");
		System.out.println(token);
		System.out.println(JwtUtil.verify(token,"test"));
		String sc="5IV696Zc0OkeIA92OkzTcK5C8FYJ907q";
		System.out.println(JwtUtil.verify("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbl9sb2dpbiIsImlhdCI6MTUxNTU3NDYwOCwic3ViIjoiLTEiLCJleHAiOjE1MTY2Njg2MTIsIm5iZiI6MTUxNTU3NDYwOH0.Dj-Xj8xpelwQZlXOFKoJOhcWBP9mjSP3LpCoiOuPsEs", sc));
		String login=JwtUtil.generate("login", "-1", 100*365*24*60*60*1000, sc);
		String customer=JwtUtil.generate("customer", "0", 100*365*24*60*60*1000, sc);
		String adminLogin=JwtUtil.generate("admin_login", "-1", 100*365*24*60*60*1000, sc);
		System.out.println(adminLogin);
		System.out.println(JwtUtil.verify(login,sc));
		System.out.println(JwtUtil.verify(customer,sc));
		System.out.println(JwtUtil.verify(adminLogin,sc));
		
	}
}