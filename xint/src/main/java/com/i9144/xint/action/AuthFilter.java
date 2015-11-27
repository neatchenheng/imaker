package com.i9144.xint.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.i9144.xint.util.CookieUtil;
import com.i9144.xint.util.SystemUtil;

public class AuthFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(AuthFilter.class);
	private static String loginUrl;
	private static String indexUrl;
	private static String[] noAuthUrls;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		for (String noAuthUrl : noAuthUrls) {
			if (uri.contains(noAuthUrl)) {
				if (CookieUtil.getFromCookies(request, "uid") != null) {
					response.sendRedirect(indexUrl);
					return;
				}
				chain.doFilter(req, resp);
				return;
			}
		}
		String targetUrl = request.getParameter("turl");
		if (CookieUtil.getFromCookies(request, "uid") == null) {
			if (StringUtils.isBlank(targetUrl)) {
				response.sendRedirect(loginUrl);
			} else {
				response.sendRedirect(new StringBuilder(loginUrl).append("?turl=").append(targetUrl).toString());
			}
			return; 
		} else {
			chain.doFilter(req, resp);
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		loginUrl  =  new StringBuilder(SystemUtil.getProperty("envUrl")).append("/v1/login").toString();
		indexUrl = new StringBuilder(SystemUtil.getProperty("envUrl")).append("/v1/index").toString();
		noAuthUrls = new String[]{"/v1/login","/v1/heartbeat"};
		logger.info(">>>> login url: " + loginUrl);
		logger.info(">>>> index url: " + indexUrl);
	}

}
