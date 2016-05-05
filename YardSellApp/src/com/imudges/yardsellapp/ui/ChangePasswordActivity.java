package com.imudges.yardsellapp.ui;

import com.imudges.yardsellapp.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.listener.UpdateListener;
import com.imudges.yardsellapp.service.UserService;
import com.imudges.yardsellapp.util.Information;

public class ChangePasswordActivity extends Activity{
	EditText et_oldpwd;
	EditText et_newpwd;
	EditText et_repeatpwd;
	Button btn_submit;
	String oldpwd;
	String newped;
	String repeatpwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		init();
	}
	private void init(){
		et_oldpwd=(EditText)findViewById(R.id.et_oldpwd);
		et_newpwd=(EditText)findViewById(R.id.et_newpwd);
		et_repeatpwd=(EditText)findViewById(R.id.et_repeatpwd);
		btn_submit=(Button)findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(new SubmitOnClickListener());
	}
	
	class SubmitOnClickListener implements OnClickListener{
		User user;
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String oldpassword=et_oldpwd.getText().toString();
			String newpassword=et_newpwd.getText().toString();
			String repeatpassword=et_repeatpwd.getText().toString();
			if (!newpassword.equals(repeatpassword)) {
				toast(new Information().getErrorInfo(501));
				return;
			}
			user=new User();
			user.setEmail(getUserDataFromPhone("email"));
			user.setHashCode(getUserDataFromPhone("hashCode"));
			user.setOldpwd(oldpassword);
			user.setNewpwd(newpassword);
			System.err.println("email:"+getUserDataFromPhone("email"));
			System.err.println("hashCode:"+getUserDataFromPhone("hashCode"));
			System.out.println(oldpassword);
			System.out.println(newpassword);
			UserService userService=new UserService();
			userService.resetPassword(user, new MyUpdateListener());
		}
	}
	
	class MyUpdateListener implements UpdateListener{

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			UserModel userModel=new UserService().getCurrentUserModel();
			Message msg =new Message();  
            msg.obj = "data update success";//可以是基本类型，可以是对象，可以是List、map等；  
            mHandler.sendMessage(msg);
		}

		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			Message msg =new Message();  
            msg.obj = "update fail,the reason:"+errorInfo;//可以是基本类型，可以是对象，可以是List、map等；  
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
				toast(data);
				if (data.contains("成功")||data.contains("success")) {
					//TODO
					//更新本地密码信息
					setUserDataFromPhone("password", et_newpwd.getText().toString());
				}
				break;
			default:
				break;
			}
		}

	};
	
	private void toast(String text){
		Toast.makeText(ChangePasswordActivity.this, text, Toast.LENGTH_SHORT).show();
	}
	
	private String getUserDataFromPhone(String param){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString(param, "");
	}
	
	private void setUserDataFromPhone(String param,String value){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString(param, value);
		editor.commit();
	}
}
