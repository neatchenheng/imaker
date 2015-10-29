package com.i9144.xpage.dao;

import org.springframework.stereotype.Service;

import com.i9144.xpage.model.Channel;

@Service
public class ChannelDAO extends AbstractDAO<Channel> {
	public Channel getByChannelId(int channelId) {
		Channel c = (Channel) getSqlMapClient().queryForObject(daoName + ".getByChannelId", channelId); 
		return c;
	}
}
