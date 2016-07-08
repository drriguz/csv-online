package com.riguz.csvonline.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.riguz.csvonline.dao.UserMapper;
import com.riguz.csvonline.model.User;
import com.riguz.csvonline.model.UserExample;
import com.riguz.csvonline.model.UserExample.Criteria;
import com.riguz.csvonline.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUsers(String name, String email) {
		return this.getUsers(name, email, null);
	}

	@Override
	public User getUser(String id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateUser(User user) {
		return this.userMapper.updateByPrimaryKey(user) > 0;
	}

	@Override
	public boolean deleteUser(String id) {
		return this.userMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean addUser(User user) {
		String maxId = this.userMapper.selectMaxUserId();
		Integer nextId = Integer.parseInt(maxId);
		nextId += 1;
		user.setUserId(nextId + "");
		return this.userMapper.insert(user) > 0;
	}

	@Override
	public List<User> getUsers(String name, String email, RowBounds page) {
		UserExample arg = new UserExample();
		Criteria c = arg.or();
		if(!Strings.isNullOrEmpty(name))
			c.andFirstNameLike("%" + name + "%");
		if(!Strings.isNullOrEmpty(email))
			c.andEmailLike("%" + email + "%");
		arg.setOrderByClause("user_id desc");
		if(page != null)
            PageHelper.startPage(page.getOffset(), page.getLimit());
		return this.userMapper.selectByExample(arg);
	}
}
