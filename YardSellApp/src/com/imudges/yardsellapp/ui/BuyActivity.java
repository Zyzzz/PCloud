package com.imudges.yardsellapp.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;









import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.bean.User;
import com.imudges.yardsellapp.factory.CenterPointFactory;
import com.imudges.yardsellapp.factory.SalePointFactory;
import com.imudges.yardsellapp.listener.Listener;
import com.imudges.yardsellapp.util.HttpRequest;
import com.imudges.yardsellapp.util.Map;

import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BuyActivity extends Activity {
	
	private GoogleMap mMap;
	private LatLng melbourne;
	private Geocoder geo;
	private View mInfoWindowContent = null;
	private LayoutInflater mInflater = null;
	private ArrayList<User> userList;
	private SeekBar seekBar;
	private Button searchButton = null;
	private int scope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }
    
    private void init(Bundle savedInstanceState) {
    	mInflater = LayoutInflater.from(this);
		melbourne = Map.getGPS(this);
		geo = new Geocoder(this, Locale.getDefault()); 
        setContentView(R.layout.buy_page);
        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.buy_map)).getMap();
		mMap.setMyLocationEnabled(true);
		searchButton = (Button)findViewById(R.id.btn_search_sale);
		if(searchButton == null)
			System.out.println("*****there is null button");
		searchButton.setOnClickListener(new MyOnClickListener());
		seekBar = (SeekBar)findViewById(R.id.seekBar);
		//marker = CenterPointFactory.getInstence(this, mMap);
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(Map.getGPS(this), 14, 0, (float)0)));
    } 
    
	public void refresh() {
		//melbourne = marker.getPosition();
		mMap.clear();
		//marker = CenterPointFactory.getInstence(this, mMap, melbourne);
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(Map.getGPS(this), 14 - ((float)scope / 15), 0, (float)0)));
	}
	
	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//switch(v.getId()) {
			//case R.id.btn_search:
			scope = seekBar.getProgress();
			System.out.println("scope:" + scope);
			getList();
			//}
		}
	}
	
	public void getList() {
		MyThread myThread = new MyThread(Map.getGPS(this).latitude, Map.getGPS(this).longitude, scope, 0, 0);
		myThread.setListener(new Listener() {

			private MyHandler myHandler;
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				System.out.println("search finish");
				if(Looper.myLooper() == null) {
					myHandler = new MyHandler(Looper.getMainLooper());
				} else {
					myHandler = new MyHandler(Looper.myLooper());
				}
				Message m = myHandler.obtainMessage(1, 1, 1, "success");
				myHandler.sendMessage(m);
			}

			@Override
			public void onFailure(String errorInfo) {
				// TODO Auto-generated method stub
				
			}
			
			class MyHandler extends Handler {
				
				public MyHandler(Looper l) {
					super(l);
				}
				
				@Override
				public void handleMessage(Message msg) {
					System.out.println("get msg!");
					//String result = (String)msg.obj;
					//if(result.equals("success")) {
						refresh();
						SalePointFactory.getInstence(BuyActivity.this, mMap, userList);
						System.out.println("factory");
					//}
				}
			}
			
		});
		myThread.start();
	}
	
	class MyThread extends Thread {

		double lat;
		double lng;
		int scope;
		long startDate;
		long endDate;
		Listener listener;
		
		public MyThread(double lat, double lng, int scope, long startDate, long endDate) {
			this.lat = lat;
			this.lng = lng;
			this.scope = scope;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		
		public void setListener(Listener listener) {
			this.listener = listener;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("search start");
			HttpRequest httpRequest = new HttpRequest();
			String result = httpRequest.sendGet("findSale.action", "lat=" + lat + "&lng=" + lng + "&scope=" + scope);
			try {
				userList = new ArrayList();
				System.out.println("result:" + result);
				JSONArray jsonArray = new JSONArray(result);
				for(int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					User user = new User();
					user.setEmail(jsonObj.getString("email"));
					user.setLatitude(jsonObj.getDouble("latitude"));
					user.setLongtitude(jsonObj.getDouble("longtitude"));
					user.setUserName(jsonObj.getString("userName"));
					user.setPhoneNumber(jsonObj.getString("phoneNumber"));
					user.setSellDate(new Date(jsonObj.getLong("sellDate")));
					user.setEndDate(new Date(jsonObj.getLong("endDate")));
					user.setAddress(jsonObj.getString("address"));
					user.setCommodityInfo(jsonObj.getString("commodityInfo"));
					userList.add(user);
				}
				listener.onSuccess();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("search error");
			}
		}
		
	}
}
