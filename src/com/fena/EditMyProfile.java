package com.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import android.app.Activity;
import android.graphics.Typeface;

public class EditMyProfile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		final Person person = LogIn.account.getMyProfile();
		Button save = (Button) findViewById(R.id.bSave);
		save.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		Button cancel = (Button) findViewById(R.id.bCancel);
		cancel.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText name = (EditText) findViewById(R.id.edName);
		name.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText skills = (EditText) findViewById(R.id.edSkills1);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText description = (EditText) findViewById(R.id.edDescription1);
		description.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText expectation = (EditText) findViewById(R.id.edExpectation);
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		name.setText(person.getName());
		final CheckBox showprofile = (CheckBox) findViewById(R.id.cb_showprofile);
		showprofile.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		if (person.getShowProfile() == 1) {
			showprofile.setChecked(true);
		} else {
			showprofile.setChecked(false);
		}
		ImageView pic = (ImageView) findViewById(R.id.imageView1);
		switch(person.getImage()){
		case 0: pic.setImageResource(R.drawable.prof_blue);
		break;
		case 1: pic.setImageResource(R.drawable.prof_red);
		break;
		case 2: pic.setImageResource(R.drawable.prof_orange);
		break;
		case 3: pic.setImageResource(R.drawable.prof_green);
		break;
		default: pic.setImageResource(R.drawable.prof_blue);
		break;
		}

		skills.setText(person.getSkills());
		expectation.setText(person.getExpectations());
		description.setText(person.getDescription());

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String jsonName = name.getText().toString();
				String jsonExpectation = expectation.getText().toString();
				String jsonSkills = skills.getText().toString();
				String jsonDesciption = description.getText().toString();
				Integer jsonShowProfile;
				if (showprofile.isChecked()) {
					jsonShowProfile = 1;
				} else {
					jsonShowProfile = 0;
				}

				Integer jsonImage = person.getImage();
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
				String url3 = "https://connectionboard.se/persons/"
						+ LogIn.account.getMyProfile().getId();
				JsonPut callbackservice3 = new JsonPut(EditMyProfile.this,
						jsonObj.toString(), LogIn.account.getToken()) {
					@Override
					public void receiveData(Object object) {
						//int profile_id = (Integer) object;
					}
				};
				callbackservice3.execute(url3, null, null);

			}

		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
