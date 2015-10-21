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
	 * 插件使用设备类型(可支持多设备), {1:PC端,2:PHONE端}
	 */
	private String[] deviceTypes;
	/**
	 * 自定义插件ID
	 */
	private int theme;
	/**
	 * 模型,{0:固定,1:通栏,2:左宽,3:中宽,4:右宽，5:左窄，6:右窄}
	 */
	private int mold;
	/**
	 * 样式,{1:横图,2:方图,3:坚图}
	 */
	private int style;
	/**
	 * 状态，{0:关闭,1:正常}
	 */
	private int status;
	/**
	 * 广告标识，{0:关闭,1:开启}
	 */
	private int adFlag;
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
	public String[] getDeviceTypes() {
		return deviceTypes;
	}
	public void setDeviceTypes(String[] deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
	public int getTheme() {
		return theme;
	}
	public void setTheme(int theme) {
		this.theme = theme;
	}
	public int getMold() {
		return mold;
	}
	public void setMold(int mold) {
		this.mold = mold;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAdFlag() {
		return adFlag;
	}
	public void setAdFlag(int adFlag) {
		this.adFlag = adFlag;
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
