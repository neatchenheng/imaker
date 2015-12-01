package com.i9144.xpage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.ModuleDataDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Module;
import com.i9144.xpage.model.ModuleData;

@Service
public class ModuleDataService {
	private static final Logger logger = Logger.getLogger(ModuleDataService.class);

	@Resource
	private ModuleDataDAO moduleDataDao;
	
	public int add(ModuleData moduleData) {
		logger.debug(">>>> add module data: " + moduleData);
		Date now = new Date();
		moduleData.setCreateTime(now);
		moduleData.setUpdateTime(now);
		Integer result =  (Integer) moduleDataDao.add(moduleData);
		if ( result == null ) {
			return 0;
		}
		return result;
	}
	
	
	public int update(ModuleData moduleData) {
		logger.debug(">>>> update module data: " + moduleData);
		Date now = new Date();
		moduleData.setUpdateTime(now);
		return moduleDataDao.update(moduleData);
	}
	
	public int delete(int moduleDataId) {
		int result =  moduleDataDao.delete(moduleDataId);
		return result;
	}
}
