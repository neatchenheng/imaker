package com.i9144.xpage.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.ChannelDAO;
import com.i9144.xpage.dao.PageDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Channel;

@Service
public class ChannelService {
	private static final Logger logger = Logger.getLogger(ChannelService.class);

	@Resource
	private ChannelDAO channelDao;
	@Resource
	private PageDAO pageDao;
	
	public void add(Channel channel) throws HelperException {
		logger.debug(">>>> add channel: " + channel);
		Channel c = channelDao.getByChannelId(channel.getChannelId());
		if ( c != null ) {
			throw new HelperException("同ID的频道已经存在了");
		}
		Date now = new Date();
		channel.setCreateTime(now);
		channel.setUpdateTime(now);
		channelDao.add(channel);
	}
	
	public List<Channel> list() {
		logger.debug(">>>> list Channels...");
		List<Channel> cs = channelDao.list();
		for (Channel c : cs) {
			int pc = pageDao.getPageCountByChannelId(c.getChannelId());
			c.setPageCount(pc);
		}
		return cs;
	}
	
	public int update(Channel channel) {
		logger.debug(">>>> update channel: " + channel);
		Date now = new Date();
		channel.setUpdateTime(now);
		return channelDao.update(channel);
	}
}
