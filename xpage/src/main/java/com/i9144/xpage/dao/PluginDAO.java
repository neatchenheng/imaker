package com.i9144.xpage.dao;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Plugin;

@Service
public class PluginDAO extends AbstractDAO<Plugin> {
	public Plugin getByPluginId(int pluginId) {
		Plugin p = (Plugin) getSqlMapClient().queryForObject(daoName + ".getByPluginId", pluginId); 
		return p;
	}
}
