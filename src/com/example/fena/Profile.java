package com.example.fena;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
		int position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView name = (TextView) findViewById(R.id.tvName);
		TextView skills = (TextView) findViewById(R.id.tvskills);
		TextView description = (TextView) findViewById(R.id.tvDescription);
		TextView expectation = (TextView) findViewById(R.id.tvExpectation);
		
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		name.setText(LogIn.persons.get(position).getName());
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		expectation.setText(LogIn.persons.get(position).getExpectations());
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		skills.setText(LogIn.persons.get(position).getSkills());
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setText(LogIn.persons.get(position).getDescription());
		

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
