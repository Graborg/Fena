package com.fena;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateMyProfile extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile);
		
		final EditText name = (EditText) findViewById(R.id.edName);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText description = (EditText) findViewById(R.id.edDescription1);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText skills = (EditText) findViewById(R.id.edSkills1);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final EditText expectation = (EditText) findViewById(R.id.edExpectation);
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		
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
				Integer jsonShowProfile = 1;
				Random rand = new Random();
				Integer jsonImage = rand.nextInt(4);
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
				String url = "https://connectionboard.se/persons";
				JsonPost callbackservice3 = new JsonPost(CreateMyProfile.this, jsonObj.toString(), LogIn.account.getToken()) {
					@Override
					public void receiveData(Object object) {
						//int profile_id = (Integer) object;
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
