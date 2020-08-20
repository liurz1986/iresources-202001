package org.com.liurz.iresources.gateway.service;

import org.com.liurz.iresources.gateway.util.RedisUtil;

import redis.clients.jedis.JedisPool;

public class RedisService extends RedisUtil {

	public RedisService(String ip, int port) {
		this.jedisPool = new JedisPool(ip, port);
		this.jedis = jedisPool.getResource();
	}

}
