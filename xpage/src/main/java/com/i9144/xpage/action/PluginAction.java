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

import com.i9144.xpage.exception.HelperException;
import com.i9144.xpage.model.Plugin;
import com.i9144.xpage.service.PluginService;

@Controller
public class PluginAction {
	private static final Logger logger = Logger.getLogger(PluginAction.class);
	@Resource
	private PluginService pluginService;

	@RequestMapping(value = "/plugins", method = RequestMethod.GET)
	public String list(Model model) {
		List<Plugin> plugins = pluginService.list();
		model.addAttribute("plugins", plugins);
		return "/plugins/list";
	}

	@RequestMapping(value = "/plugins", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute Plugin plugin) {
		try {
			pluginService.add(plugin);
		} catch (HelperException e) {
			logger.warn("!!!! " + e.getMessage());
			model.addAttribute("message", e.getMessage());
		}
		List<Plugin> plugins = pluginService.list();
		model.addAttribute("plugins", plugins);
		return "/plugins/list";
	}

	@RequestMapping(value = "/plugins/{id}", method = RequestMethod.POST)
	public String update(Model model, @PathVariable("id") int id,
			@ModelAttribute Plugin plugin) {
		plugin.setId(id);
		pluginService.update(plugin);
		List<Plugin> plugins = pluginService.list();
		model.addAttribute("plugins", plugins);
		return "/plugins/list";
	}
}
