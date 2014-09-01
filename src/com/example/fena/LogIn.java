package com.example.fena;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends Activity {

	static ArrayList<Person> persons;
	static ArrayList<Project> projects;
	static Account account;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		TextView tvConnection = (TextView) findViewById(R.id.tvConnection);
		tvConnection.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Averia Sans Libre Regular.ttf"));
		TextView tvBoard = (TextView) findViewById(R.id.tvBoard);
		tvBoard.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Averia Sans Libre Regular.ttf"));
		Button bsignup = (Button) findViewById(R.id.bsign_up);
		bsignup.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		Button blogin = (Button) findViewById(R.id.blogin);
		blogin.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText eduserName = (EditText) findViewById(R.id.eduser_name);
		eduserName.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText edpassword = (EditText) findViewById(R.id.edpassword);
		edpassword.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
	
	
		
		bsignup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String userName = eduserName.getText().toString();
				String password = edpassword.getText().toString();
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("password", password);
					jsonObj.put("username", userName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(jsonObj.toString());
				String url3 = "http://54.191.168.116:3001/accounts";

				JsonLogInPost callbackservice3 = new JsonLogInPost(LogIn.this,
						jsonObj.toString()) {
					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
						account = (Account) object;

					}
				};
				callbackservice3.execute(url3, null, null);
			}

		});

		blogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = eduserName.getText().toString();
				String password = edpassword.getText().toString();

				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("password", password);
					jsonObj.put("username", userName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(jsonObj.toString());
				String url3 = "http://54.191.168.116:3001/accounts/login";

				JsonLogInPost callbackservice3 = new JsonLogInPost(LogIn.this,
						jsonObj.toString()) {

					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
						account = (Account) object;
					}
				};
				callbackservice3.execute(url3, null, null);
			}
		});

	}
}
