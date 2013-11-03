package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Profile extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		final TextView name = (TextView) findViewById(R.id.tvName);
		final TextView email = (TextView) findViewById(R.id.tvEmail);
		final TextView occupation = (TextView) findViewById(R.id.tvOccupation);
		final TextView description1 = (TextView) findViewById(R.id.tvDescription1);
		final TextView description2 = (TextView) findViewById(R.id.tvDescription2);
		final TextView description3 = (TextView) findViewById(R.id.tvDescription3);
		final TextView experience = (TextView) findViewById(R.id.tvExperience1);
		
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	

}
