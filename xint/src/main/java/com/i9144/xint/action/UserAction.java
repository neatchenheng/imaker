package com.i9144.xint.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i9144.xint.model.User;
import com.i9144.xint.util.CookieUtil;
import com.i9144.xint.util.SystemUtil;

@Controller
public class UserAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute User user, Model model) {
		// the target url to redirect to once login
		String targetUrl = request.getParameter("turl");
		String uid = CookieUtil.getFromCookies(request, "uid");
		// if already login, redirect to the target url
		if (uid != null) {
			redirect2TargetUrl(targetUrl, response);
			return null;
		}
		// setup target url
		if (targetUrl != null) {
			model.addAttribute("turl", targetUrl);
		}
		if (user.getPassword().equals("1qaz2wsx")
				&& user.getPhone().equals("13524038227")) {
			CookieUtil.writeCookies(response, "Henry", "100");
			logger.info(">>>> user[" + user.getPhone() + "] login success!");
		} else {
			model.addAttribute("errMsg", "用户名或密码有误!");
			return "login";
		}
		redirect2TargetUrl(targetUrl, response);
		return null;
	}

	private void redirect2TargetUrl(String targetUrl,
			HttpServletResponse response) {
		if (StringUtils.isBlank(targetUrl)) {
			targetUrl = new StringBuilder(SystemUtil.getProperty("envUrl"))
					.append("/index").toString();
		}
		try {
			response.sendRedirect(targetUrl);
		} catch (IOException e) {
			logger.error("!!!! IO Exception", e);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		CookieUtil.cleanCookies(request, response);
		return "login";
	}
}
