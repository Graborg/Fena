package com.fena;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends Activity{
	private Database db;
	static TextView name;
	static TextView skills;
	static TextView description;
	static TextView expectation;
	static ImageView pic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprofile);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		db = new Database();
		name = (TextView) findViewById(R.id.tvName);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		skills = (TextView) findViewById(R.id.tvSkills1);
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		expectation = (TextView) findViewById(R.id.tvExpectation);
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description = (TextView) findViewById(R.id.tvDescription);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		pic = (ImageView) findViewById(R.id.imageView1);
		Person person = LogIn.account.getMyProfile();
		name.setText(person.getName());
		expectation.setText(Html.fromHtml("<b>" + "Expectation: " + "</b> <br /> " + person.getExpectations()));
		skills.setText(Html.fromHtml("<b>" + "Skills: " + "</b> <br /> " + person.getSkills()));
		description.setText(Html.fromHtml("<b>" + "Description: " + "</b> <br /> " + person.getDescription()));
		switch(person.getImage()){
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
	}
	

	@Override
	protected void onRestart() {
		super.onRestart();
		db.update(this);
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
