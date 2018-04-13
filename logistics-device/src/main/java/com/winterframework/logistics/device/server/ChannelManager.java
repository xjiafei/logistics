package com.winterframework.logistics.device.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.utils.Base64Util;

import io.netty.channel.Channel;

public class ChannelManager {
	private static final Logger log = LoggerFactory.getLogger(ChannelManager.class);
	private static final Map<String, Channel> channelMap = new ConcurrentHashMap<String,Channel>();
	//private final static ThreadLocal<Channel> channels = new ThreadLocal<Channel>(){};
	//private static final RedisQueue redisQueue=SpringContextHolder.getBean("redisQueue");
	//private static final RedisClient redisClient=SpringContextHolder.getBean("RedisClient");
	//private static final String queueOnline="queue_online";
	
	public static void echoChannelManager(){ 
		for(Map.Entry<String, Channel> entry:channelMap.entrySet()){
				log.error("****************************************** echoChannelManager******************************************");
				log.error(" ChannelManager: key = "+entry.getKey()+" ; value = "+entry.getValue()+" ; base64 = "+new String(Base64Util.decode(entry.getKey())));
				log.error("****************************************** echoChannelManager******************************************");
		}
	}

	
	public static Channel get(Long userId, Long deviceId) {
		if(null==userId) return null;
		String tokenKey=TokenManager.getTokenKey(userId, deviceId);
		return channelMap.get(tokenKey);
	} 
	public static Channel get(String key) {
		if(null==key) return null;
		return channelMap.get(key);
	} 
	public static void save(Long userId, Long deviceId, Channel channel){   
		if(null!=userId){
			String tokenKey=TokenManager.getTokenKey(userId,deviceId);
			if(null==channelMap.get(tokenKey)){	 
				log.error("User connect. userId="+userId); 
				channelMap.put(tokenKey, channel);
				//redisQueue.del(queueOnline, getOnlineValue(userId, deviceId));
				//redisQueue.add(queueOnline, getOnlineValue(userId, deviceId));
			}
		}
	}
	public static void save(String key,Channel channel){
		if(null!=key){
			if(null==channelMap.get(key) || !get(key).equals(channel)){
				log.error("device connect. imei="+key); 
				channelMap.put(key, channel);
			}
		}
	}
	public static void remove(Long userId,Long deviceId){ 
		if(null==userId) return;
		String tokenKey=TokenManager.getTokenKey(userId,deviceId);
		channelMap.remove(tokenKey);
		//redisQueue.del(queueOnline, getOnlineValue(userId, deviceId));
	}

	public static String remove(Channel channel){ 
		for(Map.Entry<String, Channel> entry:channelMap.entrySet()){
			if(entry.getValue()==channel){
				log.error("User disconnect. imei="+entry.getKey());
				String key=entry.getKey();
				channelMap.remove(key);
				return key;
			}
		}
		return null;
	}
	public static String getBy(Channel channel){ 
		for(Map.Entry<String, Channel> entry:channelMap.entrySet()){
			if(entry.getValue()==channel){
				return entry.getKey();
			}
		}
		return null;
	}
	public static boolean isConnected(Long userId,Long deviceId){
		//if(null!=redisClient.get("test"+userId)) return true;
		Channel c=get(userId, deviceId);
		return null!=c && c.isActive();
	}	
	public static boolean isConnected(String key){
		//if(null!=redisClient.get("test"+userId)) return true;
		Channel c=get(key);
		return null!=c && c.isActive();
	}
	public static boolean isConnected(Channel channel){
		return null!=channel && channel.isActive();
	}
}
