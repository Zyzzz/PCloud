package com.imudges.yardsellapp.ui;

import java.util.ArrayList;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.bean.UserModel;
import com.imudges.yardsellapp.ui.BuyActivity.MyOnClickListener;
import com.imudges.yardsellapp.util.JsonToObject;
import com.imudges.yardsellapp.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class LikePointActivity extends Activity {

	private GoogleMap mMap;
	private LatLng melbourne;
	private Geocoder geo;
	private Marker marker;
	private static UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }
    
    private UserModel getUserModel(){
		SharedPreferences sharedPreferences=getSharedPreferences("UserModel", MODE_PRIVATE);
		String userModelJsonString;
		userModelJsonString=sharedPreferences.getString("userModel", "");
		return new JsonToObject().JsonToUserModel(userModelJsonString);
	}
    
    private void init(Bundle savedInstanceState) {
    	/*
    	 * 在这里给usermodel初始化
    	 */
    	userModel=getUserModel();
		melbourne = new LatLng(userModel.getLatitude(), userModel.getLongtitude());
		geo = new Geocoder(this, Locale.getDefault()); 
        setContentView(R.layout.collect_point);
        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.collect_map)).getMap();
		mMap.setMyLocationEnabled(true);
		marker = mMap.addMarker(new MarkerOptions().
				title(userModel.getUserName() + "'s sale.").
				position(melbourne).
				draggable(false).flat(true));
		
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(melbourne, 14, 0, (float)0)));
    } 
}
