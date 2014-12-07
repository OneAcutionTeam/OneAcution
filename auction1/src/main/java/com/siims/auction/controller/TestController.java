package com.siims.auction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.siims.auction.domain.TestUser;
import com.siims.auction.service.TestUserService;
import com.siims.auction.service.TestUserServiceImpl;
import com.siims.auction.utils.JsonSend;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestUserServiceImpl service;
	
	@RequestMapping(value="/ok",method=RequestMethod.GET)
	public void testOk(){
		System.out.println("OK");
		
	}
	@RequestMapping(value="/testjson",method = RequestMethod.GET)
	public void printWelcome(ModelMap model,@RequestParam(value="id",required=true)int sendId,
             HttpServletRequest request,HttpServletResponse response  
			) throws Exception {
		
		List<Object> list =  service.getAllUsers();
				
//		for(int i=0;i<10;i++){
//			Map <String,Object>m= new HashMap<String,Object>();
//			m.put("title", "title"+i);
//			m.put("content", "content"+i);
//			JSONObject j = new JSONObject(m);
//			list.add(j);
//		}
		JSONArray array = new JSONArray(list);
		System.out.println("id--------> "+sendId);
		JsonSend.send(response, array.toJSONString());
	}
	
}
