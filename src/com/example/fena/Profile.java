package com.example.fena;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Activity{
	private int checkedItem = -1;
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
		Button contact = (Button) findViewById(R.id.bContact);
		
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		name.setText(LogIn.persons.get(position).getName());
		expectation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		expectation.setText(LogIn.persons.get(position).getExpectations());
		skills.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		skills.setText(LogIn.persons.get(position).getSkills());
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));
		description.setText(LogIn.persons.get(position).getDescription());
		
		contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(LogIn.account == null){
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Profile.this);
					builder.setMessage(
							"You are not signed in and can therefore not contact anyone")
							.setPositiveButton("Sign in",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											Intent i = new Intent(Profile.this, LogIn.class);
											// set the new task and clear flags
											i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
											startActivity(i);
											finish();
											
//											Intent openMainPoint = new Intent(
//													"android.intent.action.LOGIN");
//											openMainPoint.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//											startActivity(openMainPoint);
//											finish();
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
										}
									}).show();
				}
				else{
					final ArrayList<String> items = new ArrayList<String>();
			        items.add(LogIn.account.getMyProfile().getName());
			        for(Project p:LogIn.account.getMyProjects()){
			        	items.add(p.getTitle());
			        }
			        CharSequence[] cs = items.toArray(new CharSequence[items.size()]);
		            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
		            builder.setTitle("Select what you want to send");
		            builder.setSingleChoiceItems(cs, checkedItem, new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int indexSelected) {
							checkedItem = indexSelected;
						}
					})

		         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		             @Override
		             public void onClick(DialogInterface dialog, int id) {
		            	 System.out.println("Mail_Profile" + checkedItem);
		             }
		         })
		         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		             @Override
		             public void onClick(DialogInterface dialog, int id) {
		              
		             }
		         });
		            AlertDialog dialog = builder.create();
		            dialog.show();
				}

			}
		});

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
