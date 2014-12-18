package com.siims.auction.dao.contact;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siims.auction.dao.BaseDaoImpl;
import com.siims.auction.domain.Contact;
@Repository("ContactDao")
@Transactional
public class ContactDaoImpl extends BaseDaoImpl<Contact, String> implements ContactDao{

}
