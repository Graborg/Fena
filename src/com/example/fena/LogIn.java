package com.example.fena;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class LogIn extends Activity {
	
	static ArrayList<Person> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		String url = "http://31.208.72.233:3000/persons/";
		JsonPersonreceiver callbackservice = new JsonPersonreceiver(
				LogIn.this) {
			@SuppressWarnings("unchecked")
			@Override
			public void receiveData(Object object) {
				persons = (ArrayList<Person>) object;
			}
		};

		callbackservice.execute(url, null, null);
		
		TextView tvLogIn = (TextView) findViewById(R.id.tvlogin);
		tvLogIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openMainPoint = new Intent(
						"android.intent.action.MAINFENA");
				startActivity(openMainPoint);
				finish();
				
			}
		});

	}
}
