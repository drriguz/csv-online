package com.riguz.csvonline.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.riguz.csvonline.model.User;
import com.riguz.csvonline.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		view.addObject("title", "Spring MVC示例");
		return view;
	}

	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email){
		ModelAndView view = new ModelAndView("user/list");
		List<User> users = this.userService.getUsers(name, email);
		view.addObject("users", users);
		view.addObject("title", "列表示例");
		return view;
	}

	@RequestMapping(value = "view.do", method = RequestMethod.GET)
	public ModelAndView view(
			@RequestParam String id){
		ModelAndView view = new ModelAndView("user/view");
		User model = this.userService.getUser(id);
		view.addObject("user", model);
		return view;
	}

	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(required = false) String id){
		ModelAndView view = new ModelAndView("user/edit");
		if(id != null){
			User model = this.userService.getUser(id);
			view.addObject("user", model);
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "save.do", method = RequestMethod.POST, produces = "text/javascript; charset=UTF-8")
	public String save(
			@ModelAttribute("user") User user,
			HttpServletResponse response)
					throws IOException{
		boolean success = false;
		if(!Strings.isNullOrEmpty(user.getUserId())){
			logger.info("Updating user:{}", user.getUserId());
			success = this.userService.updateUser(user);
		}else{
			logger.info("Adding user");
			success = this.userService.addUser(user);
		}
		logger.info("Result:{}", success);
		if(success)
			return "<script>alert('保存成功！');window.location.href='list.do';</script>";
		return "<script>alert('保存失败！')</script>";
	}
}
