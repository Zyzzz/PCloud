package com.imudges.yardsellapp.ui;


import net.sf.json.JSONObject;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.gson.Gson;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserBean;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.listener.GetUserInfoListener;
import com.imudges.yardsellapp.listener.UpdateListener;
import com.imudges.yardsellapp.service.UserService;
import com.imudges.yardsellapp.ui.LoginActivity.GetUserInfo;
import com.imudges.yardsellapp.ui.LoginActivity.SetUserInfo;
import com.imudges.yardsellapp.ui.LoginActivity.loginOnClickListener;
import com.imudges.yardsellapp.util.HttpRequest;
import com.imudges.yardsellapp.util.Information;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MeActivity extends Activity{

	private EditText et_email;
	private EditText et_name;
	private Button btn_active;
	private Button btn_changpwd;
	private Button btn_submit;
	private ImageButton imgbtn_changename;
	private String email;
	private String name;
	private boolean isEnbled;
	private Button btn_logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_page);
		init();
	}
	public String getUserDatafromPhone(String param){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString(param, "");
	}
	public void setUserDataFromPhone(String param,String value){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString(param, value);
		editor.commit();
	}
	private void init(){
		et_email=(EditText)findViewById(R.id.et_email);
		et_name=(EditText)findViewById(R.id.et_name);
		btn_active=(Button)findViewById(R.id.btn_active);
		btn_logout=(Button)findViewById(R.id.btn_logout);
		imgbtn_changename=(ImageButton)findViewById(R.id.btn_changename);
		btn_submit=(Button)findViewById(R.id.btn_submit);
		btn_changpwd=(Button)findViewById(R.id.btn_changepwd);
		et_email.setText(getUserDatafromPhone("email"));
		et_name.setText(getUserDatafromPhone("userName"));
		if (getUserDatafromPhone("emailStatus").equals("1")) {
			btn_active.setVisibility(View.GONE); 
		}
		et_email.setEnabled(false);
		et_name.setEnabled(false);
		imgbtn_changename.setOnClickListener(new ChangeNameOnClickListener());
		isEnbled=false;
		imgbtn_changename.setBackground(getResources().getDrawable(R.drawable.jiantou));
		GetUserInfo getUserInfo=new GetUserInfo(getUserDatafromPhone("email"), getUserDatafromPhone("hashCode"));
		getUserInfo.sendRequest();
		btn_submit.setOnClickListener(new SubmitOnClickListener());
		btn_changpwd.setOnClickListener(new ChangePwdListener());
		btn_logout.setOnClickListener(new LogoutOnClickListener());
		btn_active.setOnClickListener(new ActiveOnClickListener());
	}
	class LogoutOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
			SharedPreferences.Editor editor=sharedPreferences.edit();
			editor.clear();
			editor.commit();
			editor.putString("email", "hupeng@imudges");
			editor.commit();
//			Intent intent=new Intent();
//			intent.setClass(MeActivity.this, LoginActivity.class);
//			startActivity(intent);
//			finish();
			final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());  
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	        startActivity(intent); 
	        finish();
		}
	}
	
	class ChangeNameOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (isEnbled) {
				et_name.setEnabled(false);
				imgbtn_changename.setBackground(getResources().getDrawable(R.drawable.jiantou));
				isEnbled=!isEnbled;
			}else {
				et_name.setEnabled(true);
				imgbtn_changename.setBackground(getResources().getDrawable(R.drawable.jiantou2));
				isEnbled=!isEnbled;
				et_name.selectAll();
			}
		}
	}
	
	class ActiveOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			new SendEmail().start();
			toast("The  mail has sent to your email box");
		}
	}
	
	class SendEmail extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			new HttpRequest().sendGet("sendEmail.action", "email=" + et_email.getText().toString());
		}
		
	}
	
	
	class ChangePwdListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(MeActivity.this, ChangePasswordActivity.class);
			startActivity(intent);
		}
	}
	
	
	class GetUserInfo{
		String email;
		String hashCode;
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getHashCode() {
			return hashCode;
		}

		public void setHashCode(String hashCode) {
			this.hashCode = hashCode;
		}

		public GetUserInfo(String email,String hashCode){
			this.email=email;
			this.hashCode=hashCode;
		}
		public void sendRequest(){
			User user=new User();
			user.setEmail(email);
			user.setHashCode(hashCode);
			UserService userService=new UserService();
			userService.getUserInfo(user, new MyGetUserInfoListener());
		}
	}
	
	class MyGetUserInfoListener implements GetUserInfoListener{

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			UserModel userModel=new UserService().getCurrentUserModel();
			Message msg =new Message();  
            msg.obj = "success:email="+userModel.getEmail()+":name="+userModel.getUserName();//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
		}

		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "can't get data from service";//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
		}
		
	}
	
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				//完成主界面更新,拿到数据
				String data = (String)msg.obj;
				if (data.contains("成功")||data.contains("success")) {
					//TODO
					et_email.setText(data.split(":")[1].split("=")[1]);
					et_name.setText(data.split(":")[2].split("=")[1]);
					setUserDataFromPhone("userName", data.split(":")[2].split("=")[1]);
					setUserDataFromPhone("email", data.split(":")[1].split("=")[1]);
					
//					UserBean userBean=new UserBean();
//					userBean.setAddress("Chian");
//					userBean.setEmail("guyu@guyu.com");
//					userBean.setLatitude(40.816681);
//					userBean.setLongtitude(111.683298);
//					Gson gson=new Gson();
//					
//					SharedPreferences sharedPreferences=getSharedPreferences(data.split(":")[1].split("=")[1]+"&like", MODE_PRIVATE);
//					SharedPreferences.Editor editor=sharedPreferences.edit();
//					editor.putString("guyu@guyu.com", gson.toJson(userBean));
//					editor.commit();
//					userBean.setAddress("american");
//					userBean.setEmail("123@123.com");
//					editor.putString("123@123.com",gson.toJson(userBean));
//					System.out.println(gson.toJson(userBean));
//					editor.commit();
				}else {
					toast(data);
				}
				break;
			default:
				break;
			}
		}

	};
	
	public void toast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
	
	class SubmitOnClickListener implements OnClickListener{
		private User user=new User();
		public void init(String email,String name,String hashCode){
			user.setEmail(email);
			user.setHashCode(hashCode);
			user.setUserName(name);
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			user.setUserName(et_name.getText().toString());
			user.setHashCode(getUserDatafromPhone("hashCode"));
			user.setEmail(getUserDatafromPhone("email"));
			UserService userService=new UserService();
			userService.updateUserName(user, new MyUpdateListener());
		}
		
	}
	
	class MyUpdateListener implements UpdateListener{

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "the date has submit";//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
            
		}

		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "the data submit fail,the reason:："+errorInfo;//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);  
		}
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	    	Intent intent=new Intent();
	    	intent.setClass(MeActivity.this, MainPageActivity.class);
	    	startActivity(intent);
	    	finish();
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	

}
