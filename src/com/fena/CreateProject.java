package com.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProject extends Activity{
	private int project_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_project);
		
		Button save = (Button) findViewById(R.id.bSave);
		save.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		Button cancel = (Button) findViewById(R.id.bCancel);
		cancel.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText title = (EditText) findViewById(R.id.edTitel);
		title.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText subtitle = (EditText) findViewById(R.id.edSubtitel);
		subtitle.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText description = (EditText) findViewById(R.id.edDescription_proj);
		description.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText skills = (EditText) findViewById(R.id.edReqskills);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText time = (EditText) findViewById(R.id.edTime);
		time.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText gains = (EditText) findViewById(R.id.edGains);
		gains.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));
		final EditText mail = (EditText) findViewById(R.id.edMail_proj);
		mail.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf"));

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String jsonTitle = title.getText().toString();
				String jsonSubtitle = subtitle.getText().toString();
				String jsonSkills = skills.getText().toString();
				String jsonGains = gains.getText().toString();
				String jsonDesciption = description.getText().toString();
				String jsonTime = time.getText().toString();
				Integer jsonShowProfile = 1;
				Integer jsonImage = 0;
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("title", jsonTitle);
					jsonObj.put("subheading", jsonSubtitle);
					jsonObj.put("requested_skills", jsonSkills);
					jsonObj.put("gains", jsonGains);
					jsonObj.put("description", jsonDesciption);
					jsonObj.put("time_plan", jsonTime);
					jsonObj.put("show_project", jsonShowProfile);
					jsonObj.put("image", jsonImage);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(jsonObj.toString());
				String url = "http://54.191.168.116:3001/projects/";
				JsonPost callbackservice3 = new JsonPost(CreateProject.this,
						jsonObj.toString(), LogIn.account.getToken()) {
					// behövs??
					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
						int project_id = (Integer) object;
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
