package com.i9144.xpage.model;

import java.util.Date;

public class Plugin extends Model {
	private int id;
	/**
	 * 自定义插件ID
	 */
	private int pluginId;
	/**
	 * 插件名称
	 */
	private String name;
	/**
	 * 插件支持的设备类型, {100:PC端,200:PHONE端}
	 */
	private int device;
	/**
	 * 自定义这主题,例如,{100:2015v1,101:2015v2}
	 */
	private int theme;
	/**
	 * 状态，{0:关闭,1:正常}
	 */
	private int status;
	private Date createTime;
	private Date updateTime;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPluginId() {
		return pluginId;
	}
	public void setPluginId(int pluginId) {
		this.pluginId = pluginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDevice() {
		return device;
	}
	public void setDevice(int device) {
		this.device = device;
	}
	public int getTheme() {
		return theme;
	}
	public void setTheme(int theme) {
		this.theme = theme;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}