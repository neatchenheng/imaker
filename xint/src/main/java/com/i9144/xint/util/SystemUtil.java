package com.i9144.xint.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class SystemUtil {
	private static final Logger logger = Logger.getLogger(SystemUtil.class);

	public final static ResourceBundle CONSTANTS = ResourceBundle
			.getBundle("system");

	public static String getProperty(String key) {
		String val = CONSTANTS.getString(key);
		logger.debug("#### get property[" + key + "] value[" + val + "]");
		return val;
	}
}
