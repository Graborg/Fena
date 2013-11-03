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
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// open the mainactivity, with the intent name
					Intent openMainPoint = new Intent("android.intent.action.MAINFENA");
					startActivity(openMainPoint);
					finish();
					// when splash is done it will call on this method which
					// will make the splash
					// to call on the onPause method which with finish() will
					// kill itself
				}
			}
		};
		timer.start();
	}

}
