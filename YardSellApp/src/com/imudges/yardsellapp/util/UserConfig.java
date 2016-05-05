package com.imudges.yardsellapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserConfig {

	private static SharedPreferences editor;
	
	public static void setSaleInfo(Context context) {
		editor = context.getSharedPreferences("userConfig", context.MODE_PRIVATE);
		
	}
}
