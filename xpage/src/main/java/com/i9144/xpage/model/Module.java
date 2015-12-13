package com.i9144.xpage.model;

import java.util.Date;
import java.util.List;

public class Module extends Model {
	private int id;
	/**
	 * 页面ID
	 */
	private int pageId;
	/**
	 * 插件Id
	 */
	private int pluginId;
	/**
	 * 插件名称
	 */
	private String pluginName;
	/**
	 * 模块名称
	 */
	private String name;
	/**
	 * 状态,{0:关闭,1:正常}
	 */
	private int status;
	/**
	 * 位置
	 */
	private int position;
	private Date createTime;
	private Date updateTime;
	/**
	 * 绑定模块数据
	 */
	private List<ModuleData> mdList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPluginId() {
		return pluginId;
	}
	public void setPluginId(int pluginId) {
		this.pluginId = pluginId;
	}
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
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
	
	public List<ModuleData> getMdList() {
		return mdList;
	}
	public void setMdList(List<ModuleData> mdList) {
		this.mdList = mdList;
	}



	public static enum Status {
		ENABLE(1), DISABLE(0);
		
		private int value;
		private Status(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
}
