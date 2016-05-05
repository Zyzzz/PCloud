package com.imudges.yardsellapp.DAO;




import org.json.JSONException;
import org.json.JSONObject;

import android.R.layout;

import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.util.HttpRequest;
import com.imudges.yardsellapp.util.Information;
import com.imudges.yardsellapp.util.JsonToObject;


public class UserDAO{
	
	public UserModel loginUser(User users){
		String username=users.getUserName();
		String password=users.getPassword();
		//JSONObject jsonObject=JSONObject.fromObject(new HttpRequest().sendGet("login.action", "username="+username+"&"+"password="+password));
		String httpResult=new HttpRequest().sendGet("login.action", "username="+username+"&"+"password="+password);
		UserModel userModel=new JsonToObject().JsonToUserModel(httpResult);
//		JSONObject jsonObject;
//		UserModel userModel=new UserModel();
//		userModel.setStatus(1);
//		userModel.setResult(new Information().getErrorInfo(500));
//		try {
//			jsonObject = new JSONObject(httpResult);
//			userModel.setStatus(jsonObject.getInt("status"));
//			userModel.setResult(jsonObject.getString("result"));
//			if (userModel.getStatus()==0) {
//				userModel.setEmail(jsonObject.getString("email"));
//				userModel.setHashCode(jsonObject.getString("hashCode"));
//				userModel.setIsOpen(jsonObject.getInt("isOpen"));
//				userModel.setIsVerify(jsonObject.getInt("isVerify"));
//				userModel.setUserId(jsonObject.getInt("userId"));
//				userModel.setUserName(jsonObject.getString("userName"));
//				userModel.setPassword(jsonObject.getString("password"));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userModel;
	}
	
	public UserModel registerUser(User user){
		String email=user.getEmail();
		String userName=user.getUserName();
		String password=user.getPassword();
		String repeatPassword=user.getPassword();
		String httpResult=new HttpRequest().sendGet("register.action", "userName="+userName+"&email="+email+"&password="+password+"&repeatPassword="+repeatPassword);
		UserModel userModel=new JsonToObject().JsonToUserModel(httpResult);
//		JSONObject jsonObject;
//		UserModel userModel=new UserModel();
//		userModel.setStatus(500);
//		userModel.setResult(new Information().getErrorInfo(500));
//		try {
//			jsonObject = new JSONObject(httpResult);
//			userModel.setStatus(jsonObject.getInt("status"));
//			userModel.setResult(jsonObject.getString("result"));
//			if (userModel.getStatus()==0) {
//				userModel.setEmail(jsonObject.getString("email"));
//				userModel.setHashCode(jsonObject.getString("hashCode"));
//				userModel.setIsOpen(jsonObject.getInt("isOpen"));
//				userModel.setIsVerify(jsonObject.getInt("isVerify"));
//				userModel.setUserId(jsonObject.getInt("userId"));
//				userModel.setUserName(jsonObject.getString("userName"));
//				userModel.setPassword(jsonObject.getString("password"));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userModel;
	}
	
	public UserModel getUserInfor(User user){
		UserModel userModel=new UserModel();
		JSONObject jsonObject;
		String email;
		String hashCode;
		email=user.getEmail();
		hashCode=user.getHashCode();
		String httpResult=new HttpRequest().sendGet("getUserInfo.action", "email="+email+"&hashCode="+hashCode);
		userModel = new JsonToObject().JsonToUserModel(httpResult);
//		userModel.setStatus(500);
//		userModel.setResult(new Information().getErrorInfo(500));
//		try {
//			jsonObject = new JSONObject(httpResult);
//			userModel.setStatus(jsonObject.getInt("status"));
//			userModel.setResult(jsonObject.getString("result"));
//			if (userModel.getStatus()==0) {
//				userModel.setEmail(jsonObject.getString("email"));
//				userModel.setHashCode(jsonObject.getString("hashCode"));
//				userModel.setIsOpen(jsonObject.getInt("isOpen"));
//				userModel.setIsVerify(jsonObject.getInt("isVerify"));
//				userModel.setUserId(jsonObject.getInt("userId"));
//				userModel.setUserName(jsonObject.getString("userName"));
//				userModel.setPassword(jsonObject.getString("password"));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userModel;
	}
	
	public UserModel updateUserName(User user){
		String userName;
		String hashCode;
		String email;
		userName=user.getUserName();
		hashCode=user.getHashCode();
		email=user.getEmail();
		String httpResult=new HttpRequest().sendGet("changeUserName.action", "email="+email+"&hashCode="+hashCode+"&userName="+userName);
		UserModel userModel=new JsonToObject().JsonToUserModel(httpResult);
//		UserModel userModel=new UserModel();
//		userModel.setStatus(500);
//		userModel.setResult(new Information().getErrorInfo(500));
//		JSONObject jsonObject;
//		try {
//			jsonObject = new JSONObject(httpResult);
//			userModel.setStatus(jsonObject.getInt("status"));
//			userModel.setResult(jsonObject.getString("result"));
//			if (userModel.getStatus()==0) {
//				userModel.setEmail(jsonObject.getString("email"));
//				userModel.setHashCode(jsonObject.getString("hashCode"));
//				userModel.setIsOpen(jsonObject.getInt("isOpen"));
//				userModel.setIsVerify(jsonObject.getInt("isVerify"));
//				userModel.setUserId(jsonObject.getInt("userId"));
//				userModel.setUserName(jsonObject.getString("userName"));
//				userModel.setPassword(jsonObject.getString("password"));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userModel;
	}
	
	public UserModel resetPassword(User user){
		String hashCode;
		String email;
		String oldPwd;
		String newPwd;
		hashCode=user.getHashCode();
		email=user.getEmail();
		oldPwd=user.getOldpwd();
		newPwd=user.getNewpwd();
		String httpResult=new HttpRequest().sendGet("resetPassword.action", "email="+email+"&hashCode="+hashCode+"&oldPassword="+oldPwd+"&newPassword="+newPwd+"&repeatPassword="+newPwd);
		UserModel userModel= new JsonToObject().JsonToUserModel(httpResult);
//		System.err.println("*************************************email="+email+"&hashCode="+hashCode+"&oldPassword="+oldPwd+"&newPassword="+newPwd+"&repeatPassword="+newPwd);
//		UserModel userModel=new UserModel();
//		userModel.setStatus(500);
//		userModel.setResult(new Information().getErrorInfo(500));
//		JSONObject jsonObject;
//		try {
//			jsonObject = new JSONObject(httpResult);
//			userModel.setStatus(jsonObject.getInt("status"));
//			userModel.setResult(jsonObject.getString("result"));
//			if (userModel.getStatus()==0) {
//				userModel.setEmail(jsonObject.getString("email"));
//				userModel.setHashCode(jsonObject.getString("hashCode"));
//				userModel.setIsOpen(jsonObject.getInt("isOpen"));
//				userModel.setIsVerify(jsonObject.getInt("isVerify"));
//				userModel.setUserId(jsonObject.getInt("userId"));
//				userModel.setUserName(jsonObject.getString("userName"));
//				userModel.setPassword(jsonObject.getString("password"));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return userModel;
	}
	
}
