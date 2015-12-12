package com.i9144.xpage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i9144.xpage.model.ModuleData;
import com.i9144.xpage.service.ModuleDataService;

@Controller
public class ModuleDataAction {
	private static final Logger logger = Logger
			.getLogger(ModuleDataAction.class);
	@Resource
	private ModuleDataService moduleDataService;

	@RequestMapping(value = "modules/{moduleId}/data", method = RequestMethod.POST)
	public @ResponseBody
	int bindData(HttpServletRequest request,
			@ModelAttribute ModuleData moduleData) {
		int result = moduleDataService.add(moduleData);
		return result;
	}

	@RequestMapping(value = "modules/{moduleId}/data", method = RequestMethod.GET)
	public @ResponseBody
	List<ModuleData> getByModuleId(HttpServletRequest request,
			@PathVariable("moduleId") int moduleId) {
		List<ModuleData> list = moduleDataService.getByModuleId(moduleId);
		return list;
	}

	@RequestMapping(value = "modules/{moduleId}/data/{mdId}", method = RequestMethod.DELETE)
	public @ResponseBody
	int deleteModuleData(HttpServletRequest request,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("mdId") int mdId) {
		return moduleDataService.delete(mdId);
		//List<ModuleData> list = moduleDataService.getByModuleId(moduleId);
		//return list;
	}
	
	@RequestMapping(value = "modules/{moduleId}/data/{mdId}", method = RequestMethod.POST)
	public @ResponseBody
	int updateModuleData(HttpServletRequest request,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("mdId") int mdId,
			@ModelAttribute ModuleData moduleData) {
		moduleData.setId(mdId);
		return moduleDataService.update(moduleData);
		//List<ModuleData> list = moduleDataService.getByModuleId(moduleId);
		//return list;
	}
}
