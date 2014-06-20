package com.xti.nicovb.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class JedisExample {

	@Autowired
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}
