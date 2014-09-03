package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MyProfile extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprofile);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView name = (TextView) findViewById(R.id.tvName);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		TextView skills = (TextView) findViewById(R.id.tvskills);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		TextView description = (TextView) findViewById(R.id.tvDescription);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		TextView expectation = (TextView) findViewById(R.id.tvExpectation);
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		
		Person person = LogIn.account.getMyProfile();
		
		name.setText(person.getName());
		expectation.setText(person.getExpectations());
		skills.setText(person.getSkills());
		description.setText(person.getDescription());
		

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
