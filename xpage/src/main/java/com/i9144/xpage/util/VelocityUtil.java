package com.i9144.xpage.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	private String outputEncoding = "UTF-8";
	private VelocityEngine __engine;
	private static VelocityUtil velocityUtil = null;

	private VelocityUtil() {
		__engine = new VelocityEngine();
		InputStream fis = null;
		try {
			Properties prop = new Properties();
			fis = VelocityUtil.class.getClassLoader().getResourceAsStream(
					"velocity.properties");
			prop.load(fis);
			String classpath = VelocityUtil.class.getResource("/").getPath();
			String WEB_INF_PATH = classpath.substring(0, classpath.length()
					- "/WEB-INF/classes/".length());
			prop.put("file.resource.loader.path", WEB_INF_PATH);
			prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			__engine.init(prop);
		} catch (Exception e) {
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static VelocityUtil getInstance() {
		if (velocityUtil == null) {
			velocityUtil = new VelocityUtil();
		}
		return velocityUtil;
	}

	private VelocityContext genVelocityContext() {
		VelocityContext context = new VelocityContext();
		return context;
	}

	public String process(String tplPath, Map<String, Object> data)
			throws Exception {
		VelocityContext context = genVelocityContext();
		context.put("data", data);
		return process(tplPath, context);
	}

	public String process(String tplPath, VelocityContext context)
			throws Exception {
		StringWriter sw = new StringWriter();
		__engine.getTemplate(tplPath).merge(context, sw);
		return sw.toString();
	}

	public void genHtml(String tplPath, Map<String, Object> data,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(outputEncoding);
		String html = process(tplPath, data);
		response.getWriter().write(html);
	}
}
