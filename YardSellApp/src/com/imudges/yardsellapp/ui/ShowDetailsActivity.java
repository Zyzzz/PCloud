package com.imudges.yardsellapp.ui;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import android.app.Activity;
import android.os.Bundle;

import com.imudges.yardsellapp.R;

public class ShowDetailsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_page);
		
	}
	
}
