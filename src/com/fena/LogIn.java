package com.fena;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends Activity {

	static ArrayList<Person> persons;
	static ArrayList<Project> projects;
	static ArrayList<Project> showProjects;
	static Account account;
	public static ArrayList<Person> showPersons;
	
	private boolean keepsignin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
		Button breset = (Button) findViewById(R.id.breset);
		breset.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final CheckBox cbkeepsignin = (CheckBox) findViewById(R.id.cbkeepsignin);
		cbkeepsignin.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText edmail = (EditText) findViewById(R.id.edmail);
		edmail.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText edpassword = (EditText) findViewById(R.id.edpassword);
		edpassword.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		//If the keyboard should appear when edittext is in focus.
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	

		bsignup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent openMainPoint = new Intent("android.intent.action.SIGNUP");
				startActivity(openMainPoint);
				finish();
			}

		});

		blogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String mail = edmail.getText().toString();
				String password = edpassword.getText().toString();
				keepsignin = cbkeepsignin.isChecked();
				if(!validEmail(mail)){
					Toast.makeText(getApplicationContext(), "Invalid Email, Please try again", Toast.LENGTH_LONG).show();
				}
				else{
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("password", password);
					jsonObj.put("username", mail);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String url3 = "https://connectionboard.se/accounts/login";

				JsonLogInPost callbackservice3 = new JsonLogInPost(LogIn.this,
						jsonObj.toString(), keepsignin) {

					@Override
					public void receiveData(Object object) {
						account = (Account) object;
					}
				};
				callbackservice3.execute(url3, null, null);
				}
			}
		});
		
		breset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String mail = edmail.getText().toString();
				if(!validEmail(mail)){
					Toast.makeText(getApplicationContext(), "Invalid Email, Please try again", Toast.LENGTH_LONG).show();
				}
				else{
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("username", mail);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String url = "https://connectionboard.se/password_resets";

				JsonResetPost callbackservice = new JsonResetPost(LogIn.this,
						jsonObj.toString()) {
					@Override
					public void receiveData(Object object) {
					}
				};
				callbackservice.execute(url, null, null);
				}
				
			}
		});

	}

	private boolean validEmail(String email) {
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		return pattern.matcher(email).matches();
	}
	
	public static final Pattern EMAIL_ADDRESS = Pattern
			.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
}
