package com.siims.auction.service;

import java.util.List;

import com.siims.auction.domain.User;
/**
 * 
 * @author PCNCAD-dosh
 * @version1.0
 * @time 3/12/14
 */
public interface UserService {
	public List<Object>getAllUsers();
	public User getUserById(String userId);
	public boolean deleteUserById(String userId);
	public boolean addUser(User user);
	public boolean changePassword(String userName,String oldPassword,String newPassword);
	public String isLoginOK(String userAccount,String pwd);
	public String isLoginPhone(String userAccount,String pwd);
	public boolean updateUser(User u);
}
