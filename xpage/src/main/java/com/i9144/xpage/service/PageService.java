package com.i9144.xpage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.ModuleDAO;
import com.i9144.xpage.dao.PageDAO;
import com.i9144.xpage.dao.PluginDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Module;
import com.i9144.xpage.model.Page;
import com.i9144.xpage.model.Plugin;

@Service
public class PageService {
	private static final Logger logger = Logger.getLogger(PageService.class);

	@Resource
	private PageDAO pageDao;
	@Resource
	private PluginDAO pluginDao;
	@Resource
	private ModuleDAO moduleDao;

	public void add(Page page) throws HelperException {
		logger.debug(">>>> add page: " + page);
		Date now = new Date();
		page.setCreateTime(now);
		page.setUpdateTime(now);
		pageDao.add(page);
	}

	public List<Page> listByChannelId(int channelId) {
		logger.debug(">>>> list Pages...");
		List<Page> ps = pageDao.getPagesByChannelId(channelId);
		return ps;
	}

	public int update(Page page) {
		logger.debug(">>>> update page: " + page);
		Date now = new Date();
		page.setUpdateTime(now);
		return pageDao.update(page);
	}

	public Map<String, Object> get(int pageId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = pageDao.get(pageId);
		List<Plugin> plugins = pluginDao.list();
		List<Module> modules = moduleDao.getByPageId(pageId);
		map.put("page", page);
		map.put("plugins", plugins);
		map.put("modules", modules);
		return map;
	}
}
