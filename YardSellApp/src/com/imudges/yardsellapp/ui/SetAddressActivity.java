package com.imudges.yardsellapp.ui;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.factory.CenterPointFactory;
import com.imudges.yardsellapp.listener.OnInfoWindowElemTouchListener;
import com.imudges.yardsellapp.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SetAddressActivity extends Activity{
	private GoogleMap mMap;
	private ViewGroup mInfoWindowContent = null;
	private LayoutInflater mInflater = null;
	private Geocoder geo;
	private LatLng melbourne;
	private Button confirmButton;
	private Button cancelButton; 
	private Marker marker;
	private OnInfoWindowElemTouchListener confirmListener;
	private Dialog confirmDialog;
	private AlertDialog.Builder builder;
	private TextView addressTV;
	
	public static double lat;
	public static double lng;
	public static String address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			init(savedInstanceState);
	}
	
	public void init(Bundle savedInstanceState) {

		mInflater = LayoutInflater.from(this);
		melbourne = Map.getGPS(this);
		geo = new Geocoder(this, Locale.getDefault()); 
        setContentView(R.layout.set_address);
        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        marker = CenterPointFactory.getInstence(this, mMap, new MyOnMarkerClickListener());
		mMap.setMyLocationEnabled(true);
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(Map.getGPS(SetAddressActivity.this), 14, 0, (float)0)));
		
        View view = View.inflate(SetAddressActivity.this, R.layout.confirm_dialog, null); 
		/*builder = new AlertDialog.Builder(SetAddressActivity.this);
        builder.setView(view);
        confirmDialog = builder.create();*/
        
        confirmDialog = new Dialog(this, R.style.dialog);
        confirmDialog.setCanceledOnTouchOutside(true);
        confirmDialog.setContentView(view);
        
        addressTV = (TextView)view.findViewById(R.id.comfirm_address);
        confirmButton = (Button)view.findViewById(R.id.btn_confirm_address);
        cancelButton = (Button)view.findViewById(R.id.btn_cancel_address);
        confirmButton.setOnClickListener(new MyOnClickListener());
        cancelButton.setOnClickListener(new MyOnClickListener());
	}
	
	class MyOnMarkerClickListener implements OnMarkerClickListener {

		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
			addressTV.setText(Map.getAddress(arg0, geo));
			confirmDialog.show();
			return false;
		}
	}
	
	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.btn_confirm_address:
				melbourne = marker.getPosition();
				lat = melbourne.latitude;
				lng = melbourne.longitude;
				address = Map.getAddress(marker, geo);
				SetAddressActivity.this.finish();break;
			case R.id.btn_cancel_address:
				confirmDialog.hide();break;
			}
		}
	}
	
}
