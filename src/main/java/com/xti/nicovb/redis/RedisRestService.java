package com.xti.nicovb.redis;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@Produces("text/html")
@Path("/redis")
public class RedisRestService {

	@Autowired
	private JedisPool pool;

	@PUT
	@Path("/{key}/{value}")
	public String addValue(@PathParam("key") String key,
			@PathParam("value") String value) {
		Jedis jedis = pool.getResource();
		Long valueLength = jedis.append(key, value);
		pool.returnResource(jedis);
		return String.format("Total length of the key is now %d", valueLength);
	}

	@GET
	@Path("/{key}")
	public String getValue(@PathParam("key") String key) {
		Jedis jedis = pool.getResource();
		String value = jedis.get(key);
		pool.returnResource(jedis);
		return value;
	}

}
