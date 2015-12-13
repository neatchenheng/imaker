package com.i9144.xpage.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Module;

@Service
public class ModuleDAO extends AbstractDAO<Module> {
	public List<Module> getByPageId(int pageId) {
		@SuppressWarnings("unchecked")
		List<Module> modules = getSqlMapClient().queryForList(daoName + ".getByPageId", pageId);
		return modules;
	}
	
}
