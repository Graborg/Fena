package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ProjectView extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project);
				
		Intent intent = getIntent();
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
		System.out.println(position);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView title = (TextView) findViewById(R.id.tvTitel);
		TextView subtitle = (TextView) findViewById(R.id.tvSubtitel);
		TextView reqskills = (TextView) findViewById(R.id.tvReqskills);
		TextView description = (TextView) findViewById(R.id.tvDescription_proj);
		TextView gain = (TextView) findViewById(R.id.tvGains);
		
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		title.setText(LogIn.projects.get(position).getTitle());
		subtitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		subtitle.setText(LogIn.projects.get(position).getSubheading());
		reqskills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		reqskills.setText(LogIn.projects.get(position).getRequested_skills());
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setText(LogIn.projects.get(position).getDescription());
		gain.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		gain.setText("Gains\n" + LogIn.projects.get(position).getGains() +"Time\n" + LogIn.projects.get(position).getTimePlan() + "Wanted\n");
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
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
