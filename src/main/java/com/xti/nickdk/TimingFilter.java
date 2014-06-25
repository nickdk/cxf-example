package com.xti.nickdk;

import java.io.IOException;

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

		LOGGER.info("{} ms for {}", (end - start) / 1000000,
				httpReq.getRequestURI());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
