package com.i9144.xpage.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Page;
import com.i9144.xpage.service.ModuleDataService;
import com.i9144.xpage.service.PageService;

@Controller
public class PageAction {
	private static final Logger logger = Logger.getLogger(PageAction.class);
	@Resource
	private PageService pageService;
	@Resource
	private ModuleDataService moduleDataService;

	@RequestMapping(value = "channels/{channelId}/pages", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("channelId") int channelId) {
		List<Page> pages = pageService.listByChannelId(channelId);
		model.addAttribute("pages", pages);
		return "/pages/list";
	}

	@RequestMapping(value = "channels/{channelId}/pages", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute Page page,
			@PathVariable("channelId") int channelId) {
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

	@RequestMapping(value = "channels/{channelId}/pages/{id}", method = RequestMethod.POST)
	public @ResponseBody
	int update(Model model, @PathVariable("channelId") int channelId,
			@PathVariable("id") int id, @ModelAttribute Page page) {
		page.setId(id);
		int result = pageService.update(page);
		return result;
	}

	@RequestMapping(value = "pages/{pageId}", method = RequestMethod.GET)
	public String get(Model model, @PathVariable("pageId") int pageId) {
		Map<String, Object> map = pageService.get(pageId);
		model.addAttribute("date", new DateTool());
		model.addAttribute("map", map);
		return "pages/get";
	}

	@RequestMapping(value = "pages/{pageId}/preview", method = RequestMethod.GET)
	public void preview(Model model, @PathVariable("pageId") int pageId,
			@RequestParam(value = "mid", required = false, defaultValue = "0") int mid,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			String html = pageService.getPageContent(pageId, mid);
			response.getWriter().write(html);
		} catch (Exception e) {
			logger.error("!!!! Exception", e);
		}
	}

}
