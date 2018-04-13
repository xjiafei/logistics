package com.winterframework.logistics.base.utils;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.redis.RedisClient;
import com.winterframework.logistics.base.utils.DateUtils;

@Service("numberUtil")
public class NumberUtil {
	private final ReentrantLock lock = new ReentrantLock();

	@Resource(name = "RedisClient")
	private RedisClient redis;

	/**
	 * @param 编码前缀
	 * @param 序列长度
	 * @param 是否包含日期
	 * @return
	 * @throws Exception
	 */
	public String getNumber(String prefix,Integer seqLen,boolean hasDate) throws Exception {
		try {
			lock.lock();
			StringBuilder sb = new StringBuilder();
			sb.append(prefix);
			if(hasDate){
				sb.append(DateUtils.format(DateUtils.currentDate(), DateUtils.DATE_FORMAT_PATTERN_NO_SEPARATOR));
			}
			sb.append((redis.getIncre(prefix)+100000000+"").substring(9-seqLen));
			return sb.toString();
		} finally {
			lock.unlock();
		}

	}

	
	public static void main(String[] args) throws Exception {
		NumberUtil s=new NumberUtil();
		System.out.println(s.getNumber("100", 3, false));
		System.out.println(s.getNumber("100", 5, true));
	}
}
