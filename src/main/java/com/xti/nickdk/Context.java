package com.xti.nickdk;

import java.util.Arrays;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.ws.rs.Path;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan("com.xti")
@PropertySource("classpath:application.properties")
@ImportResource("classpath:META-INF/cxf/cxf.xml")
@Import(PersistenceContext.class)
public class Context {

	@Autowired
	private ApplicationContext ctx;

	@Resource
	private Environment env;

	@Bean
	public Server jaxRsServer() {
		LinkedList<ResourceProvider> resourceProviders = new LinkedList<>();
		for (String beanName : ctx.getBeanDefinitionNames()) {
			Path path = ctx.findAnnotationOnBean(beanName, Path.class);
			if (path != null) {
				SpringResourceFactory factory = new SpringResourceFactory(beanName);
				factory.setApplicationContext(ctx);
				resourceProviders.add(factory);
				
				TimingFilter.addResource(path.value(), factory.getResourceClass());
			}
		}

		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(ctx.getBean(SpringBus.class));
		factory.setProviders(Arrays.asList(new JacksonJsonProvider()));
		factory.setResourceProviders(resourceProviders);
		return factory.create();
	}

	@Bean
	@Lazy
	public JedisPool jedisPool() {
		return new JedisPool(new JedisPoolConfig(),
				env.getProperty("datasource.jedis.host"),
				env.getProperty("datasource.jedis.port", Integer.class),
				env.getProperty("datasource.jedis.timeout", Integer.class),
				env.getProperty("datasource.jedis.password"));
	}
}