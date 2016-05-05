
package com.imudges.yardsellapp.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.factory.SalePointFactory;
import com.imudges.yardsellapp.listener.Listener;
import com.imudges.yardsellapp.util.HttpRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class SaleActivity extends Activity implements OnClickListener,OnTouchListener {

	private Button addressButton;
	private Button pictureButton;
	private Button commitButton;
	private Button closeButton;
	private EditText etStartTime;    
	private EditText etEndTime; 
	private EditText etPhoneNumber;
	private EditText etDetails;
	private EditText etAddress;
	
	public static SharedPreferences sharedPreferences;
	private int userId;
	
	private String startStr;
	private String endStr;
	
	private long startDate;
	private long endDate;
	private String phoneNumber;
	private String details;
	private String address;
	private double lat;
	private double lng;
	private String hint;
	private int isOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_page);
		init();
	}
	
	private void init () {
		sharedPreferences = getSharedPreferences("userConfig", MODE_PRIVATE);
	    userId = sharedPreferences.getInt("userId", 0);
	    if(userId == 0) {
	    	showToast("Please re login.");
	    }
		setEdit();
		addressButton = (Button)findViewById(R.id.btn_set_address);
		addressButton.setOnClickListener(this);
		pictureButton = (Button) findViewById(R.id.btn_picture);
		pictureButton.setOnClickListener(this);
		commitButton = (Button)findViewById(R.id.btn_commit_sale);
		commitButton.setOnClickListener(this);
		closeButton = (Button)findViewById(R.id.btn_close_sale);
		if(isOpen == 0)
			closeButton.setVisibility(View.GONE);
		closeButton.setOnClickListener(this);
		etStartTime = (EditText) this.findViewById(R.id.et_start_time); 
	    etEndTime = (EditText) this.findViewById(R.id.et_end_times); 
	    etStartTime.setOnTouchListener(this); 
	    etEndTime.setOnTouchListener(this);
	    etPhoneNumber = (EditText)findViewById(R.id.phoneNumber);
	    etDetails = (EditText)findViewById(R.id.details);
	    etAddress = (EditText)findViewById(R.id.address);
	    
	    setView();
	    
	    
	}
	
	public void setView() {
		etStartTime.setText(startStr);
		etEndTime.setText(endStr);
		etPhoneNumber.setText(phoneNumber);
		etDetails.setText(details);
		etAddress.setText(address);
	}

	public void showToast(String str) {
		Toast.makeText(SaleActivity.this, str, Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_set_address:
			startActivity(new Intent(this, SetAddressActivity.class));
			break;
		case R.id.btn_picture:
			startActivity(new Intent(this, SelectActivity.class));
			break;
		case R.id.btn_commit_sale:
			commitSale();
			break;
		case R.id.btn_close_sale:
			closeSale();
			break;
		}
	}
	
	public void saveEdit() {
		Editor editor = this.sharedPreferences.edit();
		editor.putString("phoneNumber", phoneNumber);
		editor.putString("details", details);
		editor.putString("address", address);
		editor.putLong("startDate", startDate);
		editor.putLong("endDate", endDate);
		editor.putString("lat", new Double(lat).toString());
		editor.putString("lng", "" + new Double(lng).toString());
		editor.putString("startStr", startStr);
		editor.putString("endStr", endStr);
		editor.commit();
	}
	
	public void setEdit() {
		phoneNumber = sharedPreferences.getString("phoneNumber", "");
		details = sharedPreferences.getString("details", "");
		address = sharedPreferences.getString("address", "");
		startDate = sharedPreferences.getLong("startDate", 0);
		endDate = sharedPreferences.getLong("endDate", 0);
		lat = Double.parseDouble(sharedPreferences.getString("lat", "0"));
		lng = Double.parseDouble(sharedPreferences.getString("lng", "0"));
		startStr = sharedPreferences.getString("startStr", "");
		endStr = sharedPreferences.getString("endStr", "");
		isOpen = sharedPreferences.getInt("isOpen", 0);
	}
	
	private void commitSale() {
		phoneNumber = etPhoneNumber.getText().toString();
		details = etDetails.getText().toString();
		address = etAddress.getText().toString();
		lat = SetAddressActivity.lat;
		lng = SetAddressActivity.lng;
		startStr = etStartTime.getText().toString();
		endStr = etEndTime.getText().toString();
		saveEdit();
		System.out.println("latitude=" + lat + "&longtitude=" + lng + "&userId=" + userId +"&address=" + address
				+ "&phoneNumber=" + phoneNumber + "&sellDate=" + startDate + "&endDate=" + endDate +"&commodityInfo=" +details);
		MySetThread mySetThread = new MySetThread();
		mySetThread.setListener(new MySetListener());
		mySetThread.start();
	}
	
	private void closeSale()
	{
		MyCloseThread myCloseThread = new MyCloseThread();
		myCloseThread.setListener(new MyCloseListener());
		myCloseThread.start();
	}
	
    @Override 
    public boolean onTouch(View v, MotionEvent event) { 
        if (event.getAction() == MotionEvent.ACTION_DOWN) { 
   
            AlertDialog.Builder builder = new AlertDialog.Builder(this); 
            View view = View.inflate(this, R.layout.date_time_dialog, null); 
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker); 
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker); 
            builder.setView(view); 
   
            Calendar cal = Calendar.getInstance(); 
            cal.setTimeInMillis(System.currentTimeMillis()); 
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null); 
   
            timePicker.setIs24HourView(true); 
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY)); 
            timePicker.setCurrentMinute(Calendar.MINUTE); 
   
            if (v.getId() == R.id.et_start_time) { 
                final int inType = etStartTime.getInputType(); 
                etStartTime.setInputType(InputType.TYPE_NULL); 
                etStartTime.onTouchEvent(event); 
                etStartTime.setInputType(inType); 
                etStartTime.setSelection(etStartTime.getText().length()); 
                   
                builder.setTitle("选取起始时间"); 
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 
   
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
   
                        StringBuffer sb = new StringBuffer(); 
                        sb.append(String.format("%d-%02d-%02d",  
                                datePicker.getYear(),  
                                datePicker.getMonth() + 1, 
                                datePicker.getDayOfMonth())); 
                        sb.append("  "); 
                        sb.append(timePicker.getCurrentHour()) 
                        .append(":").append(timePicker.getCurrentMinute()); 
   
                        etStartTime.setText(sb); 
                        Calendar c = Calendar.getInstance();
                        try {
							c.setTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm").parse(sb.toString()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        startDate = c.getTimeInMillis();
                        System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());
                        etEndTime.requestFocus(); 
                           
                        dialog.cancel(); 
                    } 
                }); 
                   
            } else if (v.getId() == R.id.et_end_times) { 
                int inType = etEndTime.getInputType(); 
                etEndTime.setInputType(InputType.TYPE_NULL);     
                etEndTime.onTouchEvent(event); 
                etEndTime.setInputType(inType); 
                etEndTime.setSelection(etEndTime.getText().length()); 
   
                builder.setTitle("选取结束时间"); 
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 
   
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
   
                        StringBuffer sb = new StringBuffer(); 
                        sb.append(String.format("%d-%02d-%02d",  
                                datePicker.getYear(),  
                                datePicker.getMonth() + 1,  
                                datePicker.getDayOfMonth())); 
                        sb.append("  "); 
                        sb.append(timePicker.getCurrentHour()) 
                        .append(":").append(timePicker.getCurrentMinute()); 
                        etEndTime.setText(sb); 
                        Calendar c = Calendar.getInstance();
                        try {
							c.setTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm").parse(sb.toString()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        endDate = c.getTimeInMillis();
                        System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());   
                        dialog.cancel(); 
                    } 
                }); 
            } 
            Dialog dialog = builder.create(); 
            dialog.show(); 
        } 
        return true; 
    } 
    
    class MyCloseListener implements Listener {
    	
    	private MyHandler myHandler;

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			System.out.println("Listen success");
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
			System.out.println("Listen fail");
			if(Looper.myLooper() == null) {
				myHandler = new MyHandler(Looper.getMainLooper());
			} else {
				myHandler = new MyHandler(Looper.myLooper());
			}
			Message m = myHandler.obtainMessage(1, 1, 1, errorInfo);
			myHandler.sendMessage(m);
			
		}
		
		class MyHandler extends Handler {
			
			public MyHandler(Looper l) {
				super(l);
			}
			
			@Override
			public void handleMessage(Message msg) {
				System.out.println("get msg!");
				String result = (String)msg.obj;
				System.out.println(result);
				if(result.equals("success")) {
					showToast("Close sale success");
					isOpen = 0;
					Editor editor = SaleActivity.this.sharedPreferences.edit();
					editor.putInt("isOpen", 0);
					editor.commit();
					closeButton.setVisibility(View.GONE);
					finish();
				} else {
					Toast.makeText(SaleActivity.this, result, Toast.LENGTH_SHORT).show();
				}
			}
		}
    	
    }
    
    class MySetListener implements Listener {
    	
    	private MyHandler myHandler;
		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			System.out.println("Listen success");
			if(Looper.myLooper() == null) {
				myHandler = new MyHandler(Looper.getMainLooper());
			} else {
				myHandler = new MyHandler(Looper.myLooper());
			}
			isOpen = 1;
			Editor editor = SaleActivity.this.sharedPreferences.edit();
			editor.putInt("isOpen", 1);
			editor.commit();
			Message m = myHandler.obtainMessage(1, 1, 1, "success");
			myHandler.sendMessage(m);
		}

		@Override
		public void onFailure(String errorInfo) {
			// TODO Auto-generated method stub
			System.out.println("Listen fail");
			if(Looper.myLooper() == null) {
				myHandler = new MyHandler(Looper.getMainLooper());
			} else {
				myHandler = new MyHandler(Looper.myLooper());
			}
			Message m = myHandler.obtainMessage(1, 1, 1, errorInfo);
			myHandler.sendMessage(m);
		}
		
		class MyHandler extends Handler {
			
			public MyHandler(Looper l) {
				super(l);
			}
			
			@Override
			public void handleMessage(Message msg) {
				System.out.println("get msg!");
				String result = (String)msg.obj;
				System.out.println(result);
				if(result.equals("success")) {
					showToast("Open success");
					closeButton.setVisibility(View.VISIBLE);
					finish();
				} else {
					Toast.makeText(SaleActivity.this, result, Toast.LENGTH_SHORT).show();
				}
			}
		}
    	
    }
	
    class MyCloseThread extends Thread {
    	
    	Listener listener;
    	
    	public void setListener(Listener listener) {
    		this.listener = listener;
    	}
    	public void run() {
			HttpRequest httpRequest = new HttpRequest();
			String result = httpRequest.sendPost("closeSell.action", "userId=" + userId);
			System.out.println("result:" + result);
			try {
				JSONObject jsonObject = new JSONObject(result);
				int status = jsonObject.getInt("status");
				if(status > 0)
					listener.onFailure(jsonObject.getString("result"));
				else
					listener.onSuccess();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    
    class MySetThread extends Thread {

    	Listener listener;
    	final String THREAD = "MySetThread";
    	
    	public void setListener(Listener listener) {
    		this.listener = listener;
    	}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			setSellInfo();
			
		}
		
		private void setSellInfo() {
			Log.i(THREAD, "startSet");
			HttpRequest httpRequest = new HttpRequest();
			String result = httpRequest.sendPost("setSellInfo.action", "latitude=" + lat + "&longtitude=" + lng + "&userId=" + userId +"&address=" + address
					+ "&phoneNumber=" + phoneNumber + "&sellDate=" + startDate + "&endDate=" + endDate +"&commodityInfo=" +details);
			System.out.println("result:" + result);
			try {
				JSONObject jsonObject = new JSONObject(result);
				int status = jsonObject.getInt("status");
				if(status > 0)
					listener.onFailure(jsonObject.getString("result"));
				else 
					openSale();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void openSale() {
			Log.i(THREAD, "startOpen");
			HttpRequest httpRequest = new HttpRequest();
			String result = httpRequest.sendGet("openSell.action", "userId=" + userId);
			try {
				JSONObject jsonObject = new JSONObject(result);
				int status = jsonObject.getInt("status");
				if(status > 0)
					listener.onFailure(jsonObject.getString("result"));
				else 
					listener.onSuccess();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    }

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		etAddress.setText(SetAddressActivity.address);
	}
    
    
    
}
