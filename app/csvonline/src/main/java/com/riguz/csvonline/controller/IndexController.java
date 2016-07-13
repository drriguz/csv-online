package com.riguz.csvonline.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.google.common.base.Strings;
import com.riguz.csvonline.bean.EasyUiGrid;
import com.riguz.csvonline.model.T1;
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

	/**
	 * 上传文件页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.GET)
	public ModelAndView upload(){
		ModelAndView view = new ModelAndView("user/upload");
		List<User> users = this.userService.getUsers(null, null);
		view.addObject("users", users);
		return view;
	}

	/**
	 * 上传文件动作
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "upload.action", method = RequestMethod.POST)
	public ModelAndView uploadAction(
			@RequestParam(required = true) MultipartFile uploadFile){

		logger.info("Uploading file:{}", uploadFile.getName());
		String fileName = uploadFile.getOriginalFilename();

		String root = System.getProperty("app.root");
		fileName = "upload" + File.separatorChar + fileName;
		logger.info("Root path:{}", root);
		try {
			File file = new File(root + fileName);
			if(!file.exists())
				file.mkdirs();
			uploadFile.transferTo(file);
			return this.info(String.format("<p>上传成功</p><img src='%s'/>", fileName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return this.error("上传失败");
	}

	/**
	 * EasyUI GRID示例
	 * @param name
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "easyui.do", method = RequestMethod.GET)
	public ModelAndView easyui(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email){
		ModelAndView view = new ModelAndView("user/easyui");
		List<User> users = this.userService.getUsers(name, email);
		view.addObject("users", users);
		view.addObject("title", "列表示例");
		return view;
	}

	@RequestMapping(value = "easycurd.do", method = RequestMethod.GET)
	public ModelAndView easycurd(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email){
		ModelAndView view = new ModelAndView("easyui/index");
		List<User> users = this.userService.getUsers(name, email);
		view.addObject("users", users);
		view.addObject("title", "列表示例");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "json.do", method = RequestMethod.GET)
	public EasyUiGrid<User> listJson(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false, defaultValue="1") int page,
			@RequestParam(required = false, defaultValue="10") int rows){
		List<User> users = this.userService.getUsers(name, email, new RowBounds(page, rows));
		EasyUiGrid<User> grid = new EasyUiGrid<User>();
		grid.setRows(users);
		grid.setTotal(((Page) users).getTotal());
		return grid;
	}

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

	@ResponseBody
	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	public T1 test(@ModelAttribute("t1") T1 t1){
		System.out.println("=>");
		System.out.println(t1.getFromDate());
		return t1;
	}
}
