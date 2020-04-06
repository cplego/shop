package org.cplego.foodie.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class RedisOperator {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String get(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
	public void set(String key,String value){
		stringRedisTemplate.opsForValue().set(key,value);
	}
	public void set(String key,String value,long timeout){
		stringRedisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);
	}



}
