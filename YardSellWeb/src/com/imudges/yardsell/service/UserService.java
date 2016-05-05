package com.imudges.yardsell.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.common.util.StringHelper;
import org.w3c.dom.ls.LSInput;

import com.imudges.yardsell.bean.User;
import com.imudges.yardsell.model.UserModel;
import com.imudges.yardsell.utils.Information;
import com.imudges.yardsell.utils.MD5;
import com.imudges.yardsell.utils.Map;
import com.imudges.yardsell.utils.RegexValidateUtil;
import com.imudges.yardsell.utils.SendEmail;
import com.imudges.yardsell.DAO.UserDAO;

public class UserService {

	private int passwordLimit = 6;
	private User user;
	private UserDAO userDao = new UserDAO();
	private List<User> list;
	public Date nowDate = new Date(System.currentTimeMillis());
	private static String host = "http://183.175.14.98:8080/";
	static{
		host="http://"+new Information().getConfigInfo("host")+":"+new Information().getConfigInfo("port")+"/";
		System.out.println("the host address has setted,the host name is "+host);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public int login(String email,String password) {
		
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		list = userDao.findByExample(user);
		if(email == "" || password == "")
			return 120;
		else if(list.isEmpty())
			return 100;
		else if(password.length() < passwordLimit)
			return 106;
		else {
			user = list.get(0);
			user.setHashCode(new MD5().Md5(user.getUserId() + user.getUserName() +
					user.getEmail() + new Date(System.currentTimeMillis())));
			userDao.save(user);
			return 0;
		}
	}
	
	public void setVerify(User user) {
		user.setIsVerify(1);
		userDao.save(user);
	}
	
	public int register(String email, String password, String repeatPassword, String userName) {

		user = new User();
		if(!RegexValidateUtil.checkEmail(email))
			return 114;
		else if(!userDao.findByEmail(email).isEmpty())
			return 101;
		else if(password.length() < passwordLimit)
			return 106;
		else {
			user.setEmail(email);
			user.setPassword(password);
			user.setUserName(userName);
			user.setIsOpen(0);
			user.setIsVerify(0);
			user.setHashCode(new MD5().Md5(user.getUserId() + user.getUserName() +
					user.getEmail() + new Date(System.currentTimeMillis())));
			userDao.save(user);
			return 0;
		}
	}
	
	public User getUserById(int userId) {
		
		user = userDao.findById(userId);
		return user;
	}
	
	public User getUserByEmail(String email) {
		List<User> list = userDao.findByEmail(email);
		if(list.isEmpty())
			return null;
		else 
			return list.get(0);
	}
	
	public int getUserIdByEmail(String email) {
		User user;
		user = getUserByEmail(email);
		if(user == null)
			return -1;
		else 
			return user.getUserId();
	}
	
	public int changeUserEmail(int userId, String email) {
		
		user = userDao.findById(userId);
		if(!RegexValidateUtil.checkEmail(email))
			return 114;
		else if(!userDao.findByEmail(email).isEmpty())
			return 101;
		else if(user == null)
			return 103;
		else {
			user.setEmail(email);
			user.setIsOpen(0);
			user.setIsVerify(0);
			userDao.save(user);
			return 0;
		}
	}
	
	public int changeUserName(int userId, String userName) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		else {
			user.setUserName(userName);
			userDao.save(user);
			return 0;
		}
	}
	
