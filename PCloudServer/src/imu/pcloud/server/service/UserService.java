package imu.pcloud.server.service;

import java.util.ArrayList;
import java.util.List;

import imu.pcloud.server.DAO.UserDAO;
import imu.pcloud.server.been.User;
import imu.pcloud.server.utils.RegexValidateUtil;

public class UserService {

	UserDAO userDAO = new UserDAO();
	User user = new User();
	List<User> userList; 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int login(String email, String password) {
		user.setEmail(email);
		user.setPassword(password);
		userList = userDAO.findByExample(user);
		if(userList.isEmpty()) {
			return 100;
		}
		user = userList.get(0);
		return 0;
	}
	
	public int register(String username, String email, String password, String rePassword) {
		user.setEmail(email);
		userList = userDAO.findByExample(user);
		if(!RegexValidateUtil.checkEmail(email)) {
			return 101;
		} else if (!userList.isEmpty()) {
			return 102;
		} else if (!password.equals(rePassword)) {
			return 103;
		} 
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setVerifyFlag(0);
		userDAO.save(user);
		return 0;
	}
	
	//public int 
}
