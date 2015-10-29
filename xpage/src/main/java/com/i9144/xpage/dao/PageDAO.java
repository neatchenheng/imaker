package com.i9144.xpage.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Page;

@Service
public class PageDAO extends AbstractDAO<Page> {

	public List<Page> getPagesByChannelId(int channelId) {
		@SuppressWarnings("unchecked")
		List<Page> pages = getSqlMapClient().queryForList(daoName + ".getPagesByChannelId", channelId);
		return pages;
	}
	
	public int getPageCountByChannelId(int channelId) {
		int count = (Integer) getSqlMapClient().queryForObject(daoName + ".getPageCountByChannelId", channelId);
		return count;
	}
}
