package com.i9144.xint.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/v1")
public class HomeAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
}
