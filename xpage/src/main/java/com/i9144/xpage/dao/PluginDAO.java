package com.i9144.xpage.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Plugin;
import com.i9144.xpage.model.PluginDevice;

@Service
public class PluginDAO extends AbstractDAO<Plugin> {
	/**
	 * 创建插件设备信息
	 * @param pd
	 * @return
	 */
	public Object addPluginDevice(PluginDevice pd) {
		Object result = getSqlMapClient().insert(daoName + ".addPluginDevice", pd);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<PluginDevice> getPluginDevices(int pluginId) {
		List<PluginDevice> l = getSqlMapClient().queryForList(daoName + ".getPluginDevice", pluginId);
		return l;
	}
	
	public Plugin getByPluginId(int pluginId) {
		Plugin p = (Plugin) getSqlMapClient().queryForObject(daoName + ".getByPluginId", pluginId); 
		return p;
	}
}
