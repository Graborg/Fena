package com.example.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateMyProfile extends Activity {
	private int profile_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile);
		
		final EditText name = (EditText) findViewById(R.id.edName);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText expectation = (EditText) findViewById(R.id.edExpectation);
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText skills = (EditText) findViewById(R.id.edSkills);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText description = (EditText) findViewById(R.id.edDescription);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		
		Button save = (Button) findViewById(R.id.bSave);
		save.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		Button cancel = (Button) findViewById(R.id.bCancel);
		cancel.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String jsonName = name.getText().toString();
				String jsonExpectation = expectation.getText().toString();
				String jsonSkills = skills.getText().toString();
				String jsonDesciption = description.getText().toString();
				String jsonShowProfile = "1";
				String jsonImage = "0";
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("name", jsonName);
					jsonObj.put("expectations", jsonExpectation);
					jsonObj.put("skills", jsonSkills);
					jsonObj.put("description", jsonDesciption);
					jsonObj.put("show_profile", jsonShowProfile);
					jsonObj.put("image", jsonImage);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(jsonObj.toString());
				String url = "http://54.191.168.116:3001/persons";
				JsonPost callbackservice3 = new JsonPost(CreateMyProfile.this, jsonObj.toString(), LogIn.account.getToken()) {
					//behövs ???
					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
						profile_id = (Integer) object;
					}
				};
				callbackservice3.execute(url, null, null);
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
