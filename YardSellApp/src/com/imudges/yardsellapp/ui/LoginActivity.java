package com.imudges.yardsellapp.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.listener.LoginListener;
import com.imudges.yardsellapp.service.UserService;

import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {


	private EditText et_username;
	private EditText et_password;
	private Button btn_login;
	private TextView tv_register;


	public EditText getEt_username() {
		return et_username;
	}

	public void setEt_username(EditText et_username) {
		this.et_username = et_username;
	}

	public EditText getEt_password() {
		return et_password;
	}

	public void setEt_password(EditText et_password) {
		this.et_password = et_password;
	}

	
	public Button getBtn_login() {
		return btn_login;
	}

	public TextView getTv_register() {
		return tv_register;
	}

	public void setTv_register(TextView tv_register) {
		this.tv_register = tv_register;
	}

	public void setBtn_login(Button btn_login) {
		this.btn_login = btn_login;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		init();
		loginOnClickListener loginOnClickListener=new loginOnClickListener();
		getBtn_login().setOnClickListener(loginOnClickListener);
		getTv_register().setOnClickListener(new registerOnclickListener());
	}

	public void init(){
		
		setEt_username((EditText)findViewById(R.id.et_username));
		setEt_password((EditText)findViewById(R.id.et_password));
		setBtn_login((Button)findViewById(R.id.btn_login));
		setTv_register((TextView)findViewById(R.id.tv_register));
		if (!new GetUserInfo().getEmail().equals("hupeng@imudges")) {
			String username=new GetUserInfo().getEmail();
			String password=new GetUserInfo().getPassword();
			User user=new User(username,password);
			UserService userService=new UserService();
			MyLoginListener mLoginListener=new MyLoginListener();
			userService.login(user,mLoginListener);
		}
	}
	class GetUserInfo{
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		String email=sharedPreferences.getString("email", "hupeng@imudges");
		String password=sharedPreferences.getString("password", "hupeng@imudges");
		public String getEmail(){
			return email;
		}
		public String getPassword(){
			return password;
		}
	}
	
	class SetUserInfo{
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		public void setUserId(int userId) {
			editor.putInt("userId", userId);
			editor.commit();
		}
		public void setEmail(String email){
			editor.putString("email",email );
			editor.commit();
		}
		public void setPassword(String password){
			editor.putString("password", password);
			editor.commit();
		}
		public void setUserName(String userName){
			editor.putString("userName", userName);
			editor.commit();
		}
		public void setHashCode(String hashCode){
			editor.putString("hashCode",hashCode);
			editor.commit();
		}
		public void setEmailStatus(String emailStatus){
			editor.putString("emailStatus", emailStatus);
			editor.commit();
		}
		
		public void saveEdit(UserModel user) {
			editor.putInt("userId", user.getUserId());
			editor.putString("email",user.getEmail());
			editor.putString("password", user.getPassword());
			editor.putString("userName", user.getUserName());
			editor.putString("hashCode",user.getHashCode());
			
			
			editor.putString("phoneNumber", user.getPhoneNumber());
			editor.putString("details", user.getCommodityInfo());
			editor.putString("address", user.getAddress());
			if(user.getSellDate() != null)
				editor.putLong("startDate", user.getSellDate().getTime());
			if(user.getEndDate() != null)
				editor.putLong("endDate", user.getEndDate().getTime());
			if(user.getLatitude() != null)
				editor.putString("lat", new Double(user.getLatitude()).toString());
			if(user.getLongtitude() != null)
				editor.putString("lng", "" + new Double(user.getLongtitude()).toString());
			if(user.getSellDate() != null)
				editor.putString("startStr", getDateStr(user.getSellDate().getTime()));
			if(user.getEndDate() != null)
				editor.putString("endStr", getDateStr(user.getEndDate().getTime()));
			editor.putInt("isOpen", user.getIsOpen());
			editor.putString("version", user.getVersion());
			editor.commit();
		}
		
		public String getDateStr(long dateMills) {
			Date date=new Date(dateMills);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd  HH:mm");
			String dateStr=format.format(date);
			return dateStr;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				//完成主界面更新,拿到数据
				String data = (String)msg.obj;
				toast(data);
				if (data.contains("成功")||data.contains("success")) {
					//TODO
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this, MainPageActivity.class);
					startActivity(intent);
					finish();
				}
				break;
			default:
				break;
			}
		}

	};


	public void toast(String text){
		Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();
	}
	
	class registerOnclickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
	        Intent intent=new Intent();
	        intent.setClass(LoginActivity.this, RegisterActivity.class);
	        startActivity(intent);
		}
		
	}
	
	class loginOnClickListener implements OnClickListener{

		private String username;
		private String password;
		private User user;
		private UserModel userModel;


		public UserModel getUserModel() {
			return userModel;
		}

		public void setUserModel(UserModel userModel) {
			this.userModel = userModel;
		}


		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			username = et_username.getText().toString();
			password = et_password.getText().toString();
			System.out.println("user:"+username);
			System.out.println("pass:"+password);
			user=new User(username,password);
			UserService userService=new UserService();
			MyLoginListener mLoginListener=new MyLoginListener();
			userService.login(user,mLoginListener);
			setUserModel(userModel);

		}
	}
	
	class MyLoginListener implements LoginListener {
		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "login success";//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
			if (UserService.userModel.getEmail()!=null) {
				SetUserInfo setUserInfo=new SetUserInfo();
				setUserInfo.setEmail(UserService.userModel.getEmail());
				setUserInfo.setHashCode(UserService.userModel.getHashCode());
				setUserInfo.setPassword(UserService.userModel.getPassword());
				setUserInfo.setUserName(UserService.userModel.getUserName());
				setUserInfo.setEmailStatus(UserService.userModel.getIsVerify()+"");
				setUserInfo.setUserId(UserService.userModel.getUserId());
				setUserInfo.saveEdit(UserService.userModel);
			}
		}

		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "login fail,the reason:："+errorInfo;//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);  
		}

	}
	
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "enter exit again", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
	

}