	public int setSellInfo(int userId, String address, String phoneNumber, double latitude, double longtitude, String commodityInfo, Timestamp sellDate, Timestamp endDate) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		else if(endDate.before(sellDate))
			return 112;
		else if(endDate.before(nowDate))
			return 108;
		else {
			user.setAddress(address);
			user.setLatitude(latitude);
			user.setLongtitude(longtitude);
			user.setCommodityInfo(commodityInfo);
			user.setSellDate(sellDate);
			user.setPhoneNumber(phoneNumber);
			user.setEndDate(endDate);
			userDao.save(user);
			return 0;
		}
	}
	
	public int resetPassword(int userId, String oldPassword, String newPassword, String repeatPassword) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		else if(!user.getPassword().equals(oldPassword))
			return 104;
		else if(!newPassword.equals(repeatPassword))
			return 102;
		else if(newPassword.length() < passwordLimit)
			return 106;
		else {
			user.setPassword(newPassword);
			userDao.save(user);
			return 0;
		}
	}
	
	public int resetPassword(String email, String oldPassword, String newPassword, String repeatPassword) {
		
		user = userDao.findById(null);
		if(user == null)
			return 103;
		else if(!user.getPassword().equals(oldPassword))
			return 104;
		else if(!newPassword.equals(repeatPassword))
			return 102;
		else if(newPassword.length() < passwordLimit)
			return 106;
		else {
			user.setPassword(newPassword);
			userDao.save(user);
			return 0;
		}
	}	
	
	public int openSell(int userId) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		else if(user.getAddress() == null &&
				user.getLatitude() == null &&
				user.getLongtitude() == null)
			return 107;
		else if(user.getSellDate() == null || user.getEndDate() == null)
			return 105;
		else if(user.getEndDate().before(user.getSellDate()))
			return 112;
		else if(user.getEndDate().before(nowDate))
			return 108;
		else if(user.getIsOpen().equals(1))
			return 109;
		else if(user.getIsVerify() == 0)
			return 113;
		else {
			user.setIsOpen(1);
			userDao.save(user);
			return 0;
		}
	}
	
	public int closeSell(int userId) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		else if(user.getIsOpen() == 0)
			return 110;
		else {
			user.setIsOpen(0);
			userDao.save(user);
			return 0;
		}
	}
	
	public boolean checkIsEnd(User user) {
		
		return user.getEndDate().before(nowDate);
	}
	
	public int refreshOpen() {
		
		try {
			list = userDao.findByIsOpen(1);
			if(list.isEmpty())
				return 0;
			else {
				for(int i = 0; i < list.size(); i++) {
					user = list.get(i);
					if(checkIsEnd(user)) {
						user.setIsOpen(0);
						userDao.save(user);
					}
				}
				return 0;
			}
		} catch(Exception e) {
			return 111;
		}
	}

	public List<UserModel> findSellByScope(double lat1, double lng1, int scope) {
		
		list = userDao.findByIsOpen(1);
		ArrayList<UserModel> resultList = new ArrayList();
		for(int i = 0; i < list.size(); i++) {
			
			User user2 = list.get(i);
			if(Map.getDistance(lat1, lng1, user2.getLatitude(), user2.getLongtitude()) <= scope) {
				resultList.add(new UserModel(user2));
			}
		}
		return resultList;
	}
	
	public List<UserModel> findSellByScopeAndDate(double lat1, double lng1, int scope, Date startDate, Date endDate) {
		
		list = userDao.findByIsOpen(1);
		ArrayList<UserModel> resultList = new ArrayList();
		for(int i = 0; i < list.size(); i++) {
			
 			User user2 = list.get(i);
			if(Map.getDistance(lat1, lng1, user.getLatitude(), user.getLongtitude()) <= scope && (startDate.after(user2.getSellDate()) || endDate.before(user2.getEndDate())))
				resultList.add(new UserModel(user2));
		}
		return resultList;
	}
	
	public int sendActivateEmail(int userId) {
		
		user = userDao.findById(userId);
		if(user == null)
			return 103;
		String date = System.currentTimeMillis() + "";
		user.setEmailDate(date);
		String md5 = new MD5().Md5(date);
		String url = host + "YardSellWeb/activateEmail.action?email=" + user.getEmail() + "&key=" + md5;
		SendEmail.sendActivateEmail(user.getEmail(), url);
		userDao.save(user);
		return 0;
	}
	
	public int checkActivateEmail(String email, String md5) {
		
		list = userDao.findByEmail(email);
		if(list.isEmpty())
			return 115;
		user = list.get(0);
		if(user.getIsVerify() == 1)
			return 119;
		if(user.getEmailDate() == null)
			return 118;  
		BigInteger now = new BigInteger(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000 + "");
		BigInteger old = new BigInteger(user.getEmailDate());
		String aidMd5 = new MD5().Md5(user.getEmailDate());
		if(old.compareTo(now) < 0)
			return 116;
		else if(!md5.equals(aidMd5))
			return 117;
		else {
			user.setEmailDate(null);
			user.setIsVerify(1);
			userDao.save(user);
			return 0;
		}	
	}
	
	public int checkResetPassword(String email,String md5){
		list = userDao.findByEmail(email);
		if(list.isEmpty())
			return 115;
		user = list.get(0);
		if(user.getIsVerify() == 1)
			return 119;
		if(user.getEmailDate() == null)
			return 118;
		BigInteger now = new BigInteger(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000 + "");
		BigInteger old = new BigInteger(user.getEmailDate());
		String aidMd5 = new MD5().Md5(user.getEmailDate());
		if(old.compareTo(now) < 0)
			return 116;
		else if(!md5.equals(aidMd5))
			return 117;
		else {
			user.setEmailDate(null);
			user.setIsVerify(1);
			userDao.save(user);
			return 0;
		}
	}
	
	public User getUserInfo(String email,String hashCode){
		User user=new User();
		user.setEmail(email);
		user.setHashCode(hashCode);
		System.out.println(email);
		System.out.println(hashCode);
		List list =userDao.findByExample(user);
		System.out.println(list.size());
		if (list==null||list.size()==0) {
			return null;
		}
		return (User)list.get(0);
	}
	public int setImage(String email,String hashCode,int imageId){
		user=new User();
		user.setEmail(email);
		user.setHashCode(hashCode);
		List list =userDao.findByExample(user);
		user = (User)list.get(0);
		user.setImage1(imageId);
		userDao.save(user);
		return 200;
	}
	public int getUserId(String email,String hashCode){
		user=new User();
		user.setEmail(email);
		user.setHashCode(hashCode);
		List list=new UserDAO().findByExample(user);
		if (list.isEmpty()) {
			return -1;
		}
		user=(User)list.get(0);
		return user.getUserId();

	}
	
	public int setPhoneNum(int userId, String phoneNum){
		user = getUserById(userId);
		if (user == null ) {
			return 122;
		}
		user.setPhoneNumber(phoneNum);
		userDao.save(user);
		return 0;
	}
}
