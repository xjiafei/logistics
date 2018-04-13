package com.winterframework.logistics.base.restful;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.redis.RedisClient;
import com.winterframework.modules.spring.exetend.PropertyConfig;

/**
 * 幂等性处理
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月28日
 */
@Service("Idempotent")
public class Idempotent {
	@Resource(name = "RedisClient")
	private RedisClient redis;
	@PropertyConfig(value = "restful.idempotent.expired")
	private Integer expired;	//过期时间(秒)

	public Idempotent() {
	}

	public String getOperId(){
		String bizId=UUID.randomUUID().toString().replace("-", "");
		redis.set(bizId, bizId, expired);
		return bizId;
	}

}
