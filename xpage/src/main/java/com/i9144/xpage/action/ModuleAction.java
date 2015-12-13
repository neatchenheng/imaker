package com.i9144.xpage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Module;
import com.i9144.xpage.service.ModuleService;

@Controller
public class ModuleAction {
	private static final Logger logger = Logger.getLogger(ModuleAction.class);
	@Resource
	private ModuleService moduleService;

	@RequestMapping(value = "pages/{pageId}/modules", method = RequestMethod.GET)
	public @ResponseBody List<Module> list(@PathVariable("pageId") int pageId) {
		List<Module> modules = moduleService.getByPageId(pageId);
		return modules;
	}

	@RequestMapping(value = "pages/{pageId}/modules", method = RequestMethod.PUT)
	public @ResponseBody int sort(@PathVariable("pageId") int pageId,@RequestBody String mids) {
		if (StringUtils.isNotBlank(mids)) {
			String[] ids = mids.split(",");
			return moduleService.sort(ids);
		}
		return 0;
	}
	
	@RequestMapping(value = "pages/{pageId}/modules", method = RequestMethod.POST)
	public @ResponseBody int save(Model model, @ModelAttribute Module module,
			@PathVariable("pageId") int pageId) {
		try {
			module.setPageId(pageId);
			int result = moduleService.add(module);
			return result;
		} catch (HelperException e) {
			logger.warn("!!!! " + e.getMessage());
			model.addAttribute("message", e.getMessage());
		}
		return 0;
	}

	@RequestMapping(value = "pages/{pageId}/modules/{id}", method = RequestMethod.POST)
	public @ResponseBody
	int update(Model model, @PathVariable("pageId") int pageId,
			@PathVariable("id") int id, @ModelAttribute Module module) {
		module.setId(id);
		int result = moduleService.update(module);
		return result;
	}
	
	@RequestMapping(value = "pages/{pageId}/modules/{id}", method = RequestMethod.DELETE)
	public @ResponseBody int delete(@PathVariable("pageId") int pageId,
			@PathVariable("id") int id) {
		int result = moduleService.delete(pageId, id);
		return result;
	}
	
	/*@RequestMapping(value="pages/{pageId}", method = RequestMethod.GET)
	public String get(Model model, @PathVariable("pageId") int pageId) {
		Map<String, Object> map = pageService.get(pageId);
		model.addAttribute("map", map);
		return "pages/get";
	}*/
}
