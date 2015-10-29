package com.i9144.xpage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Page;
import com.i9144.xpage.service.PageService;

@Controller
@RequestMapping(value = "/pages/")
public class PageAction {
	private static final Logger logger = Logger.getLogger(PageAction.class);
	@Resource
	private PageService pageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model,
			@RequestParam(value = "channelId", required = true) int channelId) {
		List<Page> pages = pageService.listByChannelId(channelId);
		model.addAttribute("pages", pages);
		return "/pages/list";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute Page page,
			@RequestParam(value = "channelId", required = true) int channelId) {
		try {
			page.setChannelId(channelId);
			pageService.add(page);
		} catch (HelperException e) {
			logger.warn("!!!! " + e.getMessage());
			model.addAttribute("message", e.getMessage());
		}
		List<Page> pages = pageService.listByChannelId(channelId);
		model.addAttribute("pages", pages);
		return "/pages/list";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String update(Model model, @PathVariable("id") int id,
			@ModelAttribute Page page,
			@RequestParam(value = "channelId", required = true) int channelId) {
		page.setId(id);
		pageService.update(page);
		List<Page> pages = pageService.listByChannelId(channelId);
		model.addAttribute("pages", pages);
		return "/pages/list";
	}
}
