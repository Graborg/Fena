package com.example.fena;

import android.app.Activity;
import android.content.Intent;
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
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
		System.out.println(position);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView title = (TextView) findViewById(R.id.tvTitel);
		TextView subtitle = (TextView) findViewById(R.id.tvSubtitel);
		TextView reqskills = (TextView) findViewById(R.id.tvReqskills);
		TextView description = (TextView) findViewById(R.id.tvDescription_proj);
		TextView gain = (TextView) findViewById(R.id.tvGains);
		TextView time = (TextView) findViewById(R.id.tvTime);
		TextView mail = (TextView) findViewById(R.id.tvMail_proj);
		
		
		title.setText(LogIn.projects.get(position).getTitle());
		subtitle.setText(LogIn.projects.get(position).getSubheading());
		reqskills.setText(LogIn.projects.get(position).getRequested_skills());
		description.setText(LogIn.projects.get(position).getDescription());
		gain.setText(LogIn.projects.get(position).getGains());
		time.setText(LogIn.projects.get(position).getTimePlan());
		mail.setText(LogIn.projects.get(position).getMail());
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.profile, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	        	Intent openMainPoint = new Intent("android.intent.action.REDPROJECT");
				startActivity(openMainPoint);
				finish();
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
