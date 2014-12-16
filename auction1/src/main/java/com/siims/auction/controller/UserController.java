package com.siims.auction.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.User;
import com.siims.auction.service.UserService;
import com.siims.auction.utils.JsonSend;
/**
 * userController返回手机端的用户信息包括各种更改查询
 * @author PCNCAD-杜帅
 *@time 4/12/14
 */
@Controller
@RequestMapping("/user")
public class UserController {
	/**
	 * service 
	 */
	@Autowired
	private UserService service;
	/**
	 * 返回所有用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/allusers",method=RequestMethod.GET)
	public void getAllUser(
            HttpServletRequest request,HttpServletResponse response
			){
		List<Object> users = service.getAllUsers();
		JSONArray array = new JSONArray(users);
		JsonSend.send(response, array.toString());
		
	}
	/**
	 * 通过Id查找用户
	 * @param userId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public void getUserById(
			@RequestParam(value="user_id")String userId,
			HttpServletRequest request,HttpServletResponse response
			){
		JSONObject j = new JSONObject();
		User u = service.getUserById(userId);
		j.put("userName", u.getName());
		j.put("account", u.getAccount());
		j.put("userId", u.getId());
		j.put("userCity", u.getCity());
		j.put("userRegion", u.getRegion());
		j.put("phone", u.getPhone());
		
		JsonSend.send(response, j.toJSONString());
	}
	@RequestMapping(value="/changePwd",method=RequestMethod.POST)
	public void changePassword(@RequestParam(value="user_name",required = true)String userName,
			@RequestParam(value="old_pwd",required = true)String oldPwd,
			@RequestParam(value="new_pwd",required = true)String newPwd,
			HttpServletRequest request,HttpServletResponse response
			){
		boolean status = service.changePassword(userName, oldPwd, newPwd);
		JSONObject j = getStatus(status);
		JsonSend.send(response, j.toJSONString());
	}
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public void addUser(@RequestParam(value="name",required=true)String name,
			@RequestParam(value="pwd",required=true)String pwd,@RequestParam(value="account",required=true)String account,
			@RequestParam(value="phone",required=false)String phone,@RequestParam(value="city",required=false)String city,
			@RequestParam(value="region",required=false)String region,
			HttpServletRequest request,HttpServletResponse response
			){
		User u = new User();
		
		u.setAccount(account);
		u.setCity(city);
		Calendar c=Calendar.getInstance();
		//以当前毫秒数作为id保证唯一性
		long uniqueId = c.getTimeInMillis();
		u.setId(""+uniqueId);
		u.setName(name);
		u.setPassword(pwd);
		u.setPhone(phone);
		u.setRegion(region);
		System.out.print("acc  "+account);
		JSONObject j = getStatus(service.addUser(u));
		JsonSend.send(response, j.toJSONString());	
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(@RequestParam(value="account")String account,@RequestParam(value="pwd")String pwd,
			HttpServletRequest request,HttpServletResponse response){
		JSONObject j = getStatus(service.isLoginOK(account, pwd));
		JsonSend.send(response, j.toJSONString());
	}
	
	
	private JSONObject getStatus(boolean status){
		JSONObject j = new JSONObject();
		if(status){
			j.put("status", 1);
		}
		else{
			j.put("status", 0);
		}
		return j;
	}
	
	private JSONObject getStatus(String status){
		JSONObject j = new JSONObject();
	
		j.put("status", status);
		return j;
	}
}
