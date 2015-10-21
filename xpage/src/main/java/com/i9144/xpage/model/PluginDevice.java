package com.i9144.xpage.model;

import java.util.Date;

public class PluginDevice extends Model {
	private int id;
	/**
	 * @see Plugin.pluginId
	 */
	private int pluginId;
	/**
	 * 设备类型
	 */
	private int deviceType;
	/**
	 * css类型,{1:link,2:code}
	 */
	private int cssType;
	/**
	 * 自定义css样式链接
	 */
	private String cssLink;
	/**
	 * 自定义css样式代码
	 */
	private String cssCode;
	/**
	 * 自定义js类型,{1:link,2:code}
	 */
	private int jsType;
	/**
	 * 自定义js链接
	 */
	private String jsLink;
	/**
	 * 自定义js代码
	 */
	private String jsCode;
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

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public int getCssType() {
		return cssType;
	}

	public void setCssType(int cssType) {
		this.cssType = cssType;
	}

	public String getCssLink() {
		return cssLink;
	}

	public void setCssLink(String cssLink) {
		this.cssLink = cssLink;
	}

	public String getCssCode() {
		return cssCode;
	}

	public void setCssCode(String cssCode) {
		this.cssCode = cssCode;
	}

	public int getJsType() {
		return jsType;
	}

	public void setJsType(int jsType) {
		this.jsType = jsType;
	}

	public String getJsLink() {
		return jsLink;
	}

	public void setJsLink(String jsLink) {
		this.jsLink = jsLink;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
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
