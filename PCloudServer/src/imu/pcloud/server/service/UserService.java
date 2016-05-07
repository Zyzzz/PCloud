package imu.pcloud.server.service;

import java.util.ArrayList;
import java.util.List;

import imu.pcloud.server.DAO.UserDAO;
import imu.pcloud.server.been.User;

public class UserService {

	UserDAO userDAO = new UserDAO();
	User user = new User();
	List<User> userList; 
	int login(String email, String password) {
		user.setEmail(email);
		user.setPassword(password);
		userList = userDAO.findByExample(user);
		
		return 0;
	}
}
