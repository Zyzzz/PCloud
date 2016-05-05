package com.imudges.yardsellapp.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Map {

	public static String getAddress(Marker marker, Geocoder geo) {
		
		String address = "";
		try {
			List<Address> addresses = geo.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
			if (addresses.isEmpty()) {  
		        Log.i("location", "addressed is Empty");  
				return "Here is the address.";
		    }  
		    else {  
		    	Address a = addresses.get(0);
		        if (addresses.size() > 0) {  
		        	if(a.getThoroughfare() != null)
		        		address += a.getThoroughfare() + " ";
		        	if(a.getLocality() != null)
		        		address += a.getLocality() + " ";
		        	if(a.getAdminArea() != null)
		        		address += a.getAdminArea() + " ";
		        	if(a.getCountryName() != null)
		        		address += a.getCountryName() + " ";
		            Log.i("location", addresses.get(0).getThoroughfare() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());  
		        }  
		    }  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Here is the address.";
		}
		return address;
	}
	
	public static LatLng getGPS(Context context) { 
	    LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);   
	    List<String> providers = lm.getProviders(true);
	    /* 循环读取providers,如果有地址信息, 退出循环*/ 
	    Location l = null; 
	       
	    for (int i=providers.size()-1; i>=0; i--) { 
	        l = lm.getLastKnownLocation(providers.get(i)); 
	        if (l != null) break; 
	    } 
	       
	    double[] gps = new double[2]; 
	    if (l != null) { 
	        gps[0] = l.getLatitude(); //纬度 
	        gps[1] = l.getLongitude();//经度 
	    }  
	    return new LatLng(gps[0], gps[1]);
	}
}
