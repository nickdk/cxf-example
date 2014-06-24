package com.xti.nicovb.redis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

@Component
@Produces("text/plain")
@Path("/redis")
public class RedisRestService {

	@Autowired
	private JedisPool pool;

	@PUT
	@Path("/{key}/{value}")
	public String addValue(@PathParam("key") String key,
			@PathParam("value") String value) {

		Jedis jedis = pool.getResource();

		// Start transaction
		Transaction transaction = jedis.multi();

		/*
		 * NX -- Only set the key if it does not already exist. XX -- Only set
		 * the key if it already exist.
		 */
		Response<String> set = transaction.set(key, value, "NX");

		// End transaction
		List<Object> execGetResponse = transaction.exec();

		pool.returnResource(jedis);
		return String.format("Total length of the key is now %s (%s)",
				set.get(), execGetResponse.get(0));
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
