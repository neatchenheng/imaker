package com.i9144.xpage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.ModuleDAO;
import com.i9144.xpage.dao.ModuleDataDAO;
import com.i9144.xpage.dao.PageDAO;
import com.i9144.xpage.dao.PluginDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Module;
import com.i9144.xpage.model.Module.Status;
import com.i9144.xpage.model.ModuleData;
import com.i9144.xpage.model.Page;
import com.i9144.xpage.model.Plugin;
import com.i9144.xpage.util.VelocityUtil;

@Service
public class PageService {
	private static final Logger logger = Logger.getLogger(PageService.class);

	@Resource
	private PageDAO pageDao;
	@Resource
	private PluginDAO pluginDao;
	@Resource
	private ModuleDAO moduleDao;
	@Resource
	private ModuleDataDAO moduleDataDao;

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

	public String getPageContent(int pageId, int moduleId) throws Exception {
		String vmExtPath = "WEB-INF/tpl/plugins/external/";
		StringBuilder builder = new StringBuilder();
		Page page = pageDao.get(pageId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", page);
		String header = VelocityUtil.getInstance().process(
				vmExtPath + "header.vm", param);
		builder.append(header);
		if (moduleId == 0) {
			// 未选择任何模块
			List<Module> list = moduleDao.getByPageId(pageId);
			for (Module m : list) {
				if (m.getStatus() == Status.ENABLE.getValue()) {
					List<ModuleData> mdList = moduleDataDao
							.getByModuleId(m.getId());
					m.setMdList(mdList);
					param.clear();
					param.put("module", m);
					String content = parseModuleVm(vmExtPath, m.getPluginId(),
							param);
					builder.append(content);
				}
			}
		} else {
			// 选择了特定模块
			Module m = moduleDao.get(moduleId);
			List<ModuleData> mdList = moduleDataDao.getByModuleId(moduleId);
			m.setMdList(mdList);
			param.clear();
			param.put("module", m);
			String content = parseModuleVm(vmExtPath, m.getPluginId(), param);
			builder.append(content);
		}
		param.clear();
		String footer = VelocityUtil.getInstance().process(
				vmExtPath + "footer.vm", param);
		builder.append(footer);
		return builder.toString();
	}

	private String parseModuleVm(String baseVmPath, int pluginId,
			Map<String, Object> param) throws Exception {
		String path = baseVmPath + pluginId + ".vm";
		String content = VelocityUtil.getInstance().process(path, param);
		return content;
	}
}
