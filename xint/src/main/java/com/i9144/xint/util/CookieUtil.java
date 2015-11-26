package com.i9144.xint.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CookieUtil {
	private static final Logger logger = Logger.getLogger(CookieUtil.class);
	
	public static String getFromCookies(HttpServletRequest request, String cName) {
		logger.info(">>>> try to get value from cookie via name: " + cName);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies) {
				if (c.getName().equals(cName)) {
					logger.info(">>>> get cookie value: " + c.getValue());
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 登陆后设置cookies
	 * @param response
	 * @param userName
	 * @param userId
	 */
	public static void writeCookies(HttpServletResponse response, String userName, String userId) {
		Cookie c = new Cookie("un", userName);
		c.setPath("/");
		// 86400 seconds = 24 hours = 1 day
		c.setMaxAge(86400);
		response.addCookie(c);
		
		c = new Cookie("uid", userId);
		c.setPath("/");
		c.setMaxAge(86400);
		response.addCookie(c);
	}
	
	public static void cleanCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c: cookies) {
				if (! c.getName().equals("un") && ! c.getName().equals("uid")) {
					continue;
				}
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
		}
	}
}
