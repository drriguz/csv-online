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


	private ModelAndView error(String msg){
		ModelAndView view = new ModelAndView("error/error");
		view.addObject("errorMsg", msg);
		return view;
	}

	private ModelAndView info(String msg){
		ModelAndView view = new ModelAndView("public/info");
		view.addObject("msg", msg);
		return view;
	}

	private ModelAndView renderResult(boolean success){
		if(success)
			return this.info("操作成功");
		return this.error("操作失败");
	}

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		view.addObject("title", "Spring MVC示例");
		return view;
	}

	/**
	 * 列表查询页面
	 * @param name
	 * @param email
	 * @return
	 */
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


	/**
	 * 查看详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "view.do", method = RequestMethod.GET)
	public ModelAndView view(
			@RequestParam String id){
		ModelAndView view = new ModelAndView("user/view");
		User model = this.userService.getUser(id);
		view.addObject("user", model);
		return view;
	}


	/**
	 * 编辑页面
	 * @param id
	 * @return
	 */
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

	/**
	 * 删除动作
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public ModelAndView delete(
			@RequestParam(required = true) String id){
		logger.info("Deleting user:{}", id);
		boolean result = this.userService.deleteUser(id);
		logger.info("Result:{}", result);
		return this.renderResult(result);
	}

	/**
	 * 新增及更新操作
	 * @param user
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "save.action", method = RequestMethod.POST)
	public ModelAndView save(
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
		return this.renderResult(success);
	}
}
