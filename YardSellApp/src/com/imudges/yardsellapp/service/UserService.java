package com.imudges.yardsellapp.service;

import android.R.integer;
import android.content.SharedPreferences;
import android.os.Looper;

import com.imudges.yardsellapp.DAO.UserDAO;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.listener.GetUserInfoListener;
import com.imudges.yardsellapp.listener.LoginListener;
import com.imudges.yardsellapp.listener.RegisterListener;
import com.imudges.yardsellapp.listener.UpdateListener;

public class UserService extends Thread{
	public static UserModel userModel;
	private User user;
	private LoginListener loginListener;
	private int taskId;
	private RegisterListener registerListener;
	private GetUserInfoListener getUserInfoListener;
	private UpdateListener updateListener;
	public void login(User user,LoginListener listener){
		this.user=user;
		this.loginListener=listener;
		taskId=0;
		start();
	}
	
	public void register(User user,RegisterListener registerListener){
		this.user=user;
		this.registerListener=registerListener;
		taskId=1;
		start();
	}
	
	public UserModel getCurrentUserModel(){
		if (userModel==null) {
			return new UserModel();
		}else {
			return userModel;
		}
	}

	public void getUserInfo(User user,GetUserInfoListener getUserInfoListener){
		this.user=user;
		this.getUserInfoListener=getUserInfoListener;
		taskId=2;
		start();
	}
	
	public void updateUserName(User user,UpdateListener updateListener){
		this.user=user;
		this.updateListener=updateListener;
		taskId=3;
		start();
	}
	
	public void resetPassword(User user,UpdateListener updateListener){
		this.user=user;
		this.updateListener=updateListener;
		taskId=4;
		start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Looper.prepare();
		UserModel userModel;
		switch (taskId) {
		case 0:
			userModel=new UserDAO().loginUser(user);
			System.out.println(userModel.getStatus());
			this.userModel=userModel;
			if (userModel.getStatus()>0) {
				loginListener.onFailure(userModel.getResult());
				return;
			}
			loginListener.onSuccess();
			break;
		case 1:
			userModel=new UserDAO().registerUser(user);
			System.out.println(userModel.getStatus());
			this.userModel=userModel;
			if (userModel.getStatus()>0) {
				registerListener.onFailure(userModel.getResult());
				return;
			}
			registerListener.onSuccess();
			break;
		case 2:
			userModel=new UserDAO().getUserInfor(user);
			System.out.println(userModel.getStatus());
			this.userModel=userModel;
			if (userModel.getStatus()>0) {
				getUserInfoListener.onFailure(userModel.getResult());
				return;
			}
			getUserInfoListener.onSuccess();
			break;
		case 3:
			userModel=new UserDAO().updateUserName(user);
			System.out.println(userModel.getStatus());
			this.userModel=userModel;
			if (userModel.getStatus()>0) {
				updateListener.onFailure(userModel.getResult());
				return;
			}
			updateListener.onSuccess();
			break;
		case 4:
			//TODO
			userModel=new UserDAO().resetPassword(user);
			System.out.println(userModel.getStatus());
			this.userModel=userModel;
			if (userModel.getStatus()>0) {
				updateListener.onFailure(userModel.getResult());
				return;
			}
			updateListener.onSuccess();
			break;
		default:
			break;
		}
		
	}
}
