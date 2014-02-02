package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Profile extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
				
		Intent intent = getIntent();
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 5);
		System.out.println(position);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView name = (TextView) findViewById(R.id.tvName);
		TextView email = (TextView) findViewById(R.id.tvEmail);
		TextView skills = (TextView) findViewById(R.id.tvskills);
		TextView description = (TextView) findViewById(R.id.tvDescription);
		TextView expectation = (TextView) findViewById(R.id.tvExpectation);
		
		name.setText(LogIn.persons.get(position).getName());
		email.setText(LogIn.persons.get(position).getMail());
		expectation.setText(LogIn.persons.get(position).getExpectations());
		skills.setText(LogIn.persons.get(position).getSkills());
		description.setText(LogIn.persons.get(position).getDescription());
		

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	        	Intent openMainPoint = new Intent("android.intent.action.REDPROFILE");
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
