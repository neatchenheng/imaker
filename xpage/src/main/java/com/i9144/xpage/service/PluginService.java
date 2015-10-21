package com.i9144.xpage.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.PluginDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Plugin;
import com.i9144.xpage.model.PluginDevice;

@Service
public class PluginService {
	private static final Logger logger = Logger.getLogger(PluginService.class);
	
	@Resource
	private PluginDAO pluginDao;
	
	public void add(Plugin plugin) throws HelperException {
		logger.debug(">>>> add plugin: " + plugin);
		Plugin p = pluginDao.getByPluginId(plugin.getPluginId());
		if ( p != null ) {
			throw new HelperException("同ID的插件已经存在了");
		}
		Date now = new Date();
		plugin.setCreateTime(now);
		plugin.setUpdateTime(now);
		String[] deviceTypes = plugin.getDeviceTypes();
		if (deviceTypes != null && deviceTypes.length > 0) {
			for (String dt: deviceTypes) {
				PluginDevice pd = new PluginDevice();
				pd.setPluginId(plugin.getPluginId());
				pd.setDeviceType((Integer.parseInt(dt)));
				pd.setCreateTime(now);
				pd.setUpdateTime(now);
				pluginDao.addPluginDevice(pd);
			}
		}
		pluginDao.add(plugin);
	}
	
	public List<Plugin> list() {
		logger.debug(">>>> list plugins...");
		List<Plugin> ps = pluginDao.list();
		for (Plugin p: ps) {
			List<PluginDevice> pds = pluginDao.getPluginDevices(p.getPluginId());
			String[] dts = new String[pds.size()];
			int i = 0;
			for (PluginDevice pd : pds) {
				dts[i++] = String.valueOf(pd.getDeviceType());
			}
			p.setDeviceTypes(dts);
		}
		return ps;
	}
}
