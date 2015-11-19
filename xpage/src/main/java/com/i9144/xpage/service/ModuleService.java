package com.i9144.xpage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.ModuleDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Module;

@Service
public class ModuleService {
	private static final Logger logger = Logger.getLogger(ModuleService.class);

	@Resource
	private ModuleDAO moduleDao;
	
	public int add(Module module) throws HelperException {
		logger.debug(">>>> add module: " + module);
		Date now = new Date();
		module.setCreateTime(now);
		module.setUpdateTime(now);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageId", module.getPageId());
		int pos = moduleDao.getMaxPosition(params);
		module.setPosition(++pos);
		Integer result =  (Integer) moduleDao.add(module);
		if ( result == null ) {
			return 0;
		}
		return result;
	}
	
	public List<Module> getByPageId(int pageId) {
		List<Module> modules = moduleDao.getByPageId(pageId);
		return modules;
	}
	
	public int update(Module module) {
		logger.debug(">>>> update module: " + module);
		Date now = new Date();
		module.setUpdateTime(now);
		return moduleDao.update(module);
	}
	
	public int delete(int pageId, int moduleId) {
		int result =  moduleDao.delete(moduleId);
		return result;
	}
}
