package com.imudges.yardsellapp.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.gson.Gson;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.UserBean;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.util.JsonToObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class LikeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.like_page);
		ListView listView = (ListView)findViewById(R.id.lv_like);
		SimpleAdapter simpleAdapter=new SimpleAdapter(this, (List<? extends Map<String, ?>>) getArrayList(), R.layout.vlist, new String[]{"startTime","address"}, new int[]{R.id.tv_title,R.id.tv_info});
		listView.setAdapter(simpleAdapter);
		//init();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				writeFile(arg2);
				Intent intent=new Intent();
				intent.setClass(LikeActivity.this, LikeDetailsActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void toast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
	private String getEmail(){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString("email", "");
	}
	private List<UserModel> readFile(){
		List<UserModel> list=new ArrayList<UserModel>();
		SharedPreferences sharedPreferences=getSharedPreferences(getEmail()+"&like", MODE_PRIVATE);
		Map<String, ?> map=sharedPreferences.getAll();
		String content;
		for(Map.Entry<String, ?>  entry : map.entrySet()){  
            content=(String) entry.getValue();
            UserModel userModel=new JsonToObject().JsonToUserModel(content);
            list.add(userModel);
        }
		return list;
	}
	
	ArrayList<UserModel>list=new ArrayList<UserModel>();
	private ArrayList<Object> getArrayList(){
		ArrayList<Object>arrayList=new ArrayList<Object>();
		
		SharedPreferences sharedPreferences=getSharedPreferences(getEmail()+"&like", MODE_PRIVATE);
		Map<String, ?> map=sharedPreferences.getAll();
		String content;
		for(Map.Entry<String, ?>  entry : map.entrySet()){  
			HashMap<String, String> hashMap=new HashMap<String, String>();
            content=(String) entry.getValue();
            if (!content.equals("")) {
            	 UserModel userModel=new JsonToObject().JsonToUserModel(content);
                 hashMap.put("startTime", userModel.getSellDate().toLocaleString());
                 hashMap.put("address",userModel.getAddress());
                 arrayList.add(hashMap);
                 list.add(userModel);
			}
        }
		return arrayList;
	}
	private void writeFile(int id){
		SharedPreferences sharedPreferences=getSharedPreferences("UserModel", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putInt("status", 1);
		Gson gson=new Gson();
		editor.putString("userModel", gson.toJson(new UserBean(list.get(id))));
		editor.commit();
	}

}
