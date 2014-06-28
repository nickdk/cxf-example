package com.xti.nickdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "TimingFilter", urlPatterns = { "/*" })
public class TimingFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger("performance");
	private static final Map<String, Class<?>> resources = new HashMap<>();
	
	public static void addResource(String path, Class<?> resource) {
		resources.put(path, resource);
	}
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest)) {
			filterChain.doFilter(req, resp);
		}

		Long start = System.nanoTime();
		filterChain.doFilter(req, resp);
		Long end = System.nanoTime();

		HttpServletRequest httpReq = (HttpServletRequest) req;
		
		Class<?> resource = null;
		for (Entry<String, Class<?>> rs : resources.entrySet()) {
			if (httpReq.getRequestURI().startsWith(rs.getKey())) {
				resource = rs.getValue();
				break;
			}
		}

		LOGGER.info("duration={} path=\"{}\" resource={}", (end - start) / 1000000,
				httpReq.getRequestURI(), resource.getSimpleName());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
