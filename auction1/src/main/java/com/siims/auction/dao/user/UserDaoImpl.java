package com.siims.auction.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siims.auction.dao.BaseDaoImpl;
import com.siims.auction.domain.User;


@Repository("UserDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User, String>implements UserDao{

}
