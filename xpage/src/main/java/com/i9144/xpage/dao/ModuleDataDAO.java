package com.i9144.xpage.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.ModuleData;

@Service
public class ModuleDataDAO extends AbstractDAO<ModuleData> {
	public List<ModuleData> getByModuleId(int moduleId) {
		@SuppressWarnings("unchecked")
		List<ModuleData>  list = getSqlMapClient().queryForList(daoName + ".getByModuleId", moduleId);
		return list;
	}
	
}
