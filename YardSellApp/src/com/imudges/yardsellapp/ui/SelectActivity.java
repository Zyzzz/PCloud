package com.imudges.yardsellapp.ui;



import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.imudges.yardsellapp.R;
import com.imudges.yardsellapp.util.FormFile;
import com.imudges.yardsellapp.util.Information;
import com.imudges.yardsellapp.util.SocketHttpRequester;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SelectActivity extends Activity implements OnClickListener {
	private LinearLayout dialogLayout;
	private Button takePhotoBtn, pickPhotoBtn, cancelBtn;
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	/** 获取到的图片路径 */
	
	private Intent lastIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
		dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
		dialogLayout.setOnClickListener(this);
		takePhotoBtn = (Button) findViewById(R.id.btn_tack_pic);
		takePhotoBtn.setOnClickListener(this);
		pickPhotoBtn = (Button) findViewById(R.id.btn_local_pic);
		pickPhotoBtn.setOnClickListener(this);
		cancelBtn = (Button) findViewById(R.id.btn_cancle_pic);
		cancelBtn.setOnClickListener(this);
		lastIntent = getIntent();
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.dialog_layout:
			break;
		case R.id.btn_tack_pic:
			takePicture();
			break;
		case R.id.btn_local_pic:
			openAlbum();
			break;
		default:
			finish();
			break;
		}
	}
	private static String picFileFullName;
	
    public void takePicture(){
    	String state = Environment.getExternalStorageState();  
        if (state.equals(Environment.MEDIA_MOUNTED)) {  
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);  
            if (!outDir.exists()) {  
            	outDir.mkdirs();  
            }  
            File outFile =  new File(outDir, System.currentTimeMillis() + ".jpg");  
            picFileFullName = outFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));  
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);  
        } 
    }
    private File file;
    Handler handler;
    public void openAlbum(){
    	Intent intent = new Intent();
    	intent.setType("image/*");   
        intent.setAction(Intent.ACTION_GET_CONTENT);   
    	this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
    }
 	@Override
 	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 		super.onActivityResult(requestCode, resultCode, data);
 		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (resultCode == RESULT_OK) {
 				toast("照相"+picFileFullName);
 				file = new File(picFileFullName);
 				Thread thread=new Thread(runnable);
 				thread.start();
 				finish();
 				//uploadUtil.uploadFile(file,"http://10.0.1.115:8080/Upload/fileUpload.action");
 			}
 			else if (resultCode == RESULT_CANCELED) {
 				
 			} 
 			
 		} else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
 			if (resultCode == RESULT_OK) {
 				Uri uri = data.getData();
 				if(uri != null){
 					String realPath = getRealPathFromURI(uri);
 					toast("手动="+realPath);
 					file = new File(realPath);
 	 				Thread thread=new Thread(runnable);
 	 				thread.start();
 	 				finish();
 				}
 			}
 		}
 	}

	public String getRealPathFromURI(Uri contentUri){
        try{
            String[] proj = {MediaStore.Images.Media.DATA};
            // Do not call Cursor.close() on a cursor obtained using this method, 
            // because the activity will do that for you at the appropriate time
            Cursor cursor = this.managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }catch (Exception e){
            return contentUri.getPath();
        }
	}
	public String newName = "image.jpg";
	public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	private String getUserDataFromPhoen(String param){
		SharedPreferences sharedPreferences=getSharedPreferences("userConfig", MODE_PRIVATE);
		return sharedPreferences.getString(param, "");
	}
	public void uploadFile(File imageFile) {
       
        try {
        	 String requestUrl = new Information().getConfigInfo("host")+":"+new Information().getConfigInfo("port")+"/"+new Information().getConfigInfo("appName")+"/upload/execute.do";
            //请求普通信息
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", getUserDataFromPhoen("email"));
            params.put("hashCode", getUserDataFromPhoen("hashCode"));
            params.put("fileName", imageFile.getName());
            //上传文件
            FormFile formfile = new FormFile(imageFile.getName(), imageFile, "image", "application/octet-stream");
            SocketHttpRequester.post(requestUrl, params, formfile);
           // Log.i(TAG, "upload success");
        } catch (Exception e) {
            //Log.i(TAG, "upload error");
            e.printStackTrace();
        }
        //Log.i(TAG, "upload end");
	}
	private Runnable runnable=new Runnable() {   
        public void run() {
            //Log.i(TAG, "runnable run");
            uploadFile(file);
           // toast("上传成功");
        }
	};
}

