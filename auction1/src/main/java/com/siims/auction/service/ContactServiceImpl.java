package com.siims.auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.dao.contact.ContactDao;
import com.siims.auction.dao.goods.IgoodsDao;
import com.siims.auction.domain.Contact;

@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactDao dao;
	@Autowired 
	private GoodsService gService;

	@Override
	public List<JSONObject> getAllContacts() {
		// TODO Auto-generated method stub
		List<Contact> contacts = dao.getAll();
		List<JSONObject> res = new ArrayList<JSONObject>();
		for(Contact c : contacts){
			JSONObject j = new JSONObject();
			j.put("cId", c.getcId());
			j.put("cName", c.getcName());
			j.put("cPhone", c.getcPhone());
			j.put("cCity", c.getcCity());
			j.put("cRegion", c.getcRegion());
			j.put("cUser", c.getcUserId());
			res.add(j);
		}
	
		return res;
	}

	@Override
	public List<JSONObject> getContactsByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Contact> contacts = dao.findBy("cUserId", userId);
		List<JSONObject> res = new ArrayList<JSONObject>();
		for(Contact c : contacts){
			JSONObject j = new JSONObject();
			j.put("cId", c.getcId());
			j.put("cName", c.getcName());
			j.put("cPhone", c.getcPhone());
			j.put("cCity", c.getcCity());
			j.put("cRegion", c.getcRegion());
			j.put("cUser", c.getcUserId());
			res.add(j);
		}
	
		return res;
	}

	@Override
	public boolean addContact(Contact c) {
		// TODO Auto-generated method stub
		try{
		dao.save(c);
		return true;
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean deleteContact(Contact c) {
		// TODO Auto-generated method stub
		
		try{
			dao.delete(c);
			return true;
			}
			catch(Exception e){
				
			}
			return false;
	}

	@Override
	public boolean deleteContact(String contactId) {
		// TODO Auto-generated method stub
		String hql = "delete from Contact c where c.cId = "+contactId;
		try{
			gService.deleteGoodsByContact(contactId);
			dao.deleteByHql(hql);
			
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean updataContact(Contact c) {
		// TODO Auto-generated method stub
		try{
			dao.saveOrUpdate(c);
			return true;
			}
			catch(Exception e){
				
			}
			return false;
	}

	@Override
	public Contact getContactById(String cId) {
		// TODO Auto-generated method stub
		
		return dao.get(cId);
	}

}
