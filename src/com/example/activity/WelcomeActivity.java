package com.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.dianping.R;
import com.example.utils.ShareUtils;

public class WelcomeActivity extends Activity {
	
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		runActivity(this);
		
	}

	private static void runActivity(final Context context) {
		final Intent intent = new Intent(context, WelcomeGuiAct.class);
		final Intent intent2 = new Intent(context, MainActivity.class);
		new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				if(ShareUtils.getSharePreferences(context)){
					context.startActivity(intent2);
					
				}else {
					context.startActivity(intent);
					ShareUtils.setSharePreferences(context, true);
				}
				return false;
			}
		}).sendEmptyMessageDelayed(0, 3000);
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause");
		finish();
	}
	
}
