package com.fena;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {
	
	static SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		sharedpreferences = getSharedPreferences("Account", Context.MODE_PRIVATE);
		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		LogIn.persons = null;
		LogIn.projects = null;
		String token = sharedpreferences.getString("token", null);
		//System.out.println(token);
		String accountId = Integer.toString(sharedpreferences.getInt("account_id", -1));
		//System.out.println(accountId);
		if(token != null){
			LogIn.account = new Account(token, accountId, true);
		}

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if(LogIn.account != null){
						Intent openMainPoint = new Intent("android.intent.action.MAINFENALOGIN");
						startActivity(openMainPoint);
						finish();
					}
					else{
						Intent openMainPoint = new Intent(
								"android.intent.action.MAINFENA");
						startActivity(openMainPoint);
						finish();
					}
				}
			}
		};
		timer.start();

	}
}
