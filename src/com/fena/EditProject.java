package com.fena;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProject extends Activity {
	private Project project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_project);
		//getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		int projectId = intent.getIntExtra(MyProjects.EXTRA_MESSAGE, -1);
		
		for(Project pro: LogIn.projects){
			if(pro.getId() == projectId){
				project = pro;
			}
		}

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
		final CheckBox showprofile = (CheckBox) findViewById(R.id.cb_showproject);
		showprofile.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		final ImageView pic = (ImageView) findViewById(R.id.imageView1);
		
		
		title.setText(project.getTitle());
		subtitle.setText(project.getSubheading());
		description.setText(project.getDescription());
		skills.setText(project.getRequested_skills());
		time.setText(project.getTimePlan());
		gains.setText(project.getGains());
		if(project.getShowProject() == 1){
			showprofile.setChecked(true);
		}else{
			showprofile.setChecked(false);
		}
		switch(project.getImage()){
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

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String jsonTitle = title.getText().toString();
				String jsonSubtitle = subtitle.getText().toString();
				String jsonSkills = skills.getText().toString();
				String jsonGains = gains.getText().toString();
				String jsonDesciption = description.getText().toString();
				String jsonTime = time.getText().toString();
				Integer jsonShowProject;
				if(showprofile.isChecked()){
					jsonShowProject = 1;
				}else{
					jsonShowProject = 0;
				}
				Random rand = new Random();
				Integer jsonImage = rand.nextInt(4);
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("title", jsonTitle);
					jsonObj.put("subheading", jsonSubtitle);
					jsonObj.put("requested_skills", jsonSkills);
					jsonObj.put("gains", jsonGains);
					jsonObj.put("description", jsonDesciption);
					jsonObj.put("time_plan", jsonTime);
					jsonObj.put("show_project", jsonShowProject);
					jsonObj.put("image", jsonImage);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String url = "https://connectionboard.se/projects/"
						+ project.getId();
				System.out.println("Project id" + project.getId());
				JsonPut callbackservice3 = new JsonPut(EditProject.this,
						jsonObj.toString(), LogIn.account.getToken()) {
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
