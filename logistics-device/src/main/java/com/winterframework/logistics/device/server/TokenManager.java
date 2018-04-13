package com.winterframework.logistics.device.server;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.enums.Separator;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.redis.RedisClient;
import com.winterframework.logistics.base.utils.Base64Util;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.base.utils.MD5Util;
import com.winterframework.modules.utils.SpringContextHolder;

public class TokenManager {
	private static final Logger log = LoggerFactory.getLogger(TokenManager.class); 
	private static final String PREFIX=Base64Util.encodeToString("TOKEN_".getBytes());
	private static final RedisClient redis=SpringContextHolder.getBean("RedisClient");

	public static boolean isValid(Long userId,Long deviceId,String token){
		if(null==userId) return false;
		String tokenTmp=getToken(userId,deviceId);
		return null==tokenTmp?false:tokenTmp.equals(token);
	}
	
	public static String getToken(Long userId,Long deviceId) { 
		String token = null;
		if(null!=userId){
			String tokenTemp = getMd5TokenKey(userId, deviceId);
			if(redis.get(tokenTemp)!=null){
				token = getRealToken(tokenTemp);
			}
		}
		
		return token;
	} 
	

	
	
	public static String getMd5TokenKey(Long userId,Long deviceId){
		if(null==userId) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(userId);
		sb.append(Separator.vertical);
		if(null==deviceId){
			sb.append("");
		}else{
			sb.append(deviceId);
		}
		return PREFIX+MD5Util.getMD5Format(sb.toString().getBytes());
	}
	
	public static Context getTokenContext(String token) { 
		if(null==token) return null;
		String ctxStr=redis.get(getRedisToken(token));
		if(null!=ctxStr){
			return JsonUtils.fromJson(ctxStr, Context.class);
		}
		return null;
		//return tokenMap.get(token);
	}
	private static String getRedisToken(String token){
		return PREFIX+token;
	}
	private static String getRealToken(String redisToken){
		return redisToken.substring(PREFIX.length());
	}
	private static String getTokenPattern(){
		return PREFIX+Separator.start;
	}
	public static String save(Context ctx){  
		String token=getToken(ctx.getUserId(),null);
		if(null==token){
			token=getMd5TokenKey(ctx.getUserId(), null);
		  //token=getTokenValue();
		  //tokenMap.put(token,userId);
		  redis.set(token, JsonUtils.toJson(ctx));
		  token=getRealToken(token);
		}
		return token;
	} 
	public static String save(Context ctx,String token){  
		String tokenTemp=getToken(ctx.getUserId(), null);
		if(null==tokenTemp){
			tokenTemp=getRedisToken(token);
		    redis.set(tokenTemp, JsonUtils.toJson(ctx));
		}
		return token;
	} 
	
	public static String remove(Long userId,Long deviceId){
		String token=getToken(userId, deviceId);
		remove(token);	
		return token;
	}
		
	public static void remove(String token){ 
		if(null==token) return;
		//tokenMap.remove(token);
		redis.del(getRedisToken(token));
	}
/*	private static String getTokenValue(){ 
		return PREFIX+UUID.randomUUID().toString().replace("-", "");
	}*/
	

	
	public static String getTokenKey(Long userId,Long deviceId){
		if(null==userId) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(userId);
		sb.append(Separator.vertical);
		if(null==deviceId){
			sb.append("");
		}else{
			sb.append(deviceId);
		}
		return Base64Util.encodeToString(sb.toString().getBytes());
	}
	
	public static void main(String[] args){
		/*String token=UUID.randomUUID().toString().replace("-", "");//Base64.encodeBase64String(UUID.randomUUID().toString().toUpperCase().getBytes(CharsetFactory.getCharset(CharsetFactory.CharsetEnum.UTF8.getCode())));
		System.out.println(token);
		String s=getTokenKey(100101L, 1001L);
		System.out.println(s);
		System.out.println(new String(Base64Util.getBytesFromBASE64(s)));*/
		
		System.out.println(getTokenKey(6697L,1483L));
		
	}
	
}