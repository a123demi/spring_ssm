package com.ssm.manager.dao;

public interface RedisDao {
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
}
