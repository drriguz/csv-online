package com.riguz.csvonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.riguz.csvonline.model.User;
import com.riguz.csvonline.service.QueryService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private QueryService queryService;

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		view.addObject("title", "Spring MVC示例");
		return view;
	}

	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("list");
		List<User> users = this.queryService.getUsers();
		System.out.println(users.size());
		view.addObject("users", users);
		view.addObject("title", "列表示例");
		return view;
	}
}
