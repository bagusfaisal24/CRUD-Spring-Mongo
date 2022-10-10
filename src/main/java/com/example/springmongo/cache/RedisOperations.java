package com.example.springmongo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * 
 *
 */
@Service
public class RedisOperations {

	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	public void hset(String key, String hashKey, Object object) {
		redisTemplate.opsForHash().put(key, hashKey, object);
	}

	public  void hset(String key, HashMap<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	public  Object hget(String key, String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}
	public  Object hget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	public void deleteByHashKey(String key, String hashKey) {
		redisTemplate.opsForHash().delete(key, hashKey);
	}
	public void putIfAbsen(String key, Object hashKey, Object data){
		redisTemplate.opsForHash().putIfAbsent(key, hashKey, data);
	}
}
