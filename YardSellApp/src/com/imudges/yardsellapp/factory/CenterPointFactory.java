package com.imudges.yardsellapp.factory;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.ui.SaleActivity;
import com.imudges.yardsellapp.ui.SetAddressActivity;
import com.imudges.yardsellapp.util.Map;

public class CenterPointFactory{

	public static Marker marker = null;
	private static Context context;
	public static LatLng melbourne;
	
	public static Marker getInstence(Context context, GoogleMap mMap) {
		CenterPointFactory.context = context;
		melbourne = Map.getGPS(context);
		init(mMap);
		mMap.setOnMarkerDragListener(new MyOnMarkerDragListener());
		mMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
		return marker;
	}
	
	public static Marker getInstence(Context context, GoogleMap mMap, LatLng melbourne) {
		CenterPointFactory.context = context;
		CenterPointFactory.melbourne = melbourne;
		init(mMap);
		mMap.setOnMarkerDragListener(new MyOnMarkerDragListener());
		mMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
		return marker;
	}
	
	public static Marker getInstence(Context context, GoogleMap mMap, InfoWindowAdapter infoWindowAdapter) {
		CenterPointFactory.context = context;
		melbourne = Map.getGPS(context);
		init(mMap);
		mMap.setOnMarkerDragListener(new MyOnMarkerDragListener());
		mMap.setOnMarkerClickListener(new MyOnMarkerClickListener());
		mMap.setInfoWindowAdapter(infoWindowAdapter);
		return marker;
	}
	public static Marker getInstence(Context context, GoogleMap mMap, OnMarkerClickListener onMarkerClickListener) {
		CenterPointFactory.context = context;
		melbourne = Map.getGPS(context);
		init(mMap);
		mMap.setOnMarkerDragListener(new MyOnMarkerDragListener());
		mMap.setOnMarkerClickListener(onMarkerClickListener);
		return marker;
	}
	

	private static void init(GoogleMap mMap) {
		marker = mMap.addMarker(new MarkerOptions().
					position(melbourne).
					draggable(true).flat(true));
	}
	
	
	

	static class MyOnMarkerClickListener implements OnMarkerClickListener{

		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
	        arg0.hideInfoWindow();
			System.out.println(arg0.getPosition());
			//Toast.makeText(context, arg0.getPosition().toString(), Toast.LENGTH_SHORT).show();
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
		    if(marker.isInfoWindowShown())
		        marker.hideInfoWindow();
			
		}
		
	}

}
