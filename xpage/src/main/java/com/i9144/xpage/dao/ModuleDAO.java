package com.i9144.xpage.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Module;

@Service
public class ModuleDAO extends AbstractDAO<Module> {
	public List<Module> getByPageId(int pageId) {
		@SuppressWarnings("unchecked")
		List<Module> modules = getSqlMapClient().queryForList(daoName + ".getByPageId", pageId);
		return modules;
	}
	
	public int sort(int moduleId, int position, Date updateDate) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", moduleId);
		params.put("position", position);
		params.put("now", updateDate);
		return getSqlMapClient().update(daoName + ".sort", params);
	}
}
