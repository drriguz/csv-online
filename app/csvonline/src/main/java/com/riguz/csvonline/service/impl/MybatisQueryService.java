package com.riguz.csvonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.riguz.csvonline.dao.UserMapper;
import com.riguz.csvonline.model.User;
import com.riguz.csvonline.model.UserExample;
import com.riguz.csvonline.service.QueryService;

public class MybatisQueryService implements QueryService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return this.userMapper.selectByExample(new UserExample());
	}

}
