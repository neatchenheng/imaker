package com.i9144.xpage.model;

import java.util.Date;

public class Page extends Model {
	private int id;
	/**
	 * 自定义频道ID
	 */
	private int channelId;
	/**
	 * 页面名称,CMS里的标识
	 */
	private String name;
	/**
	 * 页面标题
	 */
	private String title;
	/**
	 * html meta关键字
	 */
	private String metaKeywords;
	/**
	 * html meta描述
	 */
	private String metaDesc;
	/**
	 * 页面url
	 */
	private String url;
	/**
	 * 业页编辑
	 */
	private String editor;
	/**
	 * 状态,{0:关闭,1:正常}
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
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public String getMetaDesc() {
		return metaDesc;
	}
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
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
