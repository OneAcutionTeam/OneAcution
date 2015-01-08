package com.siims.auction.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;









import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.Contact;
import com.siims.auction.service.ContactService;
import com.siims.auction.utils.JsonSend;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService service;
	
	@RequestMapping(value="all_contacts",method=RequestMethod.GET)
	public void getAllContacts(
			HttpServletRequest request,HttpServletResponse response
			){
		List<JSONObject> contacts = service.getAllContacts();
	//	JSONObject j = new Js
		
		JsonSend.send(response, contacts.toString(), "contacts");
		
	}
	
	@RequestMapping(value="contacts",method=RequestMethod.GET)
	public void getContactsByUser(
			@RequestParam(value="user_id") String userId,
			HttpServletRequest request,HttpServletResponse response
			){
		List<JSONObject> contacts = service.getContactsByUserId(userId);
	//	JSONObject j = new Js
		System.out.print(contacts.toString());
		JsonSend.send(response, contacts.toString(), "contacts");
	}
	@RequestMapping(value="add_contact",method=RequestMethod.POST)
	public void addContact(
			@RequestParam(value="userId")String usetId,
			@RequestParam(value="name")String name,
			@RequestParam(value="phone")String phone,
			@RequestParam(value="city",required=false)String city,
			@RequestParam(value="region",required=false)String region,
			HttpServletRequest request,HttpServletResponse response
			){
		Calendar c1=Calendar.getInstance();
		//以当前毫秒数作为id保证唯一性
		long uniqueId = c1.getTimeInMillis();
		Contact c = new Contact();
		c.setcId(""+uniqueId);
		c.setcName(name);
		c.setcPhone(phone);
		c.setcCity(city);
		c.setcRegion(region);
		c.setcUserId(usetId);
		System.out.println(c.getcName()+c.getcRegion()+c.getcId());
		JSONObject j = getStatus(service.addContact(c));
		JsonSend.send(response, j.toJSONString());
		
	}
	
	@RequestMapping(value="delete_contact",method=RequestMethod.POST)
	public void deleteContact(
			@RequestParam(value="Id",required=false)String cId,
			HttpServletRequest request,HttpServletResponse response
			){
		JSONObject j = getStatus(service.deleteContact(cId));
		JsonSend.send(response, j.toJSONString());
	}
	
	@RequestMapping(value="update_contact",method=RequestMethod.POST)
	public void updateContact(@RequestParam(value="userId")String usetId,
			@RequestParam(value="cId")String id,
			@RequestParam(value="name")String name,
			@RequestParam(value="phone")String phone,
			@RequestParam(value="city",required=false)String city,
			@RequestParam(value="region",required=false)String region,
			HttpServletRequest request,HttpServletResponse response){
		
		Contact c = new Contact();
		c.setcId(id);
		c.setcName(name);
		c.setcPhone(phone);
		c.setcCity(city);
		c.setcRegion(region);
		c.setcUserId(usetId);
		JSONObject j = getStatus(service.updataContact(c));
		JsonSend.send(response, j.toJSONString());
	}
	
	@RequestMapping(value="contact",method=RequestMethod.GET)
	public void getContactById(
			@RequestParam(value="contact_id")String id,
			HttpServletRequest request,HttpServletResponse response
			){
		Contact c = service.getContactById(id);
		JSONObject j = new JSONObject();
		j.put("cId", c.getcId());
		j.put("cName", c.getcName());
		j.put("cPhone", c.getcPhone());
		j.put("cCity", c.getcCity());
		j.put("cRegion", c.getcRegion());
		j.put("cUser", c.getcUserId());
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
	
	

}
