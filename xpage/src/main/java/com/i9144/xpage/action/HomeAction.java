package com.i9144.xpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class HomeAction {
	private static final Logger logger = Logger.getLogger(HomeAction.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response,
			Model model){
		//model.addAttribute("channelPageCountMap",channelPageCountMap);
		return "/home";
	} 
}
