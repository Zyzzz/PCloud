package com.imudges.yardsellapp.factory;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.ui.DetailsActivity;
import com.imudges.yardsellapp.util.Map;

public class SalePointFactory {
	private static Context context;
	private static LatLng melbourne;
	private static ArrayList<Marker> markerList = new ArrayList();
	private static ArrayList<User> userList;
	private static User user;
	
	public static ArrayList<User> getUserList() {
		return userList;
	}

	public static void setUserList(ArrayList<User> userList) {
		SalePointFactory.userList = userList;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SalePointFactory.user = user;
	}

	public static ArrayList<Marker> getInstence(Context context, GoogleMap mMap, ArrayList<User> userList) {
		SalePointFactory.userList = userList;
		SalePointFactory.context = context;
		if(userList == null)
			return null;
		for(int i = 0; i < userList.size(); i++) {
			init(mMap, userList.get(i), i);
		}
		mMap.setOnMarkerDragListener(new MyOnMarkerDragListener());
		mMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
		return markerList;
	}
	
	private static void init(GoogleMap mMap, User user, int num) {

		melbourne = new LatLng(user.getLatitude(), user.getLongtitude());
		Marker marker = mMap.addMarker(new MarkerOptions().
					position(melbourne)
					.title("Sale position:" + num)
					.draggable(false)
					.flat(true)
					.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
					);
		markerList.add(marker);
	}
	
	static class MyOnMarkerClickListener implements OnMarkerClickListener{

		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
	        arg0.hideInfoWindow();
			String title = arg0.getTitle();
			int num = Integer.parseInt(title.substring(title.lastIndexOf(":") + 1));
			user = userList.get(num);
			context.startActivity(new Intent(context, DetailsActivity.class));
			return false;
		}
		
	}
	
	static class MyOnMarkerDragListener implements OnMarkerDragListener {

		@Override
		public void onMarkerDrag(Marker arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMarkerDragEnd(Marker arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMarkerDragStart(Marker arg0) {
			// TODO Auto-generated method stub
		    if(arg0.isInfoWindowShown())
		        arg0.hideInfoWindow();
		}
	}
	
}
