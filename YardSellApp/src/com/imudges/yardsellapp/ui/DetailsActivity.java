package com.imudges.yardsellapp.ui;


import java.sql.Timestamp;

import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

import com.google.gson.Gson;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserBean;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.factory.SalePointFactory;
import com.imudges.yardsellapp.util.JsonToObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class DetailsActivity extends Activity implements OnClickListener {

	private User user;
	private EditText sellDate;
	private EditText endDate;
	private EditText phoneNumber;
	private EditText address;
	private EditText commoddityInfo;
	private Button cancel;
	private Button collect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}
	private int getShareStatus(){
		SharedPreferences sharedPreferences=getSharedPreferences("UserModel", MODE_PRIVATE);
		return sharedPreferences.getInt("status", 0);
	}
	private UserModel getUserModel(){
		SharedPreferences sharedPreferences=getSharedPreferences("UserModel", MODE_PRIVATE);
		String userModelJsonString;
		userModelJsonString=sharedPreferences.getString("userModel", "");
		return new JsonToObject().JsonToUserModel(userModelJsonString);
	}
	private void toast(String text){
		Toast.makeText(DetailsActivity.this, text, Toast.LENGTH_SHORT).show();
	}
	
	private void init() {
		user = SalePointFactory.getUser();
        setContentView(R.layout.details_page);
        sellDate = (EditText)findViewById(R.id.startDate);
        endDate = (EditText)findViewById(R.id.endDate);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        address = (EditText)findViewById(R.id.address);
        commoddityInfo = (EditText)findViewById(R.id.details);
        Timestamp t = new Timestamp(user.getSellDate().getTime());
        sellDate.setText(t.toString());
        t = new Timestamp(user.getEndDate().getTime());
        endDate.setText(t.toString());
        phoneNumber.setText(user.getPhoneNumber());
        address.setText(user.getAddress());
        commoddityInfo.setText(user.getCommodityInfo());
        
        cancel = (Button)findViewById(R.id.btn_back);
        cancel.setOnClickListener(this);
        
        collect = (Button)findViewById(R.id.btn_collect);
        collect.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_back:
			finish();break;
		case R.id.btn_collect:
			writeFile();
			finish();break;
		}
	}
	
	private String getEmail(){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString("email", "email");
	}
	private void writeFile(){
		String email = getEmail();
		SharedPreferences sharedPreferences=getSharedPreferences(email + "&like", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		Gson gson=new Gson();
		editor.putString(user.getEmail(), gson.toJson(new UserBean(user)));
		editor.commit();
	}
	
	class onCancelCollectListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(collect.getText().toString().equals("collect")){
				writeFile();
				collect.setText("Cancel collect");
			}else {
				String email=getUserModel().getEmail();
				SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
				String userEmail=sharedPreferences.getString("email", "");
				sharedPreferences=getSharedPreferences(userEmail+"&like", MODE_PRIVATE);
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString(email, "");
				editor.commit();
				collect.setText("collect");
			}
			
		}
		
	}
	

}
