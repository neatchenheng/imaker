package com.i9144.xpage.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.i9144.xpage.dao.PageDAO;
import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Page;
import com.i9144.xpage.model.Page;

@Service
public class PageService {
	private static final Logger logger = Logger.getLogger(PageService.class);

	@Resource
	private PageDAO pageDao;
	
	public void add(Page page) throws HelperException {
		logger.debug(">>>> add page: " + page);
		Date now = new Date();
		page.setCreateTime(now);
		page.setUpdateTime(now);
		pageDao.add(page);
	}
	
	public List<Page> listByChannelId(int channelId) {
		logger.debug(">>>> list Pages...");
		List<Page> ps = pageDao.getPagesByChannelId(channelId);
		return ps;
	}
	
	public int update(Page page) {
		logger.debug(">>>> update page: " + page);
		Date now = new Date();
		page.setUpdateTime(now);
		return pageDao.update(page);
	}
}
