package com.example.fena;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	static ArrayList<Person> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		String url = "http://31.208.72.233:3000/persons/";
		JsonPersonreceiver callbackservice = new JsonPersonreceiver(Splash.this) {
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				persons = (ArrayList<Person>) object;
				System.out.println(persons.get(1).getName());
				// MainActivity.this.showRecordsFromJson(persons);
			}
		};

		callbackservice.execute(url, null, null);
		
		Thread timer = new Thread() {
            public void run() {
                    try {
                            sleep(20000);
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
