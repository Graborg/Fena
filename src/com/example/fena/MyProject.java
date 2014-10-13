package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProject extends Activity{
	public final static String EXTRA_MESSAGE = "com.example.fena.MESSAGE";
	private Database db;
	static int projectId;
	private Project project;
	static TextView title;
	static TextView subtitle;
	static TextView reqskills;
	static TextView description;
	static TextView gain;
	static ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myproject);
		Intent intent = getIntent();
		db = new Database();
		projectId = intent.getIntExtra(MyProjects.EXTRA_MESSAGE, -1);
		
		for(Project pro: LogIn.projects){
			if(pro.getId() == projectId){
				project = pro;
			}
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		title = (TextView) findViewById(R.id.tvTitel);
		subtitle = (TextView) findViewById(R.id.tvSubtitel);
		reqskills = (TextView) findViewById(R.id.tvReqskills);
		description = (TextView) findViewById(R.id.tvDescription_proj);
		gain = (TextView) findViewById(R.id.tvGains);
		image = (ImageView) findViewById(R.id.imageView1);
		
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		subtitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		reqskills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		gain.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));

		title.setText(project.getTitle());
		subtitle.setText(project.getSubheading());
		reqskills.setText(project.getRequested_skills());
		description.setText(project.getDescription());
		gain.setText(project.getGains());
		
		switch(project.getImage()){
		case 0: image.setImageResource(R.drawable.pic0);
		break;
		case 1: image.setImageResource(R.drawable.pic1);
		break;
		case 2: image.setImageResource(R.drawable.pic2);
		break;
		case 3: image.setImageResource(R.drawable.pic3);
		break;
		default: image.setImageResource(R.drawable.pic0);
		break;
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.myprofile, menu);
		return true;
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		db.update(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	        	Intent openMainPoint = new Intent("android.intent.action.EDITPROJECT");
	        	openMainPoint.putExtra(EXTRA_MESSAGE, projectId);
				startActivity(openMainPoint);
	            return true;
	        case R.id.action_settings:
	            return true;
		    case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
