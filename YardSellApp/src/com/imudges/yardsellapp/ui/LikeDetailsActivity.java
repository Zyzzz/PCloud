package com.imudges.yardsellapp.ui;

import java.sql.Timestamp;

import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.factory.SalePointFactory;
import com.imudges.yardsellapp.ui.DetailsActivity.onCancelCollectListener;
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

public class LikeDetailsActivity extends Activity implements OnClickListener{
	
	private String userModelString;
	private UserModel userModel;
	private EditText sellDate;
	private EditText endDate;
	private EditText phoneNumber;
	private EditText address;
	private EditText commoddityInfo;
	private Button cancel;
	private Button see_address;
	private Button collect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.like_details);
		init();
	}

	
	private void init() {
		// TODO Auto-generated method stub
		userModel=getUserModel();
		sellDate = (EditText) findViewById(R.id.like_startDate);
		endDate = (EditText) findViewById(R.id.like_endDate);
		phoneNumber = (EditText) findViewById(R.id.like_phoneNumber);
		address = (EditText) findViewById(R.id.like_address);
		commoddityInfo = (EditText) findViewById(R.id.like_details);
		cancel = (Button) findViewById(R.id.btn_like_back);
		see_address = (Button) findViewById(R.id.btn_point);
		Timestamp timestamp = new Timestamp(userModel.getSellDate().getTime());
		sellDate.setText(timestamp.toString());
		timestamp = new Timestamp(userModel.getEndDate().getTime());
		endDate.setText(timestamp.toString());
		phoneNumber.setText(userModel.getPhoneNumber());
		commoddityInfo.setText(userModel.getCommodityInfo());
		address.setText(userModel.getAddress());
		cancel.setOnClickListener(this);
		see_address.setOnClickListener(this);
		collect = (Button)findViewById(R.id.btn_like_collect);
		collect.setOnClickListener(this);
	}
	
	

	private UserModel getUserModel(){
		SharedPreferences sharedPreferences=getSharedPreferences("UserModel", MODE_PRIVATE);
		userModelString=sharedPreferences.getString("userModel", "");
		return new JsonToObject().JsonToUserModel(userModelString);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_like_back:
			Intent intent = new Intent();
			intent.setClass(LikeDetailsActivity.this, LikeActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_point:
			intent=new Intent();
			intent.setClass(LikeDetailsActivity.this, LikePointActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_like_collect:
			if (collect.getText().toString().equals("cancel collect")) {
				collect.setText("collect");
				writeFile(userModel.getEmail(), "");
			}else {
				collect.setText("cancel collect");
				writeFile(userModel.getEmail(), userModelString);
			}
			break;
		default:
			break;
		}
	}
	
	private void writeFile(String email,String value){
		SharedPreferences sharedPreferences=getSharedPreferences(getUserInfo("email")+"&like", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString(email, value);
		editor.commit();
	}
	
	private String getUserInfo(String param){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString(param, "");
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Intent intent = new Intent();
		intent.setClass(LikeDetailsActivity.this, LikeActivity.class);
		startActivity(intent);
		finish();
		return super.onKeyDown(keyCode, event);
	}
}
