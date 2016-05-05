package com.imudges.yardsellapp.ui;



import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.util.Information;

import android.R.menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainPageActivity extends Activity{

	private LinearLayout ll_sell;
	private LinearLayout ll_buy;
	private LinearLayout ll_like;
	private LinearLayout ll_me;
	private AlertDialog.Builder builder;
	private Dialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		init();
	}
	

	void init(){
		ll_sell=(LinearLayout)findViewById(R.id.ll_sale);
		ll_buy=(LinearLayout)findViewById(R.id.ll_buy);
		ll_like=(LinearLayout)findViewById(R.id.ll_like);
		ll_me=(LinearLayout)findViewById(R.id.ll_me);
		ll_sell.setOnClickListener(new SellOnClickListener());
		ll_buy.setOnClickListener(new BuyOnClickListener());
		ll_like.setOnClickListener(new LikeOnClickListener());
		ll_me.setOnClickListener(new MeOnclickListener());
		update();
	}
	
	/**
	 * Retrieves application's version number from the manifest
	 * 
	 * @return
	 */
	public String getVersion(){
		String version = "0.0.0";
		
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return version;
	}
	
	private String getNetVersion(){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString("version", "hupeng@imudges");
	}
	
	private void setNetVersion(String value){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putString("version", value);
		editor.commit();
	}
	
	private void update(){
		if (getNetVersion().equals("hupeng@imudges")) {
			return;
		}
		if (getNetVersion().equals(getVersion())) {
			return;
		}
		setNetVersion(getVersion());
		builder=new AlertDialog.Builder(MainPageActivity.this);
		builder.setTitle("A new version!");
		
		View view=LayoutInflater.from(MainPageActivity.this).inflate(R.layout.confirm_update, null);
		final Button btn_ok = (Button) view.findViewById(R.id.btn_confirm_update);
		final Button btn_cance = (Button) view.findViewById(R.id.btn_cancel_update);
		btn_ok.setOnClickListener(new UpdateListener());
		btn_cance.setOnClickListener(new UpdateListener());
		builder.setView(view);
		dialog=builder.show();
	}
	
	class UpdateListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.btn_confirm_update:
				final Uri uri = Uri.parse(new Information().getConfigInfo("host")+":"+new Information().getConfigInfo("port")+"/"+new Information().getConfigInfo("appName")+"/YardSellApp.apk");          
				final Intent it = new Intent(Intent.ACTION_VIEW, uri);          
				startActivity(it);
				break;
			case R.id.btn_cancel_update:
				dialog.dismiss();
				break;
			default:
				break;
			}
		}
		
	}
	
	class SellOnClickListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent(SaleActivity.class);
		}
	}

	class BuyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent(BuyActivity.class);
			toast("buy");
		}
	}
	
	class LikeOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent(LikeActivity.class);
		}
	}
	
	class MeOnclickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent(MeActivity.class);
			finish();
		}
		
	}
	private void toast(String text){
		Toast.makeText(MainPageActivity.this, text, Toast.LENGTH_SHORT).show();;
	}
	
	private void intent(Class<?> cla){
		Intent intent=new Intent();
		intent.setClass(MainPageActivity.this, cla);
		startActivity(intent);
	}
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "enter exit again", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
