package com.i9144.xpage.model;

import java.util.Date;

public class Channel extends Model {
	private int id;
	/**
	 * 自定义频道ID
	 */
	private int channelId;
	private String name;
	/**
	 * 频道url
	 */
	private String url;
	/**
	 * 编辑email
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
