package com.siims.auction.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siims.auction.dao.BaseDaoImpl;
import com.siims.auction.domain.TestUser;

@Repository("TestUserDao")
@SuppressWarnings("restriction")
@Transactional
public class TestUserDaoImpl extends BaseDaoImpl<TestUser, Integer> implements TestUserDao{

}
