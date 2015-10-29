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
import com.i9144.xpage.model.Channel;
import com.i9144.xpage.service.ChannelService;

@Controller
@RequestMapping(value = "/channels/")
public class ChannelAction {
	private static final Logger logger = Logger.getLogger(ChannelAction.class);
	@Resource
	private ChannelService channelService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Channel> channels = channelService.list();
		model.addAttribute("channels", channels);
		return "/channels/list";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute Channel channel) {
		try {
			channelService.add(channel);
		} catch (HelperException e) {
			logger.warn("!!!! " + e.getMessage());
			model.addAttribute("message", e.getMessage());
		}
		List<Channel> channels = channelService.list();
		model.addAttribute("channels", channels);
		return "/channels/list";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String update(Model model, @PathVariable("id") int id,
			@ModelAttribute Channel channel) {
		channel.setId(id);
		channelService.update(channel);
		List<Channel> channels = channelService.list();
		model.addAttribute("channels", channels);
		return "/channels/list";
	}
}
