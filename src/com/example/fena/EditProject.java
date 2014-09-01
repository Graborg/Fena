package com.example.fena;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProject extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_project);
		//getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		int position = intent.getIntExtra(MyProjects.EXTRA_MESSAGE, 0);

		final Project project = LogIn.account.getMyProjects().get(position);

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

		title.setText(project.getTitle());
		subtitle.setText(project.getSubheading());
		description.setText(project.getDescription());
		skills.setText(project.getRequested_skills());
		time.setText(project.getTimePlan());
		gains.setText(project.getGains());

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
				System.out.println("EditProject, ID:" + project.getId());
				String url = "http://54.191.168.116:3001/projects/"
						+ project.getId();
				JsonPut callbackservice3 = new JsonPut(EditProject.this,
						jsonObj.toString(), LogIn.account.getToken()) {
					// behövs??
					@SuppressWarnings("unchecked")
					@Override
					public void receiveData(Object object) {
						int profile_id = (Integer) object;
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
