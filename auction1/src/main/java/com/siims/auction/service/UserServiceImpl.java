package com.siims.auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.dao.user.UserDao;
import com.siims.auction.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	@Override
	public List<Object> getAllUsers() {
		// TODO Auto-generated method stub
		List<User>users = dao.getAll();
		
		List<Object> list = new ArrayList<Object>(users.size());
		
		for(User u : users){
			JSONObject j = new JSONObject();
			j.put("userName", u.getName());
			j.put("account", u.getAccount());
			j.put("userId", u.getId());
			j.put("userCity", u.getCity());
			j.put("userRegion", u.getRegion());
			j.put("phone", u.getPhone());
			list.add(j);
		}
		return list;
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		User u = dao.get(userId);
		return u;
	}

	@Override
	public boolean deleteUserById(String userId) {
		// TODO Auto-generated method stub
		try{
			dao.delete(userId);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try{
			dao.save(user);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean changePassword(String userName, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.id = ? and u.password = ? ";
		User u = dao.findEntityByHql(hql, userName,oldPassword);
		if(u==null){
			System.out.println("invalidate user");
			return false;
		}else{
			u.setPassword(newPassword);
			dao.update(u);
			return true;
		}
	}

	@Override
	public String isLoginOK(String userAccount, String pwd) {
		// TODO Auto-generated method stub
		//User u = dao.
		String hql = "from User as u where u.account = ? and u.password = ? ";
		User u = dao.findEntityByHql(hql, userAccount,pwd);
		return u == null ? "0" : u.getId();
	}

}
