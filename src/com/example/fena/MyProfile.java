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
		ImageView pic = (ImageView) findViewById(R.id.imageView1);
		Person person = LogIn.account.getMyProfile();
		
		name.setText(person.getName());
		expectation.setText(person.getExpectations());
		skills.setText(person.getSkills());
		description.setText(person.getDescription());
		switch(person.getImage()){
		case 0: pic.setImageResource(R.drawable.pic0);
		break;
		case 1: pic.setImageResource(R.drawable.pic1);
		break;
		case 2: pic.setImageResource(R.drawable.pic2);
		break;
		case 3: pic.setImageResource(R.drawable.pic3);
		break;
		default: pic.setImageResource(R.drawable.pic0);
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
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_edit:
	        	Intent openMainPoint = new Intent("android.intent.action.REDPROFILE");
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
