package com.imudges.yardsellapp.ui;




import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.listener.RegisterListener;
import com.imudges.yardsellapp.service.UserService;
import com.imudges.yardsellapp.ui.LoginActivity.SetUserInfo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity{
	
	private EditText et_email;
	private EditText et_name;
	private EditText et_password;
	private EditText et_repassword;
	private Button btn_register;
	private String email;
	private String name;
	private String password;
	private String repassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}
	
	private void init(){
		et_email=(EditText)findViewById(R.id.et_email);
		et_name=(EditText)findViewById(R.id.et_name);
		et_password=(EditText)findViewById(R.id.et_password);
		et_repassword=(EditText)findViewById(R.id.et_repassword);
		btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new RegisterOnClickListener());
	}
	
	class RegisterOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			email=et_email.getText().toString();
			name=et_name.getText().toString();
			password=et_password.getText().toString();
			repassword=et_repassword.getText().toString();
			if (!password.equals(repassword)) {
				toast("register fail,the reason:the two passowrd is not same");
			}else {
				User user=new User();
				user.setEmail(email);
				user.setUserName(name);
				user.setPassword(password);
				UserService userService=new UserService();
				userService.register(user, new MyRegisterListener());
			}
		}
	}
	
	class MyRegisterListener implements RegisterListener{

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "register success!";//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
            if (UserService.userModel.getEmail()!=null) {
				SetUserInfo setUserInfo=new SetUserInfo();
				setUserInfo.setEmail(UserService.userModel.getEmail());
				setUserInfo.setHashCode(UserService.userModel.getHashCode());
				setUserInfo.setPassword(UserService.userModel.getPassword());
				setUserInfo.setUserName(UserService.userModel.getUserName());
			}
		}
		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "register fail,the reason:"+errorInfo;//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
		}
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
					intent.setClass(RegisterActivity.this, LoginActivity.class);
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
		Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_LONG).show();
	}
	
	class SetUserInfo{
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
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
	}
}
