package com.siims.auction.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.dao.user.TestUserDao;
import com.siims.auction.dao.user.TestUserDaoImpl;
import com.siims.auction.domain.TestUser;
@Service
public class TestUserServiceImpl implements TestUserService {
	@Autowired
	private TestUserDaoImpl dao;

	@Override
	public List<Object> getAllUsers() {
		// TODO Auto-generated method stub
		List<Object> usersList = new ArrayList<Object>();
		List<TestUser> users = dao.getAll();
		System.out.println(dao.exists(5));
		for(TestUser user : users){
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", user.getId());
			m.put("name", user.getUsername());
			m.put("pwd", user.getPassword());
			JSONObject j = new JSONObject(m);
			usersList.add(j);
		}
		return usersList;
	}

	
	

}
