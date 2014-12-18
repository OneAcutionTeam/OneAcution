package com.siims.auction.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.Contact;

public interface ContactService {
	public List<JSONObject> getAllContacts();
	public List<JSONObject> getContactsByUserId(String userId);
	public boolean addContact(Contact c);
	public boolean deleteContact(Contact c);
	public boolean deleteContact(String contactId);
	public boolean updataContact(Contact c);
	
}
