package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		LogIn.persons = null;
		LogIn.projects = null;

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
