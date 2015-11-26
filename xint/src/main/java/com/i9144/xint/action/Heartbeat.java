package com.i9144.xint.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Heartbeat {
	//private static final Logger logger = Logger.getLogger(Heartbeat.class);
	
	/**
	 *  check if service has heartbeat.
	 * @return "OK" if service is live
	 */
	@RequestMapping( value = "/heartbeat")
	public @ResponseBody String isLive() {
		return "OK";
	}
}
